package com.longfor.longjian.measure.domain.externalservice;

import com.longfor.longjian.measure.po.zhijian2.ReportResult;

public interface IReportResultService {
    /**
     *
     * @param reportUuid
     * @return
     */
    ReportResult getByReportUuid(String reportUuid);

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
