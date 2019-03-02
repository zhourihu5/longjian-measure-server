package com.longfor.longjian.measure.domain.externalservice;

import com.longfor.longjian.measure.po.zhijian2.MeasureListIssue;
import com.longfor.longjian.measure.po.zhijian2.MeasureListIssueLog;

import java.util.List;

public interface IMeasureListIssueLogService {
    /**
     *
     * @param projectId
     * @param listId
     * @param lastId
     * @param timestamp
     * @param start
     * @param pageSize
     * @return
     */
    List<MeasureListIssueLog> searchIssueLogListByListIdLastIdTimestampGt(Integer projectId, Integer listId, Integer lastId, Long timestamp, Integer start, Integer pageSize);

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
     * @param projectId
     * @param uuid
     * @return
     */
    List<MeasureListIssueLog> searchByIssueUuid(Integer projectId, String uuid);

    /**
     * 
     * @param projectId
     * @param uuid
     * @param reformnocheck
     * @return
     */
    List<MeasureListIssueLog> searchIssueLogByIssueUuidAndStatus(Integer projectId, String uuid, int reformnocheck);
}
