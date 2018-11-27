package com.longfor.longjian.measure.app.appService.proMeasureManagerService;

import com.longfor.longjian.common.base.LjBaseResponse;
import com.longfor.longjian.measure.app.req.proMeasureManagerReq.GetCheckerListReq;
import com.longfor.longjian.measure.app.req.proMeasureManagerReq.GetProMeasureAreaListReq;
import com.longfor.longjian.measure.app.req.proMeasureManagerReq.GetProMeasureCheckItemsReq;
import com.longfor.longjian.measure.app.req.proMeasureManagerReq.GetProMeasurePlanListReq;
import com.longfor.longjian.measure.app.vo.ItemsVo;
import com.longfor.longjian.measure.app.vo.proMeasureVo.*;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.List;

public interface IProMeasureService  {
    /**
     * 项目.实测实量.任务管理.查看
     * @param getProMeasurePlanListReq
     * @return
     */
    LjBaseResponse<ProMeasurePlanListVo> getProMeasurePlanList(GetProMeasurePlanListReq getProMeasurePlanListReq) throws IntrospectionException, InstantiationException, IllegalAccessException, InvocationTargetException, ParseException;

    /**
     * go项目实测任务列表请求检查项
     * @param getProMeasureCheckItemsReq
     * @return
     */
    LjBaseResponse<ItemsVo<List<ProMeasureCheckIteamVo>>> getProMeasureCheckItems(GetProMeasureCheckItemsReq getProMeasureCheckItemsReq) throws InvocationTargetException, IntrospectionException, InstantiationException, IllegalAccessException;

    /**
     * go项目实测任务列表区域
     * go项目实测任务列表区域详情楼层
     * @param getProMeasureAreaListReq
     * @return
     */
    LjBaseResponse<ItemsVo<List<ProMeasureAreaVo>>> getProMeasureAreaList(GetProMeasureAreaListReq getProMeasureAreaListReq) throws InvocationTargetException, IntrospectionException, InstantiationException, IllegalAccessException;

    /**
     * go项目实测任务列表请求检查人员
     * @param getCheckerListReq
     * @return
     */
    LjBaseResponse<ItemsVo<List<CheckerVo>>> getCheckerList(GetCheckerListReq getCheckerListReq);
}
