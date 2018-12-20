package com.longfor.longjian.measure.domain.externalService.impl;

import com.longfor.longjian.measure.dao.zhijian2.MeasureListIssueLogMapper;
import com.longfor.longjian.measure.domain.externalService.IMeasureListIssueLogService;
import com.longfor.longjian.measure.po.zhijian2.MeasureListIssue;
import com.longfor.longjian.measure.po.zhijian2.MeasureListIssueLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeasureListIssueLogServiceImpl implements IMeasureListIssueLogService {
    @Autowired
    private MeasureListIssueLogMapper measureListIssueLogMapper;
    @Override
    public List<MeasureListIssueLog> searchIssueLogListByListIdLastIdTimestampGt(Integer projectId, Integer list_id, Integer last_id, Long timestamp, Integer start, Integer pageSize) {
        return measureListIssueLogMapper.searchIssueLogListByListIdLastIdTimestampGt(projectId,list_id,last_id,timestamp,start,pageSize);
    }

    @Override
    public MeasureListIssue getByUuidUnscoped(String issueUuid) {
        return measureListIssueLogMapper.getByUuidUnscoped(issueUuid);
    }

    @Override
    public int insertObjects(List<MeasureListIssueLog> needInsertIssueMap) {
        return measureListIssueLogMapper.insertList(needInsertIssueMap);
    }
}
