package com.longfor.longjian.measure.domain.externalService.impl;

import com.longfor.longjian.measure.dao.zhijian2.MeasureSquadMapper;
import com.longfor.longjian.measure.domain.externalService.IMeasureSquadService;
import com.longfor.longjian.measure.po.zhijian2.MeasureSquad;
import com.longfor.longjian.measure.po.zhijian2.MeasureZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class MeasureSquadServiceImpl implements IMeasureSquadService {
    @Autowired
    private MeasureSquadMapper measureSquadMapper;
    @Override
    public List<MeasureSquad> searchOnlyMeasureSquadByProjIdAndListId(Integer project_id, Integer measure_list_id) {
        Example example = new Example(MeasureSquad.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("listId",measure_list_id);
        criteria.andEqualTo("projectId",project_id);
        criteria.andIsNull("deleteAt");
        return measureSquadMapper.selectByExample(example);
    }

    @Override
    public List<MeasureSquad> searchMeasureSquadByListIdTimestampGt(Integer projectId, Integer list_id, String updateAtGt) {
        return measureSquadMapper.searchMeasureSquadByListIdTimestampGt(projectId,list_id,updateAtGt);
    }

    @Override
    public void delete(Integer id) {

        Example example = new Example(MeasureSquad.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("listId",id);

        MeasureSquad measureSquad=new MeasureSquad();
        measureSquad.setListId(id);
        measureSquad.setDeleteAt(new Date());

        measureSquadMapper.updateByExampleSelective(measureSquad,example);
    }

    @Override
    public int create(MeasureSquad measureSquad) {

        measureSquadMapper.insertMeasureSquad(measureSquad);

        return measureSquad.getId();
    }

    @Override
    public int update(MeasureSquad measureSquad) {
        Example example = new Example(MeasureSquad.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id",measureSquad.getId());

        return measureSquadMapper.updateByExampleSelective(measureSquad,example);
    }

    @Override
    public List<MeasureSquad> selectByExample(Example example) {
        return measureSquadMapper.selectByExample(example);
    }
}
