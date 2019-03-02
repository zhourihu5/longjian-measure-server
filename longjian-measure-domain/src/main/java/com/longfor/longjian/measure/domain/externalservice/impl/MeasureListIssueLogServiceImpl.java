package com.longfor.longjian.measure.domain.externalservice.impl;

import com.longfor.longjian.measure.dao.zhijian2.MeasureListIssueLogMapper;
import com.longfor.longjian.measure.domain.externalservice.IMeasureListIssueLogService;
import com.longfor.longjian.measure.po.zhijian2.MeasureListIssue;
import com.longfor.longjian.measure.po.zhijian2.MeasureListIssueLog;
import com.longfor.longjian.measure.util.ExampleUtil;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class MeasureListIssueLogServiceImpl implements IMeasureListIssueLogService {
    @Resource
    private MeasureListIssueLogMapper measureListIssueLogMapper;
    @Override
    public List<MeasureListIssueLog> searchIssueLogListByListIdLastIdTimestampGt(Integer projectId, Integer listId, Integer lastId, Long timestamp, Integer start, Integer pageSize) {
        return measureListIssueLogMapper.searchIssueLogListByListIdLastIdTimestampGt(projectId,listId,lastId,timestamp,start,pageSize);
    }

    @Override
    public MeasureListIssue getByUuidUnscoped(String issueUuid) {
        return measureListIssueLogMapper.getByUuidUnscoped(issueUuid);
    }

    @Override
    public int insertObjects(List<MeasureListIssueLog> needInsertIssueMap) {
        needInsertIssueMap.forEach(measureListIssueLog -> {
            measureListIssueLog.setCreateAt(new Date());
            measureListIssueLog.setUpdateAt(new Date());
            measureListIssueLog.setClientCreateAt(new Date());
        });
        return measureListIssueLogMapper.insertList(needInsertIssueMap);
    }

    @Override
    public List<MeasureListIssueLog> searchByIssueUuid(Integer projectId, String uuid) {
        Example example = new Example(MeasureListIssueLog.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("projectId",projectId);
        criteria.andEqualTo("issueUuid",uuid);
        ExampleUtil.addDeleteAtJudge(example);
        return measureListIssueLogMapper.selectByExample(example);
    }

    @Override
    public List<MeasureListIssueLog> searchIssueLogByIssueUuidAndStatus(Integer projectId, String uuid, int reformnocheck) {
        Example example = new Example(MeasureListIssueLog.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("projectId",projectId);
        criteria.andEqualTo("issueUuid",uuid);
        criteria.andEqualTo("status",reformnocheck);
        ExampleUtil.addDeleteAtJudge(example);
        return measureListIssueLogMapper.selectByExample(example);
    }
}
