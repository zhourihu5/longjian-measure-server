package com.longfor.longjian.measure.app.appservice.promeasuremanagerservice;

import com.longfor.longjian.common.base.LjBaseResponse;
import com.longfor.longjian.common.exception.LjBaseRuntimeException;
import com.longfor.longjian.measure.app.req.promeasuremanagerreq.GetCheckerListReq;
import com.longfor.longjian.measure.app.req.promeasuremanagerreq.GetProMeasureAreaListReq;
import com.longfor.longjian.measure.app.req.promeasuremanagerreq.GetProMeasureCheckItemsReq;
import com.longfor.longjian.measure.app.req.promeasuremanagerreq.GetProMeasurePlanListReq;
import com.longfor.longjian.measure.app.req.promeasurequicksearchreq.*;
import com.longfor.longjian.measure.app.vo.ItemsVo;
import com.longfor.longjian.measure.app.vo.promeasurevo.*;

import javax.servlet.http.HttpServletRequest;
import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.List;

public interface IProMeasureService  {
    /**
     * 项目.实测实量.任务管理.查看
     * @param getProMeasurePlanListReq
     * @param request
     * @return
     */
    LjBaseResponse<ProMeasurePlanListVo> getProMeasurePlanList(GetProMeasurePlanListReq getProMeasurePlanListReq, HttpServletRequest request) throws InvocationTargetException, IntrospectionException, InstantiationException, IllegalAccessException, ParseException;

    /**
     * go项目实测任务列表请求检查项
     * @param getProMeasureCheckItemsReq
     * @return
     */
    LjBaseResponse<ItemsVo<List<ProMeasureCheckIteamVo>>> getProMeasureCheckItems(GetProMeasureCheckItemsReq getProMeasureCheckItemsReq,HttpServletRequest request) throws LjBaseRuntimeException, InvocationTargetException, IntrospectionException, InstantiationException, IllegalAccessException;

    /**
     * go项目实测任务列表区域
     * go项目实测任务列表区域详情楼层
     * @param getProMeasureAreaListReq
     * @return
     */
    LjBaseResponse<AreaInfoVo> getProMeasureAreaList(GetProMeasureAreaListReq getProMeasureAreaListReq) throws InvocationTargetException, IntrospectionException, InstantiationException, IllegalAccessException;

    /**
     * go项目实测任务列表请求检查人员
     * @param getCheckerListReq
     * @return
     */
    LjBaseResponse<ItemsVo<List<CheckerVo>>> getCheckerList(GetCheckerListReq getCheckerListReq);

    /**
     * go项目实测快速查询组间对比检查组
     * @param getCompareBetweenGroupReq
     * @return
     */
    LjBaseResponse<SquadsAndPassVo> getCompareBetweenGroup(GetCompareBetweenGroupReq getCompareBetweenGroupReq) throws LjBaseRuntimeException;

    /**
     * go项目实测快查组间对比合格率最低项
     * @param getLoserCompareBetweenGroupReq
     * @return
     */
    LjBaseResponse<PassDiffVo> getLoserCompareBetweenGroup(GetLoserCompareBetweenGroupReq getLoserCompareBetweenGroupReq) throws LjBaseRuntimeException;

    /**
     * go项目实测快查组间对比检查项
     * @param getCompareItemBetweenSquadsReq
     * @return
     */
    LjBaseResponse<CompareItemBetweenSquadsVo> getCompareItemBetweenSquads(GetCompareItemBetweenSquadsReq getCompareItemBetweenSquadsReq) throws LjBaseRuntimeException;

    /**
     * go项目实测快速查询爆点情况
     * @param getBlisterRateInfoReq
     * @return
     */
    LjBaseResponse<BlisterRateInfoVo> getBlisterRateInfo(GetBlisterRateInfoReq getBlisterRateInfoReq) throws InvocationTargetException, IntrospectionException, InstantiationException, IllegalAccessException;

    /**
     * go项目实测快速查询爆点情况时间节点
     * @param getBlisterRateInfoTimeNotesReq
     * @return
     */
    LjBaseResponse<ItemsVo<List<BlisterTimeNotesVo>>> getBlisterRateInfoTimeNotes(GetBlisterRateInfoTimeNotesReq getBlisterRateInfoTimeNotesReq) throws ParseException;

    /**
     * go项目实测快速查询爆点情况检查项
     * @param getBlisterRateCheckItemsReq
     * @return
     */
    LjBaseResponse<BlisterCheckItemsVo> getBlisterRateCheckItems(GetBlisterRateCheckItemsReq getBlisterRateCheckItemsReq);

    /**
     * go项目实测快速查询区域合格率查询
     * @param getAreaPOPreq
     * @return
     */
    LjBaseResponse<ItemsVo<List<AreaPOPVo>>> getAreaPOP(GetAreaPOPReq getAreaPOPreq) throws LjBaseRuntimeException;
}
