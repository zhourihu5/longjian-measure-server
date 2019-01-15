package com.longfor.longjian.measure.app.appService.proMeasureQuickSearchService;

import java.text.ParseException;

public interface IProMeasureListIssueService {
    /**
     * 项目.实测实量.爆点管理.删除
     * @param project_id
     * @param uuid
     */
    void measureListIssueDeleteByProjUuid(Integer project_id, String uuid);

    /**
     *
     * @param uuid
     * @param projectId
     * @param uid
     * @param status
     * @param content
     * @param s
     * @return
     */
    boolean updateIssueApproveStatusByUuid(String uuid, Integer projectId, Integer uid, Integer status, String content, String s) throws ParseException;

    /**
     *
     * @param uuid
     * @param project_id
     * @param uid
     * @param status
     */
    void updateIssueCloseStatusByUuid(String uuid, Integer project_id, Integer uid, Integer status) throws ParseException;
}
