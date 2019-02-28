package com.longfor.longjian.measure.app.req.appreq;

import com.longfor.longjian.common.consts.ReqParamCheckErrors;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ApiMeasureRegionReqV2 {
    /**
     * 语言 取值范围及含义：;1 - 简体中文; ;2 - English;
     */
    private Integer lang;
    /**
     * 项目ID
     */
    @NotNull(message = ReqParamCheckErrors.PARAM_IS_NULL)
    private Integer project_id;
    /**
     * 上次ID
     */
    @NotNull(message = ReqParamCheckErrors.PARAM_IS_NULL)
    private Integer last_id;
    /**
     * 上次更新时间
     */
    @NotNull(message = ReqParamCheckErrors.PARAM_IS_NULL)
    private Long timestamp;
}
