package com.longfor.longjian.measure.app.appservice.measurelistissueservice;

import com.longfor.longjian.common.base.LjBaseResponse;
import com.longfor.longjian.common.exception.LjBaseRuntimeException;
import com.longfor.longjian.measure.app.req.measurelist.MeasureIssueQueryReq;
import com.longfor.longjian.measure.app.vo.measurelistvo.MeasureIssueQueryVo;

import javax.servlet.http.HttpServletRequest;

public interface IMeasureListIssueAppService {
    LjBaseResponse<MeasureIssueQueryVo> issueQueryJson(MeasureIssueQueryReq measureIssueQueryReq, HttpServletRequest request) throws LjBaseRuntimeException;

    void updateMeasureListIssueByProjUuid(Integer projectId, String uuid, Integer repairerId, Integer uid, Integer planEndOn) throws LjBaseRuntimeException;
}
