package com.longfor.longjian.measure.domain.externalService.impl;

import com.longfor.longjian.measure.dao.zhijian2.MeasureRegionRelMapper;
import com.longfor.longjian.measure.domain.externalService.IMeasureRegionRelService;
import com.longfor.longjian.measure.po.zhijian2.MeasureRegionRel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class MeasureRegionRelServiceImpl implements IMeasureRegionRelService {

    @Resource
    MeasureRegionRelMapper measureRegionRelMapper;

    @Override
    public Map<String, Object> selectByRelId(String rel_id) {
        return measureRegionRelMapper.selectByRelId(rel_id);
    }

    @Override
    public List<MeasureRegionRel> searchRelUnscopedByProjIdUpdateAtGt(String projectId, String updateAtGte) {
        return measureRegionRelMapper.searchRelUnscopedByProjIdUpdateAtGt(projectId,updateAtGte);
    }

    @Override
    public List<MeasureRegionRel> searchRelUnscopedByProjIdLastIdUpdateAtGt(Integer project_id, Integer last_id, Long timestamp, Integer measureApiGetPerTime, Integer start) {
        return measureRegionRelMapper.searchRelUnscopedByProjIdLastIdUpdateAtGt(project_id,last_id,timestamp,measureApiGetPerTime,start);
    }

    @Override
    public MeasureRegionRel save(MeasureRegionRel model) {
        measureRegionRelMapper.insertSelective(model);
        return model;
    }
}
