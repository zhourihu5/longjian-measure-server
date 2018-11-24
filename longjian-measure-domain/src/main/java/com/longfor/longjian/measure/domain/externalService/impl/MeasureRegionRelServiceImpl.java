package com.longfor.longjian.measure.domain.externalService.impl;

import com.longfor.longjian.measure.dao.zhijian2.MeasureRegionRelMapper;
import com.longfor.longjian.measure.domain.externalService.IMeasureRegionRelService;
import com.longfor.longjian.measure.po.zhijian2.MeasureRegionRel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class MeasureRegionRelServiceImpl implements IMeasureRegionRelService {

    @Autowired
    MeasureRegionRelMapper measureRegionRelMapper;

    @Override
    public Map<String, Object> selectByRelId(String rel_id) {
        return measureRegionRelMapper.selectByRelId(rel_id);
    }
}
