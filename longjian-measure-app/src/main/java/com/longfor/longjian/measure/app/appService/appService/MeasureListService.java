package com.longfor.longjian.measure.app.appService.appService;

import com.longfor.longjian.measure.app.req.MeasureList.SetStatusReq;
import com.longfor.longjian.measure.app.vo.measureListVo.SetStatusVo;

/**
 * Created by Wang on 2019/1/7.
 */
public interface MeasureListService {

    SetStatusVo setStatus(SetStatusReq setStatusReq);

}
