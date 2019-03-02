package com.longfor.longjian.measure.domain.externalservice;

import com.longfor.longjian.measure.po.zhijian2.MeasureZoneResult;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

public interface IMeasureZoneResultService {
    /**
     * 计算出所有小组的总检查数
     * @param measureListId
     * @return
     */
    List<Map<String,Object>> statMeasureListZoneResultCountByListIdGroupBySquad(Integer measureListId);

    /**
     * 计算指定zoneUuid列表下，各个分组的合格率和测点数情况
     * @param measureListId
     * @param categoryKey
     * @return
     */
    List<Map<String,Object>> statMearureZoneResultSquadTotalCountByListIdCategoryKey(Integer measureListId, String categoryKey);

    /**
     * 计算各项的数据
     */
    List<Map<String,Object>> statMearureZoneResultSquadTotalCountByListIdGroupByCategoryKey(Integer measureListId);

    /**
     *
     * @param measureListId
     * @return
     */
    List<Map<String,Object>> getPassPercentDiffCategoryKeyListByMeasureListId(Integer measureListId);

    /**
     *
     * @param measureListId
     * @param categoryKey
     * @param CLOSEDCODE
     * @return
     */
    List<Map<String,Object>> getSquadsZoneResultPassPercentByListIdAndCategoryKey(Integer measureListId, String categoryKey, String CLOSEDCODE);

    /**
     *
     * @param measureListId
     * @return
     */
    List<Map<String,Object>> getSquadPassPercentSmallestCategoryKeyListByMeasureListId(Integer measureListId);

    /**
     *
     * @param projectId
     * @param measureListId
     * @param category_key
     * @return
     */
    List<MeasureZoneResult> getSubActiveMeasureCategoryZonesByListIdCategoryKey(Integer projectId, Integer measureListId, String category_key);

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
     * @param projectId
     * @param listIds
     * @param key
     * @param areaId
     * @return
     */
    Integer countMeasureZoneByListIdsAndCategoryKeyAndAreaId(Integer projectId, String[] listIds, String key, String areaId);

    /**
     *
     * @param projectId
     * @param toString
     * @param lastId
     * @param timestamp
     * @param measureApiGetPerTime
     * @param start
     * @return
     */
    List<MeasureZoneResult> searchResultUnscopedByListIdLastIdUpdateAtGt(Integer projectId, String toString, Integer lastId, Integer timestamp, Integer measureApiGetPerTime, Integer start);

    /**
     *
     * @param projectId
     * @param listId
     * @param timestamp
     * @return
     */
    Integer getCountResultUnscopedByListIdLastIdUpdateAtGt(Integer projectId, Integer listId, String timestamp);

    /**
     *
     * @param projectId
     * @param listId
     * @param zoneUuid
     * @param squadId
     * @return
     */
    List<MeasureZoneResult> getByProjIdListIdZoneUuidSquadId(Integer projectId, Integer listId, String zoneUuid, Integer squadId);

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

    /**
     *
     * @param ProjectId
     * @param uuid
     * @return
     */
    List<MeasureZoneResult> SearchZoneResultByProjIdZoneUuid(Integer ProjectId,String uuid);

    /**
     * @param projId
     * @param listId
     * @return
     */
    List<MeasureZoneResult> searchByListId(Integer projId, Integer listId);

    /**
     *
     * @param projId
     * @param id
     * @return
     */
    List<MeasureZoneResult> searchByProjIdListId(Integer projId, Integer id);
}
