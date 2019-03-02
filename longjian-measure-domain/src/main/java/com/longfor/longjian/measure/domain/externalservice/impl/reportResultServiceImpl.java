package com.longfor.longjian.measure.domain.externalservice.impl;

import com.longfor.longjian.measure.dao.zhijian2.ReportResultMapper;
import com.longfor.longjian.measure.domain.externalservice.IReportResultService;
import com.longfor.longjian.measure.po.zhijian2.ReportResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class reportResultServiceImpl implements IReportResultService {
    @Resource
    private ReportResultMapper reportResultMapper;

    @Override
    public ReportResult getByReportUuid(String reportUuid) {
        return reportResultMapper.getByReportUuid(reportUuid);
    }

    @Override
    public void updateByPrimaryKey(ReportResult item) {
        reportResultMapper.updateByPrimaryKey(item);
    }

    @Override
    public void insertSelective(ReportResult reportResult) {
        reportResultMapper.insertSelective(reportResult);
    }
}
