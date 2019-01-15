package com.longfor.longjian.measure.app.req.proMeasureQuickSearchReq;

import lombok.Data;

@Data
public class MeasureListDetailUpdateCloseStatusReq {
    /**
     * 唯一编号
     */
    private String uuid;
    /**
     * 项目ID
     */
    private Integer project_id;
    /**
     * 是否关闭
     */
    private Boolean close_status;
}
