package com.longfor.longjian.measure.app.req.proMeasureQuickSearchReq;

import com.longfor.longjian.common.consts.ReqParamCheckErrors;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class MeasureListDetailUpdateCloseStatusReq {
    /**
     * 唯一编号
     */
    @NotNull(message = ReqParamCheckErrors.PARAM_IS_NULL)
    private String uuid;
    /**
     * 项目ID
     */
    @NotNull(message = ReqParamCheckErrors.PARAM_IS_NULL)
    private Integer project_id;
    /**
     * 是否关闭
     */
    @NotNull(message = ReqParamCheckErrors.PARAM_IS_NULL)
    private Boolean close_status;
}
