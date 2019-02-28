package com.longfor.longjian.measure.app.req.promeasurequicksearchreq;

import com.longfor.longjian.common.consts.ReqParamCheckErrors;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @description:
 * @author: DDC
 * @create: 2019-01-15 14:04
 **/
@Data
public class PostMeasureListDetailUpdateIssuePlanEndOnReq {
    /**
     * 唯一编号
     */
    @NotNull(message = ReqParamCheckErrors.PARAM_IS_NULL)
    private String uuid;

    /**
     *项目ID
     */
    @NotNull(message = ReqParamCheckErrors.PARAM_IS_NULL)
    private Integer project_id;

    /**
     * 整改期限
     */
    @NotNull(message = ReqParamCheckErrors.PARAM_IS_NULL)
    private Long plan_end_on;
}


