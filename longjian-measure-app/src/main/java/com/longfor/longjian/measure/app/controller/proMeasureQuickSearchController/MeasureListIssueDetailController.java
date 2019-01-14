package com.longfor.longjian.measure.app.controller.proMeasureQuickSearchController;

import com.longfor.longjian.common.base.LjBaseResponse;
import com.longfor.longjian.common.util.CtrlTool;
import com.longfor.longjian.measure.app.appService.proMeasureQuickSearchService.IMeasureListIssueDetailService;
import com.longfor.longjian.measure.app.appService.proMeasureQuickSearchService.IProMeasureListIssueLogService;
import com.longfor.longjian.measure.app.appService.proMeasureQuickSearchService.IProMeasureListIssueService;
import com.longfor.longjian.measure.app.req.proMeasureQuickSearchReq.GetMeasureListIssueDetailReq;
import com.longfor.longjian.measure.app.req.proMeasureQuickSearchReq.MeasureListDetailDeleteReq;
import com.longfor.longjian.measure.app.req.proMeasureQuickSearchReq.MeasureListDetailUpdateApproveIssueReq;
import com.longfor.longjian.measure.app.req.proMeasureQuickSearchReq.MeasureListDetailUpdateCloseStatusReq;
import com.longfor.longjian.measure.app.vo.ItemsVo;
import com.longfor.longjian.measure.app.vo.proMeasureQuickSearchVo.MeasureListIssueDetailIssueInfoVo;
import com.longfor.longjian.measure.app.vo.proMeasureQuickSearchVo.MeasureListIssueHistoryRepairLogVo;
import com.longfor.longjian.measure.consts.Enum.MeasureListCloseStatusEnum;
import com.longfor.longjian.measure.domain.externalService.IMeasureListIssueService;
import com.longfor.longjian.measure.po.zhijian2.MeasureListIssue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * @description: go服务
 * @author: DDC
 * @create: 2019-01-08 10:26
 **/
@RestController
@RequestMapping("oapi/v3/measure/measure_list_issue_detail/")
@Slf4j
public class MeasureListIssueDetailController {

    @Autowired
    IMeasureListIssueDetailService measureListIssueDetailService;
    @Resource
    IProMeasureListIssueService proMeasureListIssueService;
    @Resource
    IMeasureListIssueService measureListIssueService;
    @Resource
    private IProMeasureListIssueLogService proMeasureListIssueLogService;
    @Resource
    private CtrlTool ctrlTool;

    @GetMapping(value = "measure/measure_list_issue_detail/issue_info/",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<MeasureListIssueDetailIssueInfoVo> IssueInfo(@Valid GetMeasureListIssueDetailReq req){
        return new LjBaseResponse(measureListIssueDetailService.IssueInfo(req));
    }

    /**
     * http://192.168.37.159:3000/project/8/interface/api/3048
     * go项目实测爆点删除问题
     * /oapi/v3/measure/measure_list_issue_detail/delete/
     * @return
     */
    @PostMapping(value = "delete/",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse delete(@Valid @RequestBody MeasureListDetailDeleteReq measureListDetailDeleteReq, HttpServletRequest request) throws Exception {
        //鉴权
        ctrlTool.projPerm(request,"项目.实测实量.爆点管理.删除");
        proMeasureListIssueService.measureListIssueDeleteByProjUuid(measureListDetailDeleteReq.getProject_id(),measureListDetailDeleteReq.getUuid());
        return new LjBaseResponse();
    }

    /**
     * go项目实测爆点审核销项
     * http://192.168.37.159:3000/project/8/interface/api/3040
     * @return
     */
    @PostMapping(value = "update_approve_issue/",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse updateApproveIssue(@Valid @RequestBody MeasureListDetailUpdateApproveIssueReq measureListDetailUpdateApproveIssueReq,HttpServletRequest request) throws Exception {
        //鉴权
        ctrlTool.projPerm(request,"项目.实测实量.爆点管理.编辑");

        // todo uId := getCurUid(c)
        Integer uid = 7566;

        boolean isClosed = proMeasureListIssueService.updateIssueApproveStatusByUuid(measureListDetailUpdateApproveIssueReq.getUuid(),measureListDetailUpdateApproveIssueReq.getProjectId(),uid,measureListDetailUpdateApproveIssueReq.getStatus(),measureListDetailUpdateApproveIssueReq.getContent(),"");
        if (isClosed){
            throw new Exception("问题已被关闭");
        }
        return new LjBaseResponse();
    }


    /**
     * http://192.168.37.159:3000/project/8/interface/api/3004
     * go项目实测爆点详情
     * @return
     */
    @GetMapping(value = "history_logs/",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<ItemsVo<List<MeasureListIssueHistoryRepairLogVo>>> historyLogs(@Valid GetMeasureListIssueDetailReq measureListIssueDetailReq, HttpServletRequest request) throws Exception {
        LjBaseResponse<ItemsVo<List<MeasureListIssueHistoryRepairLogVo>>> ljBaseResponse = new LjBaseResponse<>();
        ItemsVo<List<MeasureListIssueHistoryRepairLogVo>> itemsVo = new ItemsVo<>();
        //鉴权
        ctrlTool.projPerm(request,"项目.实测实量.爆点管理.查看");

        MeasureListIssue issue = measureListIssueService.GetIssueByProjectIdAndUuid(measureListIssueDetailReq.getProject_id(),measureListIssueDetailReq.getUuid());

        List<MeasureListIssueHistoryRepairLogVo> actionLogs = proMeasureListIssueLogService.getIssueActionLogByIssueUuid(measureListIssueDetailReq.getProject_id(),measureListIssueDetailReq.getUuid());
        itemsVo.setItems(actionLogs);
        ljBaseResponse.setData(itemsVo);
        return ljBaseResponse;
    }


    /**
     * http://192.168.37.159:3000/project/8/interface/api/3044
     * go项目实测爆点关闭问题
     * @return
     */
    @PostMapping(value = "update_close_status/",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse updateCloseStatus(@Valid @RequestBody MeasureListDetailUpdateCloseStatusReq measureListDetailUpdateCloseStatusReq,HttpServletRequest request) throws Exception {
        //鉴权
        ctrlTool.projPerm(request,"项目.实测实量.爆点管理.编辑");
        // todo uId := getCurUid(c)
        Integer uid = 7566;

        Integer status = MeasureListCloseStatusEnum.UnClose.getId();
        if (measureListDetailUpdateCloseStatusReq.getClose_status()){
            status = MeasureListCloseStatusEnum.Closed.getId();
        }

        proMeasureListIssueService.updateIssueCloseStatusByUuid(measureListDetailUpdateCloseStatusReq.getUuid(),measureListDetailUpdateCloseStatusReq.getProject_id(),uid,status);
        return new LjBaseResponse();
    }

}


