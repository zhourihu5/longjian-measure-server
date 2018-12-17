package com.longfor.longjian.measure.app.controller.appController;

import com.longfor.gaia.gfs.web.mock.MockOperation;
import com.longfor.longjian.common.base.LjBaseResponse;
import com.longfor.longjian.measure.app.appService.appMeasureSyncService.IAPPMeasureSyncService;
import com.longfor.longjian.measure.app.appService.appService.IAPPMeasureService;
import com.longfor.longjian.measure.app.req.apiMeasureRuleReq.*;
import com.longfor.longjian.measure.app.req.appReq.*;
import com.longfor.longjian.measure.app.vo.appMeasureSyncVo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import javax.validation.Valid;

/**
 *   app 端实测实量同步
 * wangxs
 * 2018-11-20
 */
@RestController
@RequestMapping("v3/api/")
@Slf4j
public class APPMeasureSyncController {

    @Autowired
    private IAPPMeasureSyncService appMeasureSyncService;
    @Autowired
    private IAPPMeasureService appMeasureService;

    /**
     * 61读取测量规则
     * http://192.168.37.159:3000/project/8/interface/api/346
     * 项目同步/v3/api/info/measure_rule
     * http://192.168.37.159:3000/project/8/interface/api/634
     * @param apiMeasureRuleReq
     * @return
     */
    @GetMapping(value = "info/measure_rule/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<MeasureRuleListVo> getMeasureRule(@Valid ApiMeasureRuleReq apiMeasureRuleReq) throws Exception {
        return appMeasureSyncService.getMeasureRule(apiMeasureRuleReq);
    }

    /**
     * 获取指定项目的描画区域，此为全量接口
     * http://192.168.37.159:3000/project/8/interface/api/382
     * @param apiMeasureRegionReq
     * @return
     */
    @GetMapping(value = "info/measure_region/",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<MeasureRegionVo> getMeasureRegion(ApiMeasureRegionReq apiMeasureRegionReq) throws Exception {
        LjBaseResponse<MeasureRegionVo> ljBaseResponse = appMeasureService.getMeasureRegion(apiMeasureRegionReq);
        return ljBaseResponse;
    }

    /**
     * http://192.168.37.159:3000/project/8/interface/api/404
     * 项目同步/v3/api/info/measure_region_v2
     * http://192.168.37.159:3000/project/8/interface/api/624
     * @param apiMeasureRegionReqV2
     * @return
     */
    @GetMapping(value = "info/measure_region_v2/",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<MeasureRegionV2Vo> getMeasureRegionV2(ApiMeasureRegionReqV2 apiMeasureRegionReqV2) throws Exception {
        LjBaseResponse<MeasureRegionV2Vo> ljBaseResponse = appMeasureService.getMeasureRegionV2(apiMeasureRegionReqV2);
        return ljBaseResponse;
    }

    /**
     * 获取指定项目的描画区域-总数
     * http://192.168.37.159:3000/project/8/interface/api/410
     * 项目同步/v3/api/info/measure_region_v2_total
     * http://192.168.37.159:3000/project/8/interface/api/574
     * @param apiMeasureRegionTotalReqV2
     * @return
     */
    @GetMapping(value = "info/measure_region_v2_total/",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<TotalVo> getMeasureRegionV2Total(ApiMeasureRegionTotalReqV2 apiMeasureRegionTotalReqV2) throws Exception {
        return appMeasureService.getMeasureRegionV2Total(apiMeasureRegionTotalReqV2);
    }

    /**
     * http://192.168.37.159:3000/project/8/interface/api/414
     * 项目同步v3/api/info/measure_region_rel_v2/
     * http://192.168.37.159:3000/project/8/interface/api/624
     * @param requestParam
     * @return
     */
    @MockOperation
    @GetMapping(value = "info/measure_region_rel_v2/",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<MeasureRegionV2Vo> getMeasureRegionRelV2(RequestParam requestParam){
        return null;
    }

    /**
     * 获取与“我”相关的实测实量清单
     * http://192.168.37.159:3000/project/8/interface/api/418
     * 项目请求任务列表
     * http://192.168.37.159:3000/project/8/interface/api/538
     * @param apiMyTaskReq
     * @return
     */
    @GetMapping(value = "measure/my_task/",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<MeasureListListVo> getMyTask(ApiMyTaskReq apiMyTaskReq, HttpServletRequest request) throws Exception {
        return appMeasureSyncService.getMyTask(apiMyTaskReq,request);
    }

    /**
     * 读取实测清单下的测区。此为全量接口
     * http://192.168.37.159:3000/project/8/interface/api/422
     * @param apiMeasureZoneReq
     * @return
     */
    @GetMapping(value = "measure/measure_zone/",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<MeasureZoneListVo> getMeasureZone(ApiMeasureZoneReq apiMeasureZoneReq) throws Exception {

        return appMeasureSyncService.getMeasureZone(apiMeasureZoneReq);
    }

    /**
     * 读取实测清单下的测区-分批
     * http://192.168.37.159:3000/project/8/interface/api/424
     * 项目同步/v3/api/measure/measure_zone_v2
     * http://192.168.37.159:3000/project/8/interface/api/648
     * @param apiMeasureZoneReqV2
     * @return
     */
    @GetMapping(value = "measure/measure_zone_v2/",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<MeasureZoneListVo> getMeasureZoneV2(ApiMeasureZoneReqV2 apiMeasureZoneReqV2) throws Exception {
        return appMeasureSyncService.getMeasureZoneV2(apiMeasureZoneReqV2);
    }


    /**
     * 读取实测清单下的测区的总数
     * http://192.168.37.159:3000/project/8/interface/api/426
     * 项目同步实测区域/v3/api/measure/measure_zone_v2_total/
     * http://192.168.37.159:3000/project/8/interface/api/568
     * @param apiMeasureZoneTotalReqV2
     * @return
     */
    @GetMapping(value = "measure/measure_zone_v2_total/",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<TotalVo> getMeasureZoneV2Total(ApiMeasureZoneTotalReqV2 apiMeasureZoneTotalReqV2) throws Exception {
        return appMeasureSyncService.getMeasureZoneV2Total(apiMeasureZoneTotalReqV2);
    }

    /**
     * 读取实测清单测量小组列表及整改人员列表
     * http://192.168.37.159:3000/project/8/interface/api/428
     * 项目同步v3/api/measure/measure_squad_and_repairer
     * http://192.168.37.159:3000/project/8/interface/api/654
     * @param requestParam
     * @return
     */
    @MockOperation
    @GetMapping(value = "measure/measure_squad_and_repairer/",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<MeasureSquadAndRepairerVo> measureSquadAndRepairer(RequestParam requestParam){
        return null;
    }

    /**
     * 读取测量结果
     * http://192.168.37.159:3000/project/8/interface/api/430
     * @param requestParam
     * @return
     */
    @MockOperation
    @GetMapping(value = "measure/measure_zone_result/",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<MeasureZoneResultVo> measureZoneResult(RequestParam requestParam){
        return null;
    }

    /**
     * 读取测量结果-分批
     * http://192.168.37.159:3000/project/8/interface/api/432
     * 项目同步/v3/api/measure/measure_zone_result_v2
     * http://192.168.37.159:3000/project/8/interface/api/644
     * @return
     */
    @MockOperation
    @GetMapping(value = "measure/measure_zone_result_v2/",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<MeasureZoneResultVo> measureZoneResultV2(){
        return null;
    }

    /**
     * 读取测量结果-总数
     * http://192.168.37.159:3000/project/8/interface/api/434
     * 项目同步/v3/api/measure/measure_zone_result_v2_total
     * http://192.168.37.159:3000/project/8/interface/api/580
     * @return
     */
    @MockOperation
    @GetMapping(value = "measure/measure_zone_result_v2_total/",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<TotalVo> measureZoneResultV2Total(RequestParam requestParam){
        return null;
    }

    /**
     * 读取Issue爆点整改数据
     * http://192.168.37.159:3000/project/8/interface/api/436
     * 项目同步/v3/api/measure/issue/
     * http://192.168.37.159:3000/project/8/interface/api/652
     * @return
     */
    @GetMapping(value = "measure/issue/",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<MeasureIssueListVo> issue(ApiMeasureIssueReq apiMeasureIssueReq) throws Exception {
        return appMeasureSyncService.issue(apiMeasureIssueReq);
    }

    /**
     * 读取Issue爆点整改数据日志
     * http://192.168.37.159:3000/project/8/interface/api/438
     * 项目同步/v3/api/measure/issue_log/
     * http://192.168.37.159:3000/project/8/interface/api/650
     * @return
     */
    @GetMapping(value = "measure/issue_log/",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<MeasureIssueLogListVo> issueLog(ApiMeasureIssueLogReq apiMeasureIssueLogReq) throws Exception {
        return appMeasureSyncService.issueLog(apiMeasureIssueLogReq);
    }

    /**
     * 上报新增的描画区域
     * http://192.168.37.159:3000/project/8/interface/api/440
     * /v3/api/measure/report_region
     * http://192.168.37.159:3000/project/8/interface/api/1436
     * @return
     */
    @PostMapping(value = "measure/report_region/",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<DroppedInfoVo> reportRegion(ApiMeasureReportRegionReq apiMeasureReportRegionReq,HttpServletRequest request) throws Exception {
        return appMeasureSyncService.reportRegion(apiMeasureReportRegionReq,request);
    }

    /**
     * 上报新增的测区
     * http://192.168.37.159:3000/project/8/interface/api/442
     * ./v3/api/measure/report_zone/
     * http://192.168.37.159:3000/project/8/interface/api/1460
     * @return
     */
    @MockOperation
    @GetMapping(value = "measure/report_zone/",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<DroppedInfoVo> reportZone(ApiMeasureReportZoneReq apiMeasureReportZoneReq){
        return appMeasureSyncService.reportZone(apiMeasureReportZoneReq );
    }

    /**
     * 上报测区测量数据
     * http://192.168.37.159:3000/project/8/interface/api/444
     * @return
     */
    @GetMapping(value = "measure/report_zone_result/",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<DroppedInfoVo> reportZoneResult(ApiMeasureReportZoneResultReq apiMeasureReportZoneResultReq){
        return appMeasureService.reportZoneResult(apiMeasureReportZoneResultReq);
    }

    /**
     * 上报爆点issue数据
     * http://192.168.37.159:3000/project/8/interface/api/444
     * v3/api/measure/report_issue/
     * http://192.168.37.159:3000/project/8/interface/api/1468
     * @return
     */
    @PostMapping(value = "measure/report_issue/",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<ReportIssueVo> reportIssue(ApiMeasureReportIssueReq apiMeasureReportIssueReq, HttpServletRequest request) throws Exception {
        return appMeasureService.reportIssue(apiMeasureReportIssueReq,request);
    }

}
