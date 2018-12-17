package com.longfor.longjian.measure.domain.externalService;

import com.longfor.longjian.measure.po.zhijian2.Area;
import com.longfor.longjian.measure.po.zhijian2.CategoryV3;
import com.longfor.longjian.measure.po.zhijian2.MeasureListIssue;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface IMeasureListIssueService {
    /**
     *
     * @param id
     * @return
     */
    Integer countByMeasureListId(String id);

    /**
     * 查询爆点情况
     * @param project_id
     * @param measure_list_id
     * @param UNCLOSECODE
     * @param REPAIRABLE
     * @param NOREPAIRABLE
     * @param ASSIGNNOREFORM
     * @param REFORMNOCHECK
     * @param CHECKYES
     * @return
     */
    Map<String,Object> getMeasureListIssueBrief(Integer project_id, Integer measure_list_id, String UNCLOSECODE, String REPAIRABLE, String NOREPAIRABLE, String NOTENOASSIGN, String ASSIGNNOREFORM, String REFORMNOCHECK, String CHECKYES);


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
}
