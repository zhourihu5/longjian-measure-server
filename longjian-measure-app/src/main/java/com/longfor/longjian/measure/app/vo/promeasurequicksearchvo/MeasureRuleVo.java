package com.longfor.longjian.measure.app.vo.promeasurequicksearchvo;

import lombok.Data;

import java.util.List;

/**
 * @description:
 * @author: DDC
 * @create: 2019-01-12 17:52
 **/
@Data
public class MeasureRuleVo {
    private Integer id;
    private Integer team_id;
    private Integer rule_type;
    private String category_key;
    private String desc;
    private String formula;
    private Integer group_count_min;
    private Integer group_count_max;
    private Integer group_count_init;
    private String textures;
    private Integer point_need;
    private List<MeasurePointRuleVo> points;
    private Integer create_at;
    private Integer update_at;
}


