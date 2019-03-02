package com.longfor.longjian.measure.domain.externalservice.impl;

import com.longfor.gaia.gfs.data.mybatis.datasource.LFAssignDataSource;
import com.longfor.longjian.measure.dao.zhijian2.MeasureListMapper;
import com.longfor.longjian.measure.dao.zhijian2.ProjectMapper;
import com.longfor.longjian.measure.domain.externalservice.IProjectService;
import com.longfor.longjian.measure.po.zhijian2.MeasureList;
import com.longfor.longjian.measure.po.zhijian2.Project;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;

/**
 * Jiazm 2019/01/18 11:42
 */
@Service
@Slf4j
public class ProjectServiceImpl implements IProjectService {
    @Resource
    private ProjectMapper projectMapper;
    @Resource
    private MeasureListMapper measureListMapper;

    @Override
    @LFAssignDataSource("zhijian2")
    public Project getByIdNoFoundErr(Integer projId) {
        Example example = new Example(Project.class);
        example.createCriteria().andEqualTo("id", projId);
        return projectMapper.selectOneByExample(example);
    }

    @Override
    @LFAssignDataSource("zhijian2")
    public MeasureList getByProjIdAndIdNoFoundErr(Integer projId, Integer listId) {
        Example example = new Example(MeasureList.class);
        example.createCriteria().andEqualTo("projectId", projId).andEqualTo("id", listId);
        return measureListMapper.selectOneByExample(example);
    }
}
