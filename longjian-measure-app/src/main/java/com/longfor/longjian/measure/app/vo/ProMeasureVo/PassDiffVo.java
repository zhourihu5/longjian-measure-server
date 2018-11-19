package com.longfor.longjian.measure.app.vo.ProMeasureVo;

import lombok.Data;

import java.util.List;

/**
 * wangxs
 * 2018-11-17 15:56
 */
@Data
public class PassDiffVo {
    private List<String> pass_diff_largest;
    private List<String> pass_diff_largest_squad_names;
    private List<String> pass_percentage_smallest;
    private List<String> pass_percentage_smallest_squad_names;
}
