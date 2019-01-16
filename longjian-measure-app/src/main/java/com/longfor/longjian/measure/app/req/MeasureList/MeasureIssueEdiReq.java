package com.longfor.longjian.measure.app.req.MeasureList;

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
    @NotNull
    private String uuid; // 唯一编号
    @NotNull
    private Integer project_id;// 项目id
    @NotNull
    private Integer repairer_id;// 整改人id
    @NotNull
    private Integer plan_end_on;// 计划结束时间

}