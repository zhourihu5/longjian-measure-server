package com.longfor.longjian.measure.app.appService.proMeasureQuickSearchService;

import com.longfor.longjian.measure.app.req.proMeasureQuickSearchReq.GetMeasureStatisticTaskReq;
import com.longfor.longjian.measure.app.vo.proMeasureQuickSearchVo.MeasureStatisticSquadStatsVo;

public interface IMeasureStatisticService {
    /**
     * @Description:
     * @param getMeasureStatisticTaskReq
     * @return com.longfor.longjian.measure.app.vo.proMeasureQuickSearchVo.MeasureStatisticSquadStatsVo
     * @author DDC
     * @date 2019/1/9 15:35
     **/
    MeasureStatisticSquadStatsVo SquadMeasureStatsJson(GetMeasureStatisticTaskReq getMeasureStatisticTaskReq);
}
