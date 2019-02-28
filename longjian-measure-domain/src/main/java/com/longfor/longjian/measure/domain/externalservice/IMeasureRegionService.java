package com.longfor.longjian.measure.domain.externalservice;

import com.longfor.longjian.measure.po.zhijian2.MeasureRegion;
import tk.mybatis.mapper.entity.Example;

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

    /**
     *
     * @param example
     * @return
     */
    List<MeasureRegion>selectByExample(Example example);

    /**
     *
     * @param project_id
     * @param regionUuids
     * @return
     */
    List<MeasureRegion> searchByProjUuids(Integer project_id, List<String> regionUuids);

    /**
     *
     * @param project_id
     * @param region_ids
     * @param polygon
     * @param tag_id_list
     */
    void updateByProjectIdAndIdInNoDeleted(Integer project_id, List region_ids, String polygon, String tag_id_list);

    List<MeasureRegion> selectByProjectIdAndIdNoDeleted(Integer project_id, List<Integer> region_id_list);

    /**
     * 逻辑删除
     * @param project_id
     * @param region_id_list
     */
    void delete(Integer project_id, List<Integer> region_id_list);

    MeasureRegion GetByUuid(Integer projId,String uuid);
    /**
     *
     * @param projId
     * @param timeFmt
     * @return
     */
    List<MeasureRegion> searchUnscopedByProjIdUpdateAtGt2(Integer projId, String timeFmt);

    List<MeasureRegion> searchByIdAndAreaIdAndProjectIdNoDeleted(List<String> region_id_list, List<String> copy_area_id_list, int proj_id);
}
