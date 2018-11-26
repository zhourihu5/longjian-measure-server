package com.longfor.longjian.measure.domain.externalService.impl;

import com.longfor.longjian.measure.dao.zhijian2.MeasureListIssueMapper;
import com.longfor.longjian.measure.domain.externalService.IMeasureListIssueService;
import com.longfor.longjian.measure.po.zhijian2.MeasureListIssue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MeasureListIssueServiceImpl implements IMeasureListIssueService {
    @Autowired
    private MeasureListIssueMapper measureListIssueMapper;

    @Override
    public Integer countByMeasureListId(String id) {
        MeasureListIssue measureListIssue = new MeasureListIssue();
        measureListIssue.setListId(Integer.parseInt(id));
        return measureListIssueMapper.selectCount(measureListIssue);
    }
}
