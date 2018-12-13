package com.longfor.longjian.measure.dao.zhijian2;

import com.longfor.gaia.gfs.data.mybatis.LFMySQLMapper;
import com.longfor.longjian.measure.po.zhijian2.ReportResult;
import org.apache.ibatis.annotations.Param;

public interface ReportResultMapper extends LFMySQLMapper<ReportResult> {
    /**
     *
     * @param report_uuid
     * @return
     */
    ReportResult getByReportUuid(@Param("uuid") String report_uuid);
}