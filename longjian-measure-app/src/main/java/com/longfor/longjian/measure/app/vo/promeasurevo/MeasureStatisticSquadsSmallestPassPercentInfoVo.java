package com.longfor.longjian.measure.app.vo.promeasurevo;

import lombok.Data;

import java.util.List;

@Data
public class MeasureStatisticSquadsSmallestPassPercentInfoVo {

    /**
     * 检查项key
     */
    private String category_key;
    /**
     * 检查项名称
     */
    private String category_name;
    /**
     * 组别数据
     */
    private List<String> squads;
    /**
     * 最小的索引项
     */
    private Integer smallest_index;
}
