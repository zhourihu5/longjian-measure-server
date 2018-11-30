package com.longfor.longjian.measure.domain.externalService;

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
}
