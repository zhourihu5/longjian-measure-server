package com.longfor.longjian.measure.app.req.proMeasureManagerReq;

import lombok.Data;

@Data
public class GetProMeasureCheckItemsReq {
    /**
     * 前端传值 -- 项目id
     */
    private Integer project_id;
    /**
     * 前端传值 -- 暂时不知道干嘛用的
     */
    private Integer _;

    /**
     * 语言 取值范围及含义：;1 - 简体中文; ;2 - English;
     */
    private Integer lang;
    /**
     * key
     */
    private String key;
}
