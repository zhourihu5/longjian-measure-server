package com.longfor.longjian.measure.app.vo.appMeasureSyncVo;

import lombok.Data;

import java.util.List;

/**
 * wangxs
 * 2018-11-20 15:44
 */
@Data
public class RuleInfoVo {
    private Integer id;
    private Integer team_id;
    private String category_key;
    private String desc;
    private Integer rule_type;
    private String formula;
    private Integer group_count_min;
    private Integer group_count_max;
    private Integer group_count_init;
    private String textures;
    private List<PointVo> points;
    private Integer update_at;
    private Integer delete_at;
}
