package com.longfor.longjian.measure.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * Jiazm 2019/01/19 15:59
 */
@Data
@NoArgsConstructor
public class InputVo implements Serializable {
    private List<CategoryDataVo> data;
    private MeasuredDataVo measured_data;
}
