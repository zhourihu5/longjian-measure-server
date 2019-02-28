package com.longfor.longjian.measure.app.req.measureanalysisreq;

import lombok.Data;

/**
 * wangxs 2018-11-22
 */
@Data
public class StatGroupReq {
    private Integer group_id;
    private String page_level;
    private String tip;
    private String query;
    private String variables;
}
