package com.longfor.longjian.measure.domain.externalService;

import com.longfor.longjian.measure.po.zhijian2.MeasureList;

import java.util.List;
import java.util.Map;

public interface IMeasureListService {
    /**
     *
     * @param finish_status
     * @param q
     * @param project_id
     * @param categoryPathAndKey
     * @param areaPathAndId
     * @param userIds
     * @param page
     * @param page_size
     * @return
     */
    List<Map<String,Object>> getMeasureList(Integer finish_status, String q, Integer project_id, String categoryPathAndKey, String areaPathAndId, String[] userIds, Integer page, Integer page_size);

    /**
     * total
     * @param finish_status
     * @param q
     * @param project_id
     * @param categoryPathAndKey
     * @param areaPathAndId
     * @param userIds
     * @return
     */
    Integer getTotalMeasure(Integer finish_status, String q, Integer project_id, String categoryPathAndKey, String areaPathAndId, String[] userIds);

    /**
     *
     * @param project_id
     * @return
     */
    List<Map<String,Object>> searchByProjectId(Integer project_id);

    /**
     *
     * @param project_id
     * @param measure_list_id
     * @return
     */
    MeasureList searchByProjectIdAndMeasureListId(Integer project_id, Integer measure_list_id);

}
