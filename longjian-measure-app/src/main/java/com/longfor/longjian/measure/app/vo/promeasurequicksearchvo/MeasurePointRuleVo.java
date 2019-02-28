package com.longfor.longjian.measure.app.vo.promeasurequicksearchvo;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Jiazm 2019/01/29 11:11
 */
@Data
@NoArgsConstructor
public class MeasurePointRuleVo {
    private String key;
    private String name;
    private Integer point_type;
    private Integer count_min;
    private Integer count_max;
    private Integer count_init;
    private Integer data_type;
    private Boolean design_value_reqd;
    private String allow_range;
}
