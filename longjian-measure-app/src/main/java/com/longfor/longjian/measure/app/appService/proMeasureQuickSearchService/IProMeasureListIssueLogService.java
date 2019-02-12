package com.longfor.longjian.measure.app.appService.proMeasureQuickSearchService;

import com.longfor.longjian.measure.app.vo.proMeasureQuickSearchVo.MeasureListIssueHistoryRepairLogVo;
import com.longfor.longjian.measure.po.zhijian2.MeasureListIssueLog;

import java.util.List;

public interface IProMeasureListIssueLogService {
    /**
     *
     * @param project_id
     * @param uuid
     * @return
     */
    List<MeasureListIssueHistoryRepairLogVo> getIssueActionLogByIssueUuid(Integer project_id, String uuid);

    /**
     *
     * @param project_id
     * @param uuid
     * @param reformnocheck
     * @return
     */
    List<MeasureListIssueLog> searchIssueLogByIssueUuidAndStatus(Integer project_id, String uuid, int reformnocheck);
}
