package com.longfor.longjian.measure.app.req.measurelist;

import lombok.Data;

@Data
public class BgAddReq {
    /**
     * '集团id'
     */
    private Integer group_id;
    /**
     * '项目id'
     */
    private Integer proj_id;
    /**
     * '区域id列表'
     */
    private String area_id_list;
    /**
     * '区域类型'default='undefined'
     */
    private String area_type;
    /**
     * '顶层检查项id'
     */
    private Integer root_category_key;
    /**
     * '生成测区组信息'
     */
    private String zone_group;
    /**
     * '生成测区组信息'
     */
    private String squad_group;
    /**
     * '生成测区组信息'
     */
    private String repairer_group;
    /**
     * '开始时间'
     */
    private String plan_begin_on;
    /**
     * '结束时间'
     */
    private String plan_end_on;
    /**
     * '实测任务名'
     */
    private String name;

//    group_id=required.IntegerField(desc='集团id'),
//    proj_id=required.IntegerField(desc='项目id'),
//    area_id_list=required.StringField(desc='区域id列表'),
//    area_type=required.StringField(desc='区域类型', default='undefined'),
//    root_category_key=required.IntegerField(desc='顶层检查项id'),
//    zone_group=required.StringField(desc='生成测区组信息'),
//    squad_group=required.StringField(desc='生成测区组信息'),
//    repairer_group=required.StringField(desc='生成测区组信息'),
//    plan_begin_on=required.StringField(desc='开始时间'),
//    plan_end_on=required.StringField(desc='结束时间'),
//    name=required.StringField(desc='实测任务名'),
}
