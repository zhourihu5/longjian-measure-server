package com.longfor.longjian.measure.domain.externalservice.impl;

import com.longfor.longjian.measure.dao.zhijian2.MeasureRepairerUserMapper;
import com.longfor.longjian.measure.domain.externalservice.IMeasureRepairerUserService;
import com.longfor.longjian.measure.po.zhijian2.MeasureRepairerUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class MeasureRepairerUserServiceImpl implements IMeasureRepairerUserService {

    @Autowired
    MeasureRepairerUserMapper measureRepairerUserMapper;

    @Override
    public List<MeasureRepairerUser> searchMeasureReparierUserByListIdTimestampGt(Integer projectId, Integer list_id, String updateAtGt) {
        return measureRepairerUserMapper.searchMeasureReparierUserByListIdTimestampGt(projectId,list_id,updateAtGt);
    }

    @Override
    public List<MeasureRepairerUser> select(MeasureRepairerUser measureRepairerUser) {
        return measureRepairerUserMapper.select(measureRepairerUser);
    }

    @Override
    public void delOld(Map<String, Object> map) {
        measureRepairerUserMapper.delOld(map);
    }

    @Override
    public void insertList(List<MeasureRepairerUser> measureRepairerUsers) {
        measureRepairerUserMapper.insertList(measureRepairerUsers);
    }

    @Override
    public List<MeasureRepairerUser> selectByExample(Example example) {
        return measureRepairerUserMapper.selectByExample(example);
    }

    @Override
    public List<MeasureRepairerUser> SearchMeasureReparierUserByListId(Integer projId, Integer listId) {
        return measureRepairerUserMapper.SearchMeasureReparierUserByListId(projId,listId);
    }

    @Override
    public void create(int proj_id, Integer listId, String role_type, Integer user_id) {
        MeasureRepairerUser measureRepairerUser = new MeasureRepairerUser();
        measureRepairerUser.setProjectId(proj_id);
        measureRepairerUser.setListId(listId);
        measureRepairerUser.setRoleType(Integer.parseInt(role_type));
        measureRepairerUser.setUserId(user_id);
        measureRepairerUser.setCreateAt(new Date());
        measureRepairerUser.setUpdateAt(new Date());
        measureRepairerUserMapper.insertSelective(measureRepairerUser);
    }
}
