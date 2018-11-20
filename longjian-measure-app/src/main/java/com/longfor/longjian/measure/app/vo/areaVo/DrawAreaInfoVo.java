package com.longfor.longjian.measure.app.vo.areaVo;

import lombok.Data;

/**
 * wangxs
 * 2018-11-19 19:58
 */
@Data
public class DrawAreaInfoVo {
    private Integer area_class_id;
    private String custom_code;
    private String drawing_md5;
    private Integer father_id;
    private String full_name;
    private Integer id;
    private Integer is_lock;
    private boolean is_parent;
    private String location;
    private String name;
    private Integer order_by;
    private String path;
}
