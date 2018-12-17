package com.longfor.longjian.measure.dao.zhijian2;

import com.longfor.gaia.gfs.data.mybatis.LFMySQLMapper;
import com.longfor.longjian.measure.po.zhijian2.MeasureZone;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface MeasureZoneMapper extends LFMySQLMapper<MeasureZone> {
    /**
     * 获取测区数量
     * @param project_id
     * @param ints
     * @return
     */
    Integer searchTotalByProjectIdAndMeasureListId(@Param("projectId") Integer project_id, @Param("measureListIds") int[] ints);

    /**
     *
     * @param project_id
     * @param measure_list_id
     * @param subKey
     * @return
     */
    Integer getMeasureZoneCountByListIdCategoryKey(@Param("projectId") Integer project_id, @Param("listId") Integer measure_list_id, @Param("subKey") String subKey);

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
     * @param list_id
     * @param last_id
     * @param timestamp
     * @param start
     * @param limit
     * @return
     */
    List<MeasureZone> searchZoneUnscopedByListIdLastIdUpdateAtGt2(@Param("projectId") Integer projectId, @Param("list_id")Integer list_id, @Param("last_id")Integer last_id, @Param("timestamp") Long timestamp, @Param("start") Integer start, @Param("limit") Integer limit);

    /**
     *
     * @param projectId
     * @param list_id
     * @param timestamp
     * @return
     */
    Integer getCountZoneUnscopedByListIdUpdateAtGt(@Param("projectId")Integer projectId, @Param("list_id")Integer list_id, @Param("timestamp") Long timestamp);
}