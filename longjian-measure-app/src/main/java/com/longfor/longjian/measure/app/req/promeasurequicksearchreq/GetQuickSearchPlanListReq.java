package com.longfor.longjian.measure.app.req.promeasurequicksearchreq;

import com.longfor.longjian.common.consts.ReqParamCheckErrors;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class GetQuickSearchPlanListReq {
    /**
     * 语言 取值范围及含义：;1 - 简体中文; ;2 - English
     */
    private Integer Lang;
    /**
     * // 项目ID
     */
    @NotNull(message = ReqParamCheckErrors.PARAM_IS_NULL)
    private Integer project_id;
}