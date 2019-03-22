package com.longfor.longjian.measure.app.req.apimeasurerulereq;

import com.longfor.longjian.common.consts.ReqParamCheckErrors;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ApiMeasureRuleReqV2 {
    /**
     * 类型Key，多个Key请用半角逗号“,”分隔
     */
    @NotNull(message = ReqParamCheckErrors.PARAM_IS_NULL)
    private String category_keys;

    /**
     * 上次更新时间
     */
    @NotNull(message = ReqParamCheckErrors.PARAM_IS_NULL)
    private Long timestamp;
}
