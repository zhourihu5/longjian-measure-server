package com.longfor.longjian.measure.app.appservice.promeasurequicksearchservice;

import com.longfor.longjian.measure.app.req.promeasurequicksearchreq.GetMeasureStatisticTaskReq;
import com.longfor.longjian.measure.app.vo.promeasurequicksearchvo.MeasureStatisticSquadStatsVo;

public interface IMeasureStatisticService {
    /**
     * @Description:
     * @param getMeasureStatisticTaskReq
     * @return com.longfor.longjian.measure.app.vo.promeasurequicksearchvo.MeasureStatisticSquadStatsVo
     * @author DDC
     * @date 2019/1/9 15:35
     **/
    MeasureStatisticSquadStatsVo squadMeasureStatsJson(GetMeasureStatisticTaskReq getMeasureStatisticTaskReq);
}
