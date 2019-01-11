package com.longfor.longjian.measure.app.appService.appService;

import com.longfor.longjian.measure.app.req.MeasureList.SetStatusReq;
import com.longfor.longjian.measure.app.vo.measureListVo.SetStatusVo;
import com.longfor.longjian.measure.po.zhijian2.MeasureZone;

/**
 * Created by Wang on 2019/1/7.
 */
public interface MeasureListService {

    SetStatusVo setStatus(SetStatusReq setStatusReq);

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
