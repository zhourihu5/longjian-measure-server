package com.longfor.longjian.measure.app.appService.proMeasureQuickSearchService.impl;

import com.longfor.longjian.common.exception.LjBaseRuntimeException;
import com.longfor.longjian.measure.app.appService.appService.IAPPMeasureListService;
import com.longfor.longjian.measure.app.appService.proMeasureQuickSearchService.IMeasureStatisticService;
import com.longfor.longjian.measure.app.req.proMeasureQuickSearchReq.GetMeasureStatisticTaskReq;
import com.longfor.longjian.measure.app.vo.proMeasureQuickSearchVo.MeasureStatisticSquadStatsVo;
import com.longfor.longjian.measure.domain.externalService.IMeasureListIssueService;
import com.longfor.longjian.measure.domain.externalService.IMeasureZoneService;
import com.longfor.longjian.measure.po.zhijian2.MeasureList;
import com.longfor.longjian.measure.po.zhijian2.MeasureZone;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: DDC
 * @create: 2019-01-08 19:43
 **/
@Service
@Slf4j
public class MeasureStatisticImpl implements IMeasureStatisticService {

    @Autowired
    private IMeasureZoneService measureZoneService;

    @Autowired
    private IAPPMeasureListService measureListService;

    @Autowired
    private IMeasureListIssueService measureListIssueService;

    @Override
    public MeasureStatisticSquadStatsVo SquadMeasureStatsJson(GetMeasureStatisticTaskReq getMeasureStatisticTaskReq) {

        MeasureList measureList = measureListService.GetByProjIdAndIdNoFoundErr(getMeasureStatisticTaskReq.getProject_id(), getMeasureStatisticTaskReq.getMeasure_list_id());
        if (measureList == null) {
            throw new LjBaseRuntimeException(100000,"数据不存在");
        }

        Integer checkItemCount = measureZoneService.GetMeasureListCategoryCountAndCheckItemCount(getMeasureStatisticTaskReq.getMeasure_list_id());
        if (checkItemCount == null) {
            checkItemCount = 0;
        }
        Integer regionCount = measureZoneService.GetMeasureListBuildingCountAndRegionCount(getMeasureStatisticTaskReq.getMeasure_list_id());
        if (regionCount == null) {
            regionCount = 0;
        }
        MeasureStatisticSquadStatsVo measureStatisticSquadStatsVo = new MeasureStatisticSquadStatsVo();
        measureStatisticSquadStatsVo.setStatus(measureList.getFinishStatus());
        measureStatisticSquadStatsVo.setCheck_item_count(checkItemCount);
        measureStatisticSquadStatsVo.setRegion_count(regionCount);
        //原有逻辑就是添加为0
        measureStatisticSquadStatsVo.setCategory_count(0);
        measureStatisticSquadStatsVo.setBuilding_count(0);
        return measureStatisticSquadStatsVo;
    }
}


