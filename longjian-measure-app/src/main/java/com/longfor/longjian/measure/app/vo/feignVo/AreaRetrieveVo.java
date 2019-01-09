package com.longfor.longjian.measure.app.vo.feignVo;

import lombok.Data;

@Data
public class AreaRetrieveVo {
    /**
     * 'PK'
     */
    private Integer id;
    /**
     * '项目id'
     */
    private Integer project_id;
    /**
     * '区域名称'
     */
    private String name;
    /**
     * '以上级所有路径上的区域id拼接的路径'
     */
    private String path;
    /**
     * '区域类型'
     */
    private Integer type;
    /**
     * '户型图md5
     */
    private String drawing_md5;
    /**
     * '父级区域id'
     */
    private Integer father_id;
    /**
     * '区域名称'
     */
    private String location;
    /**
     * ''
     */
    private Integer area_class_id;
    /**
     * 'is_lock'
     */
    private Integer is_lock;
    /**
     * '显示排序字段'
     */
    private Integer order_by;
    /**
     * 'code'
     */
    private String custom_code;
    /**
     * '是否为父级'
     */
    private Boolean is_parent;
    /**
     *'路径全名'
     */
    private String full_name;

}
