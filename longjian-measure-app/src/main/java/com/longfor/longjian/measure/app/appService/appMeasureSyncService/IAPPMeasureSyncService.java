package com.longfor.longjian.measure.app.appService.appMeasureSyncService;

import com.longfor.longjian.common.base.LjBaseResponse;
import com.longfor.longjian.measure.app.req.apiMeasureRuleReq.ApiMeasureRuleReq;
import com.longfor.longjian.measure.app.req.apiMeasureRuleReq.ApiMyTaskReq;
import com.longfor.longjian.measure.app.vo.appMeasureSyncVo.MeasureListListVo;
import com.longfor.longjian.measure.app.vo.appMeasureSyncVo.MeasureListVo;
import com.longfor.longjian.measure.app.vo.appMeasureSyncVo.MeasureRuleListVo;
import com.longfor.longjian.measure.app.vo.appMeasureSyncVo.MyTaskVo;

import javax.servlet.http.HttpServletRequest;
import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;

/**
 * Jiazm
 * 2018/12/11
 */
public interface IAPPMeasureSyncService {
    /**
     * 读取测量规则
     * @param apiMeasureRuleReq
     * @return
     */
    LjBaseResponse<MeasureRuleListVo> getMeasureRule(ApiMeasureRuleReq apiMeasureRuleReq) throws Exception;

    /**
     * 获取与“我”相关的实测实量清单
     * @param apiMyTaskReq
     * @return
     */
    LjBaseResponse<MeasureListListVo> getMyTask(ApiMyTaskReq apiMyTaskReq, HttpServletRequest request) throws Exception;

}
