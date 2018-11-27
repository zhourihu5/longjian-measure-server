package com.longfor.longjian.measure.domain.externalService.impl;

import com.longfor.longjian.measure.dao.zhijian2.UserInProjectMapper;
import com.longfor.longjian.measure.domain.externalService.IUserInProjectService;
import com.longfor.longjian.measure.po.zhijian2.UserInProject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserInProjectServiceImpl implements IUserInProjectService {
    @Autowired
    UserInProjectMapper userInProjectMapper;
    @Override
    public List<Integer> getUserIdByProjectIds(int[] projectIds) {
        List<Integer> userIds = new ArrayList<>();
//        Example example = new Example(UserInProject.class);
//        Example.Criteria criteria = example.createCriteria();
//        criteria.andIn("projectId",Arrays.asList(projectIds));
//        criteria.andIsNull("deleteAt");
//        List<UserInProject> userInProjectList = userInProjectMapper.selectByExample(example);
        List<UserInProject> userInProjectList = userInProjectMapper.getUserIdByProjectIds(projectIds);
        userInProjectList.forEach(userInProject -> {
            userIds.add(userInProject.getUserId());
        });
        return userIds;
    }
}
