package com.longfor.longjian.measure.app.appService.appService.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.google.common.collect.Lists;
import com.longfor.longjian.common.base.LjBaseResponse;
import com.longfor.longjian.common.exception.LjBaseRuntimeException;
import com.longfor.longjian.measure.app.appService.appService.IAPPMeasureListService;
import com.longfor.longjian.measure.app.req.MeasureList.*;
import com.longfor.longjian.measure.app.vo.measureListVo.ListInfoVo;
import com.longfor.longjian.measure.app.vo.measureListVo.MeasureListInfoVo;
import com.longfor.longjian.measure.app.vo.measureListVo.SetStatusVo;
import com.longfor.longjian.measure.consts.Enum.MeasureCloseStatusEnum;
import com.longfor.longjian.measure.consts.Enum.MeasureFinishStatusEnum;
import com.longfor.longjian.measure.consts.Enum.RegionSrcTypeEnum;
import com.longfor.longjian.measure.domain.externalService.*;
import com.longfor.longjian.measure.po.zhijian2.*;
import com.longfor.longjian.measure.util.LambdaExceptionUtil;
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
    private IMeasureListAreaService IMeasureListAreaService;

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

        IMeasureListAreaService.delete(deleteReq.getList_id());

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
    public MeasureList GetByProjIdAndIdNoFoundErr(Integer projectId, Integer id) {
        return measureListService.GetByProjIdAndIdNoFoundErr(projectId, id);
    }

    @Override
    public LjBaseResponse<MeasureListInfoVo> conditionSearch(ConditionSearchReq req) throws Exception {
        Map<String, Object> listMap = measureListService.conditionSearch(req.getGroup_id(), req.getProject_id(), req.getPage(), req.getPage_size(), req.getArea_id(), req.getUser_id_list(), req.getFinish_status(), req.getName(), req.getCategory_key());
        LjBaseResponse<MeasureListInfoVo> ljBaseResponse = new LjBaseResponse<>();
        MeasureListInfoVo measureListInfoVo = new MeasureListInfoVo();
        List<ListInfoVo> listInfoVos = Lists.newArrayList();
        Integer total_num = (Integer) listMap.get("total_num");
        measureListInfoVo.setTotal(total_num);
        List<Map<String, Object>> return_list = (List<Map<String, Object>>) listMap.get("return_list");
        for (Map<String, Object> map : return_list) {
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
        int proj_id = (int) params.get("proj_id");
        String area_id_list = params.get("area_id_list").toString();
        String area_type = params.get("area_type").toString();
        String root_category_key = params.get("root_category_key").toString();
        String zone_group = params.get("zone_group").toString();
        String plan_begin_on = params.get("plan_begin_on").toString();
        String plan_end_on = params.get("plan_end_on").toString();
        String name = params.get("name").toString();
        String squad_group = params.get("squad_group").toString();
        String repairer_group = params.get("repairer_group").toString();
        //创建任务
        MeasureList list_model = measureListService.createMeasureList(proj_id, name, area_type, MeasureCloseStatusEnum.Open.getId(), MeasureFinishStatusEnum.Processing.getId(), root_category_key, plan_begin_on, plan_end_on);
        //获取区域信息 todo lamada表达式没弄明白😐
        String[] areaIdList = area_id_list.split(",");
        List<Integer> areaIdListInt = new ArrayList<>();
        for (String id : areaIdList
        ) {
            areaIdListInt.add(Integer.parseInt(id));
        }
        List<Area> area_list = areaService.searchByIdList(proj_id, areaIdListInt);
        List<Integer> full_area_id_list = new ArrayList<>();
        area_list.forEach(area -> {
            full_area_id_list.add(area.getId());
            String[] paths = area.getPath().split(",");
            for (int i = 1; i < paths.length - 1; i++) {
                full_area_id_list.add(Integer.parseInt(paths[i]));
            }
        });
        List<Area> full_area_list = areaService.searchByIdList(proj_id, full_area_id_list);
        Map<Integer, Area> area_dict = full_area_list.stream().collect(Collectors.toMap(Area::getId, area -> area));
        log.info("area_dict:{}", JSON.toJSON(area_dict));

        //创建任务区域
        full_area_list.forEach(area -> {
            measureListAreaService.create(proj_id, area.getId(), area.getPath() + area.getId() + "/", list_model.getId());
        });

        //检查小组组间人员查重
        int user_nums = 0;
        Set<String> user_set = new HashSet<>();
        List<Map> squadGroup = JSONArray.parseArray(squad_group, Map.class);
        for (Map squad : squadGroup) {
            squad.put("user_id_list", Arrays.asList(squad.get("user_ids").toString().split(",")));
            user_nums += JSONArray.parseArray(squad.get("user_id_list").toString(), String.class).size();
            user_set.addAll(new HashSet(JSONArray.parseArray(squad.get("user_id_list").toString(), String.class)));
            if (user_nums != user_set.size()) {
                throw new LjBaseRuntimeException(-9999, "duplicated");
            }
        }

        //创建检查小组, 以及检查小组成员
        for (Map squad : squadGroup) {
            MeasureSquad measureSquad = new MeasureSquad();
            measureSquad.setProjectId(proj_id);
            measureSquad.setListId(list_model.getId());
            measureSquad.setName(squad.get("name").toString());
            measureSquad.setPlanRate(Integer.parseInt(squad.get("plan_rate").toString()));
            measureSquad.setRate(0);
            measureSquad.setCreateAt(new Date());
            measureSquad.setUpdateAt(new Date());
            MeasureSquad squad_model = measureSquadService.createReturnSuqad(measureSquad);
            List<String> squadUserIds = JSONArray.parseArray(squad.get("user_id_list").toString(), String.class);
            squadUserIds.forEach(user_id -> {
                MeasureSquadUser measureSquadUser = new MeasureSquadUser();
                measureSquadUser.setProjectId(proj_id);
                measureSquadUser.setListId(list_model.getId());
                measureSquadUser.setSquadId(squad_model.getId());
                measureSquadUser.setUserId(Integer.parseInt(user_id));
                measureSquadUser.setCreateAt(new Date());
                measureSquadUser.setUpdateAt(new Date());
                measureSquadUserService.create(measureSquadUser);
            });
        }

        //整改小组创建
        List<Map> repairerGroup = JSONArray.parseArray(repairer_group, Map.class);
        for (Map group : repairerGroup) {
            List<String> user_id_list = Arrays.asList(group.get("user_ids").toString().split(","));
            for (String user_id : user_id_list) {
                measureRepairerUserService.create(proj_id, list_model.getId(), group.get("role_type").toString(), Integer.parseInt(user_id));
            }
        }

        //提前加载
        Set<String> region_uuid_set = new HashSet<>();
        List<Map> zoneGroup = JSONArray.parseArray(zone_group, Map.class);
        zoneGroup.forEach(pair -> {
            List<Map> groups = JSONArray.parseArray(pair.get("group").toString(), Map.class);
            for (Map zone_dict : groups) {
                region_uuid_set.add(zone_dict.get("region_uuid").toString());
            }
        });
        List<MeasureRegion> region_list = measureRegionService.searchByUuids(proj_id, region_uuid_set);
        Map<String, MeasureRegion> region_dict = region_list.stream().collect(Collectors.toMap(MeasureRegion::getUuid, region -> region));
        log.info("region_dict");

        Set<Integer> rel_id_set = new HashSet<>();
        region_list.forEach(region -> {
            rel_id_set.add(region.getRelId());
        });
        List<MeasureRegionRel> rel_list = measureRegionRelService.selectByProjectIdAndIdNoDeleted(proj_id, new ArrayList<>(rel_id_set));
        Map<Integer, MeasureRegionRel> rel_dict = rel_list.stream().collect(Collectors.toMap(MeasureRegionRel::getId, regionRel -> regionRel));
        log.info("rel_dict");

        List<MeasureZone> insert_zone_list = new ArrayList<>();
        for (Map pair : zoneGroup) {
            List<String> copy_area_id_list = Arrays.asList(pair.get("copy_to_areas").toString().split(","));
            List<Map> groups = JSONArray.parseArray(pair.get("group").toString(), Map.class);
            for (Map zone_dict : groups) {
                //定义好复制数据
                zone_dict.put("close_status", MeasureCloseStatusEnum.Open.getId());
                zone_dict.put("finish_status", MeasureFinishStatusEnum.Processing.getId());
                zone_dict.put("list_id", list_model.getId());
                zone_dict.put("src_type", RegionSrcTypeEnum.BackEnd.getId());

                //找到要复制到其他区域的描点
                String origin_region_uuid = zone_dict.remove("region_uuid").toString();
                MeasureRegion region_model_dict = region_dict.get(origin_region_uuid);
                MeasureRegionRel rel_model_dict = rel_dict.get(region_model_dict.getRelId());

                List<String> region_id_list = new ArrayList<>();
                if (rel_model_dict != null) {
                    region_id_list = Arrays.asList(rel_model_dict.getRegionIds().split(","));
                } else {
                    region_id_list.add(region_model_dict.getId().toString());
                }

                //找出复制到其他区域的描点
                List<MeasureRegion> region_model_list = measureRegionService.searchByIdAndAreaIdAndProjectIdNoDeleted(region_id_list, copy_area_id_list, proj_id);
                region_model_list.forEach(LambdaExceptionUtil.throwingConsumerWrapper(region_model -> {
                    MeasureZone measureZone = new MeasureZone();
                    Area area = area_dict.get(region_model.getAreaId());
                    String area_path_and_id = area == null ? "" : area.getPath() + area.getId() + "/";
//                    Map new_dict = zone_dict;
                    measureZone.setRegionUuid(region_model.getUuid());
                    measureZone.setUuid(UUID.randomUUID().toString().replaceAll("-", ""));
                    measureZone.setAreaId(area == null ? 0 : area.getId());
                    measureZone.setAreaPathAndId(area_path_and_id);
//                    new_dict.put("region_uuid", region_model.getUuid());
//                    new_dict.put("uuid", UUID.randomUUID().toString().replaceAll("-", ""));
//                    new_dict.put("area_id", area == null ? 0 : area.getId());

//                    new_dict.put("area_path_and_id", area_path_and_id);
                    measureZone.setCategoryKey(zone_dict.get("category_key").toString());
                    measureZone.setCategoryPathAndKey(zone_dict.get("category_path_and_key").toString());
                    measureZone.setProjectId(proj_id);
                    measureZone.setListId(list_model.getId());
                    measureZone.setSrcType(RegionSrcTypeEnum.BackEnd.getId());
                    measureZone.setFinishStatus(MeasureFinishStatusEnum.Processing.getId());
                    measureZone.setCloseStatus(MeasureCloseStatusEnum.Open.getId());
                    measureZone.setCreateAt(new Date());
                    measureZone.setUpdateAt(new Date());
                    insert_zone_list.add(measureZone);
                }));
            }
        }
        measureZoneService.insertMany(insert_zone_list);
    }
}
