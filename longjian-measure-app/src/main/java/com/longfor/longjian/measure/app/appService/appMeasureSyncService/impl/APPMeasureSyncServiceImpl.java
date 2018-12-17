package com.longfor.longjian.measure.app.appService.appMeasureSyncService.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.longfor.longjian.common.base.LjBaseResponse;
import com.longfor.longjian.measure.app.appService.appMeasureSyncService.IAPPMeasureSyncService;
import com.longfor.longjian.measure.app.appService.appService.IKeyProcedureTaskAppService;
import com.longfor.longjian.measure.app.req.apiMeasureRuleReq.*;
import com.longfor.longjian.measure.app.vo.appMeasureSyncVo.*;
import com.longfor.longjian.measure.consts.Enum.ReportStatusEnum;
import com.longfor.longjian.measure.consts.util.ConvertUtil;
import com.longfor.longjian.measure.consts.util.JsonUtil;
import com.longfor.longjian.measure.domain.externalService.*;
import com.longfor.longjian.measure.po.zhijian2.CategoryV3;
import com.longfor.longjian.measure.po.zhijian2.MeasureList;
import com.longfor.longjian.measure.po.zhijian2.MeasureRegion;
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
    public LjBaseResponse<MeasureZoneResultVo> getMeasureZone(ApiMeasureZoneReq apiMeasureZoneReq) throws Exception {
        LjBaseResponse<MeasureZoneResultVo> ljBaseResponse = new LjBaseResponse<>();
        MeasureZoneResultVo measureZoneResultVo = new MeasureZoneResultVo();
        List<ResultListVo> measureZones = new ArrayList<>();
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
                List<Map<String, Object>> measureZoneList = measureZoneService.searchZoneUnscopedByListIdLastIdUpdateAtGt(measureList.getProjectId(), listId, lastId, timestamp, limit, start);
                //map转换vo
                ResultListVo resultListVo = (ResultListVo) ConvertUtil.convertMap(ResultListVo.class, measureZoneList.get(0));
                measureZones.add(resultListVo);
            } catch (Exception e) {
                throw new Exception(e);
            }
        }
        measureZoneResultVo.setResult_list(measureZones);
        measureZoneResultVo.setLast_id(lastId);
        ljBaseResponse.setData(measureZoneResultVo);
        return ljBaseResponse;
    }

    @Override
    public LjBaseResponse<MeasureZoneResultVo> getMeasureZoneV2(ApiMeasureZoneReqV2 apiMeasureZoneReqV2) throws Exception {
        LjBaseResponse<MeasureZoneResultVo> ljBaseResponse = new LjBaseResponse<>();
        MeasureZoneResultVo measureZoneResultVo = new MeasureZoneResultVo();
        List<ResultListVo> measureZoneVos = new ArrayList<>();
        MeasureList measureList = measureListService.getNoProjNoFoundErr(apiMeasureZoneReqV2.getList_id().toString());
        Integer lastId = 0;
        lastId = measureList.getId();
        Integer start = 0;
        Integer limit = MEASURE_API_GET_PER_TIME;
        List<Map<String, Object>> measureZoneList = measureZoneService.searchZoneUnscopedByListIdLastIdUpdateAtGt2(measureList.getProjectId(), apiMeasureZoneReqV2.getList_id(), apiMeasureZoneReqV2.getLast_id(), apiMeasureZoneReqV2.getTimestamp(), start, limit);
        for (Map<String, Object> map : measureZoneList
                ) {
            ResultListVo resultListVo = (ResultListVo) ConvertUtil.convertMap(ResultListVo.class, map);
            measureZoneVos.add(resultListVo);
        }
        try {
            measureZoneResultVo.setResult_list(measureZoneVos);
            measureZoneResultVo.setLast_id(lastId);
            ljBaseResponse.setData(measureZoneResultVo);
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
        List<Map<String, Object>> measureListIssueList = measureListIssueService.searchIssueListByListIdLastIdTimestampGt(measureList.getId(), apiMeasureIssueReq.getLast_id(), apiMeasureIssueReq.getTimestamp(), start, limit);
        for (Map<String, Object> map : measureListIssueList
                ) {
            IssueListVo issueListVo = (IssueListVo) ConvertUtil.convertMap(IssueListVo.class, map);
            issueListVos.add(issueListVo);
        }
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
            List<Map<String, Object>> measureListIssueLogList = measureListIssueLogService.searchIssueLogListByListIdLastIdTimestampGt(measureList.getProjectId(), apiMeasureIssueLogReq.getList_id(), apiMeasureIssueLogReq.getLast_id(), apiMeasureIssueLogReq.getTimestamp(), start, pageSize);
            //map转换vo
            for (Map<String, Object> map : measureListIssueLogList
                    ) {
                IssueLogListVo issueLogListVo = (IssueLogListVo) ConvertUtil.convertMap(IssueLogListVo.class, map);
                measureIssueLogVos.add(issueLogListVo);
            }
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
}
