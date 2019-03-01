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
     * @param project_id
     * @param measure_list_id
     * @param startTime
     * @param endTime
     * @param UNCLOSECODE
     * @return
     */
    List<Map<String, Object>> searchMeasureListIssueTrend(Integer project_id, Integer measure_list_id, String startTime, String endTime, String UNCLOSECODE) throws ParseException;

    /**
     *
     * @param project_id
     * @param measure_list_id
     * @param UNCLOSECODE
     * @return
     */
    Integer countMeasureListIssueDistributionCategory(Integer project_id, Integer measure_list_id, String UNCLOSECODE);

    /**
     *
     * @param project_id
     * @param measure_list_id
     * @param unclosecode
     * @return
     */
    List<MeasureListIssue> searchMeasureListIssueDistributionCategory(Integer project_id, Integer measure_list_id, String unclosecode);

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
     * @param list_id
     * @param last_id
     * @param timestamp
     * @param start
     * @param limit
     * @return
     */
    List<MeasureListIssue> searchIssueListByListIdLastIdTimestampGt(Integer list_id, Integer last_id, Long timestamp, Integer start, Integer limit);

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
     * @param project_id
     * @param measure_list_id
     * @param unclosecode
     * @param categoryPathAndKey
     * @param status
     * @return
     */
    Integer searchMeasureListIssueByCloseStatusAndStatusAndCategoryPathAndKeyLike(Integer project_id, Integer measure_list_id, String unclosecode, String categoryPathAndKey, Integer status);

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
    MeasureListIssue GetIssueByProjectIdAndUuid(Integer projectId,String uuid);


    /**
     * 项目.实测实量.爆点管理.删除
     * @param project_id
     * @param uuid
     */
    void deletedByUpdateDeletedAt(Integer project_id, String uuid);

    /**
     *
     * @param project_id
     * @param uuid
     * @return
     */
    MeasureListIssue getByConditionNoFoundErr(Integer project_id, String uuid);

    /**
     *
     * @param issueUuid
     * @return
     */
    MeasureListIssue getByUuidUnscoped(String issueUuid);
}
