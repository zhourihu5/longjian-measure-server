package com.longfor.longjian.measure.domain.externalservice;

import com.longfor.longjian.measure.po.zhijian2.MeasureRegionRel;
import com.longfor.longjian.measure.vo.MeasureRegionRelVo;

import java.util.List;
import java.util.Map;

public interface IMeasureRegionRelService {
    /**
     * 查询rel Info 通过id
     * @param relId
     * @return
     */
    Map<String,Object> selectByRelId(String relId);

    /**
     *
     * @param projectId
     * @param updateAtGte
     * @return
     */
    List<MeasureRegionRel> searchRelUnscopedByProjIdUpdateAtGt(String projectId, String updateAtGte);

    /**
     *
     * @param projectId
     * @param lastId
     * @param timestamp
     * @param measureApiGetPerTime
     * @param start
     * @return
     */
    List<MeasureRegionRel> searchRelUnscopedByProjIdLastIdUpdateAtGt(Integer projectId, Integer lastId, Long timestamp, Integer measureApiGetPerTime, Integer start);

    /**
     *
     * @param model
     */
    MeasureRegionRel save(MeasureRegionRel model);

    /**
     *
     * @param id
     * @return
     */
    MeasureRegionRelVo selectById(Integer id);

    /**
     *
     * @param projectId
     * @param relIdList
     * @return
     */
    List<MeasureRegionRel> selectByProjectIdAndIdNoDeleted(Integer projectId, List<Integer> relIdList);

    /**
     *
     * @param relModel
     * @return
     */
    MeasureRegionRel update(MeasureRegionRel relModel);
}