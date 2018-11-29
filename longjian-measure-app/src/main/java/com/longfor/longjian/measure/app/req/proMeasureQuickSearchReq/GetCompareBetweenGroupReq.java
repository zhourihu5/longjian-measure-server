package com.longfor.longjian.measure.app.req.proMeasureQuickSearchReq;

import lombok.Data;

@Data
public class GetCompareBetweenGroupReq {
    /**
     * 语言 取值范围及含义：;1 - 简体中文; ;2 - English;
     */
    private Integer lang;
    /**
     * 项目ID
     */
    private Integer project_id;
    /**
     * 任务ID
     */
    private Integer measure_list_id;

    private Integer group_id;
    private Integer team_id;
}
