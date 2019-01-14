package com.longfor.longjian.measure.app.controller.MeasureListIssueController;

import com.longfor.longjian.common.base.LjBaseResponse;
import com.longfor.longjian.measure.app.appService.MeasureListIssueService.IMeasureListIssueAppService;
import com.longfor.longjian.measure.app.req.MeasureList.MeasureIssueCloseStatusReq;
import com.longfor.longjian.measure.app.req.MeasureList.MeasureIssueDeleteReq;
import com.longfor.longjian.measure.app.req.MeasureList.MeasureIssueEdiReq;
import com.longfor.longjian.measure.app.req.MeasureList.MeasureIssueQueryReq;
import com.longfor.longjian.measure.app.vo.measureListVo.MeasureIssueQueryVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Jiazm 2019/01/11 17:52
 */
@RestController
@RequestMapping("oapi/v3/measure/measure_list_issue/")
@Slf4j
public class MeasureListIssue2Controller {
    @Autowired
    private IMeasureListIssueAppService measureListIssueService;
    /**
     *http://192.168.37.159:3000/project/8/interface/api/2984
     * http://192.168.37.159:3000/mock/8/longjian.longhu.net/oapi/v3/measure/measure_list_issue/issue_query_json/?_ct=json&project_id=930&page_level=project&group_id=4&team_id=25&limit=10&page=1&category_key=&area_ids=&measure_list_ids
     * @return
     */
    @GetMapping(value = "issue_query_json/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<MeasureIssueQueryVo> issueQueryJson(MeasureIssueQueryReq measureIssueQueryReq, HttpServletRequest request) throws Exception {
        LjBaseResponse<MeasureIssueQueryVo> ljBaseResponse = measureListIssueService.issueQueryJson(measureIssueQueryReq,request);
        return ljBaseResponse;
    }
    @PostMapping(value = "issue_edit/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<Object> issueEdit(MeasureIssueEdiReq measureIssueEdiReq, HttpServletRequest request) {
        LjBaseResponse<Object> ljBaseResponse = new LjBaseResponse<>();
        return ljBaseResponse;
    }
    @PostMapping(value = "issue_close_status/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<Object> issueCloseStatus(MeasureIssueCloseStatusReq measureIssueQueryReq, HttpServletRequest request) {
        LjBaseResponse<Object> ljBaseResponse = new LjBaseResponse<>();
        return ljBaseResponse;
    }
    @PostMapping(value = "issue_del/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<Object> issueDel(MeasureIssueDeleteReq measureIssueQueryReq, HttpServletRequest request) {
        LjBaseResponse<Object> ljBaseResponse = new LjBaseResponse<>();
        return ljBaseResponse;
    }
}
