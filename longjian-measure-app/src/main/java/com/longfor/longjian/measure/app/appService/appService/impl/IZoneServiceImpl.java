package com.longfor.longjian.measure.app.appService.appService.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.longfor.longjian.common.base.LjBaseResponse;
import com.longfor.longjian.common.exception.CommonException;
import com.longfor.longjian.common.util.DateUtil;
import com.longfor.longjian.measure.app.appService.appService.IZoneService;
import com.longfor.longjian.measure.app.feignClient.ICoreAreaFeignService;
import com.longfor.longjian.measure.app.feignClient.ICoreCategoryItemFeignService;
import com.longfor.longjian.measure.app.req.feignReq.CategoriesInfoReq;
import com.longfor.longjian.measure.app.req.feignReq.SearchByIdListReq;
import com.longfor.longjian.measure.app.req.zone.*;
import com.longfor.longjian.measure.app.vo.appMeasureSyncVo.ZoneInfoVo;
import com.longfor.longjian.measure.app.vo.feignVo.AreaRetrieveVo;
import com.longfor.longjian.measure.app.vo.feignVo.CategoryJsonProtoItemVo;
import com.longfor.longjian.measure.app.vo.feignVo.CategoryJsonProtoVo;
import com.longfor.longjian.measure.app.vo.feignVo.ProjAreaSearchByIdListVo;
import com.longfor.longjian.measure.domain.externalService.*;
import com.longfor.longjian.measure.po.zhijian2.MeasureRegion;
import com.longfor.longjian.measure.po.zhijian2.MeasureRule;
import com.longfor.longjian.measure.po.zhijian2.MeasureZone;
import com.longfor.longjian.measure.po.zhijian2.MeasureZoneResult;
import com.longfor.longjian.measure.util.ArrayUtil;
import com.longfor.longjian.measure.util.ConvertUtil;
import com.longfor.longjian.measure.util.ExampleUtil;
import lombok.extern.slf4j.Slf4j;
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

    @Override
    public JSONObject getResult(GetResultReq getResultReq) {

        JSONObject totalJb = new JSONObject();

        Example example = new Example(MeasureZoneResult.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("projectId", getResultReq.getProject_id());
        criteria.andEqualTo("zoneUuid", getResultReq.getZone_uuid());

        List<MeasureZoneResult> measureZoneResults = measureZoneResultService.selectByExample(example);

        JSONArray arr = new JSONArray();

        for (int i = 0; i < measureZoneResults.size(); i++) {

            JSONObject oneJb = new JSONObject();

            MeasureZoneResult measureZoneResult = measureZoneResults.get(i);

            oneJb = JSONObject.parseObject(JSONObject.toJSONString(measureZoneResult));

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

            JSONObject ruleJb = JSONObject.parseObject(JSONObject.toJSONString(measureRule));

            ruleJb.put("points", JSONArray.parse(measureRule.getPoints()));

            oneJb.put("rule", ruleJb);

            arr.add(oneJb);
        }
        totalJb.put("zone_result", arr);

        return totalJb;
    }

    @Override
    public JSONObject paginationSearch(PaginationSearchReq paginationSearchReq) throws CommonException {

        JSONObject jb = new JSONObject();

        JSONArray jsonArray = new JSONArray();

        List<String> categoryKeyList = ConvertUtil.convertStrToList(paginationSearchReq.getCategory_key_list());
        List<String> areaIdList = ConvertUtil.convertStrToList(paginationSearchReq.getArea_id_list());

        Example example = new Example(MeasureZone.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("projectId", paginationSearchReq.getProject_id());
        criteria.andEqualTo("listId", paginationSearchReq.getList_id());
        if (areaIdList.size() > 0) {
            criteria.andIn("areaId", areaIdList);
        }
        if (categoryKeyList.size() > 0) {
            criteria.andIn("categoryKey", categoryKeyList);
        }
        Page result = PageHelper.startPage(paginationSearchReq.getPage(), paginationSearchReq.getPage_size());
        ExampleUtil.addDeleteAtJudge(example);
        measureZoneService.selectByExample(example);
        int count = measureZoneService.selectCountByExample(example);


        List<MeasureZone> measureZoneList = result.getResult();

        if (measureZoneList == null || measureZoneList.size() == 0) {
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
                zoneCriteria.andEqualTo("projectId", paginationSearchReq.getProject_id());
                zoneCriteria.andEqualTo("uuid", measureZone.getRegionUuid());

                List<MeasureRegion> measureRegionList = measureRegionService.selectByExample(zoneExample);

                String[] keyList = measureZone.getCategoryPathAndKey().split("/");

                MeasureRegion measureRegion = measureRegionList.get(0);

                String categoryFullName = "";

                for (int k = 1; k < keyList.length; k++) {
                    categoryFullName += "/" + checkItemDict.get(keyList[k]).getName();
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

                innerJb.put("area_full_name", areaDict.get(measureRegion.getAreaId()).getFull_name());
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

        map.put("project_id", updateStatusReq.getProject_id());
        map.put("close_status", updateStatusReq.getClose_status());
        map.put("zoneId", zoneId);
        map.put("update_at", new Date());

        measureZoneService.updateStatus(map);

    }

    @Override
    @Transactional
    public void delByUuidList(DelByUuidListReq delByUuidListReq) {

        String[] zoneId = delByUuidListReq.getUuid_list().split(",");

        Map<String, Object> map = new HashMap<>();

        map.put("project_id", delByUuidListReq.getProject_id());
        map.put("zoneId", zoneId);
        map.put("delete_at", new Date());
        map.put("update_at", new Date());
        measureZoneResultService.delByUuidList(map);

        measureZoneService.delByUuidList(map);

    }

    @Override
    public void delBySquadIdUuid(DelBySquadIdUuidReq delBySquadIdUuidReq) {

        String[] zoneId = delBySquadIdUuidReq.getZone_uuid_list().split(",");

        Map<String, Object> map = new HashMap<>();

        map.put("project_id", delBySquadIdUuidReq.getProject_id());
        map.put("zoneId", zoneId);
        map.put("squad_id", delBySquadIdUuidReq.getSquad_id());
        map.put("delete_at", new Date());
        map.put("update_at", new Date());

        measureZoneResultService.delBySquadIdUuid(map);

    }

    @Override
    public void delByZoneUuid(DelByZoneUuidReq delByZoneUuidReq) {

        String[] zoneId = delByZoneUuidReq.getUuid_list().split(",");

        Map<String, Object> map = new HashMap<>();

        map.put("project_id", delByZoneUuidReq.getProject_id());
        map.put("zoneId", zoneId);
        map.put("squad_id", null);
        map.put("delete_at", new Date());
        map.put("update_at", new Date());

        measureZoneResultService.delBySquadIdUuid(map);

    }
}
