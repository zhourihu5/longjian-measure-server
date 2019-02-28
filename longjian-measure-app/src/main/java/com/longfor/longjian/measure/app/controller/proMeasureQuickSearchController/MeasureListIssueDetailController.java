package com.longfor.longjian.measure.app.controller.proMeasureQuickSearchController;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.longfor.longjian.common.base.LjBaseResponse;
import com.longfor.longjian.common.exception.LjBaseRuntimeException;
import com.longfor.longjian.common.util.CtrlTool;
import com.longfor.longjian.common.util.DateUtil;
import com.longfor.longjian.common.util.SessionInfo;
import com.longfor.longjian.measure.app.appService.proMeasureQuickSearchService.IMeasureListIssueDetailService;
import com.longfor.longjian.measure.app.appService.proMeasureQuickSearchService.IProMeasureListIssueLogService;
import com.longfor.longjian.measure.app.appService.proMeasureQuickSearchService.IProMeasureListIssueService;
import com.longfor.longjian.measure.app.req.proMeasureQuickSearchReq.*;
import com.longfor.longjian.measure.app.vo.ItemsVo;
import com.longfor.longjian.measure.app.vo.proMeasureQuickSearchVo.*;
import com.longfor.longjian.measure.consts.enums.MeasureListCloseStatusEnum;
import com.longfor.longjian.measure.consts.constant.CtrlToolConstant;
import com.longfor.longjian.measure.consts.constant.MeasureListIssueType;
import com.longfor.longjian.measure.domain.externalService.IMeasureListIssueService;
import com.longfor.longjian.measure.domain.externalService.IUserService;
import com.longfor.longjian.measure.po.zhijian2.MeasureListIssue;
import com.longfor.longjian.measure.po.zhijian2.MeasureListIssueLog;
import com.longfor.longjian.measure.po.zhijian2_apisvr.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.*;

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
    private IUserService userService;
    @Resource
    private CtrlTool ctrlTool;
    @Resource
    private SessionInfo sessionInfo;

    /**
     * @return com.longfor.longjian.common.base.LjBaseResponse<com.longfor.longjian.measure.app.vo.proMeasureQuickSearchVo.MeasureListIssueDetailIssueInfoVo>
     * @Description: go项目实测爆点详情
     * http://192.168.37.159:3000/project/8/interface/api/3008
     * @author DDC
     * @date 2019/1/14 14:31
     **/
    @RequestMapping(value = "issue_info/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<MeasureListIssueDetailIssueInfoVo> IssueInfo(@Valid GetMeasureListIssueDetailReq req, HttpServletRequest request) throws Exception {
        try {
            ctrlTool.projPerm(request, CtrlToolConstant.PROJECT_MEASURE_ISSUEMANAGE_CHECK);
        } catch (Exception e) {
            throw new LjBaseRuntimeException(-9999, e.getMessage());
        }
        return new LjBaseResponse<>(measureListIssueDetailService.IssueInfo(req));
    }

    /**
     * @return com.longfor.longjian.common.base.LjBaseResponse<com.longfor.longjian.measure.app.vo.proMeasureQuickSearchVo.MeasureListIssueDetailZoneInfoVo>
     * @Description: go项目实测爆点详情查看测区
     * http://192.168.37.159:3000/project/8/interface/api/2996
     * @author DDC
     * @date 2019/1/14 14:34
     **/
    @RequestMapping(value = "zone_info/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<MeasureListIssueDetailZoneInfoVo> zoneInfo(@Valid GetMeasureListIssueDetailReq req, HttpServletRequest request) throws Exception {
        try {
            ctrlTool.projPerm(request, CtrlToolConstant.PROJECT_MEASURE_ISSUEMANAGE_CHECK);
        } catch (Exception e) {
            throw new LjBaseRuntimeException(-9999, e.getMessage());
        }
        return new LjBaseResponse<>(measureListIssueDetailService.zoneInfo(req));
    }


    /**
     * @return com.longfor.longjian.common.base.LjBaseResponse<java.util.List                                                               <                                                               com.longfor.longjian.measure.app.vo.proMeasureQuickSearchVo.MeasureListIssueDetailRepairerVo>>
     * @Description: go项目实测爆点整改测区详情
     * http://192.168.37.159:3000/project/8/interface/api/2992
     * @author DDC
     * @date 2019/1/14 14:59
     **/
    @RequestMapping(value = "repair_list/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<MeasureListIssueDetailRepairListVo> repairList(@Valid GetMeasureListIssueDetailReq req, HttpServletRequest request) throws Exception {
        try {
            ctrlTool.projPerm(request, CtrlToolConstant.PROJECT_MEASURE_ISSUEMANAGE_CHECK);
        } catch (Exception e) {
            throw new LjBaseRuntimeException(-9999, e.getMessage());
        }
        return new LjBaseResponse<>(measureListIssueDetailService.repairList(req));
    }

    /**
     * @return com.longfor.longjian.common.base.LjBaseResponse
     * @Description: go项目实测爆点详情修给整改人
     * http://192.168.37.159:3000/project/8/interface/api/3012
     * @author DDC
     * @date 2019/1/14 20:32
     **/
    @RequestMapping(value = "update_repairer/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse updateRepairer(@Valid MeasureListDetailUpdateIssueRepairerReq req, HttpServletRequest request) throws Exception {
        try {
            ctrlTool.projPerm(request, CtrlToolConstant.PROJECT_MEASURE_ISSUEMANAGE_COMPILE);
        } catch (Exception e) {
            throw new LjBaseRuntimeException(-9999, e.getMessage());
        }
        measureListIssueDetailService.updateRepairer(req);
        return new LjBaseResponse();
    }

    /**
     * http://192.168.37.159:3000/project/8/interface/api/3048
     * go项目实测爆点删除问题
     * /oapi/v3/measure/measure_list_issue_detail/delete/
     *
     * @return
     */
    @RequestMapping(value = "delete/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse delete(@Valid MeasureListDetailDeleteReq measureListDetailDeleteReq, HttpServletRequest request) throws Exception {
        //鉴权
        try {
            ctrlTool.projPerm(request, CtrlToolConstant.PROJECT_MEASURE_ISSUEMANAGE_DELETE);
        } catch (Exception e) {
            throw new LjBaseRuntimeException(-9999, e.getMessage());
        }
        proMeasureListIssueService.measureListIssueDeleteByProjUuid(measureListDetailDeleteReq.getProject_id(), measureListDetailDeleteReq.getUuid());
        return new LjBaseResponse();
    }

    /**
     * go项目实测爆点审核销项
     * http://192.168.37.159:3000/project/8/interface/api/3040
     *
     * @return
     */
    @RequestMapping(value = "update_approve_issue/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse updateApproveIssue(@Valid MeasureListDetailUpdateApproveIssueReq measureListDetailUpdateApproveIssueReq, HttpServletRequest request) throws Exception {
        //鉴权
        Integer uid = null;
        try {
            ctrlTool.projPerm(request, CtrlToolConstant.PROJECT_MEASURE_ISSUEMANAGE_COMPILE);
            uid = sessionInfo.getSessionUser().getUserId();
        } catch (Exception e) {
            throw new LjBaseRuntimeException(-9999, e.getMessage());
        }

        boolean isClosed = proMeasureListIssueService.updateIssueApproveStatusByUuid(measureListDetailUpdateApproveIssueReq.getUuid(), measureListDetailUpdateApproveIssueReq.getProject_id(), uid, measureListDetailUpdateApproveIssueReq.getStatus(), measureListDetailUpdateApproveIssueReq.getContent(), "");
        if (isClosed) {
            throw new Exception("问题已被关闭");
        }
        return new LjBaseResponse();
    }


    /**
     * http://192.168.37.159:3000/project/8/interface/api/3004
     * go项目实测爆点详情
     *
     * @return
     */
    @RequestMapping(value = "history_logs/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<ItemsVo<List<MeasureListIssueHistoryRepairLogVo>>> historyLogs(@Valid GetMeasureListIssueDetailReq measureListIssueDetailReq, HttpServletRequest request) throws Exception {
        LjBaseResponse<ItemsVo<List<MeasureListIssueHistoryRepairLogVo>>> ljBaseResponse = new LjBaseResponse<>();
        ItemsVo<List<MeasureListIssueHistoryRepairLogVo>> itemsVo = new ItemsVo<>();
        //鉴权
        try {
            ctrlTool.projPerm(request, CtrlToolConstant.PROJECT_MEASURE_ISSUEMANAGE_CHECK);
        } catch (Exception e) {
            throw new LjBaseRuntimeException(-9999, e.getMessage());
        }

        MeasureListIssue issue = measureListIssueService.GetIssueByProjectIdAndUuid(measureListIssueDetailReq.getProject_id(), measureListIssueDetailReq.getUuid());

        List<MeasureListIssueHistoryRepairLogVo> actionLogs = proMeasureListIssueLogService.getIssueActionLogByIssueUuid(measureListIssueDetailReq.getProject_id(), measureListIssueDetailReq.getUuid());
        itemsVo.setItems(actionLogs);
        ljBaseResponse.setData(itemsVo);
        return ljBaseResponse;
    }


    /**
     * http://192.168.37.159:3000/project/8/interface/api/3044
     * go项目实测爆点关闭问题
     *
     * @return
     */
    @RequestMapping(value = "update_close_status/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse updateCloseStatus(@Valid MeasureListDetailUpdateCloseStatusReq measureListDetailUpdateCloseStatusReq, HttpServletRequest request) throws Exception {
        //鉴权
        Integer uid = null;
        try {
            ctrlTool.projPerm(request, CtrlToolConstant.PROJECT_MEASURE_ISSUEMANAGE_COMPILE);
            uid = sessionInfo.getSessionUser().getUserId();
        } catch (Exception e) {
            throw new LjBaseRuntimeException(-9999, e.getMessage());
        }
        Integer status = MeasureListCloseStatusEnum.UNCLOSE.getId();
        if (measureListDetailUpdateCloseStatusReq.getClose_status()) {
            status = MeasureListCloseStatusEnum.CLOSED.getId();
        }
        proMeasureListIssueService.updateIssueCloseStatusByUuid(measureListDetailUpdateCloseStatusReq.getUuid(), measureListDetailUpdateCloseStatusReq.getProject_id(), uid, status);
        return new LjBaseResponse();
    }

    /**
     * @return com.longfor.longjian.common.base.LjBaseResponse
     * @Description: go项目实测爆点修给问题状态
     * http://192.168.37.159:3000/project/8/interface/api/3024
     * @author DDC
     * @date 2019/1/15 11:12
     **/
    @RequestMapping(value = "update_issue_type/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse updateIssueType(@Valid PostMeasureListDetailUpdateIssueTypeReq req, HttpServletRequest request) throws Exception {
        try {
            ctrlTool.projPerm(request, CtrlToolConstant.PROJECT_MEASURE_ISSUEMANAGE_COMPILE);
        } catch (Exception e) {
            throw new LjBaseRuntimeException(-9999, e.getMessage());
        }
        return measureListIssueDetailService.updateIssueType(req);
    }

    @RequestMapping(value = "update_plan_end_on/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse UpdatePlanEndOn(@Valid PostMeasureListDetailUpdateIssuePlanEndOnReq req, HttpServletRequest request) throws Exception {
        try {
            ctrlTool.projPerm(request, CtrlToolConstant.PROJECT_MEASURE_ISSUEMANAGE_COMPILE);
        } catch (Exception e) {
            throw new LjBaseRuntimeException(-9999, e.getMessage());
        }
        return measureListIssueDetailService.UpdatePlanEndOn(req);
    }

    @RequestMapping(value = "issue_status/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<MeasureListIssueGetIssueStatus> issueStatus(@Valid MeasureListIssueDetailReq req, HttpServletRequest request) throws Exception {
        //鉴权
        try {
            ctrlTool.projPerm(request, CtrlToolConstant.PROJECT_MEASURE_ISSUEMANAGE_CHECK);
        } catch (Exception e) {
            throw new LjBaseRuntimeException(-9999, e.getMessage());
        }
        return measureListIssueDetailService.getIssueByProjectIdAndUuid(req);
    }

    @RequestMapping(value = "repair_logs/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<MeasureListIssueDetailRepairLogListVo> repairLogs(@Valid MeasureListIssueDetailReq req, HttpServletRequest request) throws Exception {
        LjBaseResponse<MeasureListIssueDetailRepairLogListVo> ljBaseResponse = new LjBaseResponse<>();
        MeasureListIssueDetailRepairLogListVo measureListIssueDetailRepairLogListVo = new MeasureListIssueDetailRepairLogListVo();
        List<MeasureListIssueDetailRepairLogVo> measureListIssueDetailRepairLogVos = Lists.newArrayList();
        //鉴权
        try {
            ctrlTool.projPerm(request, CtrlToolConstant.PROJECT_MEASURE_ISSUEMANAGE_CHECK);
            MeasureListIssue issue = measureListIssueService.GetIssueByProjectIdAndUuid(req.getProject_id(), req.getUuid());
            if (issue == null) {
                throw new LjBaseRuntimeException(-1, "该爆点信息不存在");
            }
            List<MeasureListIssueLog> measureListIssueLogs = proMeasureListIssueLogService.searchIssueLogByIssueUuidAndStatus(req.getProject_id(), issue.getUuid(), MeasureListIssueType.REFORMNOCHECK);
            Set<Integer> uids = Sets.newHashSet();
            measureListIssueLogs.forEach(measureListIssueLog -> {
                uids.add(measureListIssueLog.getSenderId());
            });
            if (uids.size() > 0) {
                Map<Integer, User> userMap = userService.getUsersByIds(new ArrayList<>(uids));
                measureListIssueLogs.forEach(measureListIssueLog -> {
                    MeasureListIssueDetailRepairLogVo measureListIssueDetailRepairLogVo = new MeasureListIssueDetailRepairLogVo();
                    measureListIssueDetailRepairLogVo.setContent(measureListIssueLog.getDesc());
                    measureListIssueDetailRepairLogVo.setUser_id(measureListIssueLog.getSenderId());
                    measureListIssueDetailRepairLogVo.setCreate_at(measureListIssueLog.getCreateAt() == null ? 0 : DateUtil.dateToTimestamp(measureListIssueLog.getCreateAt()));
                    measureListIssueDetailRepairLogVo.setAttachment_md5_list(measureListIssueLog.getAttachmentMd5List().length() > 0 ? Arrays.asList(StringUtils.split(measureListIssueLog.getAttachmentMd5List(), ",")) : Arrays.asList(measureListIssueLog.getAttachmentMd5List()));
                    if (userMap.get(measureListIssueLog.getSenderId()) != null) {
                        measureListIssueDetailRepairLogVo.setUser_name(userMap.get(measureListIssueLog.getSenderId()).getRealName());
                    }
                    measureListIssueDetailRepairLogVos.add(measureListIssueDetailRepairLogVo);
                });
                measureListIssueDetailRepairLogListVo.setItems(measureListIssueDetailRepairLogVos);
            }
            ljBaseResponse.setData(measureListIssueDetailRepairLogListVo);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new LjBaseRuntimeException(-9999, e.getMessage());
        }
        return ljBaseResponse;
    }
}


