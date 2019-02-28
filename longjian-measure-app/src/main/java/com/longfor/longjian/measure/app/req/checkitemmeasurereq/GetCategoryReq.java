package com.longfor.longjian.measure.app.req.checkitemmeasurereq;

import com.longfor.longjian.common.consts.ReqParamCheckErrors;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * Jiazm 2019/01/09 18:23
 */
@Data
@NoArgsConstructor
public class GetCategoryReq {
    /**
     * Key
     */
    @NotNull(message = ReqParamCheckErrors.PARAM_IS_NULL)
    private String key;
}
