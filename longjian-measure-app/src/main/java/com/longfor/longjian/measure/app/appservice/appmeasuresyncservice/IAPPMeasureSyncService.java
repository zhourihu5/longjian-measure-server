package com.longfor.longjian.measure.app.appservice.appmeasuresyncservice;

import com.longfor.longjian.common.base.LjBaseResponse;
import com.longfor.longjian.common.exception.LjBaseRuntimeException;
import com.longfor.longjian.measure.app.req.apimeasurerulereq.*;
import com.longfor.longjian.measure.app.vo.appmeasuresyncvo.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Jiazm
 * 2018/12/11
 */
public interface IAPPMeasureSyncService {
    /**
     * 读取测量规则
     * @param apiMeasureRuleReq
     * @return
     */
    LjBaseResponse<RuleListVo> getMeasureRule(ApiMeasureRuleReq apiMeasureRuleReq) throws LjBaseRuntimeException;

    /**
     * 获取与“我”相关的实测实量清单
     * @param apiMyTaskReq
     * @return
     */
    LjBaseResponse<MyTaskVo> getMyTask(ApiMyTaskReq apiMyTaskReq, HttpServletRequest request) throws LjBaseRuntimeException;

    /**
     * 读取实测清单下的测区。此为全量接口
     * @param apiMeasureZoneReq
     * @return
     */
    LjBaseResponse<MeasureZoneVo> getMeasureZone(ApiMeasureZoneReq apiMeasureZoneReq) throws LjBaseRuntimeException;

    /**
     * 读取实测清单下的测区-分批
     * @param apiMeasureZoneReqV2
     * @return
     */
    LjBaseResponse<MeasureZoneVo> getMeasureZoneV2(ApiMeasureZoneReqV2 apiMeasureZoneReqV2) throws LjBaseRuntimeException;

    /**
     * 读取实测清单下的测区的总数
     * @param apiMeasureZoneTotalReqV2
     * @return
     */
    LjBaseResponse<TotalVo> getMeasureZoneV2Total(ApiMeasureZoneTotalReqV2 apiMeasureZoneTotalReqV2) throws LjBaseRuntimeException;

    /**
     * 读取Issue爆点整改数据
     * @param apiMeasureIssueReq
     * @return
     */
    LjBaseResponse<IssueVo> issue(ApiMeasureIssueReq apiMeasureIssueReq) throws LjBaseRuntimeException;

    /**
     * 读取Issue爆点整改数据日志
     * @param apiMeasureIssueLogReq
     * @return
     */
    LjBaseResponse<IssueLogVo> issueLog(ApiMeasureIssueLogReq apiMeasureIssueLogReq) throws LjBaseRuntimeException;

    /**
     * 上报新增的描画区域
     * @param apiMeasureReportRegionReq
     * @return
     */
    LjBaseResponse<DroppedInfoVo> reportRegion(ApiMeasureReportRegionReq apiMeasureReportRegionReq,HttpServletRequest request) throws LjBaseRuntimeException;

    /**
     * 上报新增的测区
     * @param apiMeasureReportZoneReq
     * @return
     */
    LjBaseResponse<DroppedInfoVo>  reportZone(ApiMeasureReportZoneReq apiMeasureReportZoneReq,HttpServletRequest request) throws LjBaseRuntimeException;
}
