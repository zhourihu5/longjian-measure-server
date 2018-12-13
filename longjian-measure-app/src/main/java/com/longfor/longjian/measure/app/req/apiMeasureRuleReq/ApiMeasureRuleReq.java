package com.longfor.longjian.measure.app.req.apiMeasureRuleReq;

import lombok.Data;

@Data
public class ApiMeasureRuleReq {
    /**
     * 类型Key，多个Key请用半角逗号“,”分隔
     */
    private String category_keys;
}
