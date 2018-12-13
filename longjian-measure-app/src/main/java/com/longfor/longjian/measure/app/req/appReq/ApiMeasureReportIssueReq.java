package com.longfor.longjian.measure.app.req.appReq;

import lombok.Data;

@Data
public class ApiMeasureReportIssueReq {
    /**
     *  语言 取值范围及含义：;1 - 简体中文; ;2 - English;
     */
    private Integer lang;
    /**
     * report_uuid
     */
    private String report_uuid;
    /**
     * 项目ID
     */
    private Integer project_id;
    /**
     * 要上报的数据
     */
    private String data;
}
