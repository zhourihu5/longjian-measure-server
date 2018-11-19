package com.longfor.longjian.measure.app.vo.ProMeasureVo;

import io.swagger.models.auth.In;
import lombok.Data;

/**
 * wangxs
 * 2018-11-17 14:11
 */
@Data
public class ProMeasurePlanVo {
    private String close_status;
    private Integer create_at;
    private String finish_status;
    private Integer id;
    private Integer issue_count;
    private String name;
    private String root_category;
    private String top_areas;
}
