package com.longfor.longjian.measure.app.req.proMeasureQuickSearchReq;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @description:
 * @author: DDC
 * @create: 2019-01-14 16:04
 **/
@Data
public class MeasureListDetailUpdateIssueRepairerReq {
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

    /**
     * 整改人ID
     */
    @NotNull
    private Integer repairer_id;


}


