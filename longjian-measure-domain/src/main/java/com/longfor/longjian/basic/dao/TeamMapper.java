package com.longfor.longjian.basic.dao;

import com.longfor.longjian.basic.po.Team;
import com.longfor.longjian.basic.po.TeamWithBLOBs;

import java.util.List;

public interface TeamMapper {
    int deleteByPrimaryKey(Integer teamId);

    int insert(TeamWithBLOBs record);

    int insertSelective(TeamWithBLOBs record);

    TeamWithBLOBs selectByPrimaryKey(Integer teamId);

    int updateByPrimaryKeySelective(TeamWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(TeamWithBLOBs record);

    int updateByPrimaryKey(Team record);

    /**
     * 根据childPath查询到子Team，即公司
     *
     * @param childPath
     * @return
     */
    List<Team> selectSubTeamBasics(String childPath);
}