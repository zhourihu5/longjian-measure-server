package com.longfor.longjian.measure.domain.externalservice.impl;

import com.longfor.longjian.measure.dao.zhijian2.UserInProjectMapper;
import com.longfor.longjian.measure.domain.externalservice.IUserInProjectService;
import com.longfor.longjian.measure.po.zhijian2.UserInProject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserInProjectServiceImpl implements IUserInProjectService {
    @Resource
    UserInProjectMapper userInProjectMapper;
    @Override
    public List<Integer> getUserIdByProjectIds(int[] projectIds) {
        List<Integer> userIds = new ArrayList<>();
        List<UserInProject> userInProjectList = userInProjectMapper.getUserIdByProjectIds(projectIds);
        userInProjectList.forEach(userInProject -> userIds.add(userInProject.getUserId()));
        return userIds;
    }
}
