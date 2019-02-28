package com.longfor.longjian.measure.app.vo.promeasurevo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

/**
 * wangxs
 * 2018-11-19 13:37
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AreaPOPVo {
    /**
     * 区域分布
     */
    private List<MeasureStatisticAreaDistributeVo> area_dist;
    private String category_key;
    private String category_name;
    /**
     * 是否叶子节点
     */
    private Boolean is_leaf;
}