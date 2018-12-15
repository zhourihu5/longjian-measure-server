package com.longfor.longjian.measure.app.req.apiMeasureRuleReq;

import lombok.Data;

/**
 * Jiazm
 * 2018/12/14 14:22
 */
@Data
public class ApiMeasureIssueReq {
    /**
     * 语言 取值范围及含义：;1 - 简体中文; ;2 - English;
     */
    private Integer lang;
    /**
     * 清单Id
     */
    private Integer list_id;
    /**
     * 上次获取最后的Id
     */
    private Integer last_id;
    /**
     * 更新时间
     */
    private Long    timestamp;
}
