package com.longfor.longjian.measure.app.appService.CheckItemMeasureService;

import com.longfor.longjian.common.base.LjBaseResponse;
import com.longfor.longjian.measure.app.req.CheckItemMeasureReq.GetCategoryReq;
import com.longfor.longjian.measure.app.req.CheckItemMeasureReq.ListTreeJsonReq;
import com.longfor.longjian.measure.app.req.CheckItemMeasureReq.UpdateReqMeasureReq;
import com.longfor.longjian.measure.app.vo.checkItemsVo.CheckItemListVo;
import com.longfor.longjian.measure.app.vo.proMeasureVo.GetCategoryVo;
import com.longfor.longjian.measure.app.req.CheckItemMeasureReq.GetCheckItemReq;
import com.longfor.longjian.measure.app.vo.proMeasureVo.GetCheckItemVo;
import com.longfor.longjian.measure.app.vo.proMeasureVo.CheckItemUpdateJsonVo;

import javax.servlet.http.HttpServletRequest;

public interface IOapiCheckItemMeasureService {
    /**
     * go集团实测检查项详情
     * @param getCategoryReq
     * @return
     */
    LjBaseResponse<GetCategoryVo> getCategoryJson(GetCategoryReq getCategoryReq, HttpServletRequest request);

    /**
     *
     * @param getCheckItemReq
     * @param request
     * @return
     */
    LjBaseResponse<GetCheckItemVo> getCheckItemJson(GetCheckItemReq getCheckItemReq, HttpServletRequest request);

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
}
