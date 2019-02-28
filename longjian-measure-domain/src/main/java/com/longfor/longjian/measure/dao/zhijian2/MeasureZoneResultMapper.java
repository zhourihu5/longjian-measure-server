package com.longfor.longjian.measure.dao.zhijian2;

import com.longfor.gaia.gfs.data.mybatis.LFMySQLMapper;
import com.longfor.longjian.measure.po.zhijian2.MeasureZoneResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface MeasureZoneResultMapper extends LFMySQLMapper<MeasureZoneResult> {
    /**
     * 计算出所有小组的总检查数
     * @param measure_list_id
     * @return
     */
    List<Map<String,Object>> statMeasureListZoneResultCountByListIdGroupBySquad(@Param("listId") Integer measure_list_id);

    /**
     * 计算指定zoneUuid列表下，各个分组的合格率和测点数情况
     * @param measure_list_id
     * @param categoryKey
     * @return
     */
    List<Map<String,Object>> statMearureZoneResultSquadTotalCountByListIdCategoryKey(@Param("listId") Integer measure_list_id, @Param("categoryKey") String categoryKey);

    /**
     * 计算各项的数据
     * @param measure_list_id
     * @return
     */
    List<Map<String,Object>> statMearureZoneResultSquadTotalCountByListIdGroupByCategoryKey(@Param("listId") Integer measure_list_id);

    /**
     *
     * @param measure_list_id
     * @return
     */
    List<Map<String,Object>> getPassPercentDiffCategoryKeyListByMeasureListId(@Param("listId") Integer measure_list_id);

    /**
     *
     * @param measure_list_id
     * @param category_key
     * @param CLOSEDCODE
     * @return
     */
    List<Map<String,Object>> getSquadsZoneResultPassPercentByListIdAndCategoryKey(@Param("listId") Integer measure_list_id, @Param("categoryKey") String category_key, @Param("CLOSEDCODE") String CLOSEDCODE);

    /**
     *
     * @param measure_list_id
     * @return
     */
    List<Map<String,Object>> getSquadPassPercentSmallestCategoryKeyListByMeasureListId(@Param("listId") Integer measure_list_id);

    /**
     *
     * @param project_id
     * @param measure_list_id
     * @param category_key
     * @return
     */
    List<MeasureZoneResult> getSubActiveMeasureCategoryZonesByListIdCategoryKey(@Param("projectId") Integer project_id, @Param("listId") Integer measure_list_id, @Param("categoryKey") String category_key);

    /**
     * 通过任务Id列表和检查项和区域Id获取测区的测点数和合格率
     * @param listIds
     * @param key
     * @param areaId
     * @return
     */
    Map<String, Object> getMeasureZoneResultPassPercentageByListIdsAndCategoryKeyAndAreaId(@Param("listIds") String[] listIds, @Param("key") String key, @Param("areaId") String areaId);

    /**
     *
     * @param project_id
     * @param listIds
     * @param key
     * @param areaId
     * @return
     */
    Integer countMeasureZoneByListIdsAndCategoryKeyAndAreaId(@Param("projectId") Integer project_id, @Param("listIds") String[] listIds, @Param("categoryKey") String key, @Param("areaId") String areaId);

    /**
     *
     * @param projectId
     * @param listId
     * @param lastId
     * @param timestamp
     * @param limit
     * @param start
     * @return
     */
    List<MeasureZoneResult> searchResultUnscopedByListIdLastIdUpdateAtGt(@Param("projectId") Integer projectId, @Param("listId") String listId, @Param("lastId") Integer lastId, @Param("timestamp") Integer timestamp, @Param("limit") Integer limit, @Param("start") Integer start);


    /**
     *
     * @param projectId
     * @param listId
     * @param timestamp
     * @return
     */
    Integer getCountResultUnscopedByListIdLastIdUpdateAtGt(@Param("projectId") Integer projectId, @Param("listId") Integer listId, @Param("timestamp")String timestamp);

    /**
     *
     * @param project_id
     * @param list_id
     * @param zone_uuid
     * @param squad_id
     * @return
     */
    List<MeasureZoneResult> getByProjIdListIdZoneUuidSquadId(@Param("projectId") Integer project_id, @Param("listId") Integer list_id, @Param("zoneUuid") String zone_uuid, @Param("squadId") Integer squad_id);

    /**
     *
     * @param projId
     * @param uuid
     * @return
     */
    MeasureZoneResult getByUuid(@Param("projId") Integer projId, @Param("uuid") String uuid);

    /**
     *
     * @param map
     */
    void delByUuidList(Map<String,Object>map);

    /**
     *
     * @param map
     */
    void delBySquadIdUuid(Map<String,Object>map);

    List<MeasureZoneResult> SearchZoneResultByProjIdZoneUuid(@Param("ProjectId") Integer ProjectId , @Param("uuid") String uuid);
}