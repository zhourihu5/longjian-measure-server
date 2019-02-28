package com.longfor.longjian.measure.app.appservice.checkItemmeasureservice;

import com.longfor.longjian.common.base.LjBaseResponse;
import com.longfor.longjian.measure.app.req.checkitemmeasurereq.GetCategoryReq;
import com.longfor.longjian.measure.app.req.checkitemmeasurereq.GetCheckItemReq;
import com.longfor.longjian.measure.app.req.checkitemmeasurereq.ListTreeJsonReq;
import com.longfor.longjian.measure.app.req.checkitemmeasurereq.UpdateReqMeasureReq;
import com.longfor.longjian.measure.app.req.filereq.FileReq;
import com.longfor.longjian.measure.app.vo.checkitemsvo.CheckItemListVo;
import com.longfor.longjian.measure.app.vo.promeasurevo.CheckItemUpdateJsonVo;
import com.longfor.longjian.measure.app.vo.promeasurevo.GetCategoryVo;
import com.longfor.longjian.measure.app.vo.promeasurevo.GetCheckItemVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IOapiCheckItemMeasureService {
    /**
     * go集团实测检查项详情
     * @param getCategoryReq
     * @return
     */
    LjBaseResponse<GetCategoryVo> getCategoryJson(GetCategoryReq getCategoryReq, HttpServletRequest request) throws Exception;

    /**
     *
     * @param getCheckItemReq
     * @param request
     * @return
     */
    LjBaseResponse<GetCheckItemVo> getCheckItemJson(GetCheckItemReq getCheckItemReq, HttpServletRequest request) throws Exception;

    /**
     * go集团实测检查项上传
     * @param updateReqMeasureReq
     * @param request
     * @return
     */
    LjBaseResponse<CheckItemUpdateJsonVo> updateJson(UpdateReqMeasureReq updateReqMeasureReq, HttpServletRequest request);

    /**
     * go集团实测检查项管理上传
     * @param listTreeJsonReq
     * @param request
     * @return
     */
    LjBaseResponse<CheckItemListVo> listTreeJson(ListTreeJsonReq listTreeJsonReq, HttpServletRequest request);

    /**
     * 集团检查项管理下载
     * @param fileReq
     * @param request
     * @return
     */
    LjBaseResponse<Object> file(FileReq fileReq, HttpServletRequest request, HttpServletResponse response) throws Exception;
}
