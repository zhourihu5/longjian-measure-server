package com.longfor.longjian.measure.app.req.proMeasureQuickSearchReq;

import lombok.Data;

@Data
public class GetBlisterRateInfoReq {
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
}
