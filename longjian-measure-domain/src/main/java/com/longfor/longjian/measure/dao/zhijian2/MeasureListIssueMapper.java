package com.longfor.longjian.measure.dao.zhijian2;

import com.longfor.gaia.gfs.data.mybatis.LFMySQLMapper;
import com.longfor.longjian.measure.po.zhijian2.MeasureListIssue;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface MeasureListIssueMapper extends LFMySQLMapper<MeasureListIssue> {

    /**
     *
     * @param project_id
     * @param measure_list_id
     * @param unclosecode
     * @return
     */
    Integer getZoneCount(@Param("projectId") Integer project_id, @Param("listId") Integer measure_list_id, @Param("unclosecode") String unclosecode);

    /**
     *
     * @param project_id
     * @param measure_list_id
     * @param typ
     * @return
     */
    Integer getCountByTyp(@Param("projectId") Integer project_id, @Param("listId") Integer measure_list_id, @Param("typ") String typ);

    /**
     *
     * @param project_id
     * @param measure_list_id
     * @param status
     * @return
     */
    Integer getCountByStatus(@Param("projectId") Integer project_id, @Param("listId") Integer measure_list_id, @Param("status") String status);
}