package com.longfor.longjian.measure.app.appService.appService;

import com.longfor.longjian.common.base.LjBaseResponse;
import com.longfor.longjian.measure.app.req.appReq.ApiMeasureRegionReq;
import com.longfor.longjian.measure.app.req.appReq.ApiMeasureReportIssueReq;
import com.longfor.longjian.measure.app.vo.appMeasureSyncVo.MeasureRegionVo;
import com.longfor.longjian.measure.app.vo.appMeasureSyncVo.ReportIssueVo;

import javax.servlet.http.HttpServletRequest;

public interface IAPPMeasureSyncService {
    /**
     * 上报爆点issue数据
     * v3/api/measure/report_issue/
     * @param apiMeasureReportIssueReq
     * @param request
     * @return
     */
    LjBaseResponse<ReportIssueVo> reportIssue(ApiMeasureReportIssueReq apiMeasureReportIssueReq, HttpServletRequest request) throws Exception;

    /**
     *
     * @param apiMeasureRegionReq
     * @return
     */
    LjBaseResponse<MeasureRegionVo> getMeasureRegion(ApiMeasureRegionReq apiMeasureRegionReq) throws Exception;
}
