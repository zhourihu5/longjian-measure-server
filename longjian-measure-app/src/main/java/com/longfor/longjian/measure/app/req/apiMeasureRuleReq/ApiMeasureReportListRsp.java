package com.longfor.longjian.measure.app.req.apiMeasureRuleReq;

import lombok.Data;

/**
 * Jiazm
 * 2018/12/17 17:24
 */
@Data
public class ApiMeasureReportListRsp {
    /**
     *
     */
    private String uuid;
    // 抛弃原因类型
    private String reasonType;
    // 抛弃原因说明
    private String reason;
}
