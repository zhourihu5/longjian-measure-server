package com.longfor.longjian.measure.app.req.appreq;

import com.longfor.longjian.common.consts.ReqParamCheckErrors;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ApiMeasureReportZoneResultReq {
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
