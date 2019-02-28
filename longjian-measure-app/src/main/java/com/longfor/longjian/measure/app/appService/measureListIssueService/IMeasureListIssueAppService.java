package com.longfor.longjian.measure.app.appService.measureListIssueService;

import com.longfor.longjian.common.base.LjBaseResponse;
import com.longfor.longjian.measure.app.req.measureList.MeasureIssueQueryReq;
import com.longfor.longjian.measure.app.vo.measureListVo.MeasureIssueQueryVo;

import javax.servlet.http.HttpServletRequest;

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
