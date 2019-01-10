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
    /**
     *
     * @param projectId
     * @param measureRegions
     * @return
     */
    List<MeasureRegion> createRegionsFromRegionStructList(Integer projectId, List<MeasureRegion> measureRegions) throws Exception;

    /**
     *
     * @param project_id
     * @param uuid
     * @return
     */
    MeasureRegion searchByUuid(Integer project_id, String uuid);

    /**
     * 获取measure_region现有的最大的index
     * @param project_id
     * @param area_id_list
     * @return
     */
    List<Map<String, Object>> getMaxRegionIndexGroupByAreaIdNoDeleted(Integer project_id, List area_id_list);

    MeasureRegion save(MeasureRegion model);

    MeasureRegion update(MeasureRegion mode);

    /**
     *
     * @param proj_id
     * @param region_uuid
     * @return
     */
    MeasureRegion searchByProjIdAndRegionUuid(Integer proj_id, String region_uuid);

    /**
     *
     * @param proj_id
     * @param relId
     * @return
     */
    List<MeasureRegion> searchByProjIdAndRelId(Integer proj_id, Integer relId);
}
