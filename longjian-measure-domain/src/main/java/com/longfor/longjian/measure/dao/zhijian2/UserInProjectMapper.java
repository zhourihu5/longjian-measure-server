package com.longfor.longjian.measure.dao.zhijian2;

import com.longfor.gaia.gfs.data.mybatis.LFMySQLMapper;
import com.longfor.longjian.measure.po.zhijian2.UserInProject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserInProjectMapper extends LFMySQLMapper<UserInProject> {
    /**
     *
     * @param projectIds
     * @return
     */
    List<UserInProject> getUserIdByProjectIds(@Param("projectIds") int[] projectIds);
}