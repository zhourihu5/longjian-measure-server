package com.longfor.longjian.measure.app.req.appReq;

import lombok.Data;

@Data
public class ApiMeasureSquadAndRepairerReq {
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
