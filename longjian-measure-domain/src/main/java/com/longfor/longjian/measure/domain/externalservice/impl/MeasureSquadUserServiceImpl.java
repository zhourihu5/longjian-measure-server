package com.longfor.longjian.measure.domain.externalservice.impl;

import com.longfor.longjian.measure.dao.zhijian2.MeasureSquadUserMapper;
import com.longfor.longjian.measure.domain.externalservice.IMeasureSquadUserService;
import com.longfor.longjian.measure.po.zhijian2.MeasureSquadUser;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class MeasureSquadUserServiceImpl implements IMeasureSquadUserService{

    @Resource
    MeasureSquadUserMapper measureSquadUserMapper;

    @Override
    public List<MeasureSquadUser> searchMeasureSquadUserByListIdTimestampGt(Integer projectId, Integer listId, String updateAtGt) {
        return measureSquadUserMapper.searchMeasureSquadUserByListIdTimestampGt(projectId, listId, updateAtGt);
    }

    @Override
    public void delete(Integer id) {
        Example example = new Example(MeasureSquadUser.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("listId",id);

        MeasureSquadUser measureSquadUser=new MeasureSquadUser();
        measureSquadUser.setListId(id);
        measureSquadUser.setUpdateAt(new Date());
        measureSquadUser.setDeleteAt(new Date());
        measureSquadUserMapper.updateByExampleSelective(measureSquadUser,example);
    }

    @Override
    public void create(MeasureSquadUser measureSquadUser) {
        measureSquadUserMapper.insertSelective(measureSquadUser);
    }

    @Override
    public List<MeasureSquadUser> select(MeasureSquadUser measureSquadUser) {
        return measureSquadUserMapper.select(measureSquadUser);
    }

    @Override
    public void deleteOld(Map<String, Object> map) {
        measureSquadUserMapper.deleteOld(map);
    }

    @Override
    public void insertList(List<MeasureSquadUser> measureSquadUserList) {
        measureSquadUserMapper.insertList(measureSquadUserList);
    }

    @Override
    public void deleteMeasureSquadUser(MeasureSquadUser measureSquadUser) {

        Example example = new Example(MeasureSquadUser.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("listId",measureSquadUser.getListId());
        criteria.andEqualTo("squadId",measureSquadUser.getSquadId());
        criteria.andEqualTo("projectId",measureSquadUser.getProjectId());


        measureSquadUserMapper.updateByExampleSelective(measureSquadUser,example);
    }

    @Override
    public List<MeasureSquadUser> selectByExample(Example example) {
        return measureSquadUserMapper.selectByExample(example);
    }

    @Override
    public List<MeasureSquadUser> searchBySquadId(Integer projId, Integer squadId) {
        return measureSquadUserMapper.searchBySquadId(projId,squadId);
    }
}
