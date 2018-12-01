package com.longfor.longjian.measure.app.vo.proMeasureVo;

import lombok.Data;

import java.util.List;

/**
 * wangxs
 * 2018-11-19
 */
@Data
public class CategoryDetailsVo {
    /**
     * 检查项ID
     */
    private String category_key;
    /**
     * 检查项名称
     */
    private String category_name;
    /**
     * 是否叶子节点
     */
    private Boolean is_leaf;
    /**
     * 测区数量
     */
    private Integer zone_count;
    /**
     * 检查组细节数据
     */
    private List<SquadsVo> squads;
}
