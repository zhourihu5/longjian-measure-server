package com.longfor.longjian.measure.app.req.apiMeasureRuleReq;

import com.longfor.longjian.common.consts.ReqParamCheckErrors;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ApiMeasureRuleReq {
    /**
     * 类型Key，多个Key请用半角逗号“,”分隔
     */
    @NotNull(message = ReqParamCheckErrors.PARAM_IS_NULL)
    private String category_keys;
}
