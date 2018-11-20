package com.longfor.longjian.measure.app.vo.proMeasureVo;

import lombok.Data;

import java.util.List;

/**
 * wangxs
 * 2018-11-19 10:49
 */
@Data
public class CompareItemBetweenSquadsVo {
    private List<CategoryDetailsVo> category_details;
    private List<SquadsPassVo> squads_rate;
}
