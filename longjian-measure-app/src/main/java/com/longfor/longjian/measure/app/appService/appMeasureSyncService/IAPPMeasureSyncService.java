package com.longfor.longjian.measure.app.appService.appMeasureSyncService;

import com.longfor.longjian.common.base.LjBaseResponse;
import com.longfor.longjian.measure.app.req.apiMeasureRuleReq.*;
import com.longfor.longjian.measure.app.vo.appMeasureSyncVo.*;

import javax.servlet.http.HttpServletRequest;
import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;

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
    LjBaseResponse<MeasureRuleListVo> getMeasureRule(ApiMeasureRuleReq apiMeasureRuleReq) throws Exception;

    /**
     * 获取与“我”相关的实测实量清单
     * @param apiMyTaskReq
     * @return
     */
    LjBaseResponse<MeasureListListVo> getMyTask(ApiMyTaskReq apiMyTaskReq, HttpServletRequest request) throws Exception;

    /**
     * 读取实测清单下的测区。此为全量接口
     * @param apiMeasureZoneReq
     * @return
     */
    LjBaseResponse<MeasureZoneListVo> getMeasureZone(ApiMeasureZoneReq apiMeasureZoneReq) throws Exception;

    /**
     * 读取实测清单下的测区-分批
     * @param apiMeasureZoneReqV2
     * @return
     */
    LjBaseResponse<MeasureZoneListVo> getMeasureZoneV2(ApiMeasureZoneReqV2 apiMeasureZoneReqV2) throws Exception;

    /**
     * 读取实测清单下的测区的总数
     * @param apiMeasureZoneTotalReqV2
     * @return
     */
    LjBaseResponse<TotalVo> getMeasureZoneV2Total(ApiMeasureZoneTotalReqV2 apiMeasureZoneTotalReqV2) throws Exception;

    /**
     * 读取Issue爆点整改数据
     * @param apiMeasureIssueReq
     * @return
     */
    LjBaseResponse<MeasureIssueListVo> issue(ApiMeasureIssueReq apiMeasureIssueReq) throws Exception;

    /**
     * 读取Issue爆点整改数据日志
     * @param apiMeasureIssueLogReq
     * @return
     */
    LjBaseResponse<MeasureIssueLogListVo> issueLog(ApiMeasureIssueLogReq apiMeasureIssueLogReq) throws Exception;

    /**
     * 上报新增的描画区域
     * @param apiMeasureReportRegionReq
     * @return
     */
    LjBaseResponse<DroppedInfoVo> reportRegion(ApiMeasureReportRegionReq apiMeasureReportRegionReq,HttpServletRequest request) throws Exception;

    /**
     * 上报新增的测区
     * @param apiMeasureReportZoneReq
     * @return
     */
    LjBaseResponse<DroppedInfoVo>  reportZone(ApiMeasureReportZoneReq apiMeasureReportZoneReq);
}
