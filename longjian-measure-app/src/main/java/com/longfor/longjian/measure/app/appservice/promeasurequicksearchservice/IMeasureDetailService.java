package com.longfor.longjian.measure.app.appservice.promeasurequicksearchservice;

public interface IMeasureDetailService {
    /**
     *
     * @param userId
     * @param projId
     * @param listId
     */
    void exportExcelAsync(Integer userId, Integer projId, Integer listId);
}
