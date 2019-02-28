package com.longfor.longjian.measure.app.req.promeasurequicksearchreq;

import com.longfor.longjian.common.consts.ReqParamCheckErrors;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class MeasureListDetailUpdateApproveIssueReq {
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
     * 是否通过
     */
    @NotNull(message = ReqParamCheckErrors.PARAM_IS_NULL)
    private Integer status;
    /**
     * 不通过理由
     */
    private String content;
}
