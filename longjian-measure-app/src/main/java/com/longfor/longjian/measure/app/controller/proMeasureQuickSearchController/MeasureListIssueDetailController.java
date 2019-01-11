package com.longfor.longjian.measure.app.controller.proMeasureQuickSearchController;

import com.longfor.longjian.common.base.LjBaseResponse;
import com.longfor.longjian.measure.app.appService.proMeasureQuickSearchService.IMeasureListIssueDetailService;
import com.longfor.longjian.measure.app.req.proMeasureQuickSearchReq.GetMeasureListIssueDetailReq;
import com.longfor.longjian.measure.app.vo.proMeasureQuickSearchVo.MeasureListIssueDetailIssueInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @description: go服务
 * @author: DDC
 * @create: 2019-01-08 10:26
 **/
@RestController
@RequestMapping("oapi/v3/")
@Slf4j
public class MeasureListIssueDetailController {

    @Autowired
    IMeasureListIssueDetailService measureListIssueDetailService;

    @GetMapping(value = "measure/measure_list_issue_detail/issue_info/",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<MeasureListIssueDetailIssueInfoVo> IssueInfo(@Valid GetMeasureListIssueDetailReq req){
        return new LjBaseResponse(measureListIssueDetailService.IssueInfo(req));
    }
}


