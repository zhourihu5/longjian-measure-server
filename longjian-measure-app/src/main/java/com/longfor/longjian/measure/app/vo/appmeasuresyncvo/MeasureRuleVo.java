package com.longfor.longjian.measure.app.vo.appmeasuresyncvo;

import lombok.Data;

import java.util.Date;

@Data
public class MeasureRuleVo {
    private Integer id;
    private String category_key;
    private String desc;
    private String formula;
    private Integer formula_default;
    private Integer group_count_init;
    private Integer group_count_max;
    private Integer group_count_min;
    private Integer point_need;
    private String points;
    private Integer rule_type;
    private Integer team_id;
    private String textures;
    private Date create_at;
    private Date update_at;
    private Date delete_at;
}
