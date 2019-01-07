package com.longfor.longjian.measure.app.appService.appService;

import com.longfor.longjian.measure.app.req.MeasureList.*;
import com.longfor.longjian.measure.app.vo.measureListVo.SetStatusVo;

/**
 * Created by Wang on 2019/1/7.
 */
public interface IMeasureListService {

    SetStatusVo setStatus(SetStatusReq setStatusReq);

    String updateName(UpdateNameReq updateNameReq);

    void delete(DeleteReq deleteReq);

    void updateFinishStatus(UpdateFinishStatusReq updateFinishStatusReq);

    void updateCloseStatus(UpdateCloseStatusReq updateCloseStatusReq);

}
