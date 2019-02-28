package com.longfor.longjian.measure.app.vo.appmeasuresyncvo;

import lombok.Data;
/**
 * Jiazm
 * 2018/12/13 16:25
 */
import java.util.Date;
@Data
public class MeasureListVo {
    private Integer id;
    private String name;
    private Integer project_id;
    private String area_type;
    private Integer close_status;
    private Integer finish_status;
    private String root_category_key;
    private Date plan_begin_on;
    private Date plan_end_on;
    private Date create_at;
    private Date update_at;
    private Date delete_at;
}
