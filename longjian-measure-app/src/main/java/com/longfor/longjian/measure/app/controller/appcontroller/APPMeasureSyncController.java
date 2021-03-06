package com.longfor.longjian.measure.app.controller.appcontroller;

import com.longfor.longjian.common.base.LjBaseResponse;
import com.longfor.longjian.common.exception.LjBaseRuntimeException;
import com.longfor.longjian.measure.app.appservice.appmeasuresyncservice.IAPPMeasureSyncService;
import com.longfor.longjian.measure.app.appservice.appservice.IAPPMeasureService;
import com.longfor.longjian.measure.app.req.apimeasurerulereq.*;
import com.longfor.longjian.measure.app.req.appreq.*;
import com.longfor.longjian.measure.app.vo.appmeasuresyncvo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.ParseException;

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
    @RequestMapping(value = "info/measure_rule/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<RuleListVo> getMeasureRule(@Valid ApiMeasureRuleReq apiMeasureRuleReq){
        return appMeasureSyncService.getMeasureRule(apiMeasureRuleReq);
    }

    /**
     * 分量
     * @param apiMeasureRuleReqV2
     * @return
     */
    @RequestMapping(value = "info/measure_rule_v2/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<RuleListVo> getMeasureRule(@Valid ApiMeasureRuleReqV2 apiMeasureRuleReqV2){
        return appMeasureSyncService.getMeasureRuleV2(apiMeasureRuleReqV2);
    }

    /**
     * 获取指定项目的描画区域，此为全量接口
     * http://192.168.37.159:3000/project/8/interface/api/382
     * @param apiMeasureRegionReq
     * @return
     */
    @RequestMapping(value = "info/measure_region/",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<MeasureRegionVo> getMeasureRegion(@Valid ApiMeasureRegionReq apiMeasureRegionReq) throws LjBaseRuntimeException {
        return appMeasureService.getMeasureRegion(apiMeasureRegionReq);
    }

    /**
     * http://192.168.37.159:3000/project/8/interface/api/404
     * 项目同步/v3/api/info/measure_region_v2
     * http://192.168.37.159:3000/project/8/interface/api/624
     * @param apiMeasureRegionReqV2
     * @return
     */
    @RequestMapping(value = "info/measure_region_v2/",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<MeasureRegionV2Vo> getMeasureRegionV2(@Valid ApiMeasureRegionReqV2 apiMeasureRegionReqV2) throws LjBaseRuntimeException {
        return appMeasureService.getMeasureRegionV2(apiMeasureRegionReqV2);
    }

    /**
     * 获取指定项目的描画区域-总数
     * http://192.168.37.159:3000/project/8/interface/api/410
     * 项目同步/v3/api/info/measure_region_v2_total
     * http://192.168.37.159:3000/project/8/interface/api/574
     * @param apiMeasureRegionTotalReqV2
     * @return
     */
    @RequestMapping(value = "info/measure_region_v2_total/",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<TotalVo> getMeasureRegionV2Total(@Valid ApiMeasureRegionTotalReqV2 apiMeasureRegionTotalReqV2) throws LjBaseRuntimeException {
        return appMeasureService.getMeasureRegionV2Total(apiMeasureRegionTotalReqV2);
    }

    /**
     * http://192.168.37.159:3000/project/8/interface/api/414
     * 项目同步v3/api/info/measure_region_rel_v2/
     * http://192.168.37.159:3000/project/8/interface/api/624
     * @param apiMeasureRegionRelReqV2
     * @return
     */
    @RequestMapping(value = "info/measure_region_rel_v2/",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<MeasureRegionRelV2Vo> getMeasureRegionRelV2(@Valid ApiMeasureRegionRelReqV2 apiMeasureRegionRelReqV2) throws LjBaseRuntimeException {
        return appMeasureService.getMeasureRegionRelV2(apiMeasureRegionRelReqV2);
    }

    /**
     * 获取与“我”相关的实测实量清单
     * http://192.168.37.159:3000/project/8/interface/api/418
     * 项目请求任务列表
     * http://192.168.37.159:3000/project/8/interface/api/538
     * @param apiMyTaskReq
     * @return
     */
    @RequestMapping(value = "measure/my_task/",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<MyTaskVo> getMyTask(@Valid ApiMyTaskReq apiMyTaskReq, HttpServletRequest request) throws LjBaseRuntimeException {
        return appMeasureSyncService.getMyTask(apiMyTaskReq,request);
    }

    /**
     * 读取实测清单下的测区。此为全量接口
     * http://192.168.37.159:3000/project/8/interface/api/422
     * @param apiMeasureZoneReq
     * @return
     */
    @RequestMapping(value = "measure/measure_zone/",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<MeasureZoneVo> getMeasureZone(@Valid ApiMeasureZoneReq apiMeasureZoneReq){

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
    @RequestMapping(value = "measure/measure_zone_v2/",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<MeasureZoneVo> getMeasureZoneV2(@Valid ApiMeasureZoneReqV2 apiMeasureZoneReqV2){
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
    @RequestMapping(value = "measure/measure_zone_v2_total/",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<TotalVo> getMeasureZoneV2Total(@Valid ApiMeasureZoneTotalReqV2 apiMeasureZoneTotalReqV2){
        return appMeasureSyncService.getMeasureZoneV2Total(apiMeasureZoneTotalReqV2);
    }

    /**
     * 读取实测清单测量小组列表及整改人员列表
     * http://192.168.37.159:3000/project/8/interface/api/428
     * 项目同步v3/api/measure/measure_squad_and_repairer
     * http://192.168.37.159:3000/project/8/interface/api/654
     * @param apiMeasureSquadAndRepairerReq
     * @return
     */
    @RequestMapping(value = "measure/measure_squad_and_repairer/",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<MeasureSquadAndRepairerVo> measureSquadAndRepairer(@Valid ApiMeasureSquadAndRepairerReq apiMeasureSquadAndRepairerReq) throws LjBaseRuntimeException {
        return appMeasureService.measureSquadAndRepairer(apiMeasureSquadAndRepairerReq);
    }

    /**
     * 读取测量结果
     * http://192.168.37.159:3000/project/8/interface/api/430
     * @param apiMeasureZoneResultReq
     * @return
     */
    @RequestMapping(value = "measure/measure_zone_result/",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<MeasureZoneResultVo> measureZoneResult(@Valid ApiMeasureZoneResultReq apiMeasureZoneResultReq) throws LjBaseRuntimeException {
        return appMeasureService.measureZoneResult(apiMeasureZoneResultReq);
    }

    /**
     * 读取测量结果-分批
     * http://192.168.37.159:3000/project/8/interface/api/432
     * 项目同步/v3/api/measure/measure_zone_result_v2
     * http://192.168.37.159:3000/project/8/interface/api/644
     * @return
     */
    @RequestMapping(value = "measure/measure_zone_result_v2/",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<MeasureZoneResultVo> measureZoneResultV2(@Valid ApiMeasureZoneResultReqV2 apiMeasureZoneResultReqV2) throws LjBaseRuntimeException {
        return appMeasureService.measureZoneResultV2(apiMeasureZoneResultReqV2);
    }

    /**
     * 读取测量结果-总数
     * http://192.168.37.159:3000/project/8/interface/api/434
     * 项目同步/v3/api/measure/measure_zone_result_v2_total
     * http://192.168.37.159:3000/project/8/interface/api/580
     * @return
     */
    @RequestMapping(value = "measure/measure_zone_result_v2_total/",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<TotalVo> measureZoneResultV2Total(@Valid ApiMeasureZoneResultTotalReqV2 apiMeasureZoneResultTotalReqV2) throws LjBaseRuntimeException {
        return appMeasureService.measureZoneResultV2Total(apiMeasureZoneResultTotalReqV2);
    }

    /**
     * 读取Issue爆点整改数据
     * http://192.168.37.159:3000/project/8/interface/api/436
     * 项目同步/v3/api/measure/issue/
     * http://192.168.37.159:3000/project/8/interface/api/652
     * @return
     */
    @RequestMapping(value = "measure/issue/",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<IssueVo> issue(@Valid ApiMeasureIssueReq apiMeasureIssueReq){
        return appMeasureSyncService.issue(apiMeasureIssueReq);
    }

    /**
     * 读取Issue爆点整改数据日志
     * http://192.168.37.159:3000/project/8/interface/api/438
     * 项目同步/v3/api/measure/issue_log/
     * http://192.168.37.159:3000/project/8/interface/api/650
     * @return
     */
    @RequestMapping(value = "measure/issue_log/",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<IssueLogVo> issueLog(@Valid ApiMeasureIssueLogReq apiMeasureIssueLogReq){
        return appMeasureSyncService.issueLog(apiMeasureIssueLogReq);
    }

    /**
     * 上报新增的描画区域
     * http://192.168.37.159:3000/project/8/interface/api/440
     * /v3/api/measure/report_region
     * http://192.168.37.159:3000/project/8/interface/api/1436
     * @return
     */
    @RequestMapping(value = "measure/report_region/",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<DroppedInfoVo> reportRegion(@Valid ApiMeasureReportRegionReq apiMeasureReportRegionReq,HttpServletRequest request){
        return appMeasureSyncService.reportRegion(apiMeasureReportRegionReq,request);
    }

    /**
     * 上报新增的测区
     * http://192.168.37.159:3000/project/8/interface/api/442
     * ./v3/api/measure/report_zone/
     * http://192.168.37.159:3000/project/8/interface/api/1460
     * @return
     */
    @RequestMapping(value = "measure/report_zone/",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<DroppedInfoVo> reportZone(@Valid ApiMeasureReportZoneReq apiMeasureReportZoneReq,HttpServletRequest request){
        return appMeasureSyncService.reportZone(apiMeasureReportZoneReq,request );
    }

    /**
     * 上报测区测量数据
     * http://192.168.37.159:3000/project/8/interface/api/444
     * @return
     */
    @RequestMapping(value = "measure/report_zone_result/",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<DroppedInfoVo> reportZoneResult(@Valid ApiMeasureReportZoneResultReq apiMeasureReportZoneResultReq, HttpServletRequest request) throws ParseException, LjBaseRuntimeException {
        return appMeasureService.reportZoneResult(apiMeasureReportZoneResultReq,request);
    }

    /**
     * 上报爆点issue数据
     * http://192.168.37.159:3000/project/8/interface/api/444
     * v3/api/measure/report_issue/
     * http://192.168.37.159:3000/project/8/interface/api/1468
     * @return
     */
    @RequestMapping(value = "measure/report_issue/",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<DroppedInfoVo> reportIssue(@Valid ApiMeasureReportIssueReq apiMeasureReportIssueReq, HttpServletRequest request) throws LjBaseRuntimeException,ParseException{
        return appMeasureService.reportIssue(apiMeasureReportIssueReq,request);
    }

}
