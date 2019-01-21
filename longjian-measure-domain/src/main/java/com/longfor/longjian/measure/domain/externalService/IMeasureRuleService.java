package com.longfor.longjian.measure.domain.externalService;


import com.longfor.longjian.measure.po.zhijian2.MeasureRule;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IMeasureRuleService {
    /**
     * 根据根据修改时间跟分组做查询
     * @param teamId
     * @param updateAtDate
     */
    List<MeasureRule> searchUnscopedByTeamIdUpdateAtGt(Integer teamId, Date updateAtDate);

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
}
