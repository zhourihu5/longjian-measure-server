package com.longfor.longjian.measure.app.req.appReq;

import lombok.Data;

@Data
public class ApiMeasureRegionReqV2 {
    /**
     * 语言 取值范围及含义：;1 - 简体中文; ;2 - English;
     */
    private Integer lang;
    /**
     * 项目ID
     */
    private Integer project_id;
    /**
     * 上次ID
     */
    private Integer last_id;
    /**
     * 上次更新时间
     */
    private Long timestamp;
}
