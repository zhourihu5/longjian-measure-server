package com.longfor.longjian.measure.domain.externalService;

import java.util.List;
import java.util.Map;

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
    List<Map<String,Object>> searchIssueLogListByListIdLastIdTimestampGt(Integer projectId, Integer list_id, Integer last_id, Long timestamp, Integer start, Integer pageSize);
}
