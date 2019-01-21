package com.longfor.longjian.measure.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Jiazm 2019/01/19 15:59
 */
@Data
@NoArgsConstructor
public class InputVo {
    private List<CategoryDataVo> data;
    private MeasuredDataVo measured_data;
}
