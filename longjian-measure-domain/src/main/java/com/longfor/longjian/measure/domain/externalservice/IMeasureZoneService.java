package com.longfor.longjian.measure.domain.externalservice;

import com.longfor.longjian.measure.po.zhijian2.MeasureZone;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IMeasureZoneService {
    /**
     * 获取测区数量
     * @param projectId
     * @param ints
     * @return
     */
    Integer searchTotalByProjectIdAndMeasureListId(Integer projectId, int[] ints);

    /**
     *
     * @param projectId
     * @param measureListId
     * @param subKey
     * @return
     */
    Integer getMeasureZoneCountByListIdCategoryKey(Integer projectId, Integer measureListId, String subKey);

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
    List<MeasureZone> searchZoneUnscopedByListIdLastIdUpdateAtGt(Integer projectId, String listId, Integer lastId, Long timestamp, Integer limit, Integer start);

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
    List<MeasureZone> searchZoneUnscopedByListIdLastIdUpdateAtGt2(Integer projectId, Integer listId, Integer lastId, Long timestamp, Integer start, Integer limit);

    /**
     *
     * @param projectId
     * @param listId
     * @param timestamp
     * @return
     */
    Integer getCountZoneUnscopedByListIdUpdateAtGt(Integer projectId, Integer listId, Long timestamp);

    /**
     *
     *
     * @param projId
     * @param zoneUuids
     * @return
     */
    List<MeasureZone> searchZoneByUuid(Integer projId, Set<String> zoneUuids);

    /**
     *
     * @param projectId
     * @param id
     * @return
     */
    MeasureZone getByProjIdAndIdNoFoundErr(Integer projectId,Integer id);

    /**
     *
     * @param measureListId
     * @return
     */
    Integer getMeasureListBuildingCountAndRegionCount(Integer measureListId);

    /**
     *
     * @param measureListId
     * @return
     */
    Integer getMeasureListCategoryCountAndCheckItemCount (Integer measureListId);

    /**
     *
     * @param id
     */
    void delete (Integer id);

    /**
     *
     * @param map
     */
    void updateStatus(Map<String,Object>map);

    void delByUuidList(Map<String,Object>map);

    List<MeasureZone>selectByExample(Example example);

    /**
     *
     * @param projectId
     * @param zoneUuids
     * @return
     */
    List<MeasureZone> searchZoneByProjUuids(Integer projectId, List<String> zoneUuids);

    /**
     *
     * @param projectId
     * @param regionUuidList
     * @return
     */
    List<MeasureZone> selectByProjectIdAndRegionUUIdIn(Integer projectId, List<String> regionUuidList);

    /**
     *
     * @param projId
     * @param uuid
     * @return
     */
    MeasureZone getZoneByUuid (Integer projId,String uuid);

    /**
     *
     * @param projId
     * @param listId
     * @return
     */
    List<MeasureZone> searchByListId(Integer projId, Integer listId);

    /**
     *
     * @param insertZoneList
     */
    void insertMany(List<MeasureZone> insertZoneList);

    int selectCountByExample(Example example);
}
