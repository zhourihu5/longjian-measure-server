package com.longfor.longjian.measure.app.appService.paintAreaService;

import com.longfor.longjian.common.base.LjBaseResponse;
import com.longfor.longjian.measure.app.req.measureRegionReq.AddOnGroupReq;
import com.longfor.longjian.measure.app.req.measureRegionReq.AddOnProjReq;
import com.longfor.longjian.measure.app.req.measureRegionReq.EditByProjIdReq;
import com.longfor.longjian.measure.app.req.measureRegionReq.EditOnGroupReq;
import com.longfor.longjian.measure.app.req.paintAreaReq.GetGroupMeasureRegionTagReq;
import com.longfor.longjian.measure.app.req.paintAreaReq.GetProjMeasureRegionReq;
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

    /**
     * 集团实测描画区域标签管理
     * @param editOnGroupReq
     * @return
     */
    LjBaseResponse<Object> editOnGroup(EditOnGroupReq editOnGroupReq);

    /**
     * 集团实测描画区域标签管理添加标签
     * @param addOnGroupReq
     * @return
     */
    LjBaseResponse<Object> addOnGroup(AddOnGroupReq addOnGroupReq);

    /**
     * 项目描画区域管理新增加描画区域添加标签
     * @param addOnProjReq
     * @return
     */
    LjBaseResponse<Object> addOnProj(AddOnProjReq addOnProjReq);

    /**
     * 项目描画区域管理新增描画区域修改标签
     * @param editByProjId
     * @return
     */
    LjBaseResponse<Object> editByProjId(EditByProjIdReq editByProjId);
}
