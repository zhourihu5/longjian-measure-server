package com.longfor.longjian.measure.domain.externalService;

import com.longfor.longjian.measure.po.zhijian2.MeasureSquad;

import java.util.List;
import java.util.Map;

public interface IMeasureSquadService {
    /**
     * 查询测量小组
     * @param project_id
     * @param measure_list_id
     * @return
     */
    List<MeasureSquad> searchOnlyMeasureSquadByProjIdAndListId(Integer project_id, Integer measure_list_id);

    /**
     *
     * @param projectId
     * @param list_id
     * @param updateAtGt
     * @return
     */
    List<MeasureSquad> searchMeasureSquadByListIdTimestampGt(Integer projectId, Integer list_id, String updateAtGt);
}
