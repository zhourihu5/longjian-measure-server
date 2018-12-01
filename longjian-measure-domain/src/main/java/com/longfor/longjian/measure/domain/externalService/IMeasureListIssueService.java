package com.longfor.longjian.measure.domain.externalService;

import java.util.Map;

public interface IMeasureListIssueService {
    /**
     *
     * @param id
     * @return
     */
    Integer countByMeasureListId(String id);

    /**
     * 查询爆点情况
     * @param project_id
     * @param measure_list_id
     * @param UNCLOSECODE
     * @param REPAIRABLE
     * @param NOREPAIRABLE
     * @param ASSIGNNOREFORM
     * @param REFORMNOCHECK
     * @param CHECKYES
     * @return
     */
    Map<String,Object> getMeasureListIssueBrief(Integer project_id, Integer measure_list_id, String UNCLOSECODE, String REPAIRABLE, String NOREPAIRABLE, String NOTENOASSIGN, String ASSIGNNOREFORM, String REFORMNOCHECK, String CHECKYES);
}
