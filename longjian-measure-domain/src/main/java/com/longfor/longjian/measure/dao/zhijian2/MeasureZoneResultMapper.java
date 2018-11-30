package com.longfor.longjian.measure.dao.zhijian2;

import com.longfor.gaia.gfs.data.mybatis.LFMySQLMapper;
import com.longfor.longjian.measure.po.zhijian2.MeasureZoneResult;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PatchMapping;

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
}