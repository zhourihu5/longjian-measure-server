package com.longfor.longjian.measure.domain.externalService;

import com.longfor.longjian.measure.po.zhijian2.MeasureRegion;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IMeasureRegionService {
    /**
     * 项目描画区域管理新增描画区域请求测区
     * @param project_id
     * @param area_id
     * @return
     */
    List<Map<String,Object>> getProjMeasureRegionByAreaId(Integer project_id, Integer area_id);

    /**
     *
     * @param projectId
     * @param updateAtGte
     * @return
     */
    List<MeasureRegion> searchUnscopedByProjIdUpdateAtGt(String projectId, String updateAtGte);

    /**
     *
     * @param project_id
     * @param last_id
     * @param timestamp
     * @param measureApiGetPerTime
     * @param start
     * @return
     */
    List<MeasureRegion> searchUnscopedByProjIdLastIdUpdateAtGt(Integer project_id, Integer last_id, Long timestamp, Integer measureApiGetPerTime, Integer start);

    /**
     *
     * @param project_id
     * @param timestamp
     * @return
     */
    Integer getCountUnscopedByProjIdUpdateAtGt(Integer project_id, Long timestamp);

    /**
     *
     * @param projId
     * @param keySet
     * @return
     */
    List<MeasureRegion> searchByUuids(Integer projId, Set<String> keySet);
}
