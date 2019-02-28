package com.longfor.longjian.measure.app.vo.measureanalysisvo;

import lombok.Data;

import java.util.List;

/**
 * wangxs
 * 2018-11-19 16:07
 */
@Data
public class AnalysisVo {
    private List<RankingVo> items;
    private ExtraVo extra;

    /**
     * 对比人员查询
     */
    private Integer id;
    private String realName;

    /**
     * 功能统计
     */
    private List<RankingVo> group;
    private List<RankingVo> proj;
    private List<RankingVo> team;
}
