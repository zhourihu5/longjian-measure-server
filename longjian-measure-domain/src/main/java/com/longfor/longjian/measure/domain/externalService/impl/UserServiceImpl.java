package com.longfor.longjian.measure.domain.externalService.impl;

import com.longfor.gaia.gfs.data.mybatis.datasource.LFAssignDataSource;
import com.longfor.longjian.measure.dao.zhijian2_apisvr.UserMapper;
import com.longfor.longjian.measure.domain.externalService.IUserService;
import com.longfor.longjian.measure.po.zhijian2_apisvr.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements IUserService {

    @Resource
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

    @Override
    @LFAssignDataSource("zhijian2_apisvr")
    public Map<Integer, User> getUsersByIds(List<Integer> collect) {
        List<User> items = searchUserByIdsUnscoped(collect);
        Map<Integer, User> users = new HashMap<>();
        items.forEach(item -> {
            users.put(item.getUserId(),item);
        });
        return users;
    }

    @LFAssignDataSource("zhijian2_apisvr")
    private List<User> searchUserByIdsUnscoped(List<Integer> collect) {
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andIn("userId",collect);
        return userMapper.selectByExample(example);
    }

    @LFAssignDataSource("zhijian2_apisvr")
    @Override
    public List<User> getUserEntitiesByUserIds(List<Integer> userIds) {
        return userMapper.getUserEntitiesByUserIds(userIds);
    }
}
