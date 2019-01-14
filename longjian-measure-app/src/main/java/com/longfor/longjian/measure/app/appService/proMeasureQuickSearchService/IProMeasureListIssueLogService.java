package com.longfor.longjian.measure.app.appService.proMeasureQuickSearchService;

import com.longfor.longjian.measure.app.vo.proMeasureQuickSearchVo.MeasureListIssueHistoryRepairLogVo;

import java.util.List;

public interface IProMeasureListIssueLogService {
    /**
     *
     * @param project_id
     * @param uuid
     * @return
     */
    List<MeasureListIssueHistoryRepairLogVo> getIssueActionLogByIssueUuid(Integer project_id, String uuid);
}
