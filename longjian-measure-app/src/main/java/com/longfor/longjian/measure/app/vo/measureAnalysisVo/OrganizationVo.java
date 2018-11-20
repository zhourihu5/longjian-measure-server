package com.longfor.longjian.measure.app.vo.measureAnalysisVo;

import lombok.Data;

import java.util.List;

/**
 * wangxs
 * 2018-11-19 14:54
 */
@Data
public class OrganizationVo {
    private ExtraVo extra;
    private List<RankingVo> items;

    private Integer key;
    private String label;
    private String name;
    private Integer value;
    private Integer id;
}
