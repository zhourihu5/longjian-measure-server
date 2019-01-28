package com.longfor.longjian.measure.app.vo.measureListVo;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * Jiazm 2019/01/26 16:17
 */
@Data

public class ListInfoVo {
    private Integer id;
    private String name;
    private Integer proj_id;
    private String area_type;
    private Integer close_status;
    private Integer finish_status;
    private List<String> top_areas;
    private String root_category_key;
    private String root_category_name;
    private Integer issue_count;
    private String create_at;
}
