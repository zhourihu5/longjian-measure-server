package com.longfor.longjian.measure.app.req.proMeasureQuickSearchReq;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @description:
 * @author: DDC
 * @create: 2019-01-09 17:14
 **/
@Data
public class GetMeasureListIssueDetailReq {
    /**
     * 语言 取值范围及含义：;1 - 简体中文; ;2 - English;
     */
    @NotNull
    private Integer lang;
    /**
     * 唯一编号
     */
    @NotNull
    private String uuid;
    /**
     * 项目ID
     */
    @NotNull
    private Integer project_id;
}


