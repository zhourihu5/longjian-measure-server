package com.longfor.longjian.measure.app.req.apimeasurerulereq;

import lombok.Data;

/**
 * Jiazm
 * 2018/12/18 09:16
 */
@Data
public class MeasureRegionStructReq {
    private String uuid;
    private Integer project_id;
    private Integer region_index;
    private Integer area_id;
    private Integer src_type;
    private String area_path_and_id;
    private String drawing_md5;
    private Float polygon_x;
    private Float polygon_y;
}
