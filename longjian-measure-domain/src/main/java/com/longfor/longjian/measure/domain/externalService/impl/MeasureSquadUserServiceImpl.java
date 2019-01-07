package com.longfor.longjian.measure.domain.externalService.impl;

import com.longfor.longjian.measure.dao.zhijian2.MeasureSquadUserMapper;
import com.longfor.longjian.measure.domain.externalService.IMeasureSquadUserService;
import com.longfor.longjian.measure.po.zhijian2.MeasureSquadUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MeasureSquadUserServiceImpl implements IMeasureSquadUserService{

    @Autowired
    MeasureSquadUserMapper measureSquadUserMapper;

    @Override
    public List<MeasureSquadUser> searchMeasureSquadUserByListIdTimestampGt(Integer projectId, Integer list_id, String updateAtGt) {
        return measureSquadUserMapper.searchMeasureSquadUserByListIdTimestampGt(projectId, list_id, updateAtGt);
    }

    @Override
    public void delete(Integer id) {
        measureSquadUserMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void create(MeasureSquadUser measureSquadUser) {
        measureSquadUserMapper.insert(measureSquadUser);
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
        measureSquadUserMapper.updateByPrimaryKey(measureSquadUser);
    }
}
