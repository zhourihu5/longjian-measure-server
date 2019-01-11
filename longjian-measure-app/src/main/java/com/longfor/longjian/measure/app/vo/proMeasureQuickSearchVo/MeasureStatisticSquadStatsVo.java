package com.longfor.longjian.measure.app.vo.proMeasureQuickSearchVo;

import lombok.Data;

/**
 * @description:
 * @author: DDC
 * @create: 2019-01-08 11:04
 **/
@Data
public class MeasureStatisticSquadStatsVo {
    /**
     * 任务状态
     */
    private Integer status;

    /**
     * 工程数量
     */
    private Integer category_count;

    /**
     * 检查项数量
     */
    private Integer check_item_count;

    /**
     * 楼栋数量
     */
    private Integer building_count;

    /**
     * 描画区域数量
     */
    private Integer region_count;
}


