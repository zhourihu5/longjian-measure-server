package com.longfor.longjian.measure.domain.externalService;

import com.longfor.longjian.measure.po.zhijian2.MeasureListIssue;
import com.longfor.longjian.measure.po.zhijian2.MeasureListIssueLog;

import java.util.List;

public interface IMeasureListIssueLogService {
    /**
     *
     * @param projectId
     * @param list_id
     * @param last_id
     * @param timestamp
     * @param start
     * @param pageSize
     * @return
     */
    List<MeasureListIssueLog> searchIssueLogListByListIdLastIdTimestampGt(Integer projectId, Integer list_id, Integer last_id, Long timestamp, Integer start, Integer pageSize);

    /**
     *
     * @param issueUuid
     * @return
     */
    MeasureListIssue getByUuidUnscoped(String issueUuid);

    /**
     *
     * @param needInsertIssueMap
     * @return
     */
    int insertObjects(List<MeasureListIssueLog> needInsertIssueMap);

    /**
     *
     * @param project_id
     * @param uuid
     * @return
     */
    List<MeasureListIssueLog> searchByIssueUuid(Integer project_id, String uuid);
}
