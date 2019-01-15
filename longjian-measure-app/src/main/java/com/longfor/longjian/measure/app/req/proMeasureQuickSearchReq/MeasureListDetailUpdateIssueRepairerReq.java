package com.longfor.longjian.measure.app.req.proMeasureQuickSearchReq;

import lombok.Data;

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
    private String uuid;

    /**
     * 项目ID
     */
    private Integer project_id;

    /**
     * 整改人ID
     */
    private Integer repairer_id;


}


