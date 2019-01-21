package com.longfor.longjian.measure.app.req.proMeasureQuickSearchReq;

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
    @NotNull
    private String uuid;

    /**
     *项目ID
     */
    @NotNull
    private Integer project_id;

    /**
     * 整改期限
     */
    @NotNull
    private Long plan_end_on;
}


