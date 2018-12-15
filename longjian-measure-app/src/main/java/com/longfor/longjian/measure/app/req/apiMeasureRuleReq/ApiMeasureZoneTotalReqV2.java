package com.longfor.longjian.measure.app.req.apiMeasureRuleReq;

import lombok.Data;

/**
 * Jiazm
 * 2018/12/14 11:48
 */
@Data
public class ApiMeasureZoneTotalReqV2 {
    /**
     * 语言 取值范围及含义：;1 - 简体中文; ;2 - English;
     */
    private Integer lang;
    /**
     * 清单ID
     */
    private Integer list_id;
    /**
     * 上次更新时间
     */
    private Long timestamp;
}
