package com.longfor.longjian.measure.app.req.proMeasureQuickSearchReq;

import com.longfor.longjian.common.consts.ReqParamCheckErrors;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @description:
 * @author: DDC
 * @create: 2019-01-15 10:28
 **/
@Data
public class PostMeasureListDetailUpdateIssueTypeReq {
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
     * 问题类型
     */
    @NotNull(message = ReqParamCheckErrors.PARAM_IS_NULL)
    private Integer type;
}


