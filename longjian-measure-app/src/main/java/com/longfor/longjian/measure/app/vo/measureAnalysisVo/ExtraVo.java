package com.longfor.longjian.measure.app.vo.measureAnalysisVo;

import jdk.internal.org.objectweb.asm.commons.RemappingAnnotationAdapter;
import lombok.Data;

import java.util.List;

/**
 * wangxs
 * 2018-11-19 14:59
 */
@Data
public class ExtraVo {
    private List<RankingVo> groupAvgs;
    private List<RankingVo> avgs;
    private List<RankingVo> teamAvgs;

    /**
     * 项目实测实量统计分析
     */
    private List<RankingVo> allProgress;
    private List<RankingVo> areaProgress;
    private List<RankingVo> categoryProgress;
    private List<RankingVo> alls;


    private List<RankingVo> allAvgs;
    private List<RankingVo> areaAvgs;
    private List<RankingVo> categoryAvgs;
}
