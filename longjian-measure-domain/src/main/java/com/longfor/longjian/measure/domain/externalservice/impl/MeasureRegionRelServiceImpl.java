package com.longfor.longjian.measure.domain.externalservice.impl;

import com.longfor.longjian.measure.dao.zhijian2.MeasureRegionRelMapper;
import com.longfor.longjian.measure.domain.externalservice.IMeasureRegionRelService;
import com.longfor.longjian.measure.po.zhijian2.MeasureRegionRel;
import com.longfor.longjian.measure.vo.MeasureRegionRelVo;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class MeasureRegionRelServiceImpl implements IMeasureRegionRelService {

    @Resource
    MeasureRegionRelMapper measureRegionRelMapper;

    @Override
    public Map<String, Object> selectByRelId(String relId) {
        return measureRegionRelMapper.selectByRelId(relId);
    }

    @Override
    public List<MeasureRegionRel> searchRelUnscopedByProjIdUpdateAtGt(String projectId, String updateAtGte) {
        return measureRegionRelMapper.searchRelUnscopedByProjIdUpdateAtGt(projectId,updateAtGte);
    }

    @Override
    public List<MeasureRegionRel> searchRelUnscopedByProjIdLastIdUpdateAtGt(Integer projectId, Integer lastId, Long timestamp, Integer measureApiGetPerTime, Integer start) {
        return measureRegionRelMapper.searchRelUnscopedByProjIdLastIdUpdateAtGt(projectId,lastId,timestamp,measureApiGetPerTime,start);
    }

    @Override
    public MeasureRegionRel save(MeasureRegionRel model) {
        measureRegionRelMapper.insertSelective(model);
        return model;
    }

    @Override
    public MeasureRegionRelVo selectById(Integer id) {
        return measureRegionRelMapper.selectById(id);
    }

    @Override
    public List<MeasureRegionRel> selectByProjectIdAndIdNoDeleted(Integer projectId, List<Integer> relIdList) {
        if (relIdList == null || relIdList.isEmpty()){
            return new ArrayList<>();
        }
        Example example = new Example(MeasureRegionRel.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("projectId",projectId);
        criteria.andIn("id",relIdList);
        criteria.andIsNull("deleteAt");
        return measureRegionRelMapper.selectByExample(example);
    }

    @Override
    public MeasureRegionRel update(MeasureRegionRel relModel) {
        measureRegionRelMapper.updateByPrimaryKeySelective(relModel);
        return relModel;
    }
}
