package com.longfor.longjian.measure.app.controller.proMeasureQuickSearchController;

import com.longfor.longjian.common.base.LjBaseResponse;
import com.longfor.longjian.common.entity.ProjectBase;
import com.longfor.longjian.common.entity.UserBase;
import com.longfor.longjian.common.exception.LjBaseRuntimeException;
import com.longfor.longjian.common.util.CtrlTool;
import com.longfor.longjian.common.util.SessionInfo;
import com.longfor.longjian.measure.app.appService.proMeasureQuickSearchService.IMeasureDetailService;
import com.longfor.longjian.measure.app.req.MeasureList.MeasureDetailExportExcelReq;
import com.longfor.longjian.measure.app.vo.measureListVo.NullMsgVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * Jiazm 2019/01/18 10:56
 */
@RestController
@RequestMapping("/v3/measure/measure_detail/")
@Slf4j
public class MeasureDetailController {
    @Resource
    private CtrlTool ctrlTool;
    @Resource
    private IMeasureDetailService measureDetailService;
    @Resource
    private SessionInfo sessionInfo;
    /**
     * 项目实测任务详情到处表格
     * http://192.168.37.159:3000/project/8/interface/api/2952
     * http://192.168.37.159:3000/mock/8/longjian.longhu.net/v3/measure/measure_detail/export_excel/?_ct=json&project_id=930&page_level=project&group_id=4&team_id=25
     *
     * @param request
     * @param req
     * @return
     * @throws Exception
     */
    @PostMapping(value = "export_excel", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<NullMsgVo> exportExcel(HttpServletRequest request, @Valid MeasureDetailExportExcelReq req, HttpServletResponse response) throws Exception {
        LjBaseResponse<NullMsgVo> ljBaseResponse = new LjBaseResponse<>();
        try {
            ctrlTool.projPerm(request, "项目.实测实量.任务管理.查看");
            UserBase user = sessionInfo.getSessionUser();
            ProjectBase cur_proj = (ProjectBase)sessionInfo.getBaseInfo("cur_proj");
            measureDetailService.exportExcelAsync(1, 1, req.getList_id(), response);
        } catch (Exception e) {
            log.error("error:" + e);
            throw new LjBaseRuntimeException(-9999,e.getMessage());
        }
        NullMsgVo nullMsgVo = new NullMsgVo();
        ljBaseResponse.setData(nullMsgVo);
        return ljBaseResponse;
    }
}
