package com.longfor.longjian.measure.app.vo.appMeasureSyncVo;

import lombok.Data;

import java.util.List;

/**
 * wangxs
 * 2018-11-20 15:44
 */
@Data
public class RuleInfoVo {
    private String category_key;
    private Integer delete_at;
    private String desc;
    private Integer group_count_init;
    private Integer group_count_max;
    private Integer group_count_min;
    private Integer id;
    private Integer rule_type;
    private Integer team_id;
    private String textures;
    private Integer update_at;
    private String formula;
    private List<PointVo> points;
}
