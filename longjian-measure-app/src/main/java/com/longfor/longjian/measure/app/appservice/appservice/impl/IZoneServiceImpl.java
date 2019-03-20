package com.longfor.longjian.measure.app.appservice.appservice.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.longfor.longjian.common.base.LjBaseResponse;
import com.longfor.longjian.common.exception.CommonException;
import com.longfor.longjian.common.util.DateUtil;
import com.longfor.longjian.measure.app.appservice.appservice.IZoneService;
import com.longfor.longjian.measure.app.feignclient.ICoreAreaFeignService;
import com.longfor.longjian.measure.app.feignclient.ICoreCategoryItemFeignService;
import com.longfor.longjian.measure.app.req.feignreq.CategoriesInfoReq;
import com.longfor.longjian.measure.app.req.feignreq.SearchByIdListReq;
import com.longfor.longjian.measure.app.req.zone.*;
import com.longfor.longjian.measure.app.vo.appmeasuresyncvo.MeasureRuleVo;
import com.longfor.longjian.measure.app.vo.appmeasuresyncvo.ResultListVo;
import com.longfor.longjian.measure.app.vo.appmeasuresyncvo.ZoneInfoVo;
import com.longfor.longjian.measure.app.vo.feignvo.AreaRetrieveVo;
import com.longfor.longjian.measure.app.vo.feignvo.CategoryJsonProtoItemVo;
import com.longfor.longjian.measure.app.vo.feignvo.CategoryJsonProtoVo;
import com.longfor.longjian.measure.app.vo.feignvo.ProjAreaSearchByIdListVo;
import com.longfor.longjian.measure.domain.externalservice.*;
import com.longfor.longjian.measure.po.zhijian2.MeasureRegion;
import com.longfor.longjian.measure.po.zhijian2.MeasureRule;
import com.longfor.longjian.measure.po.zhijian2.MeasureZone;
import com.longfor.longjian.measure.po.zhijian2.MeasureZoneResult;
import com.longfor.longjian.measure.util.ArrayUtil;
import com.longfor.longjian.measure.util.ConvertUtil;
import com.longfor.longjian.measure.util.ExampleUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by Wang on 2019/1/8.
 */
@Slf4j
@Service
public class IZoneServiceImpl implements IZoneService {

    @Resource
    private IMeasureZoneResultService measureZoneResultService;

    @Resource
    private IMeasureZoneService measureZoneService;

    @Resource
    private ICoreCategoryItemFeignService coreCategoryItemFeignService;

    @Resource
    private ICoreAreaFeignService coreAreaFeignService;

    @Resource
    private IMeasureRegionService measureRegionService;

    @Resource
    private IMeasureRegionRelService measureRegionRelService;

    @Resource
    private IMeasureRuleService measureRuleService;
    private static final String PROJECTID = "projectId";
    private static final String PROJECT_ID = "project_id";
    private static final String ZONEID = "zoneId";
    private static final String UPDATE_AT = "update_at";
    private static final String DELETE_AT = "delete_at";

    @Override
    public JSONObject getResult(GetResultReq getResultReq) {

        JSONObject totalJb = new JSONObject();

        Example example = new Example(MeasureZoneResult.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo(PROJECTID, getResultReq.getProject_id());
        criteria.andEqualTo("zoneUuid", getResultReq.getZone_uuid());
        ExampleUtil.addDeleteAtJudge(example);

        List<MeasureZoneResult> measureZoneResults = measureZoneResultService.selectByExample(example);

        JSONArray arr = new JSONArray();

        for (int i = 0; i < measureZoneResults.size(); i++) {

            JSONObject oneJb;

            MeasureZoneResult measureZoneResult = measureZoneResults.get(i);
            ResultListVo resultListVo = convertTheTypeMeasureZoneResultVo(measureZoneResult);
            oneJb = JSONObject.parseObject(JSONObject.toJSONString(resultListVo));
            //vo 有区别，暂时这样处理
            oneJb.put("update_at", DateUtil.dateToString(measureZoneResult.getUpdateAt()));

            String resultData = measureZoneResult.getData();//第一层data
            if (StringUtils.isNotBlank(resultData)) {
                JSONArray jsonArray = JSON.parseArray(resultData);

                JSONArray twoArr = new JSONArray();

                for (int k = 0; k < jsonArray.size(); k++) {

                    JSONObject twoJb = new JSONObject();

                    JSONObject threeJb = jsonArray.getJSONObject(k);

                    JSONArray threeArr = JSON.parseArray(threeJb.getString("Data"));

                    twoJb.put("score", threeJb.getString("Score"));
                    twoJb.put("texture", threeJb.getString("Texture"));

                    JSONArray fourArr = new JSONArray();

                    for (int z = 0; z < threeArr.size(); z++) {
                        JSONObject fourJb = new JSONObject();
                        fourJb.put("data_type", threeArr.getJSONObject(z).getString("DataType"));
                        fourJb.put("ok_total", threeArr.getJSONObject(z).getString("OkTotal"));
                        fourJb.put("design_value", threeArr.getJSONObject(z).getString("DesignValue"));
                        fourJb.put("design_value_reqd", threeArr.getJSONObject(z).getString("DesignValueReqd"));
                        fourJb.put("key", threeArr.getJSONObject(z).getString("Key"));
                        fourJb.put("seq", threeArr.getJSONObject(z).getString("Seq"));
                        fourJb.put("data", JSONArray.parse(threeArr.getJSONObject(z).getString("Data")));
                        fourArr.add(fourJb);
                    }
                    twoJb.put("data", fourArr);
                    twoArr.add(twoJb);
                }
                oneJb.put("data", twoArr);
            }
            MeasureRule measureRule = measureRuleService.selectById(measureZoneResult.getRuleId());
            JSONObject ruleJb = new JSONObject();
            ruleJb.put("points", new JSONArray());
            if (measureRule != null) {
                MeasureRuleVo measureRuleVo = convertTheTypeMeasureRuleVo(measureRule);
                ruleJb = JSONObject.parseObject(JSONObject.toJSONString(measureRuleVo));
                ruleJb.put("points", JSONArray.parse(measureRule.getPoints()));
                oneJb.put("rule", ruleJb);
            } else {
                oneJb.put("rule", ruleJb);
            }
            arr.add(oneJb);
        }
        totalJb.put("zone_result", arr);

        return totalJb;
    }

    private MeasureRuleVo convertTheTypeMeasureRuleVo(MeasureRule measureRule) {
        MeasureRuleVo measureRuleVo = new MeasureRuleVo();
        measureRuleVo.setId(measureRule.getId());
        measureRuleVo.setCategory_key(measureRule.getCategoryKey());
        measureRuleVo.setDesc(measureRule.getDesc());
        measureRuleVo.setFormula(measureRule.getFormula());
        measureRuleVo.setFormula_default(measureRule.getFormulaDefault());
        measureRuleVo.setGroup_count_init(measureRule.getGroupCountInit());
        measureRuleVo.setGroup_count_max(measureRule.getGroupCountMax());
        measureRuleVo.setGroup_count_min(measureRule.getGroupCountMin());
        measureRuleVo.setPoint_need(measureRule.getPointNeed());
        measureRuleVo.setRule_type(measureRule.getRuleType());
        measureRuleVo.setTeam_id(measureRule.getTeamId());
        measureRuleVo.setTextures(measureRule.getTextures());
        return measureRuleVo;
    }

    private ResultListVo convertTheTypeMeasureZoneResultVo(MeasureZoneResult measureZoneResult) {
        ResultListVo resultListVo = new ResultListVo();
        resultListVo.setId(measureZoneResult.getId());
        resultListVo.setUuid(measureZoneResult.getUuid());
        resultListVo.setProject_id(measureZoneResult.getProjectId());
        resultListVo.setList_id(measureZoneResult.getListId());
        resultListVo.setArea_id(measureZoneResult.getAreaId());
        resultListVo.setClose_status(measureZoneResult.getCloseStatus());
        resultListVo.setZone_uuid(measureZoneResult.getZoneUuid());
        resultListVo.setSquad_id(measureZoneResult.getSquadId());
        resultListVo.setRule_id(measureZoneResult.getRuleId());
        resultListVo.setOk_total(measureZoneResult.getOkTotal());
        resultListVo.setTotal(measureZoneResult.getTotal());
        resultListVo.setScore(measureZoneResult.getScore());
        resultListVo.setUpdate_at(measureZoneResult.getUpdateAt() == null ? 0 : DateUtil.dateToTimestamp(measureZoneResult.getUpdateAt()));
        resultListVo.setDelete_at(measureZoneResult.getDeleteAt() == null ? 0 : DateUtil.dateToTimestamp(measureZoneResult.getDeleteAt()));
        resultListVo.setRegion_uuid(measureZoneResult.getZoneUuid());
        resultListVo.setArea_path_and_id(measureZoneResult.getAreaPathAndId());
        resultListVo.setCategory_key(measureZoneResult.getCategoryKey());
        resultListVo.setCategory_path_and_key(measureZoneResult.getCategoryPathAndKey());

        return resultListVo;
    }

    @Override
    @SuppressWarnings("squid:S3776")
    public JSONObject paginationSearch(PaginationSearchReq paginationSearchReq) throws CommonException {

        JSONObject jb = new JSONObject();

        JSONArray jsonArray = new JSONArray();

        List<String> categoryKeyList = ConvertUtil.convertStrToList(paginationSearchReq.getCategory_key_list());
        List<String> areaIdList = ConvertUtil.convertStrToList(paginationSearchReq.getArea_id_list());

        Example example = new Example(MeasureZone.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo(PROJECTID, paginationSearchReq.getProject_id());
        criteria.andEqualTo("listId", paginationSearchReq.getList_id());
        if (!areaIdList.isEmpty()) {
            criteria.andIn("areaId", areaIdList);
        }
        if (!categoryKeyList.isEmpty()) {
            criteria.andIn("categoryKey", categoryKeyList);
        }
        Page result = PageHelper.startPage(paginationSearchReq.getPage(), paginationSearchReq.getPage_size());
        ExampleUtil.addDeleteAtJudge(example);
        measureZoneService.selectByExample(example);
        int count = measureZoneService.selectCountByExample(example);


        List<MeasureZone> measureZoneList = result.getResult();

        if (measureZoneList == null || measureZoneList.isEmpty()) {
            jb.put("zone_info_list", jsonArray);
            jb.put("total", 0);
            return jb;
        } else {

            Set<String> checkItemKeySet = new HashSet<>();

            Set<Integer> areaIdSet = new HashSet<>();

            for (int i = 0; i < measureZoneList.size(); i++) {
                MeasureZone measureZone = measureZoneList.get(i);
                String[] checkItemKey = measureZone.getCategoryPathAndKey().split("/");
                for (int k = 1; k < checkItemKey.length; k++) {
                    checkItemKeySet.add(checkItemKey[k]);
                }
                areaIdSet.add(measureZone.getAreaId());
            }

            List<String> checkItemList = new ArrayList<>(checkItemKeySet);

            //check_item dict

            CategoriesInfoReq categoriesInfoReq = new CategoriesInfoReq();

            categoriesInfoReq.setTeam_id(paginationSearchReq.getGroup_id());
            categoriesInfoReq.setKeys(StringUtils.join(checkItemList, ","));

            LjBaseResponse<CategoryJsonProtoItemVo> ljBaseResponse = coreCategoryItemFeignService.searchByUserIdList(categoriesInfoReq);

            List<CategoryJsonProtoVo> categoryJsonProtoVoList = ljBaseResponse.getData().getItems();

            Map<String, CategoryJsonProtoVo> checkItemDict = new HashMap<>();

            for (CategoryJsonProtoVo categoryJsonProtoVo : categoryJsonProtoVoList) {
                checkItemDict.put(categoryJsonProtoVo.getKey(), categoryJsonProtoVo);//生成key 对象
            }

            //area_dict

            SearchByIdListReq searchByIdListReq = new SearchByIdListReq();
            searchByIdListReq.setArea_id_list(new ArrayList<>(areaIdSet));

            LjBaseResponse<ProjAreaSearchByIdListVo> ljBase = coreAreaFeignService.searchByIdList(searchByIdListReq);
            List<AreaRetrieveVo> areaRetrieveVoList = ljBase.getData().getArea_list();

            Map<Integer, AreaRetrieveVo> areaDict = new HashMap<>();

            Set<Integer> areaIdDict = new HashSet<>();

            for (AreaRetrieveVo areaRetrieveVo : areaRetrieveVoList) {
                areaDict.put(areaRetrieveVo.getId(), areaRetrieveVo);
                areaIdDict.add(areaRetrieveVo.getId());
            }
            boolean flag = ArrayUtil.getSetDiff(areaIdSet, areaIdDict);

            if (flag) {
                throw new CommonException("区域缺失");
            }

            for (MeasureZone measureZone : measureZoneList) {
                Example zoneExample = new Example(MeasureRegion.class);
                Example.Criteria zoneCriteria = zoneExample.createCriteria();
                zoneCriteria.andEqualTo(PROJECTID, paginationSearchReq.getProject_id());
                zoneCriteria.andEqualTo("uuid", measureZone.getRegionUuid());

                List<MeasureRegion> measureRegionList = measureRegionService.selectByExample(zoneExample);

                String[] keyList = measureZone.getCategoryPathAndKey().split("/");
                MeasureRegion measureRegion = new MeasureRegion();
                if (CollectionUtils.isNotEmpty(measureRegionList)) {
                    measureRegion = measureRegionList.get(0);
                }

                StringBuilder categoryFullName = new StringBuilder();

                for (int k = 1; k < keyList.length; k++) {
                    categoryFullName.append("/").append(checkItemDict.get(keyList[k]) == null ? "" : checkItemDict.get(keyList[k]).getName());
                }
                Map polygon = JSON.parseObject(measureRegion.getPolygon());

                JSONObject innerJb = new JSONObject();

                Map<String, Object> regionDict = new HashMap<>();
                regionDict.put("id", measureRegion.getId());
                regionDict.put("area_id", measureRegion.getAreaId());
                regionDict.put("area_path_and_id", measureRegion.getAreaPathAndId());
                regionDict.put("drawing_md5", measureRegion.getDrawingMd5());
                regionDict.put("proj_id", measureRegion.getProjectId());
                regionDict.put("region_index", measureRegion.getRegionIndex());
                regionDict.put("src_type", measureRegion.getSrcType());
                regionDict.put("uuid", measureRegion.getUuid());
                regionDict.put("tag_id_list", measureRegion.getTagIdList());
                regionDict.put("polygon", polygon);
                regionDict.put("rel", measureRegionRelService.selectById(measureRegion.getRelId()));

                innerJb.put("area_full_name", areaDict.get(measureRegion.getAreaId()) == null ? "" : areaDict.get(measureRegion.getAreaId()).getFull_name());
                innerJb.put("category_full_name", categoryFullName);
                innerJb.put("region", regionDict);
                ZoneInfoVo zoneInfoVo = conversionType(measureZone);
                innerJb.put("zone", zoneInfoVo);

                jsonArray.add(innerJb);
            }
            jb.put("zone_info_list", jsonArray);
            jb.put("total", count);
        }
        return jb;
    }

    private ZoneInfoVo conversionType(MeasureZone measureZone) {
        ZoneInfoVo zoneInfoVo = new ZoneInfoVo();
        zoneInfoVo.setId(measureZone.getId());
        zoneInfoVo.setProject_id(measureZone.getProjectId());
        zoneInfoVo.setArea_id(measureZone.getAreaId());
        zoneInfoVo.setArea_path_and_id(measureZone.getAreaPathAndId());
        zoneInfoVo.setUuid(measureZone.getUuid());
        zoneInfoVo.setCategory_key(measureZone.getCategoryKey());
        zoneInfoVo.setCategory_path_and_key(measureZone.getCategoryPathAndKey());
        zoneInfoVo.setClose_status(measureZone.getCloseStatus());
        zoneInfoVo.setFinish_status(measureZone.getFinishStatus());
        zoneInfoVo.setList_id(measureZone.getListId());
        zoneInfoVo.setRegion_uuid(measureZone.getRegionUuid());
        zoneInfoVo.setSrc_type(measureZone.getSrcType());
        zoneInfoVo.setUpdate_at(measureZone.getUpdateAt() == null ? 0 : DateUtil.dateToTimestamp(measureZone.getUpdateAt()));
        zoneInfoVo.setCreate_at(measureZone.getCreateAt() == null ? 0 : DateUtil.dateToTimestamp(measureZone.getCreateAt()));
        zoneInfoVo.setDelete_at(measureZone.getDeleteAt() == null ? 0 : DateUtil.dateToTimestamp(measureZone.getDeleteAt()));
        return zoneInfoVo;
    }

    @Override
    public void updateStatus(UpdateStatusReq updateStatusReq) {

        String[] zoneId = updateStatusReq.getZone_id_list().split(",");

        Map<String, Object> map = new HashMap<>();

        map.put(PROJECT_ID, updateStatusReq.getProject_id());
        map.put("close_status", updateStatusReq.getClose_status());
        map.put(ZONEID, zoneId);
        map.put(UPDATE_AT, new Date());

        measureZoneService.updateStatus(map);

    }

    @Override
    @Transactional
    public void delByUuidList(DelByUuidListReq delByUuidListReq) {

        String[] zoneId = delByUuidListReq.getUuid_list().split(",");

        Map<String, Object> map = new HashMap<>();

        map.put(PROJECT_ID, delByUuidListReq.getProject_id());
        map.put(ZONEID, zoneId);
        map.put(DELETE_AT, new Date());
        map.put(UPDATE_AT, new Date());
        measureZoneResultService.delByUuidList(map);

        measureZoneService.delByUuidList(map);

    }

    @Override
    public void delBySquadIdUuid(DelBySquadIdUuidReq delBySquadIdUuidReq) {

        String[] zoneId = delBySquadIdUuidReq.getZone_uuid_list().split(",");

        Map<String, Object> map = new HashMap<>();

        map.put(PROJECT_ID, delBySquadIdUuidReq.getProject_id());
        map.put(ZONEID, zoneId);
        map.put("squad_id", delBySquadIdUuidReq.getSquad_id());
        map.put(DELETE_AT, new Date());
        map.put(UPDATE_AT, new Date());

        measureZoneResultService.delBySquadIdUuid(map);

    }

    @Override
    public void delByZoneUuid(DelByZoneUuidReq delByZoneUuidReq) {

        String[] zoneId = delByZoneUuidReq.getUuid_list().split(",");

        Map<String, Object> map = new HashMap<>();

        map.put(PROJECT_ID, delByZoneUuidReq.getProject_id());
        map.put(ZONEID, zoneId);
        map.put("squad_id", null);
        map.put(DELETE_AT, new Date());
        map.put(UPDATE_AT, new Date());

        measureZoneResultService.delBySquadIdUuid(map);

    }
}
