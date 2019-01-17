package com.longfor.longjian.measure.app.req.proMeasureQuickSearchReq;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @description:
 * @author: DDC
 * @create: 2019-01-08 10:44
 **/
@Data
public class GetMeasureStatisticTaskReq {
    /**
     * 语言 取值范围及含义：;1 - 简体中文; ;2 - English;
     */
    private Integer lang;

    /**
     * 项目ID
     */
    @NotNull
    private Integer project_id;

    /**
     * 任务ID
     */
    @NotNull
    private Integer measure_list_id;
}


