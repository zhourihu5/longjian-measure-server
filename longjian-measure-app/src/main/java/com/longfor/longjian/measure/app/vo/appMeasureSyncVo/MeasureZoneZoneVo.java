package com.longfor.longjian.measure.app.vo.appMeasureSyncVo;

import lombok.Data;

import java.util.Date;

/***
 * Jiazm
 * 2018/12/14 10:32
 */
@Data
public class MeasureZoneZoneVo {
    private Integer id;
    private Integer project_id;
    private Integer area_id;
    private String area_path_and_id;
    private String uuid;
    private String category_key;
    private String category_path_and_key;
    private Integer close_status;
    private Integer finish_status;
    private Integer list_id;
    private String region_uuid;
    private Integer src_type;
    private Date create_at;
    private Date update_at;
    private Date delete_at;
}
