package com.longfor.longjian.measure.app.vo.proMeasureVo;

import lombok.Data;

import java.util.List;

/**
 * wangxs
 * 2018-11-19 11:26
 */
@Data
public class BlisterCheckItemsVo {
    private List<BlisterCategoryDetailsVo> items;
    private Integer total;
}
