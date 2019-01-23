package com.longfor.longjian.measure.app.req.MeasureList;

import com.longfor.longjian.common.consts.ReqParamCheckErrors;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * Jiazm 2019/01/11 18:02
 */
@Data
@NoArgsConstructor
public class MeasureIssueEdiReq {
    private Integer lang;// 语言 取值范围及含义：;1 - 简体中文; ;2 - English;
    @NotNull(message = ReqParamCheckErrors.PARAM_IS_NULL)
    private String uuid; // 唯一编号
    @NotNull(message = ReqParamCheckErrors.PARAM_IS_NULL)
    private Integer project_id;// 项目id
    @NotNull(message = ReqParamCheckErrors.PARAM_IS_NULL)
    private Integer repairer_id;// 整改人id
    @NotNull(message = ReqParamCheckErrors.PARAM_IS_NULL)
    private Integer plan_end_on;// 计划结束时间

}