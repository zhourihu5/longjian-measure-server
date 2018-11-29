package com.longfor.longjian.measure.domain.externalService;

public interface IMeasureZoneService {
    /**
     * 获取测区数量
     * @param project_id
     * @param ints
     * @return
     */
    Integer searchTotalByProjectIdAndMeasureListId(Integer project_id, int[] ints);
}
