package com.longfor.longjian.measure.app.appservice.appservice.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.google.common.collect.Lists;
import com.longfor.longjian.common.base.LjBaseResponse;
import com.longfor.longjian.common.exception.LjBaseRuntimeException;
import com.longfor.longjian.measure.app.appservice.appservice.IAPPMeasureListService;
import com.longfor.longjian.measure.app.req.measurelist.*;
import com.longfor.longjian.measure.app.vo.measurelistvo.ListInfoVo;
import com.longfor.longjian.measure.app.vo.measurelistvo.MeasureListInfoVo;
import com.longfor.longjian.measure.app.vo.measurelistvo.SetStatusVo;
import com.longfor.longjian.measure.consts.enums.MeasureCloseStatusEnum;
import com.longfor.longjian.measure.consts.enums.MeasureFinishStatusEnum;
import com.longfor.longjian.measure.consts.enums.RegionSrcTypeEnum;
import com.longfor.longjian.measure.domain.externalservice.*;
import com.longfor.longjian.measure.po.zhijian2.*;
import com.longfor.longjian.measure.util.LambdaExceptionUtil;
import com.longfor.longjian.measure.vo.ConditionSearchVo;
import com.longfor.longjian.measure.vo.CreateMeasureListVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Wang on 2019/1/7.
 */
@Service
@Slf4j
public class IAPPMeasureListServiceImpl implements IAPPMeasureListService {

    @Resource
    private IMeasureListService iMeasureListService;

    @Resource
    private IMeasureListAreaService iMeasureListAreaService;

    @Resource
    private IMeasureZoneService iMeasureZoneService;

    @Resource
    private IMeasureZoneResultService iMeasureZoneResultService;

    @Resource
    private IMeasureSquadService iMeasureSquadService;

    @Resource
    private IMeasureSquadUserService iMeasureSquadUserService;

    @Autowired
    IMeasureZoneService measureZoneService;
    @Resource
    private IMeasureListService measureListService;
    @Resource
    private IAreaService areaService;
    @Resource
    private IMeasureListAreaService measureListAreaService;
    @Resource
    private IMeasureSquadService measureSquadService;
    @Resource
    private IMeasureSquadUserService measureSquadUserService;
    @Resource
    private IMeasureRepairerUserService measureRepairerUserService;
    @Resource
    private IMeasureRegionService measureRegionService;
    @Resource
    private IMeasureRegionRelService measureRegionRelService;
    private static final String USER_ID_LIST = "user_id_list";
    @Override
    public SetStatusVo setStatus(SetStatusReq setStatusReq) {

        MeasureList measureList = new MeasureList();

        measureList.setId(setStatusReq.getList_id());
        measureList.setProjectId(setStatusReq.getProject_id());
        measureList.setCloseStatus(setStatusReq.getClose_status());
        measureList.setFinishStatus(setStatusReq.getFinish_status());
        measureList.setUpdateAt(new Date());
        iMeasureListService.updateMeasureList(measureList);

        SetStatusVo setStatusVo = new SetStatusVo();

        setStatusVo.setList_id(setStatusReq.getList_id());
        setStatusVo.setClose_status(setStatusReq.getClose_status());
        setStatusVo.setFinish_status(setStatusReq.getFinish_status());

        return setStatusVo;
    }

    @Override
    public Map<String, Object> updateName(UpdateNameReq updateNameReq) {

        MeasureList measureList = new MeasureList();
        measureList.setId(updateNameReq.getList_id());
        measureList.setProjectId(updateNameReq.getProject_id());
        measureList.setName(updateNameReq.getName());
        iMeasureListService.updateMeasureList(measureList);

        Map<String, Object> map = new HashMap<>();
        map.put("name", updateNameReq.getName());
        return map;
    }

    @Override
    @Transactional
    public void delete(DeleteReq deleteReq) {
        iMeasureListService.delete(deleteReq.getList_id());

        iMeasureListAreaService.delete(deleteReq.getList_id());

        iMeasureZoneService.delete(deleteReq.getList_id());

        iMeasureZoneResultService.delete(deleteReq.getList_id());

        iMeasureSquadService.delete(deleteReq.getList_id());

        iMeasureSquadUserService.delete(deleteReq.getList_id());
    }

    @Override
    public void updateFinishStatus(UpdateFinishStatusReq updateFinishStatusReq) {

        Map<String, Object> map = new HashMap<>();

        map.put("project_id", updateFinishStatusReq.getProject_id());
        map.put("finish_status", updateFinishStatusReq.getFinish_status());
        map.put("list_ids", updateFinishStatusReq.getList_ids().split(","));
        map.put("update_at", new Date());

        iMeasureListService.updateFinishStatus(map);

    }

    @Override
    public void updateCloseStatus(UpdateCloseStatusReq updateCloseStatusReq) {
        Map<String, Object> map = new HashMap<>();

        map.put("project_id", updateCloseStatusReq.getProject_id());
        map.put("close_status", updateCloseStatusReq.getClose_status());
        map.put("list_ids", updateCloseStatusReq.getList_ids().split(","));
        map.put("update_at", new Date());

        iMeasureListService.updateCloseStatus(map);
    }

    @Override
    public MeasureList getByProjIdAndIdNoFoundErr(Integer projectId, Integer id) {
        return measureListService.getByProjIdAndIdNoFoundErr(projectId, id);
    }

    @Override
    public LjBaseResponse<MeasureListInfoVo> conditionSearch(ConditionSearchReq req) throws LjBaseRuntimeException {

        ConditionSearchVo vo =new ConditionSearchVo();
        vo.setGroup_id(req.getGroup_id());
        vo.setProject_id(req.getProject_id());
        vo.setPage(req.getPage());
        vo.setPage_size(req.getPage_size());
        vo.setArea_id(req.getArea_id());
        vo.setUser_id_list(req.getUser_id_list());
        vo.setFinish_status(req.getFinish_status());
        vo.setName(req.getName());
        vo.setCategory_key(req.getCategory_key());
        Map<String, Object> listMap = measureListService.conditionSearch(vo);
        LjBaseResponse<MeasureListInfoVo> ljBaseResponse = new LjBaseResponse<>();
        MeasureListInfoVo measureListInfoVo = new MeasureListInfoVo();
        List<ListInfoVo> listInfoVos = Lists.newArrayList();
        Integer totalNum = (Integer) listMap.get("total_num");
        measureListInfoVo.setTotal(totalNum);
        List<Map<String, Object>> returnList = (List<Map<String, Object>>) listMap.get("return_list");
        for (Map<String, Object> map : returnList) {
            ListInfoVo listInfoVo = new ListInfoVo();
            listInfoVo.setId((Integer) map.get("id"));
            listInfoVo.setName(map.get("name").toString());
            listInfoVo.setProj_id((Integer) map.get("projectId"));
            listInfoVo.setArea_type(map.get("areaType") == null ? "" : map.get("areaType").toString());
            listInfoVo.setClose_status((Integer) map.get("closeStatus"));
            listInfoVo.setFinish_status((Integer) map.get("finishStatus"));
            listInfoVo.setTop_areas((List<String>) map.get("top_areas"));
            listInfoVo.setRoot_category_key(map.get("rootCategoryKey").toString());
            listInfoVo.setRoot_category_name(map.get("root_category_name") != null ? map.get("root_category_name").toString() : null);
            listInfoVo.setIssue_count(map.get("issue_count") == null ? 0 : (Integer) map.get("issue_count"));
            listInfoVo.setCreate_at(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format((Date) map.get("createAt")));
            listInfoVos.add(listInfoVo);
        }
        measureListInfoVo.setList_info(listInfoVos);
        ljBaseResponse.setData(measureListInfoVo);
        return ljBaseResponse;
    }

    @Override
    @Transactional
    public void add(Map params) {
        int projId = (int) params.get("proj_id");
        String areaIdLists = params.get("area_id_list").toString();
        String areaType = params.get("area_type").toString();
        String rootCategoryKey = params.get("root_category_key").toString();
        String zoneGroup = params.get("zone_group").toString();
        String planBeginOn = params.get("plan_begin_on").toString();
        String planEndOn = params.get("plan_end_on").toString();
        String name = params.get("name").toString();
        String squadGroup = params.get("squad_group").toString();
        String repairerGroup = params.get("repairer_group").toString();
        //创建任务
        CreateMeasureListVo vo =new CreateMeasureListVo();
        vo.setProj_id(projId);
        vo.setName(name);
        vo.setArea_type(areaType);
        vo.setId(MeasureCloseStatusEnum.OPEN.getId());
        vo.setId1(MeasureFinishStatusEnum.PROCESSING.getId());
        vo.setRoot_category_key(rootCategoryKey);
        vo.setPlan_begin_on(planBeginOn);
        vo.setPlan_end_on(planEndOn);
        MeasureList listModel = measureListService.createMeasureList(vo);
        //获取区域信息 todo lamada表达式没弄明白😐
        String[] areaIdList = areaIdLists.split(",");
        List<Integer> areaIdListInt = new ArrayList<>();
        for (String id : areaIdList
        ) {
            areaIdListInt.add(Integer.parseInt(id));
        }
        List<Area> areaList = areaService.searchByIdList(projId, areaIdListInt);
        List<Integer> fullAreaIdList = new ArrayList<>();
        areaList.forEach(area -> {
            fullAreaIdList.add(area.getId());
            String[] paths = area.getPath().split(",");
            for (int i = 1; i < paths.length - 1; i++) {
                fullAreaIdList.add(Integer.parseInt(paths[i]));
            }
        });
        List<Area> fullAreaList = areaService.searchByIdList(projId, fullAreaIdList);
        Map<Integer, Area> areaDict = fullAreaList.stream().collect(Collectors.toMap(Area::getId, area -> area));
        log.info("area_dict:{}", JSON.toJSON(areaDict));

        //创建任务区域
        fullAreaList.forEach(area -> {
            measureListAreaService.create(projId, area.getId(), area.getPath() + area.getId() + "/", listModel.getId());
        });

        //检查小组组间人员查重
        int userNums = 0;
        Set<String> userSet = new HashSet<>();
        List<Map> squadGroups = JSONArray.parseArray(squadGroup, Map.class);
        for (Map squad : squadGroups) {
            squad.put(USER_ID_LIST, Arrays.asList(squad.get("user_ids").toString().split(",")));
            userNums += JSONArray.parseArray(squad.get(USER_ID_LIST).toString(), String.class).size();
            userSet.addAll(new HashSet(JSONArray.parseArray(squad.get(USER_ID_LIST).toString(), String.class)));
            if (userNums != userSet.size()) {
                throw new LjBaseRuntimeException(-9999, "duplicated");
            }
        }

        //创建检查小组, 以及检查小组成员
        for (Map squad : squadGroups) {
            MeasureSquad measureSquad = new MeasureSquad();
            measureSquad.setProjectId(projId);
            measureSquad.setListId(listModel.getId());
            measureSquad.setName(squad.get("name").toString());
            measureSquad.setPlanRate(Integer.parseInt(squad.get("plan_rate").toString()));
            measureSquad.setRate(0);
            measureSquad.setCreateAt(new Date());
            measureSquad.setUpdateAt(new Date());
            MeasureSquad squadModel = measureSquadService.createReturnSuqad(measureSquad);
            List<String> squadUserIds = JSONArray.parseArray(squad.get(USER_ID_LIST).toString(), String.class);
            squadUserIds.forEach(userId -> {
                MeasureSquadUser measureSquadUser = new MeasureSquadUser();
                measureSquadUser.setProjectId(projId);
                measureSquadUser.setListId(listModel.getId());
                measureSquadUser.setSquadId(squadModel.getId());
                measureSquadUser.setUserId(Integer.parseInt(userId));
                measureSquadUser.setCreateAt(new Date());
                measureSquadUser.setUpdateAt(new Date());
                measureSquadUserService.create(measureSquadUser);
            });
        }

        //整改小组创建
        List<Map> repairerGroups = JSONArray.parseArray(repairerGroup, Map.class);
        for (Map group : repairerGroups) {
            List<String> userIdList = Arrays.asList(group.get("user_ids").toString().split(","));
            for (String userId : userIdList) {
                measureRepairerUserService.create(projId, listModel.getId(), group.get("role_type").toString(), Integer.parseInt(userId));
            }
        }

        //提前加载
        Set<String> regionUuidSet = new HashSet<>();
        List<Map> zoneGroups = JSONArray.parseArray(zoneGroup, Map.class);
        zoneGroups.forEach(pair -> {
            List<Map> groups = JSONArray.parseArray(pair.get("group").toString(), Map.class);
            for (Map zoneDict : groups) {
                regionUuidSet.add(zoneDict.get("region_uuid").toString());
            }
        });
        List<MeasureRegion> regionList = measureRegionService.searchByUuids(projId, regionUuidSet);
        Map<String, MeasureRegion> regionDict = regionList.stream().collect(Collectors.toMap(MeasureRegion::getUuid, region -> region));
        log.info("region_dict");

        Set<Integer> relIdSet = new HashSet<>();
        regionList.forEach(region -> {
            relIdSet.add(region.getRelId());
        });
        List<MeasureRegionRel> relList = measureRegionRelService.selectByProjectIdAndIdNoDeleted(projId, new ArrayList<>(relIdSet));
        Map<Integer, MeasureRegionRel> relDict = relList.stream().collect(Collectors.toMap(MeasureRegionRel::getId, regionRel -> regionRel));
        log.info("rel_dict");

        List<MeasureZone> insertZoneList = new ArrayList<>();
        for (Map pair : zoneGroups) {
            List<String> copyAreaIdList = Arrays.asList(pair.get("copy_to_areas").toString().split(","));
            List<Map> groups = JSONArray.parseArray(pair.get("group").toString(), Map.class);
            for (Map zonedict : groups) {
                //定义好复制数据
                zonedict.put("close_status", MeasureCloseStatusEnum.OPEN.getId());
                zonedict.put("finish_status", MeasureFinishStatusEnum.PROCESSING.getId());
                zonedict.put("list_id", listModel.getId());
                zonedict.put("src_type", RegionSrcTypeEnum.BACKEND.getId());

                //找到要复制到其他区域的描点
                String originRegionuuid = zonedict.remove("region_uuid").toString();
                MeasureRegion regionModelDict = regionDict.get(originRegionuuid);
                MeasureRegionRel relModelDict = relDict.get(regionModelDict.getRelId());

                List<String> regionIdList = new ArrayList<>();
                if (relModelDict != null) {
                    regionIdList = Arrays.asList(relModelDict.getRegionIds().split(","));
                } else {
                    regionIdList.add(regionModelDict.getId().toString());
                }

                //找出复制到其他区域的描点
                List<MeasureRegion> regionModelList = measureRegionService.searchByIdAndAreaIdAndProjectIdNoDeleted(regionIdList, copyAreaIdList, projId);
                regionModelList.forEach(LambdaExceptionUtil.throwingConsumerWrapper(regionModel -> {
                    MeasureZone measureZone = new MeasureZone();
                    Area area = areaDict.get(regionModel.getAreaId());
                    String areaPathAndId = area == null ? "" : area.getPath() + area.getId() + "/";
                    measureZone.setRegionUuid(regionModel.getUuid());
                    measureZone.setUuid(UUID.randomUUID().toString().replaceAll("-", ""));
                    measureZone.setAreaId(area == null ? 0 : area.getId());
                    measureZone.setAreaPathAndId(areaPathAndId);
                    measureZone.setCategoryKey(zonedict.get("category_key").toString());
                    measureZone.setCategoryPathAndKey(zonedict.get("category_path_and_key").toString());
                    measureZone.setProjectId(projId);
                    measureZone.setListId(listModel.getId());
                    measureZone.setSrcType(RegionSrcTypeEnum.BACKEND.getId());
                    measureZone.setFinishStatus(MeasureFinishStatusEnum.PROCESSING.getId());
                    measureZone.setCloseStatus(MeasureCloseStatusEnum.OPEN.getId());
                    measureZone.setCreateAt(new Date());
                    measureZone.setUpdateAt(new Date());
                    insertZoneList.add(measureZone);
                }));
            }
        }
        measureZoneService.insertMany(insertZoneList);
    }
}
