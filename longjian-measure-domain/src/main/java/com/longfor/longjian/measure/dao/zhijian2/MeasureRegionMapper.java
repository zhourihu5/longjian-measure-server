package com.longfor.longjian.measure.dao.zhijian2;

import com.longfor.gaia.gfs.data.mybatis.LFMySQLMapper;
import com.longfor.longjian.measure.po.zhijian2.MeasureRegion;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface MeasureRegionMapper extends LFMySQLMapper<MeasureRegion> {
    /**
     * 项目描画区域管理新增描画区域请求测区
     * @param project_id
     * @param area_id
     * @return
     */
    List<Map<String,Object>> getProjMeasureRegionByAreaId(@Param("projId") Integer project_id, @Param("areaId") Integer area_id);

    /**
     *
     * @param projectId
     * @param updateAtGte
     * @return
     */
    List<MeasureRegion> searchUnscopedByProjIdUpdateAtGt(@Param("projectId") String projectId, @Param("updateAtGte") String updateAtGte);

    /**
     *
     * @param project_id
     * @param last_id
     * @param timestamp
     * @param measureApiGetPerTime
     * @param start
     * @return
     */
    List<MeasureRegion> searchUnscopedByProjIdLastIdUpdateAtGt(@Param("projectId") Integer project_id, @Param("lastId") Integer last_id, @Param("timestamp") Long timestamp, @Param("measureApiGetPerTime") Integer measureApiGetPerTime, @Param("start") Integer start);

    /**
     *
     * @param project_id
     * @param timestamp
     * @return
     */
    Integer getCountUnscopedByProjIdUpdateAtGt(@Param("projectId") Integer project_id, @Param("timestamp") Long timestamp);

    /**
     *
     * @param projId
     * @param uuids
     * @return
     */
    List<MeasureRegion> searchByUuids(@Param("projId") Integer projId, @Param("uuids") Set<String> uuids);

    /**
     *
     * @param projectId
     * @param areaIds
     * @return
     */
    Map<String,Integer> searchMeasureRegionAreaMaxIndexByAreaIdList(@Param("projectId")Integer projectId, @Param("areaIds")Set<Integer> areaIds);

    /**
     *
     * @param measureRegionLists
     */
    void insertMeasureRegion(List<MeasureRegion> measureRegionLists);
    /**
     *
     * @param project_id
     * @param uuid
     * @return
     */
    MeasureRegion getByConditionNoFoundErr(@Param("project_id") Integer project_id, @Param("uuid") String uuid);

    /**
     * 获取measure_region现有的最大的index
     * @param project_id
     * @param area_id_list
     * @return
     */
    List<Map<String, Object>> getMaxRegionIndexGroupByAreaIdNoDeleted(@Param("projId") Integer project_id, @Param("areaIdList") List area_id_list);

//    /**
//     *
//     * @param model
//     */
//    void save(@Param("model") MeasureRegion model);

    MeasureRegion GetByUuid(@Param("projId") Integer projId, @Param("uuid") String uuid);
}