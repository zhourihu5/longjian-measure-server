package com.longfor.longjian.measure.app.appService.appMeasureSyncService.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.longfor.longjian.common.base.LjBaseResponse;
import com.longfor.longjian.common.util.DateUtil;
import com.longfor.longjian.measure.app.appService.appMeasureSyncService.IAPPMeasureSyncService;
import com.longfor.longjian.measure.app.appService.appService.IKeyProcedureTaskAppService;
import com.longfor.longjian.measure.app.req.apiMeasureRuleReq.*;
import com.longfor.longjian.measure.app.vo.appMeasureSyncVo.*;
import com.longfor.longjian.measure.consts.Enum.*;
import com.longfor.longjian.measure.consts.constant.ApiDropDataReasonConstant;
import com.longfor.longjian.measure.domain.externalService.*;
import com.longfor.longjian.measure.po.zhijian2.*;
import com.longfor.longjian.measure.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Slf4j
public class APPMeasureSyncServiceImpl implements IAPPMeasureSyncService {
    private static Integer MEASURE_API_GET_PER_TIME = 5000;
    @Autowired
    private IMeasureRuleService measureRuleService;
    @Autowired
    private ICategoryV3Service categoryV3Service;
    @Autowired
    private IMeasureListService measureListService;
    @Autowired
    private IMeasureZoneService measureZoneService;
    @Autowired
    private IMeasureListIssueService measureListIssueService;
    @Autowired
    private IMeasureListIssueLogService measureListIssueLogService;
    @Autowired
    private IKeyProcedureTaskAppService keyProcedureTaskAppService;
    @Autowired
    private IAreaService areaService;
    @Autowired
    private IMeasureRegionService measureRegionService;
    @Autowired
    private ICheckItemService checkItemService;

    @Override
    public LjBaseResponse<RuleListVo> getMeasureRule(ApiMeasureRuleReq apiMeasureRuleReq) throws Exception {
        LjBaseResponse<RuleListVo> ljBaseResponse = new LjBaseResponse<>();
        List<RuleInfoVo> ruleInfoVos = new ArrayList<>();
        RuleListVo ruleListVo = new RuleListVo();
        //切割categoryKeys
        if (apiMeasureRuleReq.getCategory_keys().length() == 0) {
            throw new Exception("category keys is empty.");
        }
        String[] categoryKeys = apiMeasureRuleReq.getCategory_keys().split(",");
        //数组转换List
        List<String> categoryKeysList = Arrays.asList(categoryKeys);
        //查询categoryKeys
        List<CategoryV3> categoryList = categoryV3Service.getCategoryByKeys(categoryKeysList);
        //updateAtDate不知道参数什么意思  默认时间0001-01-01 00:00:00  +0000  UTC
        String stringTime = "0001-01-01 00:00:00";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dateString = formatter.parse(stringTime);
        for (CategoryV3 category : categoryList
        ) {
            if (category.getUpdateAt() != null) {
                List<MeasureRule> measureRuleList = measureRuleService.searchUnscopedByTeamIdUpdateAtGt(category.getTeamId(), dateString);
                for (MeasureRule measureRule : measureRuleList
                ) {
                    RuleInfoVo ruleInfoVo = converMeasureRuleToRuleInfoVo(measureRule);
                    ruleInfoVos.add(ruleInfoVo);
                }
            }
        }
        ruleListVo.setRule_list(ruleInfoVos);
        ljBaseResponse.setData(ruleListVo);
        return ljBaseResponse;
    }

    @Override
    public LjBaseResponse<MyTaskVo> getMyTask(ApiMyTaskReq apiMyTaskReq, HttpServletRequest request) throws Exception {
        LjBaseResponse<MyTaskVo> ljBaseResponse = new LjBaseResponse<>();
        MyTaskVo myTaskVo = new MyTaskVo();
        List<TaskInfoVo> taskInfoVos = new ArrayList<>();
        //TaskInfoVo
        if (apiMyTaskReq.getProject_id().length() == 0) {
            throw new Exception("project id is empty.");
        }
        String[] projectIds = apiMyTaskReq.getProject_id().split(",");
        //HttpSession session = request.getSession();
        //todo session没有uid
        //Integer userId = (Integer) session.getAttribute("uid");
        Integer userId = 6;
        for (String projectId : projectIds
        ) {
            List<MeasureList> measureLists = measureListService.searchListByProjIdUserId(projectId, userId);
            measureLists.forEach(measureList -> {
                TaskInfoVo taskInfoVo = convermeasureListToTaskInfoVo(measureList);
                taskInfoVos.add(taskInfoVo);
            });
        }
        myTaskVo.setMeasure_list(taskInfoVos);
        ljBaseResponse.setData(myTaskVo);
        return ljBaseResponse;
    }

    @Override
    public LjBaseResponse<MeasureZoneVo> getMeasureZone(ApiMeasureZoneReq apiMeasureZoneReq) throws Exception {
        LjBaseResponse<MeasureZoneVo> ljBaseResponse = new LjBaseResponse<>();
        MeasureZoneVo measureZoneVo = new MeasureZoneVo();
        List<ZoneInfoVo> zoneInfoVos = new ArrayList<>();
        if (apiMeasureZoneReq.getList_ids().length() == 0) {
            throw new Exception("list is empty.");
        }
        String[] listIds = apiMeasureZoneReq.getList_ids().split(",");
        Integer lastId = 0;
        Integer limit = MEASURE_API_GET_PER_TIME;
        Integer start = 0;
        Long timestamp = null;
        for (String listId : listIds
        ) {
            try {
                MeasureList measureList = measureListService.getNoProjNoFoundErr(listId);
                lastId = measureList.getId();
                //最后一次获取的id
                List<MeasureZone> measureZones = measureZoneService.searchZoneUnscopedByListIdLastIdUpdateAtGt(measureList.getProjectId(), listId, lastId, timestamp, limit, start);
                measureZones.forEach(measureZone -> {
                    ZoneInfoVo zoneInfoVo = converMeasureZoneToZoneInfoVo(measureZone);
                    zoneInfoVos.add(zoneInfoVo);
                });
            } catch (Exception e) {
                throw new Exception(e);
            }
        }
        measureZoneVo.setZone_list(zoneInfoVos);
        measureZoneVo.setLast_id(lastId);
        ljBaseResponse.setData(measureZoneVo);
        return ljBaseResponse;
    }

    @Override
    public LjBaseResponse<MeasureZoneVo> getMeasureZoneV2(ApiMeasureZoneReqV2 apiMeasureZoneReqV2) throws Exception {
        LjBaseResponse<MeasureZoneVo> ljBaseResponse = new LjBaseResponse<>();
        MeasureZoneVo measureZoneVo = new MeasureZoneVo();
        List<ZoneInfoVo> zoneInfoVos = new ArrayList<>();
        MeasureList measureList = measureListService.getNoProjNoFoundErr(apiMeasureZoneReqV2.getList_id().toString());
        Integer lastId = 0;
        Integer start = 0;
        Integer limit = MEASURE_API_GET_PER_TIME;
        List<MeasureZone> measureZones = measureZoneService.searchZoneUnscopedByListIdLastIdUpdateAtGt2(measureList.getProjectId(), apiMeasureZoneReqV2.getList_id(), apiMeasureZoneReqV2.getLast_id(), apiMeasureZoneReqV2.getTimestamp(), start, limit);
        if((measureZones.size()-1)>0){
            lastId =measureZones.get(measureZones.size()-1).getId();
        }
        measureZones.forEach(measureZone -> {
            ZoneInfoVo zoneInfoVo = converMeasureZoneToZoneInfoVo(measureZone);
            zoneInfoVos.add(zoneInfoVo);
        });
        try {
            measureZoneVo.setZone_list(zoneInfoVos);
            measureZoneVo.setLast_id(lastId);
            ljBaseResponse.setData(measureZoneVo);
        } catch (Exception e) {
            log.error("SearchZoneUnscopedByListIdLastIdUpdateAtGt" + "[" + apiMeasureZoneReqV2.getList_id() + "],error:" + e);
            throw new Exception("读取数据失败，code:zone");
        }
        return ljBaseResponse;
    }

    @Override
    public LjBaseResponse<TotalVo> getMeasureZoneV2Total(ApiMeasureZoneTotalReqV2 apiMeasureZoneTotalReqV2) throws Exception {
        LjBaseResponse<TotalVo> ljBaseResponse = new LjBaseResponse<>();
        MeasureList measureList = measureListService.getNoProjNoFoundErr(apiMeasureZoneTotalReqV2.getList_id().toString());
        TotalVo totalVo = new TotalVo();
        Integer total = measureZoneService.getCountZoneUnscopedByListIdUpdateAtGt(measureList.getProjectId(), apiMeasureZoneTotalReqV2.getList_id(), apiMeasureZoneTotalReqV2.getTimestamp());
        try {
            totalVo.setTotal(total);
            ljBaseResponse.setData(totalVo);
        } catch (Exception e) {
            log.error("GetCountUnscopedByProjIdUpdateAtGt" + "[" + apiMeasureZoneTotalReqV2.getList_id() + "],error:" + e);
            throw new Exception("读取数据失败，code:zone_total");
        }
        return ljBaseResponse;
    }

    @Override
    public LjBaseResponse<IssueVo> issue(ApiMeasureIssueReq apiMeasureIssueReq) throws Exception {
        LjBaseResponse<IssueVo> ljBaseResponse = new LjBaseResponse<>();
        IssueVo issueVo = new IssueVo();
        List<IssueListVo> issueListVos = new ArrayList<>();
        MeasureList measureList = measureListService.getNoProjNoFoundErr(apiMeasureIssueReq.getList_id().toString());
        //判断用户是否在MeasureList中, 暂缺
        Integer start = 0;
        Integer limit = MEASURE_API_GET_PER_TIME;
        List<MeasureListIssue> measureListIssueList = measureListIssueService.searchIssueListByListIdLastIdTimestampGt(measureList.getId(), apiMeasureIssueReq.getLast_id(), apiMeasureIssueReq.getTimestamp(), start, limit);
        measureListIssueList.forEach(measureListIssue -> {
            IssueListVo issueListVo = converMeasureListIssueToIssueListVo(measureListIssue);
            issueListVos.add(issueListVo);
        });
        try {
            issueVo.setIssue_list(issueListVos);
            IssueListVo issueListVo = issueListVos.get(issueListVos.size() - 1);
            issueVo.setLast_id(issueListVo.getId());
            ljBaseResponse.setData(issueVo);
        } catch (Exception e) {
            log.error("SearchIssueListByListIdLastIdTimestampGt +[" + apiMeasureIssueReq.getList_id() + "],error:" + e);
            throw new Exception("读取数据失败，code:issue_list");
        }
        return ljBaseResponse;
    }

    @Override
    public LjBaseResponse<IssueLogVo> issueLog(ApiMeasureIssueLogReq apiMeasureIssueLogReq) throws Exception {
        LjBaseResponse<IssueLogVo> ljBaseResponse = new LjBaseResponse<>();
        IssueLogVo issueLogVo = new IssueLogVo();
        List<IssueLogListVo> measureIssueLogVos = new ArrayList<>();
        MeasureList measureList = measureListService.getNoProjNoFoundErr(apiMeasureIssueLogReq.getList_id().toString());
        Integer start = 0;
        Integer pageSize = MEASURE_API_GET_PER_TIME;
        List<MeasureListIssueLog> measureListIssueLogs = measureListIssueLogService.searchIssueLogListByListIdLastIdTimestampGt(measureList.getProjectId(), apiMeasureIssueLogReq.getList_id(), apiMeasureIssueLogReq.getLast_id(), apiMeasureIssueLogReq.getTimestamp(), start, pageSize);
        //map转换vo
        measureListIssueLogs.forEach(measureListIssueLog -> {
            IssueLogListVo issueLogListVo = measureListIssueLogToIssueLogListVo(measureListIssueLog);
            measureIssueLogVos.add(issueLogListVo);
        });
        try {
            issueLogVo.setIssue_log_list(measureIssueLogVos);
            IssueLogListVo measureIssueLogVoEnt = measureIssueLogVos.get(measureIssueLogVos.size() - 1);
            issueLogVo.setLast_id(measureIssueLogVoEnt.getId());
            ljBaseResponse.setData(issueLogVo);
        } catch (Exception e) {
            log.error("SearchIssueListByListIdLastIdTimestampGt +[" + apiMeasureIssueLogReq.getList_id() + "],error:" + e);
            throw new Exception("读取数据失败，code:issue_log_list");
        }

        return ljBaseResponse;
    }

    @Override
    public LjBaseResponse<DroppedInfoVo> reportRegion(ApiMeasureReportRegionReq apiMeasureReportRegionReq, HttpServletRequest request) throws Exception {
        // 检查uuid，没有uuid也可以执行以下代码以保存请求内容
        LjBaseResponse<DroppedInfoVo> ljBaseResponse = new LjBaseResponse<>();
        DroppedInfoVo droppedInfoVo = new DroppedInfoVo();
        List<DroppedVo> droppedVos = new ArrayList<>();
        ReportStatusEnum reportUuidStatus = ReportStatusEnum.ERROR;
        //获取uid
        //todo 暂时获取不到uid
        //Integer uid = (Integer)request.getSession().getAttribute("uid");
        Integer uid = 8;
        try {
            keyProcedureTaskAppService.startReport(apiMeasureReportRegionReq.getReport_uuid(), uid, request);
        } catch (Exception e) {
            throw new Exception(e);
        } finally {
            keyProcedureTaskAppService.updateReportStatus(apiMeasureReportRegionReq.getReport_uuid(), reportUuidStatus.getValue().toString());
        }
        //解析json数据
        JSONArray jsonArray = JSON.parseArray(apiMeasureReportRegionReq.getData());
        List<ReportRegionDataVo> reportRegionDataVos = jsonArray.toJavaList(ReportRegionDataVo.class);
        List<MeasureRegionStructReq> measureRegionStructReqs = new ArrayList<>();
        List<Integer> areaIds = new ArrayList<>();
        for (ReportRegionDataVo reportRegionDataVo : reportRegionDataVos
        ) {
            String polygon = reportRegionDataVo.getPolygon();
            List<Float> polygons = new ArrayList<>();
            if (polygon.length() > 0) {
                String[] polygonStrings = polygon.split(",");
                for (int i = 0; i < polygonStrings.length; i++) {
                    String polygonStringtrim = polygonStrings[i].replaceAll("", "");//去掉所有空格
                    try {
                        float polygonFloat = Float.parseFloat(polygonStringtrim);//转换成单精度浮点格式
                        polygons.add(polygonFloat);
                    } catch (Exception e) {
                        throw new Exception(e);
                    }
                }
                if (polygonStrings.length != 2) {
                    log.error("polygons is not validated. polygons:[" + reportRegionDataVo.getPolygon() + "],error:");
                    throw new Exception("polygons is not validated.");
                }
            }
            MeasureRegionStructReq measureRegionStructReq = new MeasureRegionStructReq();
            measureRegionStructReq.setUuid(reportRegionDataVo.getUuid());
            measureRegionStructReq.setProject_id(reportRegionDataVo.getProject_id());
            measureRegionStructReq.setArea_id(reportRegionDataVo.getArea_id());
            measureRegionStructReq.setSrc_type(MeasureRegionSrcType.App.getId());
            measureRegionStructReq.setPolygon_x(polygons.get(0));
            measureRegionStructReq.setPolygon_y(polygons.get(1));
            measureRegionStructReqs.add(measureRegionStructReq);
            areaIds.add(reportRegionDataVo.getArea_id());
        }
        List<Area> areaList = areaService.getAreaByIds(areaIds);
        Map<Integer, Area> areaMap = new HashMap<>();
        areaList.forEach(area -> {
            areaMap.put(area.getId(), area);
        });
        //k-areaId value-Region
        Map<Integer, List<MeasureRegionStructReq>> measureRegionStructReqMap = new HashMap<>();
        measureRegionStructReqs.forEach(measureRegionStructReq -> {
            measureRegionStructReqMap.get(measureRegionStructReq.getArea_id()).add(measureRegionStructReq);
        });
        Set<Map.Entry<Integer, List<MeasureRegionStructReq>>> entrySet = measureRegionStructReqMap.entrySet();
        List<MeasureRegionStructReq> measureRegionStructReqList = new ArrayList<>();
        entrySet.forEach(areaId -> {
            Area area = areaMap.get(areaId);
            if (area == null) {
                log.error("not fount area.");
                List<MeasureRegionStructReq> measureRegionStructReqList1 = areaId.getValue();
                measureRegionStructReqList1.forEach(measureRegionStructReq -> {
                    DroppedVo droppedVo = new DroppedVo();
                    droppedVo.setUuid(measureRegionStructReq.getUuid());
                    droppedVo.setReason_type(Integer.parseInt(ApiDropDataReasonEnum.NotFound.getValue()));
                    droppedVo.setReason("未找到相关的区域id。");
                    droppedVos.add(droppedVo);
                });
            }
            List<MeasureRegionStructReq> measureRegionStructReqs1 = measureRegionStructReqMap.get(areaId);
            measureRegionStructReqs1.forEach(measureRegionStructReq -> {
                measureRegionStructReq.setArea_path_and_id(area.getPath() + areaId + "/");
            });
            List<MeasureRegion> measureRegions = converMeasureRegionStructReqToMeasureRegion(measureRegionStructReqList);
            try {
                List<MeasureRegion> measureRegionList = measureRegionService.createRegionsFromRegionStructList(area.getProjectId(), measureRegions);
            } catch (Exception e) {
                log.error("error:" + e);
                measureRegionStructReqList.addAll(measureRegionStructReqMap.get(areaId));
            }
        });
        measureRegionStructReqList.forEach(measureRegionStructReq -> {
            DroppedVo droppedVo = new DroppedVo();
            droppedVo.setUuid(measureRegionStructReq.getUuid());
            droppedVo.setReason_type(Integer.parseInt(ApiDropDataReasonEnum.OTHER.getValue()));
            droppedVo.setReason("添加失败。");
            droppedVos.add(droppedVo);
        });
        droppedInfoVo.setDropped(droppedVos);
        ljBaseResponse.setData(droppedInfoVo);
        return ljBaseResponse;
    }

    @Override
    public LjBaseResponse<DroppedInfoVo> reportZone(ApiMeasureReportZoneReq req, HttpServletRequest request) throws Exception {
        ReportStatusEnum reportUuidStatus = ReportStatusEnum.ERROR;
        LjBaseResponse<DroppedInfoVo> ljBaseResponse = new LjBaseResponse<>();
        DroppedInfoVo droppedInfoVo = new DroppedInfoVo();
        //获取uid
        //todo 暂时获取不到uid
        //Integer uid = (Integer)request.getSession().getAttribute("uid");
        Integer uid = 8;
        try {
            keyProcedureTaskAppService.startReport(req.getReport_uuid(), uid, request);
        } catch (Exception e) {
            throw new Exception(e);
        } finally {
            keyProcedureTaskAppService.updateReportStatus(req.getReport_uuid(), reportUuidStatus.getValue().toString());
        }
        List<DroppedVo> droppedVos = new ArrayList<>();
        //解析json数据
        JSONArray jsonArray = JSON.parseArray(req.getData());
        List<ReportZoneVo> reportZoneVoJsonList = jsonArray.toJavaList(ReportZoneVo.class);
        reportZoneVoJsonList.forEach(reportZoneVo -> {
            MeasureRegion measureRegion = null;
            try {
                measureRegion = measureRegionService.searchByUuid(reportZoneVo.getProject_id(), reportZoneVo.getUuid());
                if (measureRegion == null) {
                    log.error("not found region by uuid.");
                }
            } catch (Exception e) {
                DroppedVo droppedVo = new DroppedVo();
                droppedVo.setUuid(reportZoneVo.getUuid());
                droppedVo.setReason_type(Integer.parseInt(ApiDropDataReasonConstant.MEASUREREGIONNOTFOUND));
                droppedVo.setReason(ApiDropDataReasonConstant.MEASUREREGIONNOTFOUND);
                droppedVos.add(droppedVo);
            }
            CategoryV3 category = null;
            try {
                category = checkItemService.getCategoryByKeyNoFoundErr(reportZoneVo.getCategory_key());
            } catch (Exception e) {
                log.error("error:" + e);
                DroppedVo droppedVo = new DroppedVo();
                droppedVo.setUuid(reportZoneVo.getUuid());
                droppedVo.setReason_type(Integer.parseInt(ApiDropDataReasonConstant.CATEGORYNOTFOUND));
                droppedVo.setReason(ApiDropDataReasonConstant.CATEGORYNOTFOUND);
                droppedVos.add(droppedVo);
            }
            try {
                List<MeasureZone> measureZones = measureListService.searchZoneByMeasureListIdRegionUuidCategoryKey(reportZoneVo.getProject_id(), reportZoneVo.getList_id(), reportZoneVo.getUuid(), reportZoneVo.getCategory_key());
                if (measureZones.size() > 0) {
                    MeasureZone measureZone = measureListService.getZoneByUuid(reportZoneVo.getProject_id(), reportZoneVo.getUuid());
                    if (measureZone == null) {
                        DroppedVo droppedVo = new DroppedVo();
                        droppedVo.setUuid(reportZoneVo.getUuid());
                        droppedVo.setReason_type(Integer.parseInt(ApiDropDataReasonConstant.MEASUREZONEUUIDEXISTS));
                        droppedVo.setReason(ApiDropDataReasonConstant.MEASUREZONEUUIDEXISTS);
                        droppedVos.add(droppedVo);
                    }
                    DroppedVo droppedVo = new DroppedVo();
                    droppedVo.setUuid(reportZoneVo.getUuid());
                    droppedVo.setReason_type(Integer.parseInt(ApiDropDataReasonConstant.MEASUREZONEEXISTS));
                    droppedVo.setReason(ApiDropDataReasonConstant.MEASUREZONEEXISTS);
                    droppedVos.add(droppedVo);
                }
            } catch (Exception e) {
                log.error("error:" + e + "reportZoneVo.uuid" + reportZoneVo.getUuid());
            }
            try {
                measureListService.createZoneFromApp(reportZoneVo.getProject_id(), reportZoneVo.getList_id(), reportZoneVo.getUuid(), measureRegion.getUuid(), measureRegion.getAreaId(), measureRegion.getAreaPathAndId(), reportZoneVo.getCategory_key(), category.getPath() + category.getKey() + "/", MeasureListFinishStatusEnum.UnFinish.getId(), MeasureListCloseStatusEnum.UnClose.getId());
            } catch (Exception e) {
                log.error("create zone fail, error:" + e);
                DroppedVo droppedVo = new DroppedVo();
                droppedVo.setUuid(reportZoneVo.getUuid());
                String measureregionnotfound = ApiDropDataReasonConstant.MEASUREREGIONNOTFOUND;
                JSONObject jsonObject = JSON.parseObject(measureregionnotfound);
                int value = jsonObject.getIntValue("value");
                String name = jsonObject.getString("name");
                droppedVo.setReason_type(value);
                droppedVo.setReason(name);
                droppedVos.add(droppedVo);
            }
        });
        droppedInfoVo.setDropped(droppedVos);
        ljBaseResponse.setData(droppedInfoVo);
        return ljBaseResponse;
    }

    /**
     * @param measureRule
     * @return
     */
    private RuleInfoVo converMeasureRuleToRuleInfoVo(MeasureRule measureRule) {
        RuleInfoVo ruleInfoVo = new RuleInfoVo();
        ruleInfoVo.setCategory_key(measureRule.getCategoryKey());
        ruleInfoVo.setDelete_at(measureRule.getDeleteAt() == null ? 0 : measureRule.getDeleteAt().getTime());
        ruleInfoVo.setDesc(measureRule.getDesc());
        ruleInfoVo.setGroup_count_init(measureRule.getGroupCountInit());
        ruleInfoVo.setGroup_count_max(measureRule.getGroupCountMax());
        ruleInfoVo.setGroup_count_min(measureRule.getGroupCountMin());
        ruleInfoVo.setId(measureRule.getId());
        ruleInfoVo.setRule_type(measureRule.getRuleType());
        ruleInfoVo.setTeam_id(measureRule.getTeamId());
        ruleInfoVo.setTextures(measureRule.getTextures());
        ruleInfoVo.setUpdate_at(measureRule.getUpdateAt() == null ? 0 : measureRule.getUpdateAt().getTime());
        ruleInfoVo.setFormula(measureRule.getFormula());
        ruleInfoVo.setPoints(stringtoPointVo(measureRule.getPoints()));
        return ruleInfoVo;
    }

    /**
     * @param point
     * @return
     */
    private List<PointVo> stringtoPointVo(String point) {
        List<PointVo> pointVos = JsonUtil.GsonToList(point, PointVo.class);
        return pointVos;
    }

    /**
     * @param measureList
     * @return
     */
    private TaskInfoVo convermeasureListToTaskInfoVo(MeasureList measureList) {
        TaskInfoVo taskInfoVo = new TaskInfoVo();
        taskInfoVo.setId(measureList.getId());
        taskInfoVo.setProject_id(measureList.getProjectId());
        taskInfoVo.setName(measureList.getName());
        taskInfoVo.setFinish_status(measureList.getFinishStatus());
        taskInfoVo.setClose_status(measureList.getCloseStatus());
        taskInfoVo.setPlan_begin_on(DateUtil.dateToTimestamp(measureList.getPlanBeginOn()));
        taskInfoVo.setPlan_end_on(DateUtil.dateToTimestamp(measureList.getPlanEndOn()));
        taskInfoVo.setArea_ids(measureList.getAreaType());
        taskInfoVo.setRoot_category_key(measureList.getRootCategoryKey());
        taskInfoVo.setUpdate_at(DateUtil.dateToTimestamp(measureList.getUpdateAt()));
        taskInfoVo.setDelete_at(DateUtil.dateToTimestamp(measureList.getUpdateAt()));
        return taskInfoVo;
    }

    private ZoneInfoVo converMeasureZoneToZoneInfoVo(MeasureZone measureZone) {
        ZoneInfoVo zoneInfoVo = new ZoneInfoVo();
        zoneInfoVo.setId(measureZone.getId());
        zoneInfoVo.setUuid(measureZone.getUuid());
        zoneInfoVo.setProject_id(measureZone.getProjectId());
        zoneInfoVo.setList_id(measureZone.getListId());
        zoneInfoVo.setArea_id(measureZone.getAreaId());
        zoneInfoVo.setRegion_uuid(measureZone.getRegionUuid());
        zoneInfoVo.setCategory_key(measureZone.getCategoryKey());
        zoneInfoVo.setCategory_path_and_key(measureZone.getCategoryPathAndKey());
        zoneInfoVo.setSrc_type(measureZone.getSrcType());
        zoneInfoVo.setFinish_status(measureZone.getFinishStatus());
        zoneInfoVo.setClose_status(measureZone.getCloseStatus());
        zoneInfoVo.setUpdate_at(measureZone.getUpdateAt() == null ? 0 : DateUtil.dateToTimestamp(measureZone.getUpdateAt()));
        zoneInfoVo.setDelete_at(measureZone.getDeleteAt() == null ? 0 : DateUtil.dateToTimestamp(measureZone.getDeleteAt()));
        zoneInfoVo.setArea_path_and_id(measureZone.getAreaPathAndId());
        return zoneInfoVo;
    }

    private IssueListVo converMeasureListIssueToIssueListVo(MeasureListIssue measureListIssue) {
        IssueListVo issueListVo = new IssueListVo();
        issueListVo.setId(measureListIssue.getId());
        issueListVo.setUuid(measureListIssue.getUuid());
        issueListVo.setProject_id(measureListIssue.getProjectId());
        issueListVo.setCategory_key(measureListIssue.getCategoryKey());
        issueListVo.setCategory_path_and_key(measureListIssue.getCategoryPathAndKey());
        issueListVo.setList_id(measureListIssue.getListId());
        issueListVo.setRegion_uuid(measureListIssue.getRegionUuid());
        issueListVo.setArea_id(measureListIssue.getAreaId());
        issueListVo.setArea_path_and_id(measureListIssue.getAreaPathAndId());
        issueListVo.setUpdate_at(measureListIssue.getUpdateAt() == null ? 0 : DateUtil.dateToTimestamp(measureListIssue.getUpdateAt()));
        issueListVo.setDelete_at(measureListIssue.getDeleteAt() == null ? 0 : DateUtil.dateToTimestamp(measureListIssue.getDeleteAt()));
        issueListVo.setZone_uuid(measureListIssue.getZoneUuid());
        issueListVo.setSquad_id(measureListIssue.getSquadId());
        issueListVo.setDrawing_md5(measureListIssue.getDrawingMd5());
        issueListVo.setSender_id(measureListIssue.getSenderId());
        issueListVo.setRepairer_id(measureListIssue.getRepairerId());
        issueListVo.setTyp(measureListIssue.getTyp());
        issueListVo.setStatus(measureListIssue.getStatus());
        issueListVo.setPlan_end_on(measureListIssue.getPlanEndOn());
        issueListVo.setEnd_on(measureListIssue.getEndOn());
        issueListVo.setPos_x(measureListIssue.getPosX());
        issueListVo.setPos_y(measureListIssue.getPosY());
        issueListVo.setDesc(measureListIssue.getDesc());
        issueListVo.setCondition(measureListIssue.getCondition());
        issueListVo.setAttachment_md5_list(measureListIssue.getAttachmentMd5List());
        issueListVo.setLast_assigner(measureListIssue.getLastAssigner());
        issueListVo.setLast_assigner_at(measureListIssue.getLastAssignerAt());
        issueListVo.setDestroy_at(measureListIssue.getDestroyAt());
        issueListVo.setDestroy_user(measureListIssue.getDestroyUser());
        issueListVo.setDelete_user(measureListIssue.getDestroyUser());
        issueListVo.setDelete_time(measureListIssue.getDeleteAt() == null ? 0 : DateUtil.dateToTimestamp(measureListIssue.getDeleteAt()));
        issueListVo.setClose_user(measureListIssue.getCloseUser());
        issueListVo.setClose_time(measureListIssue.getCloseTime());
        issueListVo.setClose_status(measureListIssue.getCloseStatus());
        issueListVo.setClient_create_at(measureListIssue.getClientCreateAt() == null ? 0 : DateUtil.dateToTimestamp(measureListIssue.getClientCreateAt()));
        return issueListVo;
    }

    private IssueLogListVo measureListIssueLogToIssueLogListVo(MeasureListIssueLog measureListIssueLog) {
        IssueLogListVo issueLogListVo = new IssueLogListVo();
        issueLogListVo.setId(measureListIssueLog.getId());
        issueLogListVo.setUuid(measureListIssueLog.getUuid());
        issueLogListVo.setProject_id(measureListIssueLog.getProjectId());
        issueLogListVo.setCategory_key(measureListIssueLog.getCategoryKey());
        issueLogListVo.setAttachment_md5_list(measureListIssueLog.getAttachmentMd5List());
        issueLogListVo.setDesc(measureListIssueLog.getDesc());
        issueLogListVo.setIssue_uuid(measureListIssueLog.getIssueUuid());
        issueLogListVo.setList_id(measureListIssueLog.getListId());
        issueLogListVo.setSender_id(measureListIssueLog.getSenderId());
        issueLogListVo.setStatus(measureListIssueLog.getStatus());
        issueLogListVo.setTyp(measureListIssueLog.getTyp());
        issueLogListVo.setClient_create_at(measureListIssueLog.getClientCreateAt() == null ? 0 : DateUtil.dateToTimestamp(measureListIssueLog.getClientCreateAt()));
        issueLogListVo.setCreate_at(measureListIssueLog.getCreateAt() == null ? 0 : DateUtil.dateToTimestamp(measureListIssueLog.getCreateAt()));
        issueLogListVo.setUpdate_at(measureListIssueLog.getUpdateAt() == null ? 0 : DateUtil.dateToTimestamp(measureListIssueLog.getUpdateAt()));
        issueLogListVo.setDelete_at(measureListIssueLog.getDeleteAt() == null ? 0 : DateUtil.dateToTimestamp(measureListIssueLog.getDeleteAt()));
        return issueLogListVo;
    }

    private List<MeasureRegion> converMeasureRegionStructReqToMeasureRegion(List<MeasureRegionStructReq> measureRegionStructReqs) {
        List<MeasureRegion> measureRegions = new ArrayList<>();
        measureRegionStructReqs.forEach(measureRegionStructReq -> {
            MeasureRegion measureRegion = new MeasureRegion();
            measureRegion.setUuid(measureRegionStructReq.getUuid());
            measureRegion.setProjectId(measureRegionStructReq.getProject_id());
            measureRegion.setRegionIndex(measureRegionStructReq.getRegion_index());
            measureRegion.setAreaId(measureRegionStructReq.getArea_id());
            measureRegion.setSrcType(measureRegionStructReq.getSrc_type());
            measureRegion.setAreaPathAndId(measureRegionStructReq.getArea_path_and_id());
            measureRegion.setDrawingMd5(measureRegionStructReq.getDrawing_md5());
            Map<String, Object> map = new HashMap<>();
            map.put("X", measureRegionStructReq.getPolygon_x());
            map.put("Y", measureRegionStructReq.getPolygon_y());
            String jsonString = JSON.toJSONString(map);
            measureRegion.setPolygon(jsonString);
            measureRegions.add(measureRegion);
        });
        return measureRegions;
    }
}
