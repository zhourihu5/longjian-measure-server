package com.longfor.longjian.measure.app.vo.proMeasureQuickSearchVo;

import lombok.Data;

import java.util.Date;

/**
 * @description:
 * @author: DDC
 * @create: 2019-01-12 14:39
 **/
@Data
public class MeasureRegionVo {
    private Integer id;
    private Integer project_id;
    private Integer area_id;
    private String area_path_and_id;
    private String drawing_md5;
    private Object polygon;
    private Integer region_index;
    private Integer rel_id;
    private Integer src_type;
    private String uuid;
    private String tag_id_list;
    private Date create_at;
    private Date update_at;
    private Date delete_at;
}


