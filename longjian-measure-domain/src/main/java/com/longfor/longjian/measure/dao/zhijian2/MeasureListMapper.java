package com.longfor.longjian.measure.dao.zhijian2;

import com.longfor.gaia.gfs.data.mybatis.LFMySQLMapper;
import com.longfor.longjian.measure.po.zhijian2.MeasureList;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface MeasureListMapper extends LFMySQLMapper<MeasureList> {
    /**
     *
     * @param finish_status
     * @param q
     * @param project_id
     * @param categoryPathAndKey
     * @param areaPathAndId
     * @param userIds
     * @param page
     * @param page_size
     * @return
     */
    List<Map<String,Object>> getMeasureList(@Param("finishStatus") Integer finish_status, @Param("q") String q, @Param("projectId") Integer project_id, @Param("categoryPathAndKey") String categoryPathAndKey, @Param("areaPathAndId") String areaPathAndId, @Param("userIds") String[] userIds, @Param("page") Integer page, @Param("pageSize") Integer page_size);

    /**
     * total
     * @param finish_status
     * @param q
     * @param project_id
     * @param categoryPathAndKey
     * @param areaPathAndId
     * @param userIds
     * @return
     */
    Integer getTotalMeasure(@Param("finishStatus") Integer finish_status, @Param("q") String q, @Param("projectId") Integer project_id, @Param("categoryPathAndKey") String categoryPathAndKey, @Param("areaPathAndId") String areaPathAndId, @Param("userIds") String[] userIds);

    /**
     *
     * @param project_id
     * @return
     */
    List<Map<String,Object>> searchByProjectId(@Param("project_id") Integer project_id);

    /**
     *
     * @param project_id
     * @return
     */
    List<Map<String,Object>> searchListByProjId(@Param("project_id")String project_id);

    /**
     *
     * @param listId
     * @return
     */
    MeasureList getNoProjNoFoundErr(@Param("listId")String listId);

    /**
     *
     * @param projectId
     * @param userId
     * @return
     */
    List<MeasureList> searchListByProjIdUserId(@Param("projectId") String projectId,@Param("userId") Integer userId);

    MeasureList getMeasureListByProjIdAndId(@Param("projId") Integer projId, @Param("Id") Integer Id);
}