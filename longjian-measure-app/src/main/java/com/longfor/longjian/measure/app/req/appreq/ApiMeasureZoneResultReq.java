package com.longfor.longjian.measure.app.req.appreq;

import com.longfor.longjian.common.consts.ReqParamCheckErrors;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ApiMeasureZoneResultReq {
    /**
     * 清单ID，多可id请用半角逗号“,”分隔
     */
    @NotNull(message = ReqParamCheckErrors.PARAM_IS_NULL)
    private String list_ids;
}
