package com.longfor.longjian.basic.dao;

import com.longfor.longjian.basic.po.UserInProjectRole;

public interface UserInProjectRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserInProjectRole record);

    int insertSelective(UserInProjectRole record);

    UserInProjectRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserInProjectRole record);

    int updateByPrimaryKey(UserInProjectRole record);
}