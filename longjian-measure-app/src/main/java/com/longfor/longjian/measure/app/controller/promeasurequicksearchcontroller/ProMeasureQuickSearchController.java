package com.longfor.longjian.measure.app.controller.promeasurequicksearchcontroller;

import com.longfor.longjian.common.base.LjBaseResponse;
import com.longfor.longjian.measure.app.appservice.promeasuremanagerservice.IProMeasureService;
import com.longfor.longjian.measure.app.appservice.promeasurequicksearchservice.IProMeasureQuickSearchService;
import com.longfor.longjian.measure.app.req.promeasuremanagerreq.GetCheckerListReq;
import com.longfor.longjian.measure.app.req.promeasuremanagerreq.GetProMeasureAreaListReq;
import com.longfor.longjian.measure.app.req.promeasuremanagerreq.GetProMeasureCheckItemsReq;
import com.longfor.longjian.measure.app.req.promeasurequicksearchreq.*;
import com.longfor.longjian.measure.app.vo.ItemsVo;
import com.longfor.longjian.measure.app.vo.promeasurevo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.List;

/**
 * go服务
 * 项目实测快速查询(go)
 * 爆点整改(go)
 * wangxs
 * 2018-11-17
 */
@RestController
@RequestMapping("oapi/v3/")
@Slf4j
public class ProMeasureQuickSearchController {

    @Autowired
    private IProMeasureQuickSearchService proMeasureQuickSearchService;
    @Autowired
    private IProMeasureService proMeasureService;

    /**
     * go项目实测快速查询任务概览
     * http://192.168.37.159:3000/project/8/interface/api/174
     * go集团实测区域合格率任务
     * http://192.168.37.159:3000/project/8/interface/api/190
     *go项目实测爆点整改任务列表
     * http://192.168.37.159:3000/project/8/interface/api/202
     * @param getQuickSearchPlanListReq
     * @return
     */
    @RequestMapping(value = "measure/measure_statistic/measure_list_json/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<ItemsVo<List<QuickSearchPlanVo>>> getQuickSearchPlanList(@Valid GetQuickSearchPlanListReq getQuickSearchPlanListReq, HttpServletRequest request){
        LjBaseResponse<ItemsVo<List<QuickSearchPlanVo>>> ljBaseResponse = proMeasureQuickSearchService.getQuickSearchPlanList(getQuickSearchPlanListReq,request);
        return ljBaseResponse;
    }

    /**
     * go项目实测快速查询区域合格率检查项
     * http://192.168.37.159:3000/project/8/interface/api/180
     * @param getAreaPOPCheckItemList
     * @return
     */
    @RequestMapping(value = "measure/ajax_json/get_root_category/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<CategoryListVo> getAreaPOPCheckItemList(@Valid GetAreaPOPCheckItemListReq getAreaPOPCheckItemList, HttpServletRequest request){
        LjBaseResponse<CategoryListVo> ljBaseResponse = proMeasureQuickSearchService.getAreaPOPCheckItemList(getAreaPOPCheckItemList,request);
        return ljBaseResponse;
    }

    /**
     * go项目实测爆点整改整改人
     * http://192.168.37.159:3000/project/8/interface/api/208
     * @param getCheckerListReq
     * @return
     */
    @RequestMapping(value = "project/user/simple_list/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<ItemsVo<List<CheckerVo>>> getBlisterReformer(@Valid GetCheckerListReq getCheckerListReq){
        LjBaseResponse<ItemsVo<List<CheckerVo>>> ljBaseResponse = proMeasureService.getCheckerList(getCheckerListReq);
        return ljBaseResponse;
    }

    /**
     * go项目实测爆点整改区域
     * http://192.168.37.159:3000/project/8/interface/api/212
     * go项目实测快速查询区域合格率选择区域
     * http://192.168.37.159:3000/project/8/interface/api/268
     * @param getProMeasureAreaListReq
     * @return
     */
    @RequestMapping(value = "area/area/subs/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<AreaInfoVo> getBlisterAreaList(GetProMeasureAreaListReq getProMeasureAreaListReq) throws IntrospectionException, InstantiationException, IllegalAccessException, InvocationTargetException {
        return proMeasureService.getProMeasureAreaList(getProMeasureAreaListReq);
    }

    /**
     * go项目实测爆点整改检查项
     * http://192.168.37.159:3000/project/8/interface/api/214
     * @param getProMeasureCheckItemsReq
     * @return
     */
    @RequestMapping(value = "measure/measure_list/sub_categorys/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<ItemsVo<List<ProMeasureCheckIteamVo>>> getBlisterCheckItems(@Valid GetProMeasureCheckItemsReq getProMeasureCheckItemsReq,HttpServletRequest request) throws Exception {
        return proMeasureService.getProMeasureCheckItems(getProMeasureCheckItemsReq,request);
    }

    /**
     * go项目实测快速查询组间对比检查组
     * http://192.168.37.159:3000/project/8/interface/api/236
     * @param getCompareBetweenGroupReq
     * @return
     */
    @RequestMapping(value = "measure/measure_statistic/squad_completeness_json/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<SquadsAndPassVo> getCompareBetweenGroup(@Valid GetCompareBetweenGroupReq getCompareBetweenGroupReq) throws Exception {
        return  proMeasureService.getCompareBetweenGroup(getCompareBetweenGroupReq);
    }


    /**
     * go项目实测快查组间对比合格率最低项
     * http://192.168.37.159:3000/project/8/interface/api/240
     * @param getLoserCompareBetweenGroupReq
     * @return
     */
    @RequestMapping(value = "measure/measure_statistic/squad_special_point_json/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<PassDiffVo> getLoserCompareBetweenGroup(@Valid GetLoserCompareBetweenGroupReq getLoserCompareBetweenGroupReq) throws Exception {
        return proMeasureService.getLoserCompareBetweenGroup(getLoserCompareBetweenGroupReq);
    }

    /**
     * go项目实测快查组间对比检查项
     * http://192.168.37.159:3000/project/8/interface/api/246
     * @param getCompareItemBetweenSquadsReq
     * @return
     */
    @RequestMapping(value = "measure/measure_statistic/category_details_json/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<CompareItemBetweenSquadsVo> getCompareItemBetweenSquads(@Valid GetCompareItemBetweenSquadsReq getCompareItemBetweenSquadsReq) throws Exception {
        return proMeasureService.getCompareItemBetweenSquads(getCompareItemBetweenSquadsReq);
    }

    /**
     * go项目实测快速查询爆点情况
     * http://192.168.37.159:3000/project/8/interface/api/252
     * @param getBlisterRateInfoReq
     * @return
     */
    @RequestMapping(value = "measure/measure_statistic/issue_brief_json/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<BlisterRateInfoVo> getBlisterRateInfo(@Valid GetBlisterRateInfoReq getBlisterRateInfoReq) throws IntrospectionException, InstantiationException, IllegalAccessException, InvocationTargetException {
        return proMeasureService.getBlisterRateInfo(getBlisterRateInfoReq);
    }


    /**
     * go项目实测快速查询爆点情况时间节点
     * http://192.168.37.159:3000/project/8/interface/api/256
     * @param getBlisterRateInfoTimeNotesReq
     * @return
     */
    @RequestMapping(value = "measure/measure_statistic/issue_trend_json/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<ItemsVo<List<BlisterTimeNotesVo>>> getBlisterRateInfoTimeNotes(@Valid GetBlisterRateInfoTimeNotesReq getBlisterRateInfoTimeNotesReq) throws ParseException {
        return proMeasureService.getBlisterRateInfoTimeNotes(getBlisterRateInfoTimeNotesReq);
    }

    /**
     * go项目实测快速查询爆点情况检查项
     * http://192.168.37.159:3000/project/8/interface/api/260
     * @param getBlisterRateCheckItemsReq
     * @return
     */
    @RequestMapping(value = "measure/measure_statistic/issue_distribution_category_json/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<BlisterCheckItemsVo> getBlisterRateCheckItems(@Valid GetBlisterRateCheckItemsReq getBlisterRateCheckItemsReq){
        return proMeasureService.getBlisterRateCheckItems(getBlisterRateCheckItemsReq);
    }

    /**
     * go项目实测快速查询区域合格率查询
     * http://192.168.37.159:3000/project/8/interface/api/270
     * @return
     */
    @RequestMapping(value = "measure/measure_statistic/sub_category_area_percentage/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<ItemsVo<List<AreaPOPVo>>> getAreaPOP(@Valid GetAreaPOPReq getAreaPOPreq) throws Exception {
        return proMeasureService.getAreaPOP(getAreaPOPreq);
    }
    
}
