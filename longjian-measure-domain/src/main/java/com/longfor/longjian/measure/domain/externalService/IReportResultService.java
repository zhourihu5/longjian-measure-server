package com.longfor.longjian.measure.domain.externalService;

import com.longfor.longjian.measure.po.zhijian2.ReportResult;

public interface IReportResultService {
    /**
     *
     * @param report_uuid
     * @return
     */
    ReportResult getByReportUuid(String report_uuid);

    /**
     *
     * @param item
     */
    void updateByPrimaryKey(ReportResult item);

    /**
     *
     * @param reportResult
     */
    void insertSelective(ReportResult reportResult);
}
