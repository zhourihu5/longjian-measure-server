package com.longfor.longjian.measure.domain.externalservice;

import com.longfor.longjian.measure.po.zhijian2.Area;
import com.longfor.longjian.measure.po.zhijian2.CategoryV3;
import com.longfor.longjian.measure.po.zhijian2.MeasureListIssue;
import com.longfor.longjian.measure.vo.GetMeasureListIssueBriefVo;
import com.longfor.longjian.measure.vo.SearchMeasueListIssueInProjVo;

import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IMeasureListIssueService {
    /**
     *
     * @param id
     * @return
     */
    Integer countByMeasureListId(String id);

    /**
     * 查询爆点情况
     */
    Map<String,Object> getMeasureListIssueBrief(GetMeasureListIssueBriefVo getMeasureListIssueBriefVo);


    /**
     * go项目实测快速查询爆点情况时间节点数据
     * @param projectId
     * @param measureListId
     * @param startTime
     * @param endTime
     * @param unCloseCode
     * @return
     */
    List<Map<String, Object>> searchMeasureListIssueTrend(Integer projectId, Integer measureListId, String startTime, String endTime, String unCloseCode) throws ParseException;

    /**
     *
     * @param projectId
     * @param measureListId
     * @param unCloseCode
     * @return
     */
    Integer countMeasureListIssueDistributionCategory(Integer projectId, Integer measureListId, String unCloseCode);

    /**
     *
     * @param projectId
     * @param measureListId
     * @param unclosecode
     * @return
     */
    List<MeasureListIssue> searchMeasureListIssueDistributionCategory(Integer projectId, Integer measureListId, String unclosecode);

    /**
     *
     * @param listIds
     * @param categoryV3
     * @param area
     * @param closedcode
     * @return
     */
    List<Map<String, Object>> getMeasureListIssueStatusMapByListIdsAndCategoryKeyAndAreaId(String[] listIds, CategoryV3 categoryV3, Area area, String closedcode);

    /**
     *
     * @param listId
     * @param lastId
     * @param timestamp
     * @param start
     * @param limit
     * @return
     */
    List<MeasureListIssue> searchIssueListByListIdLastIdTimestampGt(Integer listId, Integer lastId, Long timestamp, Integer start, Integer limit);

    /**
     *
     * @param zoneUuids
     */
    List<MeasureListIssue> searchMeasueListIssueInZoneUuids(Set<String> zoneUuids);

    /**
     *
     * @param value
     */
    void insertMeasureListIssueObject(MeasureListIssue value);

    /**
     *
     * @param issue
     */
    void updateFullNoAffectedErr(MeasureListIssue issue);

    /**
     *
     * @param projectId
     * @param measureListId
     * @param unclosecode
     * @param categoryPathAndKey
     * @param status
     * @return
     */
    Integer searchMeasureListIssueByCloseStatusAndStatusAndCategoryPathAndKeyLike(Integer projectId, Integer measureListId, String unclosecode, String categoryPathAndKey, Integer status);

    /**
     *
     * @param vo
     * @return
     * @throws ParseException
     */
    Map<String, Object> searchMeasueListIssueInProj(SearchMeasueListIssueInProjVo vo ) throws ParseException;

    /**
     *
     * @param categoryKeys
     * @return
     */
    Map<String, List<String>> getCategoryPathNamesMap(List<String> categoryKeys);

    /**
     *
     * @param projectId
     * @param measureListIds
     * @return
     */
    Map<Integer, String> getMeasureListNameMap(Integer projectId, List<Integer> measureListIds);
    /**
     *
     * @param areaIdLists
     * @return
     */
    Map<Integer, List<String>> getAreaPathNamesMap(List<Integer> areaIdLists);

    /**
     * @Description:
     * @param projectId
     * @param uuid
     * @return com.longfor.longjian.measure.po.zhijian2.MeasureListIssue
     * @author DDC
     * @date 2019/1/10 19:40
     **/
    MeasureListIssue getIssueByProjectIdAndUuid(Integer projectId,String uuid);


    /**
     * 项目.实测实量.爆点管理.删除
     * @param projectId
     * @param uuid
     */
    void deletedByUpdateDeletedAt(Integer projectId, String uuid);

    /**
     *
     * @param projectId
     * @param uuid
     * @return
     */
    MeasureListIssue getByConditionNoFoundErr(Integer projectId, String uuid);

    /**
     *
     * @param issueUuid
     * @return
     */
    MeasureListIssue getByUuidUnscoped(String issueUuid);
}
