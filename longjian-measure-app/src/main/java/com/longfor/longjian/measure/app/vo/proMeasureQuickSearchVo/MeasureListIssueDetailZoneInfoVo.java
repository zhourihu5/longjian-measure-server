package com.longfor.longjian.measure.app.vo.proMeasureQuickSearchVo;

import lombok.Data;

import java.util.List;

/**
 * @description:
 * @author: DDC
 * @create: 2019-01-11 17:25
 **/
@Data
public class MeasureListIssueDetailZoneInfoVo {
    /**
     * 测点信息
     */
    private MeasureRegionVo region;

    /**
     * 实测结果集
     */
    private List<MeasureListIssueDetailSquadResultVo> results;

    /**
     * 实测测量小组
     */
    private List<MeasureListIssueDetailSquadVo> squads;

    /**
     *
     */
    private MeasureRuleVo rule;


}


