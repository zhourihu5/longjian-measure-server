package com.longfor.longjian.measure.app.req.appReq;

import com.longfor.longjian.common.consts.ReqParamCheckErrors;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ApiMeasureReportIssueReq {
    /**
     *  语言 取值范围及含义：;1 - 简体中文; ;2 - English;
     */
    private Integer lang;
    /**
     * report_uuid
     */
    @NotNull(message = ReqParamCheckErrors.PARAM_IS_NULL)
    private String report_uuid;
    /**
     * 项目ID
     */
    @NotNull(message = ReqParamCheckErrors.PARAM_IS_NULL)
    private Integer project_id;
    /**
     * 要上报的数据
     */
    @NotNull(message = ReqParamCheckErrors.PARAM_IS_NULL)
    private String data;
}
