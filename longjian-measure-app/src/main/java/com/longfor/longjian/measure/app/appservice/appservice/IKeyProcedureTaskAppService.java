package com.longfor.longjian.measure.app.appservice.appservice;

import com.longfor.longjian.measure.util.CommonException;

import javax.servlet.http.HttpServletRequest;

public interface IKeyProcedureTaskAppService {
    /**
     *
     * @param report_uuid
     * @param uid
     * @param request
     */
    void startReport(String report_uuid, Integer uid, HttpServletRequest request) throws CommonException, Exception;

    /**
     *
     * @param report_uuid
     * @param reportUuidStatus
     */
    void updateReportStatus(String report_uuid, String reportUuidStatus);
}
