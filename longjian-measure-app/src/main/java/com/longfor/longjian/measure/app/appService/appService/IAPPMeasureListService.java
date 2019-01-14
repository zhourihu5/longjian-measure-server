package com.longfor.longjian.measure.app.appService.appService;

import com.longfor.longjian.measure.app.req.MeasureList.*;
import com.longfor.longjian.measure.app.vo.measureListVo.SetStatusVo;
import com.longfor.longjian.measure.po.zhijian2.MeasureZone;

import java.util.Map;

/**
 * Created by Wang on 2019/1/7.
 */
public interface IAPPMeasureListService {

    SetStatusVo setStatus(SetStatusReq setStatusReq);

    Map<String,Object> updateName(UpdateNameReq updateNameReq);

    void delete(DeleteReq deleteReq);

    void updateFinishStatus(UpdateFinishStatusReq updateFinishStatusReq);

    void updateCloseStatus(UpdateCloseStatusReq updateCloseStatusReq);


    /**
     * @Description:
     * @param id
     * @param projectId
     * @return com.longfor.longjian.measure.app.vo.proMeasureQuickSearchVo.MeasureStatisticSquadStatsVo
     * @author DDC
     * @date 2019/1/9 10:37
     **/
    MeasureZone GetByProjIdAndIdNoFoundErr(Integer projectId , Integer id);

}
