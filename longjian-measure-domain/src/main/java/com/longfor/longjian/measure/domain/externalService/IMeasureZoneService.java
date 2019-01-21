package com.longfor.longjian.measure.domain.externalService;

import com.longfor.longjian.measure.po.zhijian2.MeasureZone;
import com.longfor.longjian.measure.po.zhijian2.MeasureZoneResult;

import com.longfor.longjian.measure.po.zhijian2.MeasureZone;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IMeasureZoneService {
    /**
     * 获取测区数量
     * @param project_id
     * @param ints
     * @return
     */
    Integer searchTotalByProjectIdAndMeasureListId(Integer project_id, int[] ints);

    /**
     *
     * @param project_id
     * @param measure_list_id
     * @param subKey
     * @return
     */
    Integer getMeasureZoneCountByListIdCategoryKey(Integer project_id, Integer measure_list_id, String subKey);

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
     * @param list_id
     * @param last_id
     * @param timestamp
     * @param start
     * @param limit
     * @return
     */
    List<MeasureZone> searchZoneUnscopedByListIdLastIdUpdateAtGt2(Integer projectId, Integer list_id, Integer last_id, Long timestamp, Integer start, Integer limit);

    /**
     *
     * @param projectId
     * @param list_id
     * @param timestamp
     * @return
     */
    Integer getCountZoneUnscopedByListIdUpdateAtGt(Integer projectId, Integer list_id, Long timestamp);

    /**
     *
     *
     * @param projId
     * @param zoneUuids
     * @return
     */
    List<MeasureZone> searchZoneByUuid(Integer projId, Set<String> zoneUuids);

    MeasureZone GetByProjIdAndIdNoFoundErr(Integer projectId,Integer id);

    Integer GetMeasureListBuildingCountAndRegionCount(Integer measureListId);

    Integer GetMeasureListCategoryCountAndCheckItemCount (Integer measureListId);

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
     * @param project_id
     * @param zoneUuids
     * @return
     */
    List<MeasureZone> searchZoneByProjUuids(Integer project_id, List<String> zoneUuids);
    List<MeasureZone> selectByProjectIdAndRegionUUIdIn(Integer project_id, List<String> region_uuid_list);
    MeasureZone GetZoneByUuid (Integer projId,String uuid);

    /**
     *
     * @param projId
     * @param list_id
     * @return
     */
    List<MeasureZone> searchByListId(Integer projId, Integer list_id);
}
