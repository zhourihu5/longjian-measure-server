package com.longfor.longjian.measure.app.appservice.appservice;

import com.longfor.longjian.common.base.LjBaseResponse;
import com.longfor.longjian.common.exception.LjBaseRuntimeException;
import com.longfor.longjian.measure.app.req.appreq.*;
import com.longfor.longjian.measure.app.vo.appmeasuresyncvo.*;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

public interface IAPPMeasureService {
    /**
     * 上报爆点issue数据
     * v3/api/measure/report_issue/
     * @param apiMeasureReportIssueReq
     * @param request
     * @return
     */
    LjBaseResponse<DroppedInfoVo> reportIssue(ApiMeasureReportIssueReq apiMeasureReportIssueReq, HttpServletRequest request) throws LjBaseRuntimeException,ParseException;

    /**
     *
     * @param apiMeasureRegionReq
     * @return
     */
    LjBaseResponse<MeasureRegionVo> getMeasureRegion(ApiMeasureRegionReq apiMeasureRegionReq) throws LjBaseRuntimeException;

    /**
     *
     * @param apiMeasureRegionReqV2
     * @return
     */
    LjBaseResponse<MeasureRegionV2Vo> getMeasureRegionV2(ApiMeasureRegionReqV2 apiMeasureRegionReqV2) throws LjBaseRuntimeException;

    /**
     *
     * @param apiMeasureRegionTotalReqV2
     * @return
     */
    LjBaseResponse<TotalVo> getMeasureRegionV2Total(ApiMeasureRegionTotalReqV2 apiMeasureRegionTotalReqV2) throws LjBaseRuntimeException;

    /**
     *
     * @param apiMeasureRegionRelReqV2
     * @return
     */
    LjBaseResponse<MeasureRegionRelV2Vo> getMeasureRegionRelV2(ApiMeasureRegionRelReqV2 apiMeasureRegionRelReqV2) throws LjBaseRuntimeException;

    /**
     *
     * @param apiMeasureSquadAndRepairerReq
     * @return
     */
    LjBaseResponse<MeasureSquadAndRepairerVo> measureSquadAndRepairer(ApiMeasureSquadAndRepairerReq apiMeasureSquadAndRepairerReq) throws LjBaseRuntimeException;

    /**
     *
     * @param apiMeasureZoneResultReq
     * @return
     */
    LjBaseResponse<MeasureZoneResultVo> measureZoneResult(ApiMeasureZoneResultReq apiMeasureZoneResultReq) throws LjBaseRuntimeException;

    /**
     *
     * @param apiMeasureZoneResultReqV2
     * @return
     */
    LjBaseResponse<MeasureZoneResultVo> measureZoneResultV2(ApiMeasureZoneResultReqV2 apiMeasureZoneResultReqV2) throws LjBaseRuntimeException;

    /**
     *
     * @param apiMeasureZoneResultTotalReqV2
     * @return
     */
    LjBaseResponse<TotalVo> measureZoneResultV2Total(ApiMeasureZoneResultTotalReqV2 apiMeasureZoneResultTotalReqV2) throws LjBaseRuntimeException;

    /**
     *
     * @param apiMeasureReportZoneResultReq
     * @param request
     * @return
     */
    LjBaseResponse<DroppedInfoVo> reportZoneResult(ApiMeasureReportZoneResultReq apiMeasureReportZoneResultReq, HttpServletRequest request) throws ParseException,LjBaseRuntimeException;
}
