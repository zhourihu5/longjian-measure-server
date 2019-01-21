package com.longfor.longjian.measure.app.req.proMeasureQuickSearchReq;

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
    @NotNull
    private String uuid;

    /**
     *项目ID
     */
    @NotNull
    private Integer project_id;

    /**
     * 问题类型
     */
    @NotNull
    private Integer type;
}


