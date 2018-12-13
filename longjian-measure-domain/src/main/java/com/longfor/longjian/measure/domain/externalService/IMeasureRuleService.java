package com.longfor.longjian.measure.domain.externalService;


import java.util.Date;
import java.util.List;
import java.util.Map;

public interface IMeasureRuleService {
    /**
     * 根据根据修改时间跟分组做查询
     * @param teamId
     * @param updateAtDate
     */
    List<Map<String,Object>> searchUnscopedByTeamIdUpdateAtGt(Integer teamId, Date updateAtDate);
}
