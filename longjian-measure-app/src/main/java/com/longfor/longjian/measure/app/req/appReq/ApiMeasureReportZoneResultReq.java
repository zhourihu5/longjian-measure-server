package com.longfor.longjian.measure.app.req.appReq;

import com.longfor.longjian.common.consts.ReqParamCheckErrors;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ApiMeasureReportZoneResultReq {
    /**
     * 语言 取值范围及含义：;1 - 简体中文; ;2 - English;
     */
    private Integer lang;
    /**
     * 本次上传的唯一字符串，用于查询是否上传成功
     */
    @NotNull(message = ReqParamCheckErrors.PARAM_IS_NULL)
    private String report_uuid;
    /**
     * 要上传的数据
     */
    @NotNull(message = ReqParamCheckErrors.PARAM_IS_NULL)
    private String data;
}
