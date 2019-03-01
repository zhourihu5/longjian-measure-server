package com.longfor.longjian.measure.vo;

import lombok.Data;

/**
 * @Date 2019/3/1 16:44
 * Created by Jiazhongmin
 */
@Data
public class CreateMeasureListVo {
    private int proj_id;
    private String name;
    private String area_type;
    private Integer id;
    private Integer id1;
    private String root_category_key;
    private String plan_begin_on;
    private String plan_end_on;
}
