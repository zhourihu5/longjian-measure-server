package com.longfor.longjian.measure.domain.externalservice.impl;

import com.longfor.gaia.gfs.data.mybatis.datasource.LFAssignDataSource;
import com.longfor.longjian.measure.dao.zhijian2.MeasureRuleMapper;
import com.longfor.longjian.measure.domain.externalservice.IMeasureRuleService;
import com.longfor.longjian.measure.po.zhijian2.MeasureRule;
import com.longfor.longjian.measure.util.ExampleUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

@Service
public class MeasureRuleServiceImpl implements IMeasureRuleService {

    @Resource
    private MeasureRuleMapper measureRuleMapper;


    @Override
    public List<MeasureRule> searchUnscopedByTeamIdUpdateAtGt(Integer teamId, String updateAtDate) {
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

    @Override
    public List<MeasureRule> searchUnscopedByTeamIdLastUpdateAtGt(Integer teamId, Long timestamp) {
        if (timestamp == 0l){
            Example example = new Example(MeasureRule.class);
            example.createCriteria().andEqualTo("teamId", teamId);
            ExampleUtil.addDeleteAtJudge(example);
            return measureRuleMapper.selectByExample(example);
        }else {
            return measureRuleMapper.searchUnscopedByTeamIdLastUpdateAtGt(teamId,timestamp);
        }
    }
}
