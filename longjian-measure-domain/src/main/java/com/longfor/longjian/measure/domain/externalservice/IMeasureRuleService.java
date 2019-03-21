package com.longfor.longjian.measure.domain.externalservice;


import com.longfor.longjian.measure.po.zhijian2.MeasureRule;

import java.util.List;
import java.util.Set;

public interface IMeasureRuleService {
    /**
     * 根据根据修改时间跟分组做查询
     * @param teamId
     * @param updateAtDate
     */
    List<MeasureRule> searchUnscopedByTeamIdUpdateAtGt(Integer teamId, String updateAtDate);

    /**
     *
     * @param currentCategoryKey
     * @return
     */
    MeasureRule getByCategoryKey(String currentCategoryKey);

    MeasureRule selectById(Integer id);

    /**
     *
     * @param keySet
     * @return
     */
    List<MeasureRule> searchUnscopedByIds(Set<Integer> keySet);

    /**
     *
     * @param keySet
     * @return
     */
    List<MeasureRule> searchByIds(Set<Integer> keySet);

    List<MeasureRule> searchUnscopedByTeamIdLastUpdateAtGt(Integer v, Long timestamp);
}
