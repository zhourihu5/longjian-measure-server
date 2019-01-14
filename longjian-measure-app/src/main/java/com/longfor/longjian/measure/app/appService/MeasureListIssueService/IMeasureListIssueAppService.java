package com.longfor.longjian.measure.app.appService.MeasureListIssueService;

import com.longfor.longjian.common.base.LjBaseResponse;
import com.longfor.longjian.measure.app.req.MeasureList.MeasureIssueQueryReq;
import com.longfor.longjian.measure.app.vo.measureListVo.MeasureIssueQueryVo;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

public interface IMeasureListIssueAppService {
    /**
     *
     * @param measureIssueQueryReq
     * @param request
     * @return
     */
    LjBaseResponse<MeasureIssueQueryVo> issueQueryJson(MeasureIssueQueryReq measureIssueQueryReq, HttpServletRequest request) throws Exception;
}
