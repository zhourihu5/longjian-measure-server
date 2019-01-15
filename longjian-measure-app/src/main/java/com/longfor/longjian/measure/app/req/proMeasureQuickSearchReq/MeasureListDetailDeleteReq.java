package com.longfor.longjian.measure.app.req.proMeasureQuickSearchReq;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class MeasureListDetailDeleteReq {
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
