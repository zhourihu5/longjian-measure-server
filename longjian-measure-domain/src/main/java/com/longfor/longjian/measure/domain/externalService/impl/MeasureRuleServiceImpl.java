package com.longfor.longjian.measure.domain.externalService.impl;

import com.longfor.gaia.gfs.data.mybatis.datasource.LFAssignDataSource;
import com.longfor.longjian.measure.dao.zhijian2.MeasureRuleMapper;
import com.longfor.longjian.measure.domain.externalService.IMeasureRuleService;
import com.longfor.longjian.measure.po.zhijian2.MeasureRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class MeasureRuleServiceImpl implements IMeasureRuleService {

    @Autowired
    private MeasureRuleMapper measureRuleMapper;


    @Override
    public List<MeasureRule> searchUnscopedByTeamIdUpdateAtGt(Integer teamId, Date updateAtDate) {
        return measureRuleMapper.searchUnscopedByTeamIdUpdateAtGt(teamId, updateAtDate);
    }

    @Override
    public MeasureRule getByCategoryKey(String currentCategoryKey) {
        return measureRuleMapper.getByCategoryKey(currentCategoryKey);
    }

    @Override
    public MeasureRule selectById(Integer id) {
        return measureRuleMapper.selectById(id);
    }

    @Override
    @LFAssignDataSource("zhijian2")
    public List<MeasureRule> searchUnscopedByIds(Set<Integer> keySet) {
        Example example = new Example(MeasureRule.class);
        example.createCriteria().andIn("id", keySet);
        return measureRuleMapper.selectByExample(example);
    }

    @Override
    public List<MeasureRule> searchByIds(Set<Integer> keySet) {
        Example example = new Example(MeasureRule.class);
        example.createCriteria().andIn("id", keySet);
        return measureRuleMapper.selectByExample(example);
    }
}
