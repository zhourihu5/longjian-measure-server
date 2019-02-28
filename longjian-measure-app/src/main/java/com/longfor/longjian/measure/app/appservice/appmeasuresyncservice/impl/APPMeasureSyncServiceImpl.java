package com.longfor.longjian.measure.app.appservice.appmeasuresyncservice.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.longfor.longjian.common.base.LjBaseResponse;
import com.longfor.longjian.common.exception.LjBaseRuntimeException;
import com.longfor.longjian.common.util.DateUtil;
import com.longfor.longjian.common.util.SessionInfo;
import com.longfor.longjian.measure.app.appservice.appmeasuresyncservice.IAPPMeasureSyncService;
import com.longfor.longjian.measure.app.appservice.appservice.IKeyProcedureTaskAppService;
import com.longfor.longjian.measure.app.req.apimeasurerulereq.*;
import com.longfor.longjian.measure.app.vo.appmeasuresyncvo.*;
import com.longfor.longjian.measure.consts.enums.*;
import com.longfor.longjian.measure.domain.externalservice.*;
import com.longfor.longjian.measure.po.zhijian2.*;
import com.longfor.longjian.measure.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
    @Resource
    private SessionInfo sessionInfo;
    @Resource
    private IMeasureListAreaService measureListAreaService;

    private static final String USERID = "userId";
    private static final String ERROR = "],error:";
    private static final String ERRORLOG = "error:";
    @Override
    public LjBaseResponse<RuleListVo> getMeasureRule(ApiMeasureRuleReq apiMeasureRuleReq) throws LjBaseRuntimeException {
        LjBaseResponse<RuleListVo> ljBaseResponse = new LjBaseResponse<>();
        List<RuleInfoVo> ruleInfoVos = new ArrayList<>();
        RuleListVo ruleListVo = new RuleListVo();
        //切割categoryKeys
        if (apiMeasureRuleReq.getCategory_keys().length() == 0) {
            throw new LjBaseRuntimeException(-9999,"category keys is empty.");
        }
        String[] categoryKeys = apiMeasureRuleReq.getCategory_keys().split(",");
        //数组转换List
        List<String> categoryKeysList = Arrays.asList(categoryKeys);
        //查询categoryKeys
        List<CategoryV3> categoryList = categoryV3Service.getCategoryByKeys(categoryKeysList);
        //updateAtDate不知道参数什么意思  默认时间0001-01-01 00:00:00  +0000  UTC
        String stringTime = "0001-01-01 00:00:00";
        /*SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dateString = formatter.parse(stringTime);*/
        Map<Integer, Integer> mTeamId = Maps.newHashMap();
        for (CategoryV3 category : categoryList
        ) {
            mTeamId.put(category.getTeamId(), category.getTeamId());
        }
        mTeamId.forEach((k, v) -> {
            List<MeasureRule> measureRuleList = measureRuleService.searchUnscopedByTeamIdUpdateAtGt(v, stringTime);
            for (MeasureRule measureRule : measureRuleList
            ) {
                RuleInfoVo ruleInfoVo = converMeasureRuleToRuleInfoVo(measureRule);
                ruleInfoVos.add(ruleInfoVo);
            }
        });
        ruleListVo.setRule_list(ruleInfoVos);
        ljBaseResponse.setData(ruleListVo);
        return ljBaseResponse;
    }

    @Override
    public LjBaseResponse<MyTaskVo> getMyTask(ApiMyTaskReq apiMyTaskReq, HttpServletRequest request) throws LjBaseRuntimeException {
        LjBaseResponse<MyTaskVo> ljBaseResponse = new LjBaseResponse<>();
        MyTaskVo myTaskVo = new MyTaskVo();
        List<TaskInfoVo> taskInfoVos = new ArrayList<>();
        //TaskInfoVo
        if (apiMyTaskReq.getProject_id().length() == 0) {
            throw new LjBaseRuntimeException(-9999,"project id is empty.");
        }
        String[] projectIds = apiMyTaskReq.getProject_id().split(",");
        Integer userId = null;
        try {
            userId = (Integer) sessionInfo.getBaseInfo(USERID);
        } catch (Exception e) {
            throw new LjBaseRuntimeException(-9999, e.getMessage());
        }
        for (String projectId : projectIds
        ) {
            List<MeasureList> measureLists = measureListService.searchListByProjIdUserId(projectId, userId);
            measureLists.forEach(measureList -> {
                List<MeasureListArea> areas = measureListAreaService.searchByListId(projectId, measureList.getId());
                TaskInfoVo taskInfoVo = convermeasureListToTaskInfoVo(measureList, areas);
                taskInfoVos.add(taskInfoVo);
            });
        }
        myTaskVo.setMeasure_list(taskInfoVos);
        ljBaseResponse.setData(myTaskVo);
        return ljBaseResponse;
    }

    @Override
    public LjBaseResponse<MeasureZoneVo> getMeasureZone(ApiMeasureZoneReq apiMeasureZoneReq) throws LjBaseRuntimeException {
        LjBaseResponse<MeasureZoneVo> ljBaseResponse = new LjBaseResponse<>();
        MeasureZoneVo measureZoneVo = new MeasureZoneVo();
        List<ZoneInfoVo> zoneInfoVos = new ArrayList<>();
        if (apiMeasureZoneReq.getList_ids().length() == 0) {
            throw new LjBaseRuntimeException(-9999,"list is empty.");
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
                throw new LjBaseRuntimeException(-9999,e + "");
            }
        }
        measureZoneVo.setZone_list(zoneInfoVos);
        measureZoneVo.setLast_id(lastId);
        ljBaseResponse.setData(measureZoneVo);
        return ljBaseResponse;
    }

    @Override
    public LjBaseResponse<MeasureZoneVo> getMeasureZoneV2(ApiMeasureZoneReqV2 apiMeasureZoneReqV2) throws LjBaseRuntimeException {
        LjBaseResponse<MeasureZoneVo> ljBaseResponse = new LjBaseResponse<>();
        MeasureZoneVo measureZoneVo = new MeasureZoneVo();
        List<ZoneInfoVo> zoneInfoVos = new ArrayList<>();
        MeasureList measureList = measureListService.getNoProjNoFoundErr(apiMeasureZoneReqV2.getList_id().toString());
        Integer lastId = 0;
        Integer start = 0;
        Integer limit = MEASURE_API_GET_PER_TIME;
        List<MeasureZone> measureZones = measureZoneService.searchZoneUnscopedByListIdLastIdUpdateAtGt2(measureList.getProjectId(), apiMeasureZoneReqV2.getList_id(), apiMeasureZoneReqV2.getLast_id(), apiMeasureZoneReqV2.getTimestamp(), start, limit);
        if ((measureZones.size()) > 0) {
            lastId = measureZones.get(measureZones.size() - 1).getId();
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
            log.error("SearchZoneUnscopedByListIdLastIdUpdateAtGt" + "[" + apiMeasureZoneReqV2.getList_id() + ERROR + e);
            throw new LjBaseRuntimeException(-9999,"读取数据失败，code:zone");
        }
        return ljBaseResponse;
    }

    @Override
    public LjBaseResponse<TotalVo> getMeasureZoneV2Total(ApiMeasureZoneTotalReqV2 apiMeasureZoneTotalReqV2) throws LjBaseRuntimeException {
        LjBaseResponse<TotalVo> ljBaseResponse = new LjBaseResponse<>();
        MeasureList measureList = measureListService.getNoProjNoFoundErr(apiMeasureZoneTotalReqV2.getList_id().toString());
        TotalVo totalVo = new TotalVo();
        Integer total = measureZoneService.getCountZoneUnscopedByListIdUpdateAtGt(measureList.getProjectId(), apiMeasureZoneTotalReqV2.getList_id(), apiMeasureZoneTotalReqV2.getTimestamp());
        try {
            totalVo.setTotal(total);
            ljBaseResponse.setData(totalVo);
        } catch (Exception e) {
            log.error("GetCountUnscopedByProjIdUpdateAtGt" + "[" + apiMeasureZoneTotalReqV2.getList_id() + ERROR + e);
            throw new LjBaseRuntimeException(-9999,"读取数据失败，code:zone_total");
        }
        return ljBaseResponse;
    }

    @Override
    public LjBaseResponse<IssueVo> issue(ApiMeasureIssueReq apiMeasureIssueReq) throws LjBaseRuntimeException {
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
            if (issueListVos.size() > 0) {
                IssueListVo issueListVo = issueListVos.get(issueListVos.size() - 1);
                issueVo.setLast_id(issueListVo.getId());
            }
            ljBaseResponse.setData(issueVo);
        } catch (Exception e) {
            log.error("SearchIssueListByListIdLastIdTimestampGt +[" + apiMeasureIssueReq.getList_id() + ERROR + e);
            throw new LjBaseRuntimeException(-9999,"读取数据失败，code:issue_list");
        }
        return ljBaseResponse;
    }

    @Override
    public LjBaseResponse<IssueLogVo> issueLog(ApiMeasureIssueLogReq apiMeasureIssueLogReq) throws LjBaseRuntimeException {
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
            if (measureIssueLogVos.size() > 0) {
                IssueLogListVo measureIssueLogVoEnt = measureIssueLogVos.get(measureIssueLogVos.size() - 1);
                issueLogVo.setLast_id(measureIssueLogVoEnt.getId());
            }
            ljBaseResponse.setData(issueLogVo);
        } catch (Exception e) {
            log.error("SearchIssueListByListIdLastIdTimestampGt +[" + apiMeasureIssueLogReq.getList_id() +ERROR + e);
            throw new LjBaseRuntimeException(-9999,"读取数据失败，code:issue_log_list");
        }

        return ljBaseResponse;
    }

    @Override
    public LjBaseResponse<DroppedInfoVo> reportRegion(ApiMeasureReportRegionReq apiMeasureReportRegionReq, HttpServletRequest request) throws LjBaseRuntimeException {
        // 检查uuid，没有uuid也可以执行以下代码以保存请求内容
        LjBaseResponse<DroppedInfoVo> ljBaseResponse = new LjBaseResponse<>();
        DroppedInfoVo droppedInfoVo = new DroppedInfoVo();
        List<DroppedVo> droppedVos = new ArrayList<>();
        ReportStatusEnum reportUuidStatus = ReportStatusEnum.ERROR;
        //获取uid
        Integer uid = null;
        try {
            uid = (Integer) sessionInfo.getBaseInfo(USERID);
        } catch (Exception e) {
            throw new LjBaseRuntimeException(-9999, e.getMessage());
        }
        try {
            keyProcedureTaskAppService.startReport(apiMeasureReportRegionReq.getReport_uuid(), uid, request);
        } catch (Exception e) {
            throw new LjBaseRuntimeException(-9999,e+"");
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
                        throw new LjBaseRuntimeException(-9999,e+"");
                    }
                }
                if (polygonStrings.length != 2) {
                    log.error("polygons is not validated. polygons:[" + reportRegionDataVo.getPolygon() + ERROR);
                    throw new LjBaseRuntimeException(-9999,"polygons is not validated.");
                }
            }
            MeasureRegionStructReq measureRegionStructReq = new MeasureRegionStructReq();
            measureRegionStructReq.setUuid(reportRegionDataVo.getUuid());
            measureRegionStructReq.setProject_id(reportRegionDataVo.getProject_id());
            measureRegionStructReq.setArea_id(reportRegionDataVo.getArea_id());
            measureRegionStructReq.setDrawing_md5(reportRegionDataVo.getDrawing_md5());
            measureRegionStructReq.setSrc_type(MeasureRegionSrcType.APP.getId());
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
        List<MeasureRegionStructReq> measureRegionStructReqs2 = null;
        for (MeasureRegionStructReq measureRegionStructReq : measureRegionStructReqs) {
            if (measureRegionStructReqMap.get(measureRegionStructReq.getArea_id()) == null) {
                measureRegionStructReqs2 = new ArrayList<>();
                measureRegionStructReqMap.put(measureRegionStructReq.getArea_id(), measureRegionStructReqs2);
            }
            measureRegionStructReqMap.get(measureRegionStructReq.getArea_id()).add(measureRegionStructReq);
        }
        ;
        Set<Map.Entry<Integer, List<MeasureRegionStructReq>>> entrySet = measureRegionStructReqMap.entrySet();
        List<MeasureRegionStructReq> measureRegionStructReqList = new ArrayList<>();
        for (Map.Entry<Integer, List<MeasureRegionStructReq>> areaId : entrySet) {

            Area area = areaMap.get(areaId.getKey());
            if (area == null) {
                log.error("not fount area.");
                List<MeasureRegionStructReq> measureRegionStructReqList1 = areaId.getValue();
                measureRegionStructReqList1.forEach(measureRegionStructReq -> {
                    DroppedVo droppedVo = new DroppedVo();
                    droppedVo.setUuid(measureRegionStructReq.getUuid());
                    droppedVo.setReason_type(Integer.parseInt(ApiDropDataReasonEnum.NOTFOUND.getValue()));
                    droppedVo.setReason("未找到相关的区域id。");
                    droppedVos.add(droppedVo);
                });
            }else{
                List<MeasureRegionStructReq> measureRegionStructReqs1 = measureRegionStructReqMap.get(areaId.getKey());
                if (measureRegionStructReqs1 != null) {
                    measureRegionStructReqs1.forEach(measureRegionStructReq -> {
                        measureRegionStructReq.setArea_path_and_id(area.getPath() + areaId.getKey() + "/");
                    });
                }
                if(measureRegionStructReqs1 ==null){
                    measureRegionStructReqs1 = Lists.newArrayList();
                }
                List<MeasureRegion> measureRegions = converMeasureRegionStructReqToMeasureRegion(measureRegionStructReqs1);
                try {
                    List<MeasureRegion> measureRegionList = measureRegionService.createRegionsFromRegionStructList(area.getProjectId(), measureRegions);
                } catch (Exception e) {
                    log.error(ERRORLOG + e);
                    measureRegionStructReqList.addAll(measureRegionStructReqMap.get(areaId.getKey()));
                }
            }
        }
        if (measureRegionStructReqList != null) {
            measureRegionStructReqList.forEach(measureRegionStructReq -> {
                DroppedVo droppedVo = new DroppedVo();
                droppedVo.setUuid(measureRegionStructReq.getUuid());
                droppedVo.setReason_type(Integer.parseInt(ApiDropDataReasonEnum.OTHER.getValue()));
                droppedVo.setReason("添加失败。");
                droppedVos.add(droppedVo);
            });
        }
        droppedInfoVo.setDropped(droppedVos);
        ljBaseResponse.setData(droppedInfoVo);
        return ljBaseResponse;
    }

    @Override
    public LjBaseResponse<DroppedInfoVo> reportZone(ApiMeasureReportZoneReq req, HttpServletRequest request) throws LjBaseRuntimeException {
        ReportStatusEnum reportUuidStatus = ReportStatusEnum.ERROR;
        LjBaseResponse<DroppedInfoVo> ljBaseResponse = new LjBaseResponse<>();
        DroppedInfoVo droppedInfoVo = new DroppedInfoVo();
        //获取uid
        Integer uid = null;
        try {
            uid = (Integer) sessionInfo.getBaseInfo(USERID);
        } catch (Exception e) {
            throw new LjBaseRuntimeException(-9999, e.getMessage());
        }
        try {
            keyProcedureTaskAppService.startReport(req.getReport_uuid(), uid, request);
        } catch (Exception e) {
            throw new LjBaseRuntimeException(-9999,e+"");
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
                measureRegion = measureRegionService.searchByUuid(reportZoneVo.getProject_id(), reportZoneVo.getRegion_uuid());
                if (measureRegion == null) {
                    log.error("not found region by uuid.");
                }
            } catch (Exception e) {
                DroppedVo droppedVo = new DroppedVo();
                droppedVo.setUuid(reportZoneVo.getUuid());
                droppedVo.setReason_type(Integer.parseInt(ApiDropDataReasonEnum.MEASUREREGIONNOTFOUND.getValue()));
                droppedVo.setReason(ApiDropDataReasonEnum.MEASUREREGIONNOTFOUND.getName());
                droppedVos.add(droppedVo);
            }
            CategoryV3 category = null;
            try {
                category = checkItemService.getCategoryByKeyNoFoundErr(reportZoneVo.getCategory_key());
            } catch (Exception e) {
                log.error(ERRORLOG + e);
                DroppedVo droppedVo = new DroppedVo();
                droppedVo.setUuid(reportZoneVo.getUuid());
                droppedVo.setReason_type(Integer.parseInt(ApiDropDataReasonEnum.CATEGORYNOTFOUND.getValue()));
                droppedVo.setReason(ApiDropDataReasonEnum.CATEGORYNOTFOUND.getName());
                droppedVos.add(droppedVo);
            }
            try {
                List<MeasureZone> measureZones = measureListService.searchZoneByMeasureListIdRegionUuidCategoryKey(reportZoneVo.getProject_id(), reportZoneVo.getList_id(), reportZoneVo.getRegion_uuid(), reportZoneVo.getCategory_key());
                if (measureZones.size() > 0) {
                    List<MeasureZone> measureZoneList = measureListService.getZoneByUuid(reportZoneVo.getProject_id(), reportZoneVo.getUuid());
                    if (measureZoneList == null) {
                        DroppedVo droppedVo = new DroppedVo();
                        droppedVo.setUuid(reportZoneVo.getUuid());
                        droppedVo.setReason_type(Integer.parseInt(ApiDropDataReasonEnum.MEASUREZONEUUIDEXISTS.getValue()));
                        droppedVo.setReason(ApiDropDataReasonEnum.MEASUREZONEUUIDEXISTS.getName());
                        droppedVos.add(droppedVo);
                    }
                    DroppedVo droppedVo = new DroppedVo();
                    droppedVo.setUuid(reportZoneVo.getUuid());
                    droppedVo.setReason_type(Integer.parseInt(ApiDropDataReasonEnum.MEASUREZONEEXISTS.getValue()));
                    droppedVo.setReason(ApiDropDataReasonEnum.MEASUREZONEEXISTS.getName());
                    droppedVos.add(droppedVo);
                }
            } catch (Exception e) {
                log.error(ERRORLOG + e + "reportZoneVo.uuid" + reportZoneVo.getUuid());
            }
            try {
                measureListService.createZoneFromApp(reportZoneVo.getProject_id(), reportZoneVo.getList_id(), reportZoneVo.getUuid(), measureRegion.getUuid(), measureRegion.getAreaId(), measureRegion.getAreaPathAndId(), reportZoneVo.getCategory_key(), String.format("%s%s/",category.getPath(),category.getKey()), MeasureListFinishStatusEnum.UNFINISH.getId(), MeasureListCloseStatusEnum.UNCLOSE.getId());
            } catch (Exception e) {
                log.error("create zone fail, "+ERRORLOG + e);
                DroppedVo droppedVo = new DroppedVo();
                droppedVo.setUuid(reportZoneVo.getUuid());
                //String measureregionnotfound = ApiDropDataReasonConstant.MEASUREREGIONNOTFOUND;
                //JSONObject jsonObject = JSON.parseObject(measureregionnotfound);
                //int value = jsonObject.getIntValue("value");
                //String name = jsonObject.getString("name");
                droppedVo.setReason_type(Integer.parseInt(ApiDropDataReasonEnum.MEASUREREGIONNOTFOUND.getValue()));
                droppedVo.setReason(ApiDropDataReasonEnum.MEASUREREGIONNOTFOUND.getName());
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
        ruleInfoVo.setDelete_at(measureRule.getDeleteAt() == null ? 0 : DateUtil.dateToTimestamp(measureRule.getDeleteAt()));
        ruleInfoVo.setDesc(measureRule.getDesc());
        ruleInfoVo.setGroup_count_init(measureRule.getGroupCountInit());
        ruleInfoVo.setGroup_count_max(measureRule.getGroupCountMax());
        ruleInfoVo.setGroup_count_min(measureRule.getGroupCountMin());
        ruleInfoVo.setId(measureRule.getId());
        ruleInfoVo.setRule_type(measureRule.getRuleType());
        ruleInfoVo.setTeam_id(measureRule.getTeamId());
        ruleInfoVo.setTextures(measureRule.getTextures());
        ruleInfoVo.setUpdate_at(measureRule.getUpdateAt() == null ? 0 : DateUtil.dateToTimestamp(measureRule.getUpdateAt()));
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
    private TaskInfoVo convermeasureListToTaskInfoVo(MeasureList measureList, List<MeasureListArea> areas) {
        TaskInfoVo taskInfoVo = new TaskInfoVo();
        taskInfoVo.setId(measureList.getId());
        taskInfoVo.setProject_id(measureList.getProjectId());
        taskInfoVo.setName(measureList.getName());
        taskInfoVo.setFinish_status(measureList.getFinishStatus());
        taskInfoVo.setClose_status(measureList.getCloseStatus());
        taskInfoVo.setPlan_begin_on(DateUtil.dateToTimestamp(measureList.getPlanBeginOn()));
        taskInfoVo.setPlan_end_on(DateUtil.dateToTimestamp(measureList.getPlanEndOn()));
        List<Integer> idLists = Lists.newArrayList();
        areas.forEach(measureListArea -> {
            idLists.add(measureListArea.getAreaId());
        });
        String s = StringUtils.join(idLists, ",");
        taskInfoVo.setArea_ids(s);
        taskInfoVo.setRoot_category_key(measureList.getRootCategoryKey());
        taskInfoVo.setUpdate_at(DateUtil.dateToTimestamp(measureList.getUpdateAt()));
        taskInfoVo.setDelete_at(measureList.getDeleteAt() == null ? 0 : DateUtil.dateToTimestamp(measureList.getDeleteAt()));
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
        issueLogListVo.setList_id(measureListIssueLog.getListId());
        JSONObject detail = JSON.parseObject(measureListIssueLog.getDetail());
        issueLogListVo.setZone_uuid(detail.getString("ZoneUuid"));
        issueLogListVo.setIssue_uuid(measureListIssueLog.getIssueUuid());
        issueLogListVo.setSender_id(measureListIssueLog.getSenderId());
        issueLogListVo.setDesc(measureListIssueLog.getDesc());
        issueLogListVo.setTyp(measureListIssueLog.getTyp());
        issueLogListVo.setStatus(measureListIssueLog.getStatus());
        issueLogListVo.setAttachment_md5_list(measureListIssueLog.getAttachmentMd5List());
        issueLogListVo.setPlan_end_on(detail.getInteger("PlanEndOn"));
        issueLogListVo.setEnd_on(detail.getInteger("EndOn"));
        issueLogListVo.setRepairer_id(detail.getInteger("RepairerId"));
        issueLogListVo.setCondition(detail.getInteger("Condition"));
        issueLogListVo.setCategory_key(measureListIssueLog.getCategoryKey());
        issueLogListVo.setDrawing_md5(detail.getString("DrawingMd5"));
        issueLogListVo.setPos_x(detail.getInteger("PosX"));
        issueLogListVo.setPos_y(detail.getInteger("PosY"));
        issueLogListVo.setArea_id(detail.getInteger("AreaId"));
        issueLogListVo.setClose_status(detail.getInteger("CloseStatus"));
        issueLogListVo.setClose_user(detail.getInteger("CloseUser"));
        issueLogListVo.setClose_time(detail.getInteger("CloseTime"));
        issueLogListVo.setClient_create_at(measureListIssueLog.getClientCreateAt() == null ? 0 : DateUtil.dateToTimestamp(measureListIssueLog.getClientCreateAt()));
        issueLogListVo.setUpdate_at(measureListIssueLog.getUpdateAt() == null ? 0 : DateUtil.dateToTimestamp(measureListIssueLog.getUpdateAt()));
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
