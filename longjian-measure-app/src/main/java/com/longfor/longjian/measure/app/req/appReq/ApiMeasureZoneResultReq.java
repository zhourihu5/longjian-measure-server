package com.longfor.longjian.measure.app.req.appReq;

import lombok.Data;

@Data
public class ApiMeasureZoneResultReq {
    /**
     * 语言 取值范围及含义：;1 - 简体中文; ;2 - English;
     */
    private Integer lang;
    /**
     * 清单ID，多可id请用半角逗号“,”分隔
     */
    private String list_ids;
}
