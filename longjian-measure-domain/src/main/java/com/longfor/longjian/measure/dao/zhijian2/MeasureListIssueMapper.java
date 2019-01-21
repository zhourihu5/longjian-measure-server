package com.longfor.longjian.measure.dao.zhijian2;

import com.longfor.gaia.gfs.data.mybatis.LFMySQLMapper;
import com.longfor.longjian.measure.po.zhijian2.MeasureListIssue;
import com.longfor.longjian.measure.po.zhijian2.MeasureSquadUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
import java.util.Set;

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
    Integer getCountByTyp(@Param("projectId") Integer project_id, @Param("listId") Integer measure_list_id, @Param("typ") String typ,@Param("closeStatus") String closeStatus);

    /**
     *
     * @param project_id
     * @param measure_list_id
     * @param status
     * @return
     */
    Integer getCountByStatus(@Param("projectId") Integer project_id, @Param("listId") Integer measure_list_id, @Param("status") String status,@Param("closeStatus") String closeStatus);

    /**
     * newCountList
     * @param project_id
     * @param measure_list_id
     * @param startTime
     * @param endTime
     * @param unclosecode
     * @return
     */
    List<Map<String,Object>> searchMeasureListIssueTrendNewCountList(@Param("projectId") Integer project_id, @Param("listId") Integer measure_list_id, @Param("startTime") String startTime, @Param("endTime") String endTime, @Param("unclosecode") String unclosecode);

    /**
     * reformList
     * @param project_id
     * @param measure_list_id
     * @param startTime
     * @param endTime
     * @param unclosecode
     * @return
     */
    List<Map<String,Object>> searchMeasureListIssueTrendReformList(@Param("projectId") Integer project_id, @Param("listId") Integer measure_list_id, @Param("startTime") String startTime, @Param("endTime") String endTime, @Param("unclosecode") String unclosecode);

    /**
     *
     * @param project_id
     * @param measure_list_id
     * @param unclosecode
     * @return
     */
    Integer countMeasureListIssueDistributionCategory(@Param("projectId") Integer project_id, @Param("listId") Integer measure_list_id, @Param("unclosecode") String unclosecode);

    /**
     *
     * @param listIds
     * @param cateChildPath
     * @param areaChilePath
     * @param closedcode
     * @return
     */
    List<Map<String, Object>> getMeasureListIssueStatusMapByListIdsAndCategoryKeyAndAreaId(@Param("listIds") String[] listIds, @Param("cateChildPath") String cateChildPath, @Param("areaChilePath") String areaChilePath, @Param("closedcode") String closedcode);

    /**
     *
     * @param list_id
     * @param last_id
     * @param timestamp
     * @param start
     * @param limit
     * @return
     */
    List<MeasureListIssue> searchIssueListByListIdLastIdTimestampGt(@Param("list_id") Integer list_id, @Param("last_id") Integer last_id, @Param("timestamp") Long timestamp, @Param("start") Integer start, @Param("limit") Integer limit);

    /**
     *
     * @param zoneUuids
     * @return
     */
    List<MeasureListIssue> searchMeasueListIssueInZoneUuids(@Param("zoneUuids") Set<String> zoneUuids);

    /**
     *
     * @param uuid
     * @return
     */
    MeasureSquadUser getByUuidUnscoped(@Param("uuid") String uuid);

    /**
     *
     * @param project_id
     * @param measure_list_id
     * @param unclosecode
     * @param categoryPathAndKey
     * @param status
     * @return
     */
    Integer searchMeasureListIssueByCloseStatusAndStatusAndCategoryPathAndKeyLike(@Param("projectId") Integer project_id, @Param("listId") Integer measure_list_id, @Param("unclosecode") String unclosecode, @Param("categoryPathAndKey") String categoryPathAndKey, @Param("status") Integer status);

    /**
     *
     * @param project_id
     * @param measure_list_id
     * @param unclosecode
     * @return
     */
    List<MeasureListIssue> searchMeasureListIssueDistributionCategory(@Param("projId") Integer project_id, @Param("listId") Integer measure_list_id, @Param("unclosecode") String unclosecode);

    MeasureListIssue GetIssueByProjectIdAndUuid(@Param("projectId") Integer projectId, @Param("uuid") String uuid);

    /**
     *
     * @param issueUuid
     * @return
     */
    MeasureListIssue getByUuid(@Param("issueUuid") String issueUuid);
}