package com.longfor.longjian.measure.domain.externalservice.impl;

import com.longfor.longjian.measure.dao.zhijian2.MeasureSquadMapper;
import com.longfor.longjian.measure.domain.externalservice.IMeasureSquadService;
import com.longfor.longjian.measure.po.zhijian2.MeasureSquad;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class MeasureSquadServiceImpl implements IMeasureSquadService {
    @Resource
    private MeasureSquadMapper measureSquadMapper;
    @Override
    public List<MeasureSquad> searchOnlyMeasureSquadByProjIdAndListId(Integer projectId, Integer measureListId) {
        Example example = new Example(MeasureSquad.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("listId",measureListId);
        criteria.andEqualTo("projectId",projectId);
        criteria.andIsNull("deleteAt");
        return measureSquadMapper.selectByExample(example);
    }

    @Override
    public List<MeasureSquad> searchMeasureSquadByListIdTimestampGt(Integer projectId, Integer listId, String updateAtGt) {
        return measureSquadMapper.searchMeasureSquadByListIdTimestampGt(projectId,listId,updateAtGt);
    }

    @Override
    public void delete(Integer id) {

        Example example = new Example(MeasureSquad.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("listId",id);

        MeasureSquad measureSquad=new MeasureSquad();
        measureSquad.setListId(id);
        measureSquad.setDeleteAt(new Date());
        measureSquad.setUpdateAt(new Date());
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

    @Override
    public List<MeasureSquad> searchSquadByProjIdListId(Integer projId, Integer listId) {
        return measureSquadMapper.searchSquadByProjIdListId(projId,listId);
    }

    @Override
    public MeasureSquad createReturnSuqad(MeasureSquad measureSquad) {
        measureSquadMapper.insertSelective(measureSquad);
        return measureSquad;
    }

    @Override
    public Integer selectByCount(Example example) {
        return measureSquadMapper.selectCountByExample(example);
    }
}
