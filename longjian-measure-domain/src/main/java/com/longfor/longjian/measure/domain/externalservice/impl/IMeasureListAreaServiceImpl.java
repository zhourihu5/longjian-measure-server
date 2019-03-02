package com.longfor.longjian.measure.domain.externalservice.impl;

import com.longfor.longjian.measure.dao.zhijian2.MeasureListAreaMapper;
import com.longfor.longjian.measure.domain.externalservice.IMeasureListAreaService;
import com.longfor.longjian.measure.po.zhijian2.MeasureListArea;
import com.longfor.longjian.measure.util.ExampleUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by Wang on 2019/1/7.
 */
@Slf4j
@Service
public class IMeasureListAreaServiceImpl implements IMeasureListAreaService {

    @Resource
    private MeasureListAreaMapper measureListAreaMapper;
    private static final String LISTID="listId";
    @Override
    public void delete(Integer id) {

        Example example = new Example(MeasureListArea.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo(LISTID,id);

        MeasureListArea measureListArea=new MeasureListArea();
        measureListArea.setListId(id);
        measureListArea.setDeleteAt(new Date());
        measureListArea.setUpdateAt(new Date());
        measureListAreaMapper.updateByExampleSelective(measureListArea,example);
    }

    @Override
    public List<MeasureListArea> searchListAreaByListIdIn(Integer projectId, List<Integer> listIds) {
        Example example = new Example(MeasureListArea.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("projectId",projectId);
        criteria.andIn(LISTID,listIds);
        ExampleUtil.addDeleteAtJudge(example);
        return measureListAreaMapper.selectByExample(example);
    }

    @Override
    public List<MeasureListArea> searchByListId(String projectId, Integer id) {
        Example example = new Example(MeasureListArea.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("projectId",projectId);
        criteria.andEqualTo(LISTID,id);
        ExampleUtil.addDeleteAtJudge(example);
        return measureListAreaMapper.selectByExample(example);
    }

    @Override
    public void create(int projId, Integer areaId, String s, Integer listId) {
        MeasureListArea measureListArea = new MeasureListArea();
        measureListArea.setProjectId(projId);
        measureListArea.setAreaId(areaId);
        measureListArea.setAreaPathAndId(s);
        measureListArea.setListId(listId);
        measureListArea.setCreateAt(new Date());
        measureListArea.setUpdateAt(new Date());
        measureListAreaMapper.insertSelective(measureListArea);
    }
}
