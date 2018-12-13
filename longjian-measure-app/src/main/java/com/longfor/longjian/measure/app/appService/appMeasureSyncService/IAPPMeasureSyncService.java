package com.longfor.longjian.measure.app.appService.appMeasureSyncService;

import com.longfor.longjian.common.base.LjBaseResponse;
import com.longfor.longjian.measure.app.req.apiMeasureRuleReq.ApiMeasureRuleReq;
import com.longfor.longjian.measure.app.vo.appMeasureSyncVo.MeasureRuleListVo;

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
}
