package com.longfor.longjian.measure.app.vo.promeasurevo;

import lombok.Data;

import java.util.List;

/**
 * wangxs
 * 2018-11-17 15:56
 */
@Data
public class PassDiffVo {
    /**
     * 合格率差值最大组名
     */
    private List<MeasureStatisticSquadsPassDiffLargestInfoVo> pass_diff_largest;
    /**
     * 合格率差值最大
     */
    private List<String> pass_diff_largest_squad_names;
    /**
     * 合格率最小组名
     */
    private List<MeasureStatisticSquadsSmallestPassPercentInfoVo> pass_percentage_smallest;
    /**
     * 合格率最低
     */
    private List<String> pass_percentage_smallest_squad_names;
}
