package com.longfor.longjian.measure.domain.externalservice;

import com.longfor.longjian.measure.po.zhijian2.MeasureZoneResult;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

public interface IMeasureZoneResultService {
    /**
     * 计算出所有小组的总检查数
     * @param measure_list_id
     * @return
     */
    List<Map<String,Object>> statMeasureListZoneResultCountByListIdGroupBySquad(Integer measure_list_id);

    /**
     * 计算指定zoneUuid列表下，各个分组的合格率和测点数情况
     * @param measure_list_id
     * @param categoryKey
     * @return
     */
    List<Map<String,Object>> statMearureZoneResultSquadTotalCountByListIdCategoryKey(Integer measure_list_id, String categoryKey);

    /**
     * 计算各项的数据
     */
    List<Map<String,Object>> statMearureZoneResultSquadTotalCountByListIdGroupByCategoryKey(Integer measure_list_id);

    /**
     *
     * @param measure_list_id
     * @return
     */
    List<Map<String,Object>> getPassPercentDiffCategoryKeyListByMeasureListId(Integer measure_list_id);

    /**
     *
     * @param measure_list_id
     * @param category_key
     * @param CLOSEDCODE
     * @return
     */
    List<Map<String,Object>> getSquadsZoneResultPassPercentByListIdAndCategoryKey(Integer measure_list_id, String category_key, String CLOSEDCODE);

    /**
     *
     * @param measure_list_id
     * @return
     */
    List<Map<String,Object>> getSquadPassPercentSmallestCategoryKeyListByMeasureListId(Integer measure_list_id);

    /**
     *
     * @param project_id
     * @param measure_list_id
     * @param category_key
     * @return
     */
    List<MeasureZoneResult> getSubActiveMeasureCategoryZonesByListIdCategoryKey(Integer project_id, Integer measure_list_id, String category_key);

    /**
     * 通过任务Id列表和检查项和区域Id获取测区的测点数和合格率
     * @param listIds
     * @param key
     * @param areaId
     * @return
     */
    Map<String, Object> getMeasureZoneResultPassPercentageByListIdsAndCategoryKeyAndAreaId(String[] listIds, String key, String areaId);

    /**
     *
     * @param project_id
     * @param listIds
     * @param key
     * @param areaId
     * @return
     */
    Integer countMeasureZoneByListIdsAndCategoryKeyAndAreaId(Integer project_id, String[] listIds, String key, String areaId);

    /**
     *
     * @param projectId
     * @param toString
     * @param last_id
     * @param timestamp
     * @param measureApiGetPerTime
     * @param start
     * @return
     */
    List<MeasureZoneResult> searchResultUnscopedByListIdLastIdUpdateAtGt(Integer projectId, String toString, Integer last_id, Integer timestamp, Integer measureApiGetPerTime, Integer start);

    /**
     *
     * @param projectId
     * @param list_id
     * @param timestamp
     * @return
     */
    Integer getCountResultUnscopedByListIdLastIdUpdateAtGt(Integer projectId, Integer list_id, String timestamp);

    /**
     *
     * @param project_id
     * @param list_id
     * @param zone_uuid
     * @param squad_id
     * @return
     */
    List<MeasureZoneResult> getByProjIdListIdZoneUuidSquadId(Integer project_id, Integer list_id, String zone_uuid, Integer squad_id);

    /**
     *
     * @param projId
     * @param uuid
     * @return
     */
    MeasureZoneResult getByUuid(Integer projId, String uuid);

    /**
     *
     * @param zoneResult
     * @return
     */
    int insertObjectNoAffectedErr(MeasureZoneResult zoneResult);

    /**
     *
     * @param id
     */
    void delete (Integer id);

    /**
     *
     * @param example
     * @return
     */
    List<MeasureZoneResult>selectByExample(Example example);

    /**
     * \
     * @param map
     */
    void delByUuidList(Map<String,Object>map);

    /**
     *
     * @param map
     */
    void delBySquadIdUuid(Map<String,Object>map);

    List<MeasureZoneResult> SearchZoneResultByProjIdZoneUuid(Integer ProjectId,String uuid);

    /**
     * @param projId
     * @param list_id
     * @return
     */
    List<MeasureZoneResult> searchByListId(Integer projId, Integer list_id);

    /**
     *
     * @param projId
     * @param id
     * @return
     */
    List<MeasureZoneResult> searchByProjIdListId(Integer projId, Integer id);
}
