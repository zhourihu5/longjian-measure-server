package com.longfor.longjian.measure.app.appService.appMeasureSyncService.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.google.gson.JsonObject;
import com.longfor.longjian.common.base.LjBaseResponse;
import com.longfor.longjian.measure.app.appService.appMeasureSyncService.IAPPMeasureSyncService;
import com.longfor.longjian.measure.app.appService.appService.IKeyProcedureTaskAppService;
import com.longfor.longjian.measure.app.req.apiMeasureRuleReq.*;
import com.longfor.longjian.measure.app.vo.appMeasureSyncVo.*;
import com.longfor.longjian.measure.consts.Enum.ReportStatusEnum;
import com.longfor.longjian.measure.consts.util.ConvertUtil;
import com.longfor.longjian.measure.consts.util.JsonUtil;
import com.longfor.longjian.measure.domain.externalService.*;
import com.longfor.longjian.measure.po.zhijian2.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
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
        String[] projectIds = apiMyTaskReq.getProject_id().split(",");
        if (projectIds.length == 0) {
            throw new Exception("project id is empty.");
        }
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
        String[] listIds = apiMeasureZoneReq.getList_ids().split(",");
        if (listIds.length == 0) {
            throw new Exception("list is empty.");
        }
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
                List<MeasureZone> measureZones= measureZoneService.searchZoneUnscopedByListIdLastIdUpdateAtGt(measureList.getProjectId(), listId, lastId, timestamp, limit, start);
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
        lastId = measureList.getId();
        Integer start = 0;
        Integer limit = MEASURE_API_GET_PER_TIME;
        List<MeasureZone> measureZones = measureZoneService.searchZoneUnscopedByListIdLastIdUpdateAtGt2(measureList.getProjectId(), apiMeasureZoneReqV2.getList_id(), apiMeasureZoneReqV2.getLast_id(), apiMeasureZoneReqV2.getTimestamp(), start, limit);
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
        List<ReportRegionDataVo> reportRegionDataVos = JsonUtil.GsonToList(apiMeasureReportRegionReq.getData(), ReportRegionDataVo.class);
        List<MeasureRegion> measureRegions = new ArrayList<>();
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
            MeasureRegion measureRegion = new MeasureRegion();
            measureRegion.setUuid(reportRegionDataVo.getUuid());
            measureRegion.setProjectId(reportRegionDataVo.getProject_id());
            measureRegion.setAreaId(reportRegionDataVo.getArea_id());
            //measureRegion.setSrcType();
            measureRegions.add(measureRegion);
        }
        return null;
    }

    @Override
    public LjBaseResponse<DroppedInfoVo> reportZone(ApiMeasureReportZoneReq apiMeasureReportZoneReq) {
        return null;
    }

    /**
     *
     * @param measureRule
     * @return
     */
    private RuleInfoVo converMeasureRuleToRuleInfoVo(MeasureRule measureRule){
        RuleInfoVo ruleInfoVo = new RuleInfoVo();
        ruleInfoVo.setCategory_key(measureRule.getCategoryKey());
        ruleInfoVo.setDelete_at(measureRule.getDeleteAt() ==  null ? 0 : measureRule.getDeleteAt().getTime());
        ruleInfoVo.setDesc(measureRule.getDesc());
        ruleInfoVo.setGroup_count_init(measureRule.getGroupCountInit());
        ruleInfoVo.setGroup_count_max(measureRule.getGroupCountMax());
        ruleInfoVo.setGroup_count_min(measureRule.getGroupCountMin());
        ruleInfoVo.setId(measureRule.getId());
        ruleInfoVo.setRule_type(measureRule.getRuleType());
        ruleInfoVo.setTeam_id(measureRule.getTeamId());
        ruleInfoVo.setTextures(measureRule.getTextures());
        ruleInfoVo.setUpdate_at(measureRule.getUpdateAt() ==  null ? 0 : measureRule.getUpdateAt().getTime());
        ruleInfoVo.setFormula(measureRule.getFormula());
        ruleInfoVo.setPoints(stringtoPointVo(measureRule.getPoints()));
        return  ruleInfoVo;
    }

    /**
     *
     * @param point
     * @return
     */
    private List<PointVo> stringtoPointVo(String point){
        List<PointVo> pointVos = JsonUtil.GsonToList(point, PointVo.class);
        return pointVos;
    }

    /**
     *
     * @param measureList
     * @return
     */
    private TaskInfoVo convermeasureListToTaskInfoVo(MeasureList measureList){
        TaskInfoVo taskInfoVo = new TaskInfoVo();
        taskInfoVo.setId(measureList.getId());
        taskInfoVo.setProject_id(measureList.getProjectId());
        taskInfoVo.setName(measureList.getName());
        taskInfoVo.setFinish_status(measureList.getFinishStatus());
        taskInfoVo.setClose_status(measureList.getCloseStatus());
        taskInfoVo.setPlan_begin_on((int) measureList.getPlanBeginOn().getTime());
        taskInfoVo.setPlan_end_on((int)measureList.getPlanEndOn().getTime());
        taskInfoVo.setArea_ids(measureList.getAreaType());
        taskInfoVo.setRoot_category_key(measureList.getRootCategoryKey());
        taskInfoVo.setUpdate_at((int)measureList.getUpdateAt().getTime());
        taskInfoVo.setDelete_at((int)measureList.getUpdateAt().getTime());
        return  taskInfoVo;
    }
    private ZoneInfoVo converMeasureZoneToZoneInfoVo(MeasureZone measureZone){
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
        zoneInfoVo.setUpdate_at((int)(measureZone.getUpdateAt()==  null ? 0 : measureZone.getUpdateAt().getTime()));
        zoneInfoVo.setDelete_at((int)(measureZone.getDeleteAt() ==  null ? 0 : measureZone.getDeleteAt().getTime()));
        zoneInfoVo.setArea_path_and_id(measureZone.getAreaPathAndId());
        return zoneInfoVo;
    }
    private IssueListVo converMeasureListIssueToIssueListVo(MeasureListIssue measureListIssue){
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
        issueListVo.setUpdate_at((int)(measureListIssue.getUpdateAt()==null ? 0 :measureListIssue.getUpdateAt().getTime()));
        issueListVo.setDelete_at((int)(measureListIssue.getDeleteAt()==null ? 0 :measureListIssue.getDeleteAt().getTime()));
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
        issueListVo.setDelete_time((int)(measureListIssue.getDeleteAt() == null ? 0 : measureListIssue.getDeleteAt().getTime()));
        issueListVo.setClose_user(measureListIssue.getCloseUser());
        issueListVo.setClose_time(measureListIssue.getCloseTime());
        issueListVo.setClient_create_at((int)(measureListIssue.getClientCreateAt()==null ?0:measureListIssue.getClientCreateAt().getTime()));
        issueListVo.setCreate_at((int)(measureListIssue.getCreateAt()==null ? 0 :measureListIssue.getCreateAt().getTime()));
        return  issueListVo;
    }
    private IssueLogListVo measureListIssueLogToIssueLogListVo(MeasureListIssueLog measureListIssueLog){
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
        issueLogListVo.setClient_create_at((int)(measureListIssueLog.getClientCreateAt()==null ? 0 : measureListIssueLog.getClientCreateAt().getTime()));
        issueLogListVo.setCreate_at((int)(measureListIssueLog.getCreateAt()==null ? 0 : measureListIssueLog.getCreateAt().getTime()));
        issueLogListVo.setUpdate_at((int)(measureListIssueLog.getUpdateAt()==null ? 0 :measureListIssueLog.getUpdateAt().getTime()));
        issueLogListVo.setDelete_at((int)(measureListIssueLog.getDeleteAt()==null ? 0 : measureListIssueLog.getDeleteAt().getTime()));
        return issueLogListVo;
    }
}
