package com.longfor.longjian.measure.app.req.proMeasureQuickSearchReq;

import lombok.Data;

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
    private String uuid;

    /**
     *项目ID
     */
    private Integer project_id;

    /**
     * 整改期限
     */
    private Long plan_end_on;
}


