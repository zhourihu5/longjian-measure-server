package com.longfor.longjian.measure.dao.zhijian2;

import com.longfor.gaia.gfs.data.mybatis.LFMySQLMapper;
import com.longfor.longjian.measure.po.zhijian2.MeasureTag;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface MeasureTagMapper extends LFMySQLMapper<MeasureTag> {
    /**
     * 通过groupId查询tag
     * wangxs
     * 2018-11-22 17:17
     *
     * @param groupId
     * @param ownership
     * @return
     */
    List<Map<String,Object>> searchByGroupId(@Param("groupId") Integer groupId, @Param("ownership") Integer ownership);

    /**
     * 通过groupId,projId查询tag
     * @param groupId
     * @param projId
     * @param ownership
     * @return
     */
    List<Map<String,Object>> searchByGroupIdAndProjId(@Param("groupId") Integer groupId, @Param("projId") Integer projId, @Param("ownership") Integer ownership);

    /**
     *
     * @param groupId
     * @param tagId
     * @param ownership
     * @return
     */
    Integer updateByIdAndOwnership(@Param("group_id")Integer groupId, @Param("tagId")Integer tagId,@Param("name")String name, @Param("ownership") Integer ownership, @Param("newDate")Date newDate);

    /**
     *
     * @param groupId
     * @param projId
     * @param tagId
     * @param ownership
     * @param newDate
     * @return
     */
    Integer updateByProjectIdAndOwnership(@Param("group_id")Integer groupId, @Param("project_id")Integer projId, @Param("tagId")Integer tagId,@Param("name")String name, @Param("ownership")Integer ownership, @Param("newDate")Date newDate);
}