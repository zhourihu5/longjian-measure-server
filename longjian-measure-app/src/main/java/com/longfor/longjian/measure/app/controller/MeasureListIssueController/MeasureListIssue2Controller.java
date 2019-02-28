package com.longfor.longjian.measure.app.controller.MeasureListIssueController;

import com.longfor.longjian.common.base.LjBaseResponse;
import com.longfor.longjian.common.entity.UserBase;
import com.longfor.longjian.common.exception.LjBaseRuntimeException;
import com.longfor.longjian.common.util.CtrlTool;
import com.longfor.longjian.common.util.SessionInfo;
import com.longfor.longjian.measure.app.appService.MeasureListIssueService.IMeasureListIssueAppService;
import com.longfor.longjian.measure.app.appService.proMeasureQuickSearchService.IProMeasureListIssueService;
import com.longfor.longjian.measure.app.req.MeasureList.MeasureIssueCloseStatusReq;
import com.longfor.longjian.measure.app.req.MeasureList.MeasureIssueDeleteReq;
import com.longfor.longjian.measure.app.req.MeasureList.MeasureIssueEdiReq;
import com.longfor.longjian.measure.app.req.MeasureList.MeasureIssueQueryReq;
import com.longfor.longjian.measure.app.vo.measureListVo.MeasureIssueQueryVo;
import com.longfor.longjian.measure.app.vo.measureListVo.UpdateVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Jiazm 2019/01/11 17:52
 */
@RestController
@RequestMapping("oapi/v3/measure/measure_list_issue/")
@Slf4j
public class MeasureListIssue2Controller {
    @Autowired
    private IMeasureListIssueAppService measureListIssueService;
    @Autowired
    private IProMeasureListIssueService proMeasureListIssueService;
    @Resource
    private CtrlTool ctrlTool;
    @Resource
    private SessionInfo sessionInfo;

    /**
     * go项目实测爆点整改搜所
     *http://192.168.37.159:3000/project/8/interface/api/2984
     * http://192.168.37.159:3000/mock/8/longjian.longhu.net/oapi/v3/measure/measure_list_issue/issue_query_json/?_ct=json&project_id=930&page_level=project&group_id=4&team_id=25&limit=10&page=1&category_key=&area_ids=&measure_list_ids
     * @return
     */
    @RequestMapping(value = "issue_query_json/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<MeasureIssueQueryVo> issueQueryJson(@Valid MeasureIssueQueryReq measureIssueQueryReq, HttpServletRequest request) throws Exception {
        LjBaseResponse<MeasureIssueQueryVo> ljBaseResponse = measureListIssueService.issueQueryJson(measureIssueQueryReq,request);
        return ljBaseResponse;
    }

    /**
     * go项目实测爆点列表编辑
     *
     * http://192.168.37.159:3000/project/8/interface/api/3064
     * http://192.168.37.159:3000/mock/8/oapi/v3/measure/measure_list_issue/issue_edit
     * @param request
     * @return
     */
    @RequestMapping(value = "issue_edit/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<UpdateVo> issueEdit(@Valid MeasureIssueEdiReq req, HttpServletRequest request) throws Exception {
        UserBase userBase =null;
        try {
            ctrlTool.projPerm(request, "项目.实测实量.爆点管理.编辑");
            userBase = sessionInfo.getSessionUser();
        }catch (Exception e){
            throw new LjBaseRuntimeException(-9999,e.getMessage());
        }
        LjBaseResponse<UpdateVo> ljBaseResponse = new LjBaseResponse<>();
        UpdateVo updateVo = new UpdateVo();
        try {
            Integer uid = userBase.getUserId();
            measureListIssueService.updateMeasureListIssueByProjUuid(req.getProject_id(), req.getUuid(), req.getRepairer_id(), uid, req.getPlan_end_on());
        } catch (Exception e) {
            log.error("error:" + e);
            throw new Exception(e);
        }
        ljBaseResponse.setData(updateVo);
        return ljBaseResponse;
    }

    /**
     * go项目实测爆点关闭问题
     * http://192.168.37.159:3000/project/8/interface/api/3052
     * http://192.168.37.159:3000/mock/8/devlongjian.longhu.net/oapi/v3/measure/measure_list_issue/issue_close_status/?_ct=json
     * @param req
     * @param request
     * @return
     */
    @RequestMapping(value = "issue_close_status/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<UpdateVo> issueCloseStatus(@Valid MeasureIssueCloseStatusReq req, HttpServletRequest request) throws Exception {
        UserBase userBase =null;
        try {
            ctrlTool.projPerm(request, "项目.实测实量.爆点管理.编辑");
            userBase = sessionInfo.getSessionUser();
        }catch (Exception e){
            throw new LjBaseRuntimeException(-9999,e.getMessage());
        }
        LjBaseResponse<UpdateVo> ljBaseResponse = new LjBaseResponse<>();
        UpdateVo updateVo = new UpdateVo();
        //TODO 在此添加Controller逻辑（golang中就是todo）
        Integer uid =userBase.getUserId();
        proMeasureListIssueService.updateIssueCloseStatusByUuid(req.getUuid(),req.getProject_id(),uid,req.getClose_status());
        ljBaseResponse.setData(updateVo);
        return ljBaseResponse;
    }

    /**
     * go项目实测爆点删除问题
     * http://192.168.37.159:3000/project/8/interface/api/3060
     * http://192.168.37.159:3000/mock/8//devlongjian.longhu.net/oapi/v3/measure/measure_list_issue/issue_del/?_ct=json
     * @param req
     * @param request
     * @return
     */
    @RequestMapping(value = "issue_del/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<UpdateVo> issueDel(@Valid MeasureIssueDeleteReq req, HttpServletRequest request) throws Exception {
        try {
            ctrlTool.projPerm(request,"项目.实测实量.爆点管理.删除");
        }catch (Exception e){
            throw new LjBaseRuntimeException(-9999,e.getMessage());
        }
        LjBaseResponse<UpdateVo> ljBaseResponse = new LjBaseResponse<>();
        UpdateVo updateVo = new UpdateVo();
        //TODO 在此添加Controller逻辑（golang中就是todo）
        proMeasureListIssueService.measureListIssueDeleteByProjUuid(req.getProject_id(),req.getUuid());
        ljBaseResponse.setData(updateVo);
        return ljBaseResponse;
    }
}
