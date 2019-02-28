package com.longfor.longjian.measure.app.req.promeasuremanagerreq;

import lombok.Data;

@Data
public class GetProMeasureCheckItemsReq {
    /**
     * 前端传值 -- 项目id
     */
    private Integer project_id;

    /**
     * key
     */
    private String key;
}
