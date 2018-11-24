package com.longfor.longjian.measure.app.appService.proMeasureManagerService;

import com.longfor.longjian.common.base.LjBaseResponse;
import com.longfor.longjian.measure.app.req.proMeasureManagerReq.GetProMeasurePlanListReq;
import com.longfor.longjian.measure.app.vo.ItemsVo;
import com.longfor.longjian.measure.app.vo.proMeasureVo.ProMeasurePlanListVo;
import com.longfor.longjian.measure.app.vo.proMeasureVo.ProMeasurePlanVo;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface IProMeasureService  {
    /**
     * 项目.实测实量.任务管理.查看
     * @param getProMeasurePlanListReq
     * @return
     */
    LjBaseResponse<ProMeasurePlanListVo> getProMeasurePlanList(GetProMeasurePlanListReq getProMeasurePlanListReq) throws IntrospectionException, InstantiationException, IllegalAccessException, InvocationTargetException;
}
