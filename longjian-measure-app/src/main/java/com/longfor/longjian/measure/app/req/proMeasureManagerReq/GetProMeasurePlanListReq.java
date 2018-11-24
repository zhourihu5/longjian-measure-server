package com.longfor.longjian.measure.app.req.proMeasureManagerReq;

import lombok.Data;

/**
 * wangxs
 * 2018-11-23
 */
@Data
public class GetProMeasurePlanListReq {
    /**
     * 语言 取值范围及含义：;1 - 简体中文; ;2 - English;
     */
    private  Integer lang;
    /**
     *  检查项
     */
    private String category_key;
    /**
     * 人员
     */
    private String user_ids;
    /**
     * 区域
     */
    private Integer area_id;
    /**
     * 完成状态
     */
    private Integer finish_status;
    /**
     * 名称
     */
    private String q;
    /**
     * 页次
     */
    private Integer page;
    /**
     * 每页数
     */
    private Integer page_size;


    /**
     * 前端传值 -- 项目id
     */
    private Integer project_id;
    /**
     * 前端传值 -- 暂时不知道干嘛用的
     */
    private Integer _;
}
