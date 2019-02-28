package com.longfor.longjian.measure.app.appService.proMeasureQuickSearchService.impl;

import com.longfor.longjian.common.base.LjBaseResponse;
import com.longfor.longjian.common.entity.TeamBase;
import com.longfor.longjian.common.exception.LjBaseRuntimeException;
import com.longfor.longjian.common.util.CtrlTool;
import com.longfor.longjian.common.util.SessionInfo;
import com.longfor.longjian.measure.app.appService.proMeasureQuickSearchService.IProMeasureQuickSearchService;
import com.longfor.longjian.measure.app.req.proMeasureQuickSearchReq.GetAreaPOPCheckItemListReq;
import com.longfor.longjian.measure.app.req.proMeasureQuickSearchReq.GetQuickSearchPlanListReq;
import com.longfor.longjian.measure.app.vo.ItemsVo;
import com.longfor.longjian.measure.app.vo.proMeasureVo.AreaCheckItemVo;
import com.longfor.longjian.measure.app.vo.proMeasureVo.CategoryListVo;
import com.longfor.longjian.measure.app.vo.proMeasureVo.QuickSearchPlanVo;
import com.longfor.longjian.measure.consts.constant.CategoryClsTypeConstant;
import com.longfor.longjian.measure.domain.externalService.ICategoryV3Service;
import com.longfor.longjian.measure.domain.externalService.IMeasureListService;
import com.longfor.longjian.measure.po.zhijian2_apisvr.Team;
import com.longfor.longjian.measure.util.ConvertUtil;
import com.longfor.longjian.measure.util.LambdaExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ProMeasureQuickSearchServiceImpl implements IProMeasureQuickSearchService {

    @Autowired
    private IMeasureListService measureListService;
    @Autowired
    private ICategoryV3Service categoryV3Service;
    @Resource
    private SessionInfo sessionInfo;
    @Resource
    private CtrlTool ctrlTool;
    @Override
    public LjBaseResponse<ItemsVo<List<QuickSearchPlanVo>>> getQuickSearchPlanList(GetQuickSearchPlanListReq getQuickSearchPlanListReq, HttpServletRequest request) {
        try {
            ctrlTool.projPerm(request,"项目.实测实量.统计.查看");
        }catch (Exception e){
            throw new LjBaseRuntimeException(-9999,e.getMessage());
        }
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
    public LjBaseResponse<CategoryListVo> getAreaPOPCheckItemList(GetAreaPOPCheckItemListReq getAreaPOPCheckItemList,HttpServletRequest request) {
        TeamBase teamBase = null;
        try {
            ctrlTool.projPerm(request,"项目.实测实量.描画区域管理.查看");
            teamBase =(TeamBase) sessionInfo.getBaseInfo("cur_team");
        }catch (Exception e){
            throw new LjBaseRuntimeException(-9999,e.getMessage());
        }
        LjBaseResponse<CategoryListVo> ljBaseResponse = new LjBaseResponse<>();
        CategoryListVo categoryListVo = new CategoryListVo();
        List<AreaCheckItemVo> areaCheckItemVos = new ArrayList<>();
        Team team = new Team();
        team.setTeamId(teamBase.getTeamId());
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
