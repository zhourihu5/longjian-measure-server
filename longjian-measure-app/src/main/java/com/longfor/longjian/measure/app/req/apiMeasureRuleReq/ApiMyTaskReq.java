package com.longfor.longjian.measure.app.req.apiMeasureRuleReq;

import lombok.Data;

@Data
public class ApiMyTaskReq {
    /**
     * 语言 取值范围及含义：;1 - 简体中文; ;2 - English;
     */
    private int lang;
    /**
     * 项目ID，多可id请用半角逗号“,”分隔
     */
    private String  project_id;

}
