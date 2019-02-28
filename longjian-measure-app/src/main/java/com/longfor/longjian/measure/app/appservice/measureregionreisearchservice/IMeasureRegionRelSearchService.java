package com.longfor.longjian.measure.app.appservice.measureregionreisearchservice;

import com.longfor.longjian.common.base.LjBaseResponse;
import com.longfor.longjian.measure.app.req.measureregionreq.MeasureRegionRelReq;
import com.longfor.longjian.measure.app.vo.appmeasuresyncvo.MeasureRegionRelVo;

public interface IMeasureRegionRelSearchService {
    LjBaseResponse<MeasureRegionRelVo> searchByRegionUuid(MeasureRegionRelReq measureRegionRelReq);
}
