package com.longfor.longjian.measure.app.appservice.appservice;

import com.longfor.longjian.measure.util.CommonException;

import javax.servlet.http.HttpServletRequest;

public interface IKeyProcedureTaskAppService {
    /**
     *
     * @param reportuuid
     * @param uid
     * @param request
     */
    void startReport(String reportuuid, Integer uid, HttpServletRequest request) throws CommonException;

    /**
     *
     * @param
     * @param reportUuidStatus
     */
    void updateReportStatus(String reportuuid, String reportUuidStatus);
}
