package com.longfor.longjian.measure.app.appService.MeasureListIssueService;

import com.longfor.longjian.common.base.LjBaseResponse;
import com.longfor.longjian.measure.app.req.MeasureList.MeasureIssueCloseStatusReq;
import com.longfor.longjian.measure.app.req.MeasureList.MeasureIssueDeleteReq;
import com.longfor.longjian.measure.app.req.MeasureList.MeasureIssueEdiReq;
import com.longfor.longjian.measure.app.req.MeasureList.MeasureIssueQueryReq;
import com.longfor.longjian.measure.app.vo.measureListVo.MeasureIssueQueryVo;
import com.longfor.longjian.measure.app.vo.measureListVo.UpdateVo;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

public interface IMeasureListIssueAppService {
    /**
     *go项目实测爆点整改搜所
     * @param measureIssueQueryReq
     * @param request
     * @return
     */
    LjBaseResponse<MeasureIssueQueryVo> issueQueryJson(MeasureIssueQueryReq measureIssueQueryReq, HttpServletRequest request) throws Exception;
    /**
     *
     * @param project_id
     * @param uuid
     * @param repairer_id
     * @param uid
     * @param plan_end_on
     * @throws Exception
     */
    void updateMeasureListIssueByProjUuid(Integer project_id, String uuid, Integer repairer_id, Integer uid, Integer plan_end_on) throws Exception;
}
