package com.longfor.longjian.measure.app.appService.proMeasureQuickSearchService;

import javax.servlet.http.HttpServletResponse;

public interface IMeasureDetailService {
    /**
     *
     * @param userId
     * @param projId
     * @param list_id
     */
    void exportExcelAsync(Integer userId, Integer projId, Integer list_id, HttpServletResponse response);
}
