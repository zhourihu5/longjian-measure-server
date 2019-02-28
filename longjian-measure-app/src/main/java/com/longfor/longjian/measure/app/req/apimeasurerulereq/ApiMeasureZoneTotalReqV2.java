package com.longfor.longjian.measure.app.req.apimeasurerulereq;

import com.longfor.longjian.common.consts.ReqParamCheckErrors;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Jiazm
 * 2018/12/14 11:48
 */
@Data
public class ApiMeasureZoneTotalReqV2 {
    /**
     * 清单ID
     */
    @NotNull(message = ReqParamCheckErrors.PARAM_IS_NULL)
    private Integer list_id;
    /**
     * 上次更新时间
     */
    @NotNull(message = ReqParamCheckErrors.PARAM_IS_NULL)
    private Long timestamp;
}
