package com.longfor.longjian.measure.app.controller.checkitemcontroller;

import com.longfor.longjian.common.base.LjBaseResponse;
import com.longfor.longjian.measure.app.appservice.checkItemmeasureservice.IOapiCheckItemMeasureService;
import com.longfor.longjian.measure.app.req.checkitemmeasurereq.GetCategoryReq;
import com.longfor.longjian.measure.app.req.checkitemmeasurereq.GetCheckItemReq;
import com.longfor.longjian.measure.app.req.checkitemmeasurereq.ListTreeJsonReq;
import com.longfor.longjian.measure.app.req.checkitemmeasurereq.UpdateReqMeasureReq;
import com.longfor.longjian.measure.app.vo.checkitemsvo.CheckItemListVo;
import com.longfor.longjian.measure.app.vo.promeasurevo.CheckItemUpdateJsonVo;
import com.longfor.longjian.measure.app.vo.promeasurevo.GetCategoryVo;
import com.longfor.longjian.measure.app.vo.promeasurevo.GetCheckItemVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Created by Wang on 2019/1/7.
 */
@RestController
@RequestMapping("oapi/v3/check_item/measure/")
@Slf4j
public class OapiCheckItemMeasureController {
    @Resource
    private IOapiCheckItemMeasureService oapiCheckItemMeasureService;

    /**
     * go集团实测检查项详情
     * http://192.168.37.159:3000/project/8/interface/api/2692
     * http://192.168.37.159:3000/mock/8/oapi/v3/check_item/measure/get_category_json/
     *
     * @return
     */
    @RequestMapping(value = "get_category_json/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<GetCategoryVo> getCategoryJson(@Valid GetCategoryReq getCategoryReq, HttpServletRequest request) throws Exception {
        LjBaseResponse<GetCategoryVo> ljBaseResponse = oapiCheckItemMeasureService.getCategoryJson(getCategoryReq, request);
        return ljBaseResponse;
    }


    /**
     * @return
     */
    @RequestMapping(value = "get_check_item_json/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<GetCheckItemVo> getCheckItemJson(@Valid GetCheckItemReq getCheckItemReq, HttpServletRequest request) throws Exception {
        LjBaseResponse<GetCheckItemVo> ljBaseResponse = oapiCheckItemMeasureService.getCheckItemJson(getCheckItemReq, request);
        return ljBaseResponse;


    }

    /**
     * go集团实测检查项上传
     * http://192.168.37.159:3000/project/8/interface/api/2852
     * http://192.168.37.159:3000/mock/8/oapi/v3/check_item/measure/update_json/
     *
     * @return
     */
    @RequestMapping(value = "update_json/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<CheckItemUpdateJsonVo> updateJson(@Valid UpdateReqMeasureReq updateReqMeasureReq, HttpServletRequest request) {
        LjBaseResponse<CheckItemUpdateJsonVo> ljBaseResponse = oapiCheckItemMeasureService.updateJson(updateReqMeasureReq, request);
        return ljBaseResponse;
    }


    /**
     * go集团实测检查项管理上传
     * http://192.168.37.159:3000/project/8/interface/api/2856
     * http://192.168.37.159:3000/mock/8/longjian.longhu.net/oapi/v3/check_item/check_item/list_tree_json/?_ct=json&team_id=4&module=scsl&group_id=4&page_level=group&id=18397&category_cls=102
     *
     * @return
     */
    @RequestMapping(value = "list_tree_json/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<CheckItemListVo> listTreeJson(@Valid ListTreeJsonReq listTreeJsonReq, HttpServletRequest request) {
        LjBaseResponse<CheckItemListVo> ljBaseResponse = oapiCheckItemMeasureService.listTreeJson(listTreeJsonReq, request);
        return ljBaseResponse;
    }
}
