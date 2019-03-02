package com.longfor.longjian.measure.domain.externalservice;

import com.longfor.longjian.common.exception.LjBaseRuntimeException;
import com.longfor.longjian.measure.po.zhijian2.MeasureRegion;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IMeasureRegionService {
    /**
     * 项目描画区域管理新增描画区域请求测区
     * @param projectId
     * @param areaId
     * @return
     */
    List<Map<String,Object>> getProjMeasureRegionByAreaId(Integer projectId, Integer areaId);

    /**
     *
     * @param projectId
     * @param updateAtGte
     * @return
     */
    List<MeasureRegion> searchUnscopedByProjIdUpdateAtGt(String projectId, String updateAtGte);

    /**
     *
     * @param projectId
     * @param lastId
     * @param timestamp
     * @param measureApiGetPerTime
     * @param start
     * @return
     */
    List<MeasureRegion> searchUnscopedByProjIdLastIdUpdateAtGt(Integer projectId, Integer lastId, Long timestamp, Integer measureApiGetPerTime, Integer start);

    /**
     *
     * @param projectId
     * @param timestamp
     * @return
     */
    Integer getCountUnscopedByProjIdUpdateAtGt(Integer projectId, Long timestamp);

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
    List<MeasureRegion> createRegionsFromRegionStructList(Integer projectId, List<MeasureRegion> measureRegions) throws LjBaseRuntimeException;

    /**
     *
     * @param projectId
     * @param uuid
     * @return
     */
    MeasureRegion searchByUuid(Integer projectId, String uuid);

    /**
     * 获取measure_region现有的最大的index
     * @param projectId
     * @param areaIdList
     * @return
     */
    List<Map<String, Object>> getMaxRegionIndexGroupByAreaIdNoDeleted(Integer projectId, List areaIdList);

    MeasureRegion save(MeasureRegion model);

    MeasureRegion update(MeasureRegion mode);

    /**
     *
     * @param projectId
     * @param regionUuid
     * @return
     */
    MeasureRegion searchByProjIdAndRegionUuid(Integer projectId, String regionUuid);

    /**
     *
     * @param projectId
     * @param relId
     * @return
     */
    List<MeasureRegion> searchByProjIdAndRelId(Integer projectId, Integer relId);

    /**
     *
     * @param example
     * @return
     */
    List<MeasureRegion>selectByExample(Example example);

    /**
     *
     * @param projectId
     * @param regionUuids
     * @return
     */
    List<MeasureRegion> searchByProjUuids(Integer projectId, List<String> regionUuids);

    /**
     *
     * @param projectId
     * @param regionIds
     * @param polygon
     * @param tagIdList
     */
    void updateByProjectIdAndIdInNoDeleted(Integer projectId, List regionIds, String polygon, String tagIdList);

    /**
     *
     * @param projectId
     * @param regionIdList
     * @return
     */
    List<MeasureRegion> selectByProjectIdAndIdNoDeleted(Integer projectId, List<Integer> regionIdList);

    /**
     * 逻辑删除
     * @param projectId
     * @param regionIdList
     */
    void delete(Integer projectId, List<Integer> regionIdList);

    /**
     *
     * @param projId
     * @param uuid
     * @return
     */
    MeasureRegion GetByUuid(Integer projId,String uuid);
    /**
     *
     * @param projId
     * @param timeFmt
     * @return
     */
    List<MeasureRegion> searchUnscopedByProjIdUpdateAtGt2(Integer projId, String timeFmt);

    /**
     *
     * @param regionIdList
     * @param copyAreaIdList
     * @param projId
     * @return
     */
    List<MeasureRegion> searchByIdAndAreaIdAndProjectIdNoDeleted(List<String> regionIdList, List<String> copyAreaIdList, int projId);
}
