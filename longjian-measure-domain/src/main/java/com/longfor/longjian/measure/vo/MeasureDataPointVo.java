package com.longfor.longjian.measure.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Jiazm 2019/01/18 14:33
 */
@Data
@NoArgsConstructor
public class MeasureDataPointVo {
    private String key;
    private String name;
    private Integer data_type;
    private List<Float> data;
    private boolean design_value_reqd;
    private Float design_value;
    private String allow_range;
    private String seq;
    private List<Float> deviation;
}
