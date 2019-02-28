package com.longfor.longjian.measure.app.vo.promeasurevo;

import lombok.Data;

@Data
public class MeasureStatisticAreaDistributeVo {
    /**
     * 区域ID
     */
    private Integer area_id;
    /**
     * 合格率
     */
    private String percentage;
    /**
     * 爆点问题数
     */
    private Integer issue_count;
    /**
     * 已修复爆点问题数
     */
    private Integer checked_issue_count;
}
