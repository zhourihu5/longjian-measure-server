package com.longfor.longjian.measure.app.req.proMeasureQuickSearchReq;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class MeasureListDetailUpdateApproveIssueReq {
    /**
     * 唯一编号
     */
    @NotNull
    private String uuid;
    /**
     * 项目ID
     */
    @NotNull
    private Integer ProjectId;
    /**
     * 是否通过
     */
    @NotNull
    private Integer status;
    /**
     * 不通过理由
     */
    @NotNull
    private String content;
}
