package com.longfor.longjian.measure.app.req.proMeasureQuickSearchReq;

import lombok.Data;

@Data
public class GetBlisterRateCheckItemsReq {
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
     * 传入key取出其下的所有子项目的数据,空返回顶级检查项
     */
    private String category_key;
}
