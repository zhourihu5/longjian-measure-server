package com.longfor.longjian.measure.domain.externalService.impl;

import com.longfor.gaia.gfs.data.mybatis.datasource.LFAssignDataSource;
import com.longfor.longjian.measure.dao.zhijian2_apisvr.UserMapper;
import com.longfor.longjian.measure.domain.externalService.IUserService;
import com.longfor.longjian.measure.po.zhijian2_apisvr.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @LFAssignDataSource("zhijian2_apisvr")
    @Override
    public List<Map<String, Object>> getUserByUserIds(List<Integer> userIds) {
        return userMapper.getUserByUserIds(userIds);
    }

    @LFAssignDataSource("zhijian2_apisvr")
    @Override
    public User getUserByUserId(Integer id) {
        return userMapper.getUserByUserId(id);
    }

    @LFAssignDataSource("zhijian2_apisvr")
    @Override
    public List<User> getUserEntitiesByUserIds(List<Integer> userIds) {
        return userMapper.getUserEntitiesByUserIds(userIds);
    }
}
