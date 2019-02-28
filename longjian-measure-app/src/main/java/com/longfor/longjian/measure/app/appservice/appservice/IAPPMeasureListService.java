package com.longfor.longjian.measure.app.appservice.appservice;

import com.longfor.longjian.common.base.LjBaseResponse;
import com.longfor.longjian.common.exception.LjBaseRuntimeException;
import com.longfor.longjian.measure.app.req.measurelist.*;
import com.longfor.longjian.measure.app.vo.measurelistvo.MeasureListInfoVo;
import com.longfor.longjian.measure.app.vo.measurelistvo.SetStatusVo;
import com.longfor.longjian.measure.po.zhijian2.MeasureList;

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
     * @return com.longfor.longjian.measure.app.vo.promeasurequicksearchvo.MeasureStatisticSquadStatsVo
     * @author DDC
     * @date 2019/1/9 10:37
     **/
    MeasureList GetByProjIdAndIdNoFoundErr(Integer projectId , Integer id);

    /**
     *
     * @param req
     * @return
     */
    LjBaseResponse<MeasureListInfoVo> conditionSearch(ConditionSearchReq req) throws LjBaseRuntimeException;

    /**
     * bgtask --- bgAdd
     */
    void add(Map params);
}
