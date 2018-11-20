package com.longfor.longjian.measure.app.vo.measureAnalysisVo;

import lombok.Data;

import java.util.List;

/**
 * wangxs
 * 2018-11-19 15:14
 */
@Data
public class UsedFuncAnalysisVo {
    private RankingVo group;
    private List<RankingVo> items;
    private ExtraVo extra;
    private RankingVo team;
}
