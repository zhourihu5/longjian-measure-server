package com.longfor.longjian.measure.dao.zhijian2;

import com.longfor.gaia.gfs.data.mybatis.LFMySQLMapper;
import com.longfor.longjian.measure.po.zhijian2.MeasureList;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface MeasureListMapper extends LFMySQLMapper<MeasureList> {
    /**
     *
     * @param map
     * @return
     */
    List<Map<String,Object>> getMeasureList(Map<String,Object> map);

    /**
     * total
     * @param finishStatus
     * @param q
     * @param projectId
     * @param categoryPathAndKey
     * @param areaPathAndId
     * @param userIds
     * @return
     */
    Integer getTotalMeasure(@Param("finishStatus") Integer finishStatus, @Param("q") String q, @Param("projectId") Integer projectId, @Param("categoryPathAndKey") String categoryPathAndKey, @Param("areaPathAndId") String areaPathAndId, @Param("userIds") String[] userIds);

    /**
     *
     * @param projectId
     * @return
     */
    List<Map<String,Object>> searchByProjectId(@Param("project_id") Integer projectId);

    /**
     *
     * @param projectId
     * @return
     */
    List<Map<String,Object>> searchListByProjId(@Param("project_id")String projectId);

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

    /**
     *
     * @param map
     */
    void updateFinishStatus(Map<String,Object>map);

    /**
     *
     * @param map
     */
    void updateCloseStatus(Map<String,Object>map);

}