package com.longfor.longjian.measure.app.vo.appmeasuresyncvo;

import lombok.Data;

/**
 * wangxs
 * 2018-11-20 15:50
 */
@Data
public class PointVo {
    private String key;
    private String name;
    private Integer point_type;
    private Integer count_min;
    private Integer count_max;
    private Integer count_init;
    private Integer data_type;
    private boolean design_value_reqd;
    private String allow_range;
}
