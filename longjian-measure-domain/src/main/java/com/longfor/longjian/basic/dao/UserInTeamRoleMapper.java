package com.longfor.longjian.basic.dao;

import com.longfor.longjian.basic.po.UserInTeamRole;

public interface UserInTeamRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserInTeamRole record);

    int insertSelective(UserInTeamRole record);

    UserInTeamRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserInTeamRole record);

    int updateByPrimaryKey(UserInTeamRole record);
}