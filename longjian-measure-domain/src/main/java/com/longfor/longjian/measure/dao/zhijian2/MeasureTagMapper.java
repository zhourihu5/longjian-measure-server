package com.longfor.longjian.measure.dao.zhijian2;

import com.longfor.gaia.gfs.data.mybatis.LFMySQLMapper;
import com.longfor.longjian.measure.po.zhijian2.MeasureTag;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PatchMapping;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface MeasureTagMapper extends LFMySQLMapper<MeasureTag> {
    /**
     * 通过groupId查询tag
     * wangxs
     * 2018-11-22 17:17
     *
     * @param group_id
     * @param ownership
     * @return
     */
    List<Map<String,Object>> searchByGroupId(@Param("groupId") Integer group_id, @Param("ownership") Integer ownership);

    /**
     * 通过groupId,projId查询tag
     * @param group_id
     * @param proj_id
     * @param ownership
     * @return
     */
    List<Map<String,Object>> searchByGroupIdAndProjId(@Param("groupId") Integer group_id, @Param("projId") Integer proj_id, @Param("ownership") Integer ownership);

    /**
     *
     * @param group_id
     * @param tagId
     * @param ownership
     * @return
     */
    Integer updateByIdAndOwnership(@Param("group_id")Integer group_id, @Param("tagId")Integer tagId, @Param("ownership") Integer ownership, @Param("newDate")Date newDate);
}