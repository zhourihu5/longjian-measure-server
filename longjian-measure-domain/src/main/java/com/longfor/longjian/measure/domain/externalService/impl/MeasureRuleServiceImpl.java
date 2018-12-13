package com.longfor.longjian.measure.domain.externalService.impl;

import com.longfor.longjian.measure.dao.zhijian2.MeasureRuleMapper;
import com.longfor.longjian.measure.domain.externalService.IMeasureRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class MeasureRuleServiceImpl implements IMeasureRuleService {

    @Autowired
    private MeasureRuleMapper measureRuleMapper;


    @Override
    public List<Map<String, Object>> searchUnscopedByTeamIdUpdateAtGt(Integer teamId, Date updateAtDate) {
            return measureRuleMapper.searchUnscopedByTeamIdUpdateAtGt(teamId,updateAtDate);
    }

}
