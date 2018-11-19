package com.longfor.longjian.measure.app.vo.ProMeasureVo;

import lombok.Data;

import java.util.List;

/**
 * wangxs
 * 2018-11-19
 */
@Data
public class CategoryDetailsVo {
    private String category_key;
    private String category_name;
    private boolean is_leaf;
    private Integer zone_count;
    private List<SquadsVo> squads;
}
