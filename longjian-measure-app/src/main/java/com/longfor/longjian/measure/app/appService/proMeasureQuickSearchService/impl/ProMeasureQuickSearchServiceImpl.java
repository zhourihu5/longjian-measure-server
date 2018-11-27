package com.longfor.longjian.measure.app.appService.proMeasureQuickSearchService.impl;

import com.longfor.longjian.common.base.LjBaseResponse;
import com.longfor.longjian.measure.app.appService.proMeasureQuickSearchService.IProMeasureQuickSearchService;
import com.longfor.longjian.measure.app.req.proMeasureQuickSearchReq.GetQuickSearchPlanListReq;
import com.longfor.longjian.measure.app.vo.ItemsVo;
import com.longfor.longjian.measure.app.vo.proMeasureVo.QuickSearchPlanVo;
import com.longfor.longjian.measure.consts.util.ConvertUtil;
import com.longfor.longjian.measure.consts.util.LambdaExceptionUtil;
import com.longfor.longjian.measure.domain.externalService.IMeasureListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ProMeasureQuickSearchServiceImpl implements IProMeasureQuickSearchService {

    @Autowired
    private IMeasureListService measureListService;

    @Override
    public LjBaseResponse<ItemsVo<List<QuickSearchPlanVo>>> getQuickSearchPlanList(GetQuickSearchPlanListReq getQuickSearchPlanListReq) {
        LjBaseResponse<ItemsVo<List<QuickSearchPlanVo>>> ljBaseResponse = new LjBaseResponse<>();
        ItemsVo<List<QuickSearchPlanVo>> itemsVo = new ItemsVo<>();
        List<QuickSearchPlanVo> quickSearchPlanVos = new ArrayList<>();
        List<Map<String,Object>> list = measureListService.searchByProjectId(getQuickSearchPlanListReq.getProject_id());
        list.forEach(LambdaExceptionUtil.throwingConsumerWrapper( measureList -> {
            QuickSearchPlanVo quickSearchPlanVo = (QuickSearchPlanVo)ConvertUtil.convertMap(QuickSearchPlanVo.class,measureList);
            quickSearchPlanVos.add(quickSearchPlanVo);
        }));
        itemsVo.setItems(quickSearchPlanVos);
        ljBaseResponse.setData(itemsVo);
        return ljBaseResponse;
    }
}
