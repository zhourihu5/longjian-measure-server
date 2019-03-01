package com.longfor.longjian.measure.app.appservice.promeasurequicksearchservice;

import com.longfor.longjian.measure.app.vo.promeasurequicksearchvo.MeasureListIssueHistoryRepairLogVo;
import com.longfor.longjian.measure.po.zhijian2.MeasureListIssueLog;

import java.util.List;

public interface IProMeasureListIssueLogService {
    /**
     *
     * @param projectId
     * @param uuid
     * @return
     */
    List<MeasureListIssueHistoryRepairLogVo> getIssueActionLogByIssueUuid(Integer projectId, String uuid);

    /**
     *
     * @param projectId
     * @param uuid
     * @param reformnocheck
     * @return
     */
    List<MeasureListIssueLog> searchIssueLogByIssueUuidAndStatus(Integer projectId, String uuid, int reformnocheck);
}
