package com.longfor.longjian.measure.app.req.appReq;

import com.longfor.longjian.common.consts.ReqParamCheckErrors;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ApiMeasureZoneResultTotalReqV2 {
    /**
     * 语言 取值范围及含义：;1 - 简体中文; ;2 - English;
     */
    private Integer lang;
    /**
     * 清单ID
     */
    @NotNull(message = ReqParamCheckErrors.PARAM_IS_NULL)
    private Integer list_id;
    /**
     * 上次更新时间
     */
    @NotNull(message = ReqParamCheckErrors.PARAM_IS_NULL)
    private String timestamp;
}
