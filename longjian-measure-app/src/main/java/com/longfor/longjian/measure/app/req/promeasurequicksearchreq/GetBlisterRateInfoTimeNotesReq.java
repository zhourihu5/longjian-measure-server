package com.longfor.longjian.measure.app.req.promeasurequicksearchreq;

import com.longfor.longjian.common.consts.ReqParamCheckErrors;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class GetBlisterRateInfoTimeNotesReq {
    /**
     * 项目id
     */
    @NotNull(message = ReqParamCheckErrors.PARAM_IS_NULL)
    private Integer project_id;
    /**
     * 实测清单ID
     */
    @NotNull(message = ReqParamCheckErrors.PARAM_IS_NULL)
    private Integer measure_list_id;
    /**
     * 开始时间戳
     */
    @NotNull(message = ReqParamCheckErrors.PARAM_IS_NULL)
    private Long begin_on;
    /**
     * 结束时间戳
     */
    @NotNull(message = ReqParamCheckErrors.PARAM_IS_NULL)
    private Long end_on;
}
