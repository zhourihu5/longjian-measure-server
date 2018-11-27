package com.longfor.longjian.measure.app.appService.proMeasureQuickSearchService;

import com.longfor.longjian.common.base.LjBaseResponse;
import com.longfor.longjian.measure.app.req.proMeasureQuickSearchReq.GetAreaPOPCheckItemListReq;
import com.longfor.longjian.measure.app.req.proMeasureQuickSearchReq.GetBlisterReformerReq;
import com.longfor.longjian.measure.app.req.proMeasureQuickSearchReq.GetQuickSearchPlanListReq;
import com.longfor.longjian.measure.app.vo.ItemsVo;
import com.longfor.longjian.measure.app.vo.proMeasureVo.CategoryListVo;
import com.longfor.longjian.measure.app.vo.proMeasureVo.CheckerVo;
import com.longfor.longjian.measure.app.vo.proMeasureVo.QuickSearchPlanVo;

import java.util.List;

public interface IProMeasureQuickSearchService {
    /**
     * go项目实测快速查询任务概览
     * go集团实测区域合格率任务
     * o项目实测爆点整改任务列表
     * @param getQuickSearchPlanListReq
     * @return
     */
    LjBaseResponse<ItemsVo<List<QuickSearchPlanVo>>> getQuickSearchPlanList(GetQuickSearchPlanListReq getQuickSearchPlanListReq);

    /**
     * go项目实测快速查询区域合格率检查项
     * @param getAreaPOPCheckItemList
     * @return
     */
    LjBaseResponse<CategoryListVo> getAreaPOPCheckItemList(GetAreaPOPCheckItemListReq getAreaPOPCheckItemList);

}
