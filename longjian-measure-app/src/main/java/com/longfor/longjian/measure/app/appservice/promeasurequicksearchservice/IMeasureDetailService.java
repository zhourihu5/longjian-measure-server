package com.longfor.longjian.measure.app.appservice.promeasurequicksearchservice;

public interface IMeasureDetailService {
    /**
     *
     * @param userId
     * @param projId
     * @param list_id
     */
    void exportExcelAsync(Integer userId, Integer projId, Integer list_id);
}
