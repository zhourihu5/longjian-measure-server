package com.longfor.longjian.measure.app.req.apiMeasureRuleReq;

import lombok.Data;

/**
 * Jiazm
 * 2018/12/13 16:39
 */
@Data
public class ApiMeasureZoneReq {
    /**
     * 语言 取值范围及含义：;1 - 简体中文; ;2 - English;
     */
    private int lang;
    /**
     * 清单ID，多可id请用半角逗号“,”分隔
     */
    private String list_ids;
}
