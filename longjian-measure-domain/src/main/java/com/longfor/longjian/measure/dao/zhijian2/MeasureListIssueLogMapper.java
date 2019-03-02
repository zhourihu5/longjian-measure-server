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
     * @param listId
     * @param lastId
     * @param timestamp
     * @param start
     * @param pageSize
     * @return
     */
    List<MeasureListIssueLog> searchIssueLogListByListIdLastIdTimestampGt(@Param("projectId") Integer projectId, @Param("list_id")Integer listId, @Param("last_id")Integer lastId, @Param("timestamp")Long timestamp, @Param("start")Integer start, @Param("pageSize")Integer pageSize);

    /**
     *
     * @param issueUuid
     * @return
     */
    MeasureListIssue getByUuidUnscoped(@Param("uuid") String issueUuid);
}