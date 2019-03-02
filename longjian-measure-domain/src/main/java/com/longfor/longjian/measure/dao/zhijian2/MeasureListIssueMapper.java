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
     * @param projectId
     * @param measureListId
     * @param unclosecode
     * @return
     */
    Integer getZoneCount(@Param("projectId") Integer projectId, @Param("listId") Integer measureListId, @Param("unclosecode") String unclosecode);

    /**
     *
     * @param projectId
     * @param measureListId
     * @param typ
     * @return
     */
    Integer getCountByTyp(@Param("projectId") Integer projectId, @Param("listId") Integer measureListId, @Param("typ") String typ,@Param("closeStatus") String closeStatus);

    /**
     *
     * @param projectId
     * @param measureListId
     * @param status
     * @return
     */
    Integer getCountByStatus(@Param("projectId") Integer projectId, @Param("listId") Integer measureListId, @Param("status") String status,@Param("closeStatus") String closeStatus);

    /**
     * newCountList
     * @param projectId
     * @param measureListId
     * @param startTime
     * @param endTime
     * @param unclosecode
     * @return
     */
    List<Map<String,Object>> searchMeasureListIssueTrendNewCountList(@Param("projectId") Integer projectId, @Param("listId") Integer measureListId, @Param("startTime") String startTime, @Param("endTime") String endTime, @Param("unclosecode") String unclosecode);

    /**
     * reformList
     * @param projectId
     * @param measureListId
     * @param startTime
     * @param endTime
     * @param unclosecode
     * @return
     */
    List<Map<String,Object>> searchMeasureListIssueTrendReformList(@Param("projectId") Integer projectId, @Param("listId") Integer measureListId, @Param("startTime") String startTime, @Param("endTime") String endTime, @Param("unclosecode") String unclosecode);

    /**
     *
     * @param projectId
     * @param measureListId
     * @param unclosecode
     * @return
     */
    Integer countMeasureListIssueDistributionCategory(@Param("projectId") Integer projectId, @Param("listId") Integer measureListId, @Param("unclosecode") String unclosecode);

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
     * @param listId
     * @param lastId
     * @param timestamp
     * @param start
     * @param limit
     * @return
     */
    List<MeasureListIssue> searchIssueListByListIdLastIdTimestampGt(@Param("list_id") Integer listId, @Param("last_id") Integer lastId, @Param("timestamp") Long timestamp, @Param("start") Integer start, @Param("limit") Integer limit);

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
     * @param projectId
     * @param measureListId
     * @param unclosecode
     * @param categoryPathAndKey
     * @param status
     * @return
     */
    Integer searchMeasureListIssueByCloseStatusAndStatusAndCategoryPathAndKeyLike(@Param("projectId") Integer projectId, @Param("listId") Integer measureListId, @Param("unclosecode") String unclosecode, @Param("categoryPathAndKey") String categoryPathAndKey, @Param("status") Integer status);

    /**
     *
     * @param projectId
     * @param measureListId
     * @param unclosecode
     * @return
     */
    List<MeasureListIssue> searchMeasureListIssueDistributionCategory(@Param("projId") Integer projectId, @Param("listId") Integer measureListId, @Param("unclosecode") String unclosecode);

    MeasureListIssue GetIssueByProjectIdAndUuid(@Param("projectId") Integer projectId, @Param("uuid") String uuid);

    /**
     *
     * @param issueUuid
     * @return
     */
    MeasureListIssue getByUuid(@Param("issueUuid") String issueUuid);
}