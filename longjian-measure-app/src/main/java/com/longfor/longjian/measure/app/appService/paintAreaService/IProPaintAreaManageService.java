package com.longfor.longjian.measure.app.appService.paintAreaService;

import com.longfor.longjian.common.base.LjBaseResponse;
import com.longfor.longjian.measure.app.req.GetProjMeasureRegionReq;
import com.longfor.longjian.measure.app.req.paintAreaReq.GetGroupMeasureRegionTagReq;
import com.longfor.longjian.measure.app.req.paintAreaReq.GetProjMeasureRegionTagReq;
import com.longfor.longjian.measure.app.vo.proPaintAreaManageVo.AreaRegionTagVo;
import com.longfor.longjian.measure.app.vo.proPaintAreaManageVo.GroupRegionTagVo;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;

/**
 * wangxs
 * 2018-11-22
 */
public interface IProPaintAreaManageService {

    /**
     * 集团描画区域新增加描画区域请求标签
     * @param getGroupMeasureRegionTagReq
     * @return
     * @throws InvocationTargetException
     * @throws IntrospectionException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    LjBaseResponse<GroupRegionTagVo> getGroupMeasureRegionTag(GetGroupMeasureRegionTagReq getGroupMeasureRegionTagReq) throws InvocationTargetException, IntrospectionException, InstantiationException, IllegalAccessException;

    /**
     * 项目描画区域新增加描画区域请求标签
     * @param getProjMeasureRegionTagReq
     * @return
     */
    LjBaseResponse<GroupRegionTagVo> getProjMeasureRegionTag(GetProjMeasureRegionTagReq getProjMeasureRegionTagReq) throws InvocationTargetException, IntrospectionException, InstantiationException, IllegalAccessException;

    /**
     * 项目描画区域管理新增描画区域请求测区
     * @param getProjMeasureRegionReq
     * @return
     */
    LjBaseResponse<AreaRegionTagVo> getProjMeasureRegionByAreaId(GetProjMeasureRegionReq getProjMeasureRegionReq) throws InvocationTargetException, IntrospectionException, InstantiationException, IllegalAccessException;
}
