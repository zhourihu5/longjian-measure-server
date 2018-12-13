package com.longfor.longjian.measure.app.appService.appService;

import com.longfor.longjian.common.base.LjBaseResponse;
import com.longfor.longjian.measure.app.req.appReq.ApiMeasureRegionReq;
import com.longfor.longjian.measure.app.req.appReq.ApiMeasureRegionReqV2;
import com.longfor.longjian.measure.app.req.appReq.ApiMeasureRegionTotalReqV2;
import com.longfor.longjian.measure.app.req.appReq.ApiMeasureReportIssueReq;
import com.longfor.longjian.measure.app.vo.appMeasureSyncVo.MeasureRegionV2Vo;
import com.longfor.longjian.measure.app.vo.appMeasureSyncVo.MeasureRegionVo;
import com.longfor.longjian.measure.app.vo.appMeasureSyncVo.ReportIssueVo;
import com.longfor.longjian.measure.app.vo.appMeasureSyncVo.TotalVo;

import javax.servlet.http.HttpServletRequest;

public interface IAPPMeasureService {
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

    /**
     *
     * @param apiMeasureRegionReqV2
     * @return
     */
    LjBaseResponse<MeasureRegionV2Vo> getMeasureRegionV2(ApiMeasureRegionReqV2 apiMeasureRegionReqV2) throws Exception;

    /**
     *
     * @param apiMeasureRegionTotalReqV2
     * @return
     */
    LjBaseResponse<TotalVo> getMeasureRegionV2Total(ApiMeasureRegionTotalReqV2 apiMeasureRegionTotalReqV2) throws Exception;
}
