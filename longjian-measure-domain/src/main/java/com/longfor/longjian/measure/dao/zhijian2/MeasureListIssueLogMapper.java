package com.longfor.longjian.measure.dao.zhijian2;

import com.longfor.gaia.gfs.data.mybatis.LFMySQLMapper;
import com.longfor.longjian.measure.po.zhijian2.MeasureListIssue;
import com.longfor.longjian.measure.po.zhijian2.MeasureListIssueLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MeasureListIssueLogMapper extends LFMySQLMapper<MeasureListIssueLog> {
    /**
     *
     * @param projectId
     * @param list_id
     * @param last_id
     * @param timestamp
     * @param start
     * @param pageSize
     * @return
     */
    List<MeasureListIssueLog> searchIssueLogListByListIdLastIdTimestampGt(@Param("projectId") Integer projectId, @Param("list_id")Integer list_id, @Param("last_id")Integer last_id, @Param("timestamp")Long timestamp, @Param("start")Integer start, @Param("pageSize")Integer pageSize);

    /**
     *
     * @param issueUuid
     * @return
     */
    MeasureListIssue getByUuidUnscoped(@Param("uuid") String issueUuid);
}