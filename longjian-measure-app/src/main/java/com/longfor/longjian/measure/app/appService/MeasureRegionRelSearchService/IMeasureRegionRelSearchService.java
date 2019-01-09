package com.longfor.longjian.measure.app.appService.MeasureRegionRelSearchService;

import com.longfor.longjian.common.base.LjBaseResponse;
import com.longfor.longjian.measure.app.req.measureRegionReq.MeasureRegionRelReq;
import com.longfor.longjian.measure.app.vo.appMeasureSyncVo.MeasureRegionRelVo;

public interface IMeasureRegionRelSearchService {
    LjBaseResponse<MeasureRegionRelVo> searchByRegionUuid(MeasureRegionRelReq measureRegionRelReq);
}
