package com.longfor.longjian.measure.app.req.apimeasurerulereq;

import com.longfor.longjian.common.consts.ReqParamCheckErrors;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Jiazm
 * 2018/12/15 11:52
 */
@Data
public class ApiMeasureReportZoneReq {
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
