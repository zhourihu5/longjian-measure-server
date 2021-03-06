package com.longfor.longjian.measure.app.appservice.paintareaservice;

import com.longfor.longjian.common.base.LjBaseResponse;
import com.longfor.longjian.common.exception.LjBaseRuntimeException;
import com.longfor.longjian.measure.app.req.measureregionreq.AddOnGroupReq;
import com.longfor.longjian.measure.app.req.measureregionreq.AddOnProjReq;
import com.longfor.longjian.measure.app.req.measureregionreq.EditByProjIdReq;
import com.longfor.longjian.measure.app.req.measureregionreq.EditOnGroupReq;
import com.longfor.longjian.measure.app.req.paintareareq.GetGroupMeasureRegionTagReq;
import com.longfor.longjian.measure.app.req.paintareareq.GetProjMeasureRegionReq;
import com.longfor.longjian.measure.app.req.paintareareq.GetProjMeasureRegionTagReq;
import com.longfor.longjian.measure.app.vo.propaintareamanagevo.AreaRegionTagVo;
import com.longfor.longjian.measure.app.vo.propaintareamanagevo.GroupRegionTagVo;

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
    LjBaseResponse<GroupRegionTagVo> getGroupMeasureRegionTag(GetGroupMeasureRegionTagReq getGroupMeasureRegionTagReq) throws LjBaseRuntimeException;

    /**
     * 项目描画区域新增加描画区域请求标签
     * @param getProjMeasureRegionTagReq
     * @return
     */
    LjBaseResponse<GroupRegionTagVo> getProjMeasureRegionTag(GetProjMeasureRegionTagReq getProjMeasureRegionTagReq) throws LjBaseRuntimeException;

    /**
     * 项目描画区域管理新增描画区域请求测区
     * @param getProjMeasureRegionReq
     * @return
     */
    LjBaseResponse<AreaRegionTagVo> getProjMeasureRegionByAreaId(GetProjMeasureRegionReq getProjMeasureRegionReq) throws LjBaseRuntimeException;

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
