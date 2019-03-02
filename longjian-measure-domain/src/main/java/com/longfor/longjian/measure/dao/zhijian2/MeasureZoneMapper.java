package com.longfor.longjian.measure.dao.zhijian2;

import com.longfor.gaia.gfs.data.mybatis.LFMySQLMapper;
import com.longfor.longjian.measure.po.zhijian2.MeasureZone;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface MeasureZoneMapper extends LFMySQLMapper<MeasureZone> {
    /**
     * 获取测区数量
     * @param projId
     * @param ints
     * @return
     */
    Integer searchTotalByProjectIdAndMeasureListId(@Param("projectId") Integer projId, @Param("measureListIds") int[] ints);

    /**
     *
     * @param projId
     * @param measureListId
     * @param subKey
     * @return
     */
    Integer getMeasureZoneCountByListIdCategoryKey(@Param("projectId") Integer projId, @Param("listId") Integer measureListId, @Param("subKey") String subKey);

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
    List<MeasureZone> searchZoneUnscopedByListIdLastIdUpdateAtGt(@Param("projectId")Integer projectId,@Param("listId") String listId, @Param("lastId")Integer lastId, @Param("timestamp")Long timestamp, @Param("limit")Integer limit, @Param("start")Integer start);

    /**
     *
     * @param projectId
     * @param listId
     * @param lastId
     * @param timestamp
     * @param start
     * @param limit
     * @return
     */
    List<MeasureZone> searchZoneUnscopedByListIdLastIdUpdateAtGt2(@Param("projectId") Integer projectId, @Param("list_id")Integer listId, @Param("last_id")Integer lastId, @Param("timestamp") Long timestamp, @Param("start") Integer start, @Param("limit") Integer limit);

    /**
     *
     * @param projectId
     * @param listId
     * @param timestamp
     * @return
     */
    Integer getCountZoneUnscopedByListIdUpdateAtGt(@Param("projectId")Integer projectId, @Param("list_id")Integer listId, @Param("timestamp") Long timestamp);

    /**
     *
     *
     * @param projId
     * @param zoneUuids
     * @return
     */
    List<MeasureZone> searchZoneByUuid(@Param("projId") Integer projId, @Param("zoneUuids") Set<String> zoneUuids);

    /**
     *
     * @param projId
     * @param listId
     * @param uuid
     * @param category_key
     * @return
     */
    List<MeasureZone> searchByCondition(@Param("project_id") Integer projId, @Param("list_id") Integer listId, @Param("uuid") String uuid, @Param("category_key") String category_key);

    /**
     *
     * @param projId
     * @param uuid
     * @return
     */
    MeasureZone getZoneByUuid(@Param("project_id")Integer projId, @Param("uuid")String uuid);
    /**
     * @Description:
     * @param id
     * @param projectId
     * @return com.longfor.longjian.measure.po.zhijian2.MeasureZone
     **/
    MeasureZone GetByCondition(@Param("projectId") Integer projectId , @Param("id") Integer id);

    /**
     * @Description:
     * @param measureListId
     * @return java.lang.Integer
     * @author DDC
     * @date 2019/1/9 14:06
     **/
    Integer GetMeasureListCategoryCount(@Param("measureListId") Integer measureListId);

    /**
     * @Description:
     * @param measureListId
     * @return java.lang.Integer
     * @author DDC
     * @date 2019/1/9 14:06
     **/
    Integer GetMeasureListBuildingCount(@Param("measureListId") Integer measureListId);

    /**
     *
     * @param map
     */
    void updateStatus(Map<String,Object>map);

    /**
     *
     * @param map
     */
    void delByUuidList(Map<String,Object>map);
}