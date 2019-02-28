package com.longfor.longjian.measure.app.appservice.appservice;

import com.longfor.longjian.common.base.LjBaseResponse;
import com.longfor.longjian.measure.app.req.appreq.*;
import com.longfor.longjian.measure.app.vo.appmeasuresyncvo.*;

import javax.servlet.http.HttpServletRequest;

public interface IAPPMeasureService {
    /**
     * 上报爆点issue数据
     * v3/api/measure/report_issue/
     * @param apiMeasureReportIssueReq
     * @param request
     * @return
     */
    LjBaseResponse<DroppedInfoVo> reportIssue(ApiMeasureReportIssueReq apiMeasureReportIssueReq, HttpServletRequest request) throws Exception;

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

    /**
     *
     * @param apiMeasureRegionRelReqV2
     * @return
     */
    LjBaseResponse<MeasureRegionRelV2Vo> getMeasureRegionRelV2(ApiMeasureRegionRelReqV2 apiMeasureRegionRelReqV2) throws Exception;

    /**
     *
     * @param apiMeasureSquadAndRepairerReq
     * @return
     */
    LjBaseResponse<MeasureSquadAndRepairerVo> measureSquadAndRepairer(ApiMeasureSquadAndRepairerReq apiMeasureSquadAndRepairerReq) throws Exception;

    /**
     *
     * @param apiMeasureZoneResultReq
     * @return
     */
    LjBaseResponse<MeasureZoneResultVo> measureZoneResult(ApiMeasureZoneResultReq apiMeasureZoneResultReq) throws Exception;

    /**
     *
     * @param apiMeasureZoneResultReqV2
     * @return
     */
    LjBaseResponse<MeasureZoneResultVo> measureZoneResultV2(ApiMeasureZoneResultReqV2 apiMeasureZoneResultReqV2) throws Exception;

    /**
     *
     * @param apiMeasureZoneResultTotalReqV2
     * @return
     */
    LjBaseResponse<TotalVo> measureZoneResultV2Total(ApiMeasureZoneResultTotalReqV2 apiMeasureZoneResultTotalReqV2) throws Exception;

    /**
     *
     * @param apiMeasureReportZoneResultReq
     * @param request
     * @return
     */
    LjBaseResponse<DroppedInfoVo> reportZoneResult(ApiMeasureReportZoneResultReq apiMeasureReportZoneResultReq, HttpServletRequest request) throws Exception;
}