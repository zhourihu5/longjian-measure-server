package com.longfor.longjian.measure.app.vo.measureListVo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Jiazm 2019/01/11 18:10
 */
@Data
@NoArgsConstructor
public class MeasureIssueQueryVo {
    /**
     *  CurrentPage
     */
    private Integer page;
    /**
     * PageSize
     */
    private Integer limit;
    /**
     * 总数
     */
    private Integer count;
    /**
     * 问题列表
     */
    private List<MeasureIssueQueryItemVo> items;

}
