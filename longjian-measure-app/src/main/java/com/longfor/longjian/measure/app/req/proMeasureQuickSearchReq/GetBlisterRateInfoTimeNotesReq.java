package com.longfor.longjian.measure.app.req.proMeasureQuickSearchReq;

import lombok.Data;

@Data
public class GetBlisterRateInfoTimeNotesReq {
    /**
     * 语言 取值范围及含义：;1 - 简体中文; ;2 - English;
     */
    private Integer lang;
    /**
     * 项目id
     */
    private Integer project_id;
    /**
     * 实测清单ID
     */
    private Integer measure_list_id;
    /**
     * 开始时间戳
     */
    private Long begin_on;
    /**
     * 结束时间戳
     */
    private Long end_on;
}
