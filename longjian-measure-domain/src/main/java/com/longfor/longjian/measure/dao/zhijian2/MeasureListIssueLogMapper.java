package com.longfor.longjian.measure.dao.zhijian2;

import com.longfor.gaia.gfs.data.mybatis.LFMySQLMapper;
import com.longfor.longjian.measure.po.zhijian2.MeasureListIssueLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

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
    List<Map<String,Object>> searchIssueLogListByListIdLastIdTimestampGt(@Param("projectId") Integer projectId, @Param("list_id")Integer list_id, @Param("last_id")Integer last_id, @Param("timestamp")Long timestamp, @Param("start")Integer start, @Param("pageSize")Integer pageSize);
}