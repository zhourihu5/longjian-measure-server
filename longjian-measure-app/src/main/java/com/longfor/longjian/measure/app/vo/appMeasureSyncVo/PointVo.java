package com.longfor.longjian.measure.app.vo.appMeasureSyncVo;

import lombok.Data;

/**
 * wangxs
 * 2018-11-20 15:50
 */
@Data
public class PointVo {
    private String allow_range;
    private Integer count_init;
    private Integer count_max;
    private Integer count_min;
    private Integer data_type;
    private boolean design_value_reqd;
    private String key;
    private String name;
    private Integer point_type;
}
