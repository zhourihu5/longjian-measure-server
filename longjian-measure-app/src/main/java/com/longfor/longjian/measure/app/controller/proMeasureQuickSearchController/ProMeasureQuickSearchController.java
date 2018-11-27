package com.longfor.longjian.measure.app.controller.proMeasureQuickSearchController;

import com.longfor.gaia.gfs.web.mock.MockOperation;
import com.longfor.longjian.common.base.LjBaseResponse;
import com.longfor.longjian.measure.app.appService.proMeasureQuickSearchService.IProMeasureQuickSearchService;
import com.longfor.longjian.measure.app.req.proMeasureQuickSearchReq.GetQuickSearchPlanListReq;
import com.longfor.longjian.measure.app.vo.ItemsVo;
import com.longfor.longjian.measure.app.vo.proMeasureVo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
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
    public LjBaseResponse<ItemsVo<List<QuickSearchPlanVo>>> getQuickSearchPlanList(@Valid GetQuickSearchPlanListReq getQuickSearchPlanListReq){
        LjBaseResponse<ItemsVo<List<QuickSearchPlanVo>>> ljBaseResponse = proMeasureQuickSearchService.getQuickSearchPlanList(getQuickSearchPlanListReq);
        return ljBaseResponse;
    }

    /**
     * go项目实测快速查询区域合格率检查项
     * http://192.168.37.159:3000/mock/8/oapi/v3/measure/ajax_json/get_root_category/?_ct=json&project_id=927&page_level=project&group_id=4&team_id=25
     * @param requestParam
     * @return
     */
    @MockOperation
    @GetMapping(value = "measure/ajax_json/get_root_category/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<CategoryListVo> getAreaPOPCheckItemList(RequestParam requestParam){
        return null;
    }

    /**
     * go项目实测爆点整改整改人
     * http://192.168.37.159:3000/mock/8//oapi/v3/project/user/simple_list/?_ct=json&project_id=927&page_level=project
     * @param requestParam
     * @return
     */
    @MockOperation
    @GetMapping(value = "project/user/simple_list/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<ItemsVo<List<CheckerVo>>> getBlisterReformer(RequestParam requestParam){
        return null;
    }

    /**
     * go项目实测爆点整改区域
     * http://192.168.37.159:3000/mock/8//oapi/v3/area/area/subs/?_ct=json&project_id=927&page_level=project&group_id=4&team_id=25
     * go项目实测快速查询区域合格率选择区域
     * http://192.168.37.159:3000/mock/8///oapi/v3/area/area/subs/?_ct=json&project_id=927&page_level=project&group_id=4&team_id=25
     * @param requestParam
     * @return
     */
    @MockOperation
    @GetMapping(value = "area/area/subs/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<AreaInfoVo> getBlisterAreaList(RequestParam requestParam){
        return null;
    }

    /**
     * go项目实测爆点整改检查项
     * http://192.168.37.159:3000/mock/8/oapi/v3/measure/measure_list/sub_categorys/?_ct=json&project_id=927&page_level=project&group_id=4&team_id=25
     * @param requestParam
     * @return
     */
    @MockOperation
    @GetMapping(value = "measure/measure_list/sub_categorys/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<ItemsVo<List<ProMeasureCheckIteamVo>>> getBlisterCheckItems(RequestParam requestParam){
        return null;
    }

    /**
     * go项目实测快速查询组间对比检查组
     * http://192.168.37.159:3000/mock/8/oapi/v3/measure/measure_statistic/squad_completeness_json/?_ct=json&measure_list_id=5527&project_id=927&page_level=project&group_id=4&team_id=25
     * @param requestParam
     * @return
     */
    @MockOperation
    @GetMapping(value = "measure/measure_statistic/squad_completeness_json/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<SquadsAndPassVo> getCompareBetweenGroup(RequestParam requestParam){
        return null;
    }


    /**
     * go项目实测快查组间对比合格率最低项
     * http://192.168.37.159:3000/mock/8/oapi/v3/measure/measure_statistic/squad_special_point_json/?_ct=json&measure_list_id=5527&project_id=927&page_level=project&group_id=4&team_id=25
     * @param requestParam
     * @return
     */
    @MockOperation
    @GetMapping(value = "measure/measure_statistic/squad_special_point_json/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<PassDiffVo> getLoserCompareBetweenGroup(RequestParam requestParam){
        return null;
    }

    /**
     * go项目实测快查组间对比检查项
     * http://192.168.37.159:3000/mock/8/oapi/v3/measure/measure_statistic/category_details_json/?_ct=json&measure_list_id=5527&project_id=927&page_level=project&group_id=4&team_id=25&category_key=
     * @param requestParam
     * @return
     */
    @MockOperation
    @GetMapping(value = "measure/measure_statistic/category_details_json/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<CompareItemBetweenSquadsVo> getCompareItemBetweenSquads(RequestParam requestParam){
        return null;
    }

    /**
     * go项目实测快速查询爆点情况
     * http://192.168.37.159:3000/mock/8/oapi/v3/measure/measure_statistic/issue_brief_json/?_ct=json&project_id=927&page_level=project&group_id=4&team_id=25&measure_list_id=5527
     * @param requestParam
     * @return
     */
    @MockOperation
    @GetMapping(value = "measure/measure_statistic/issue_brief_json/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<BlisterRateInfoVo> getBlisterRateInfo(RequestParam requestParam){
        return null;
    }


    /**
     * go项目实测快速查询爆点情况时间节点
     * http://192.168.37.159:3000/mock/8/oapi/v3/measure/measure_statistic/issue_trend_json/?_ct=json&project_id=927&page_level=project&group_id=4&team_id=25&measure_list_id=5527&begin_on=0&end_on=0
     * @param requestParam
     * @return
     */
    @MockOperation
    @GetMapping(value = "measure/measure_statistic/issue_trend_json/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<ItemsVo<List<BlisterTimeNotesVo>>> getBlisterRateInfoTimeNotes(RequestParam requestParam){
        return null;
    }

    /**
     * go项目实测快速查询爆点情况检查项
     * http://192.168.37.159:3000/mock/8/oapi/v3/measure/measure_statistic/issue_distribution_category_json/?_ct=json&project_id=927&page_level=project&group_id=4&team_id=25&measure_list_id=5527&category_key=
     * @param requestParam
     * @return
     */
    @MockOperation
    @GetMapping(value = "measure/measure_statistic/issue_distribution_category_json/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<BlisterCheckItemsVo> getBlisterRateCheckItems(RequestParam requestParam){
        return null;
    }

    /**
     * go项目实测快速查询区域合格率查询
     * http://192.168.37.159:3000/mock/8/oapi/v3/measure/measure_statistic/sub_category_area_percentage/?_ct=json&project_id=927&page_level=project&group_id=4&team_id=25&list_ids=5525&area_ids=2942526&parent_category_key=10203
     * @return
     */
    @MockOperation
    @GetMapping(value = "measure/measure_statistic/sub_category_area_percentage/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<ItemsVo<List<AreaPOPVo>>> getAreaPOP(RequestParam requestParam){
        return null;
    }
    
}
