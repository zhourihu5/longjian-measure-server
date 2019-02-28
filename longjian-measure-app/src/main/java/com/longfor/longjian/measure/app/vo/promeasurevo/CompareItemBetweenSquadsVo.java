package com.longfor.longjian.measure.app.vo.promeasurevo;

import lombok.Data;

import java.util.List;

/**
 * wangxs
 * 2018-11-19 10:49
 */
@Data
public class CompareItemBetweenSquadsVo {
    /**
     * 检查项细节
     */
    private List<CategoryDetailsVo> category_details;
    /**
     * 检查组别信息
     */
    private List<SquadsPassVo> squads_rate;
}
