package com.longfor.longjian.measure.app.appService.proMeasureQuickSearchService.impl;

import com.longfor.longjian.common.base.LjBaseResponse;
import com.longfor.longjian.measure.app.appService.proMeasureQuickSearchService.IProMeasureQuickSearchService;
import com.longfor.longjian.measure.app.req.proMeasureQuickSearchReq.GetAreaPOPCheckItemListReq;
import com.longfor.longjian.measure.app.req.proMeasureQuickSearchReq.GetQuickSearchPlanListReq;
import com.longfor.longjian.measure.app.vo.ItemsVo;
import com.longfor.longjian.measure.app.vo.proMeasureVo.AreaCheckItemVo;
import com.longfor.longjian.measure.app.vo.proMeasureVo.CategoryListVo;
import com.longfor.longjian.measure.app.vo.proMeasureVo.QuickSearchPlanVo;
import com.longfor.longjian.measure.consts.constant.CategoryClsTypeConstant;
import com.longfor.longjian.measure.consts.util.ConvertUtil;
import com.longfor.longjian.measure.consts.util.LambdaExceptionUtil;
import com.longfor.longjian.measure.domain.externalService.ICategoryV3Service;
import com.longfor.longjian.measure.domain.externalService.IMeasureListService;
import com.longfor.longjian.measure.po.zhijian2_apisvr.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ProMeasureQuickSearchServiceImpl implements IProMeasureQuickSearchService {

    @Autowired
    private IMeasureListService measureListService;
    @Autowired
    private ICategoryV3Service categoryV3Service;

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

    @Override
    public LjBaseResponse<CategoryListVo> getAreaPOPCheckItemList(GetAreaPOPCheckItemListReq getAreaPOPCheckItemList) {
        LjBaseResponse<CategoryListVo> ljBaseResponse = new LjBaseResponse<>();
        CategoryListVo categoryListVo = new CategoryListVo();
        List<AreaCheckItemVo> areaCheckItemVos = new ArrayList<>();
        //todo 从sessionz中取出group信息（Team表）,然后找到最顶层的父级team ,暂时手动赋值
        Team team = new Team();
        team.setTeamId(4);
        List<Map<String,Object>> list = categoryV3Service.getRootCategoryByClsTeamId(CategoryClsTypeConstant.MEASURE,team.getTeamId());
        list.forEach(LambdaExceptionUtil.throwingConsumerWrapper(category -> {
            AreaCheckItemVo areaCheckItemVo = (AreaCheckItemVo)ConvertUtil.convertMap(AreaCheckItemVo.class,category);
            areaCheckItemVos.add(areaCheckItemVo);
        }));
        categoryListVo.setCategory_list(areaCheckItemVos);
        ljBaseResponse.setData(categoryListVo);
        return ljBaseResponse;
    }
}
