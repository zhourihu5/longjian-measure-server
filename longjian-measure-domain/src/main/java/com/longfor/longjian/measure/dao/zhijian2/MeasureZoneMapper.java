package com.longfor.longjian.measure.dao.zhijian2;

import com.longfor.gaia.gfs.data.mybatis.LFMySQLMapper;
import com.longfor.longjian.measure.po.zhijian2.MeasureZone;
import org.apache.ibatis.annotations.Param;

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
}