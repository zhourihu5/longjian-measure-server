package com.longfor.longjian.measure.domain.externalService.impl;

import com.longfor.longjian.measure.dao.zhijian2.MeasureListIssueLogMapper;
import com.longfor.longjian.measure.domain.externalService.IMeasureListIssueLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class MeasureListIssueLogServiceImpl implements IMeasureListIssueLogService {
    @Autowired
    private MeasureListIssueLogMapper measureListIssueLogMapper;
    @Override
    public List<Map<String, Object>> searchIssueLogListByListIdLastIdTimestampGt(Integer projectId, Integer list_id, Integer last_id, Long timestamp, Integer start, Integer pageSize) {
        return measureListIssueLogMapper.searchIssueLogListByListIdLastIdTimestampGt(projectId,list_id,last_id,timestamp,start,pageSize);
    }
}
