package com.longfor.longjian.measure.app.appService.appMeasureSyncService.impl;

import com.longfor.longjian.common.base.LjBaseResponse;
import com.longfor.longjian.measure.app.appService.appMeasureSyncService.IAPPMeasureSyncService;
import com.longfor.longjian.measure.app.appService.appService.IKeyProcedureTaskAppService;
import com.longfor.longjian.measure.app.req.apiMeasureRuleReq.*;
import com.longfor.longjian.measure.app.vo.appMeasureSyncVo.*;
import com.longfor.longjian.measure.consts.Enum.ReportStatusEnum;
import com.longfor.longjian.measure.consts.util.ConvertUtil;
import com.longfor.longjian.measure.domain.externalService.*;
import com.longfor.longjian.measure.po.zhijian2.CategoryV3;
import com.longfor.longjian.measure.po.zhijian2.MeasureList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
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
    public LjBaseResponse<MeasureRuleListVo> getMeasureRule(ApiMeasureRuleReq apiMeasureRuleReq) throws Exception {
        LjBaseResponse<MeasureRuleListVo> ljBaseResponse = new LjBaseResponse<>();
        List<MeasureRuleVo> measureRuleListVos = new ArrayList<>();
        MeasureRuleListVo measureRuleListVo = new MeasureRuleListVo();
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
                List<Map<String, Object>> measureRuleList = measureRuleService.searchUnscopedByTeamIdUpdateAtGt(category.getTeamId(), dateString);
                for (Map<String, Object> map : measureRuleList
                        ) {
                    //map转换成vo
                    MeasureRuleVo measureRuleVo = (MeasureRuleVo) ConvertUtil.convertMap(MeasureRuleVo.class, map);
                    measureRuleListVos.add(measureRuleVo);
                }
            }
        }
        measureRuleListVo.setRule_list(measureRuleListVos);
        ljBaseResponse.setData(measureRuleListVo);
        return ljBaseResponse;
    }

    @Override
    public LjBaseResponse<MeasureListListVo> getMyTask(ApiMyTaskReq apiMyTaskReq, HttpServletRequest request) throws Exception {
        LjBaseResponse<MeasureListListVo> ljBaseResponse = new LjBaseResponse<>();
        MeasureListListVo measureListListVo = new MeasureListListVo();
        List<MeasureListVo> measureListVos = new ArrayList<>();
        String[] projectIds = apiMyTaskReq.getProject_id().split(",");
        if (projectIds.length == 0) {
            throw new Exception("project id is empty.");
        }
        //HttpSession session = request.getSession();
        //todo session没有uid
        //Integer userId = (Integer) session.getAttribute("uid");
        Integer userId = 1;
        for (String projectId : projectIds
                ) {
            List<Map<String, Object>> measureLists = measureListService.searchListByProjIdUserId(projectId, userId);
            for (Map<String, Object> map : measureLists
                    ) {
                //map转换成vo
                MeasureListVo measureListVo = (MeasureListVo) ConvertUtil.convertMap(MeasureListVo.class, map);
                measureListVos.add(measureListVo);
            }
        }
        measureListListVo.setMeasurelist_list(measureListVos);
        ljBaseResponse.setData(measureListListVo);
        return ljBaseResponse;
    }

    @Override
    public LjBaseResponse<MeasureZoneListVo> getMeasureZone(ApiMeasureZoneReq apiMeasureZoneReq) throws Exception {
        LjBaseResponse<MeasureZoneListVo> ljBaseResponse = new LjBaseResponse<>();
        MeasureZoneListVo measureZoneListVo = new MeasureZoneListVo();
        List<MeasureZoneZoneVo> measureZoneZoneVos = new ArrayList<>();
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
            List<MeasureList> measureLists = measureListService.getNoProjNoFoundErr(listId);
            if (measureLists.size() > 0) {
                lastId = measureLists.get(measureLists.size() - 1).getId();
            }
            //最后一次获取的id
            measureZoneListVo.setLast_id(lastId);
            for (MeasureList measureList : measureLists
                    ) {
                List<Map<String, Object>> measureZoneList = measureZoneService.searchZoneUnscopedByListIdLastIdUpdateAtGt(measureList.getProjectId(), listId, lastId, timestamp, limit, start);
                //map转换vo
                for (Map<String, Object> map : measureZoneList
                        ) {
                    MeasureZoneZoneVo measureZoneZoneVo = (MeasureZoneZoneVo) ConvertUtil.convertMap(MeasureZoneZoneVo.class, map);
                    measureZoneZoneVos.add(measureZoneZoneVo);
                }
            }
        }
        measureZoneListVo.setZone_list(measureZoneZoneVos);
        measureZoneListVo.setLast_id(lastId);
        ljBaseResponse.setData(measureZoneListVo);
        return ljBaseResponse;
    }

    @Override
    public LjBaseResponse<MeasureZoneListVo> getMeasureZoneV2(ApiMeasureZoneReqV2 apiMeasureZoneReqV2) throws Exception {
        LjBaseResponse<MeasureZoneListVo> ljBaseResponse = new LjBaseResponse<>();
        MeasureZoneListVo measureZoneListVo = new MeasureZoneListVo();
        List<MeasureZoneZoneVo> measureZoneZoneVos = new ArrayList<>();
        List<MeasureList> measureLists = measureListService.getNoProjNoFoundErr(apiMeasureZoneReqV2.getList_id().toString());
        Integer lastId = 0;
        if (measureLists.size() > 0) {
            MeasureList measureList = measureLists.get(measureLists.size() - 1);
            lastId = measureList.getId();
            Integer start = 0;
            Integer limit = MEASURE_API_GET_PER_TIME;
            List<Map<String, Object>> measureZoneList = measureZoneService.searchZoneUnscopedByListIdLastIdUpdateAtGt2(measureList.getProjectId(), apiMeasureZoneReqV2.getList_id(), apiMeasureZoneReqV2.getLast_id(), apiMeasureZoneReqV2.getTimestamp(), start, limit);
            for (Map<String, Object> map : measureZoneList
                    ) {
                MeasureZoneZoneVo measureZoneZoneVo = (MeasureZoneZoneVo) ConvertUtil.convertMap(MeasureZoneZoneVo.class, map);
                measureZoneZoneVos.add(measureZoneZoneVo);
            }
        }
        try {
            measureZoneListVo.setZone_list(measureZoneZoneVos);
            measureZoneListVo.setLast_id(lastId);
            ljBaseResponse.setData(measureZoneListVo);
        } catch (Exception e) {
            log.error("SearchZoneUnscopedByListIdLastIdUpdateAtGt" + "[" + apiMeasureZoneReqV2.getList_id() + "],error:" + e);
            throw new Exception("读取数据失败，code:zone");
        }
        return ljBaseResponse;
    }

    @Override
    public LjBaseResponse<TotalVo> getMeasureZoneV2Total(ApiMeasureZoneTotalReqV2 apiMeasureZoneTotalReqV2) throws Exception {
        LjBaseResponse<TotalVo> ljBaseResponse = new LjBaseResponse<>();
        List<MeasureList> measureLists = measureListService.getNoProjNoFoundErr(apiMeasureZoneTotalReqV2.getList_id().toString());
        TotalVo totalVo = new TotalVo();
        if (measureLists.size() > 0) {
            MeasureList measureList = measureLists.get(measureLists.size() - 1);
            Integer total = measureZoneService.getCountZoneUnscopedByListIdUpdateAtGt(measureList.getProjectId(), apiMeasureZoneTotalReqV2.getList_id(), apiMeasureZoneTotalReqV2.getTimestamp());
            try {
                totalVo.setTotal(total);
                ljBaseResponse.setData(totalVo);
            } catch (Exception e) {
                log.error("GetCountUnscopedByProjIdUpdateAtGt" + "[" + apiMeasureZoneTotalReqV2.getList_id() + "],error:" + e);
                throw new Exception("读取数据失败，code:zone_total");
            }
        }
        return ljBaseResponse;
    }

    @Override
    public LjBaseResponse<MeasureIssueListVo> issue(ApiMeasureIssueReq apiMeasureIssueReq) throws Exception {
        LjBaseResponse<MeasureIssueListVo> ljBaseResponse = new LjBaseResponse<>();
        MeasureIssueListVo measureIssueListVo = new MeasureIssueListVo();
        List<MeasureIssueVo> issueListVos = new ArrayList<>();
        List<MeasureList> measureLists = measureListService.getNoProjNoFoundErr(apiMeasureIssueReq.getList_id().toString());
        Integer start = 0;
        Integer limit = MEASURE_API_GET_PER_TIME;
        if (measureLists.size() > 0) {
            MeasureList measureList = measureLists.get(measureLists.size() - 1);
            List<Map<String, Object>> measureListIssueList = measureListIssueService.searchIssueListByListIdLastIdTimestampGt(measureList.getId(), apiMeasureIssueReq.getLast_id(), apiMeasureIssueReq.getTimestamp(), start, limit);
            for (Map<String, Object> map : measureListIssueList
                    ) {
                MeasureIssueVo measureIssueVo = (MeasureIssueVo) ConvertUtil.convertMap(MeasureIssueVo.class, map);
                issueListVos.add(measureIssueVo);
            }
            try {
                measureIssueListVo.setIssue_list(issueListVos);
                MeasureIssueVo measureIssueVo = issueListVos.get(issueListVos.size() - 1);
                measureIssueListVo.setLast_id(measureIssueVo.getId());
                ljBaseResponse.setData(measureIssueListVo);
            } catch (Exception e) {
                log.error("SearchIssueListByListIdLastIdTimestampGt +[" + apiMeasureIssueReq.getList_id() + "],error:" + e);
                throw new Exception("读取数据失败，code:issue_list");
            }
        }
        return ljBaseResponse;
    }

    @Override
    public LjBaseResponse<MeasureIssueLogListVo> issueLog(ApiMeasureIssueLogReq apiMeasureIssueLogReq) throws Exception {
        LjBaseResponse<MeasureIssueLogListVo> ljBaseResponse = new LjBaseResponse<>();
        MeasureIssueLogListVo measureIssueLogListVo = new MeasureIssueLogListVo();
        List<MeasureIssueLogVo> measureIssueLogVos = new ArrayList<>();
        List<MeasureList> measureLists = measureListService.getNoProjNoFoundErr(apiMeasureIssueLogReq.getList_id().toString());
        Integer start = 0;
        Integer pageSize = MEASURE_API_GET_PER_TIME;
        if (measureLists.size() > 0) {
            MeasureList measureList = measureLists.get(measureLists.size() - 1);
            List<Map<String, Object>> measureListIssueLogList = measureListIssueLogService.searchIssueLogListByListIdLastIdTimestampGt(measureList.getProjectId(), apiMeasureIssueLogReq.getList_id(), apiMeasureIssueLogReq.getLast_id(), apiMeasureIssueLogReq.getTimestamp(), start, pageSize);
            //map转换vo
            for (Map<String, Object> map : measureListIssueLogList
                    ) {
                MeasureIssueLogVo measureIssueLogVo = (MeasureIssueLogVo) ConvertUtil.convertMap(MeasureIssueLogVo.class, map);
                measureIssueLogVos.add(measureIssueLogVo);
            }
            try {
                measureIssueLogListVo.setIssueLog_list(measureIssueLogVos);
                MeasureIssueLogVo measureIssueLogVoEnt = measureIssueLogVos.get(measureIssueLogVos.size() - 1);
                measureIssueLogListVo.setLast_id(measureIssueLogVoEnt.getId());
                ljBaseResponse.setData(measureIssueLogListVo);
            } catch (Exception e) {
                log.error("SearchIssueListByListIdLastIdTimestampGt +[" + apiMeasureIssueLogReq.getList_id() + "],error:" + e);
                throw new Exception("读取数据失败，code:issue_log_list");
            }

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
            keyProcedureTaskAppService.updateReportStatus(apiMeasureReportRegionReq.getReport_uuid(), reportUuidStatus.getValue().toString());
            throw new Exception(e);
        }
        List<ReportRegionDataVo> reportRegionDataVos = new ArrayList<>();
        //解析json数据
        //JSONObject dataJson=new JSONObject(apiMeasureReportRegionReq.getData());
        //todo data不知道什么格式的json，暂不知道怎么解析
        //reportRegionDataVos.add(dataJson);
        return null;
    }

    @Override
    public LjBaseResponse<DroppedInfoVo> reportZone(ApiMeasureReportZoneReq apiMeasureReportZoneReq) {
        return null;
    }
}
