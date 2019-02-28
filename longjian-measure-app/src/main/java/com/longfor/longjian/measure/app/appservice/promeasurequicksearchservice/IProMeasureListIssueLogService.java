package com.longfor.longjian.measure.app.appservice.promeasurequicksearchservice;

import com.longfor.longjian.measure.app.vo.promeasurequicksearchvo.MeasureListIssueHistoryRepairLogVo;
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
