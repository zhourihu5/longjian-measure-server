package com.longfor.longjian.measure.domain.externalservice;

import com.longfor.longjian.measure.po.zhijian2.MeasureRegionRel;
import com.longfor.longjian.measure.vo.MeasureRegionRelVo;

import java.util.List;
import java.util.Map;

public interface IMeasureRegionRelService {
    /**
     * 查询rel Info 通过id
     * @param rel_id
     * @return
     */
    Map<String,Object> selectByRelId(String rel_id);

    /**
     *
     * @param projectId
     * @param updateAtGte
     * @return
     */
    List<MeasureRegionRel> searchRelUnscopedByProjIdUpdateAtGt(String projectId, String updateAtGte);

    /**
     *
     * @param project_id
     * @param last_id
     * @param timestamp
     * @param measureApiGetPerTime
     * @param start
     * @return
     */
    List<MeasureRegionRel> searchRelUnscopedByProjIdLastIdUpdateAtGt(Integer project_id, Integer last_id, Long timestamp, Integer measureApiGetPerTime, Integer start);

    /**
     *
     * @param model
     */
    MeasureRegionRel save(MeasureRegionRel model);

    MeasureRegionRelVo selectById(Integer id);

    List<MeasureRegionRel> selectByProjectIdAndIdNoDeleted(Integer project_id, List<Integer> rel_id_list);

    MeasureRegionRel update(MeasureRegionRel rel_model);
}