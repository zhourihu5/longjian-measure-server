package com.longfor.longjian.measure.app.controller.proMeasureQuickSearchController;

import com.longfor.gaia.gfs.web.mock.MockOperation;
import com.longfor.longjian.common.base.LjBaseResponse;
import com.longfor.longjian.measure.app.appService.proMeasureManagerService.IProMeasureService;
import com.longfor.longjian.measure.app.appService.proMeasureQuickSearchService.IProMeasureQuickSearchService;
import com.longfor.longjian.measure.app.req.proMeasureManagerReq.GetCheckerListReq;
import com.longfor.longjian.measure.app.req.proMeasureManagerReq.GetProMeasureAreaListReq;
import com.longfor.longjian.measure.app.req.proMeasureManagerReq.GetProMeasureCheckItemsReq;
import com.longfor.longjian.measure.app.req.proMeasureQuickSearchReq.*;
import com.longfor.longjian.measure.app.vo.ItemsVo;
import com.longfor.longjian.measure.app.vo.proMeasureVo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    @GetMapping(value = "measure/measure_statistic/measure_list_json/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
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
    @GetMapping(value = "measure/ajax_json/get_root_category/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
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
    @GetMapping(value = "project/user/simple_list/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<ItemsVo<List<CheckerVo>>> getBlisterReformer(GetCheckerListReq getCheckerListReq){
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
    @GetMapping(value = "area/area/subs/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<AreaInfoVo> getBlisterAreaList(GetProMeasureAreaListReq getProMeasureAreaListReq) throws IntrospectionException, InstantiationException, IllegalAccessException, InvocationTargetException {
        return proMeasureService.getProMeasureAreaList(getProMeasureAreaListReq);
    }

    /**
     * go项目实测爆点整改检查项
     * http://192.168.37.159:3000/project/8/interface/api/214
     * @param getProMeasureCheckItemsReq
     * @return
     */
    @GetMapping(value = "measure/measure_list/sub_categorys/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<ItemsVo<List<ProMeasureCheckIteamVo>>> getBlisterCheckItems(GetProMeasureCheckItemsReq getProMeasureCheckItemsReq) throws IntrospectionException, InstantiationException, IllegalAccessException, InvocationTargetException {
        return proMeasureService.getProMeasureCheckItems(getProMeasureCheckItemsReq);
    }

    /**
     * go项目实测快速查询组间对比检查组
     * http://192.168.37.159:3000/project/8/interface/api/236
     * @param getCompareBetweenGroupReq
     * @return
     */
    @GetMapping(value = "measure/measure_statistic/squad_completeness_json/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<SquadsAndPassVo> getCompareBetweenGroup(GetCompareBetweenGroupReq getCompareBetweenGroupReq) throws Exception {
        return  proMeasureService.getCompareBetweenGroup(getCompareBetweenGroupReq);
    }


    /**
     * go项目实测快查组间对比合格率最低项
     * http://192.168.37.159:3000/project/8/interface/api/240
     * @param getLoserCompareBetweenGroupReq
     * @return
     */
    @GetMapping(value = "measure/measure_statistic/squad_special_point_json/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<PassDiffVo> getLoserCompareBetweenGroup(GetLoserCompareBetweenGroupReq getLoserCompareBetweenGroupReq) throws Exception {
        return proMeasureService.getLoserCompareBetweenGroup(getLoserCompareBetweenGroupReq);
    }

    /**
     * go项目实测快查组间对比检查项
     * http://192.168.37.159:3000/project/8/interface/api/246
     * @param getCompareItemBetweenSquadsReq
     * @return
     */
    @GetMapping(value = "measure/measure_statistic/category_details_json/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<CompareItemBetweenSquadsVo> getCompareItemBetweenSquads(GetCompareItemBetweenSquadsReq getCompareItemBetweenSquadsReq) throws Exception {
        return proMeasureService.getCompareItemBetweenSquads(getCompareItemBetweenSquadsReq);
    }

    /**
     * go项目实测快速查询爆点情况
     * http://192.168.37.159:3000/project/8/interface/api/252
     * @param getBlisterRateInfoReq
     * @return
     */
    @GetMapping(value = "measure/measure_statistic/issue_brief_json/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<BlisterRateInfoVo> getBlisterRateInfo(GetBlisterRateInfoReq getBlisterRateInfoReq) throws IntrospectionException, InstantiationException, IllegalAccessException, InvocationTargetException {
        return proMeasureService.getBlisterRateInfo(getBlisterRateInfoReq);
    }


    /**
     * go项目实测快速查询爆点情况时间节点
     * http://192.168.37.159:3000/project/8/interface/api/256
     * @param getBlisterRateInfoTimeNotesReq
     * @return
     */
    @GetMapping(value = "measure/measure_statistic/issue_trend_json/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<ItemsVo<List<BlisterTimeNotesVo>>> getBlisterRateInfoTimeNotes(GetBlisterRateInfoTimeNotesReq getBlisterRateInfoTimeNotesReq) throws ParseException {
        return proMeasureService.getBlisterRateInfoTimeNotes(getBlisterRateInfoTimeNotesReq);
    }

    /**
     * go项目实测快速查询爆点情况检查项
     * http://192.168.37.159:3000/project/8/interface/api/260
     * @param getBlisterRateCheckItemsReq
     * @return
     */
    @GetMapping(value = "measure/measure_statistic/issue_distribution_category_json/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<BlisterCheckItemsVo> getBlisterRateCheckItems(GetBlisterRateCheckItemsReq getBlisterRateCheckItemsReq){
        return proMeasureService.getBlisterRateCheckItems(getBlisterRateCheckItemsReq);
    }

    /**
     * go项目实测快速查询区域合格率查询
     * http://192.168.37.159:3000/project/8/interface/api/270
     * @return
     */
    @GetMapping(value = "measure/measure_statistic/sub_category_area_percentage/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<ItemsVo<List<AreaPOPVo>>> getAreaPOP(GetAreaPOPReq getAreaPOPreq) throws Exception {
        return proMeasureService.getAreaPOP(getAreaPOPreq);
    }
    
}
