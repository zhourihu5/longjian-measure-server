package com.longfor.longjian.measure.dao.zhijian2;

import com.longfor.gaia.gfs.data.mybatis.LFMySQLMapper;
import com.longfor.longjian.measure.po.zhijian2.MeasureRule;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface MeasureRuleMapper extends LFMySQLMapper<MeasureRule> {
    /**
     *
     * @param teamId
     * @param updateAtDate
     * @return
     */
    List<Map<String,Object>> searchUnscopedByTeamIdUpdateAtGt(@Param("teamId")Integer teamId, @Param("updateAtDate")Date updateAtDate);
}