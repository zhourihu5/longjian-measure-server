package com.longfor.longjian.measure.app.req.proMeasureQuickSearchReq;

import lombok.Data;

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
    private String uuid;

    /**
     *项目ID
     */
    private Integer project_id;

    /**
     * 问题类型
     */
    private Integer type;
}


