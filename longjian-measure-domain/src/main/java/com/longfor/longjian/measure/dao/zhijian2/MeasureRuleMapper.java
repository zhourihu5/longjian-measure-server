package com.longfor.longjian.measure.dao.zhijian2;

import com.longfor.gaia.gfs.data.mybatis.LFMySQLMapper;
import com.longfor.longjian.measure.po.zhijian2.MeasureRule;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MeasureRuleMapper extends LFMySQLMapper<MeasureRule> {
    /**
     *
     * @param teamId
     * @param updateAtDate
     * @return
     */
    List<MeasureRule> searchUnscopedByTeamIdUpdateAtGt(@Param("teamId")Integer teamId, @Param("updateAtDate")String updateAtDate);

    /**
     *
     * @param currentCategoryKey
     * @return
     */
    MeasureRule getByCategoryKey(@Param("categoryKey") String currentCategoryKey);

    MeasureRule selectById(Integer id);

    List<MeasureRule> searchUnscopedByTeamIdLastUpdateAtGt(@Param("teamId") Integer teamId, @Param("timestamp") Long timestamp);
}