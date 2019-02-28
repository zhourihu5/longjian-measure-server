package com.longfor.longjian.measure.domain.externalService;

import com.longfor.longjian.measure.po.zhijian2.MeasureList;
import com.longfor.longjian.measure.po.zhijian2.MeasureSquad;
import com.longfor.longjian.measure.po.zhijian2.MeasureSquadUser;
import com.longfor.longjian.measure.po.zhijian2.MeasureZone;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IMeasureListService {
    /**
     *
     * @param finish_status
     * @param q
     * @param project_id
     * @param categoryPathAndKey
     * @param areaPathAndId
     * @param userIds
     * @param page
     * @param page_size
     * @return
     */
    List<Map<String,Object>> getMeasureList(Integer finish_status, String q, Integer project_id, String categoryPathAndKey, String areaPathAndId, String[] userIds, Integer page, Integer page_size);

    /**
     * total
     * @param finish_status
     * @param q
     * @param project_id
     * @param categoryPathAndKey
     * @param areaPathAndId
     * @param userIds
     * @return
     */
    Integer getTotalMeasure(Integer finish_status, String q, Integer project_id, String categoryPathAndKey, String areaPathAndId, String[] userIds);

    /**
     *
     * @param project_id
     * @return
     */
    List<Map<String,Object>> searchByProjectId(Integer project_id);

    /**
     *
     * @param project_id
     * @param measure_list_id
     * @return
     */
    MeasureList searchByProjectIdAndMeasureListId(Integer project_id, Integer measure_list_id);
    /**
     *
     * @param listId
     * @return
     */
    MeasureList getNoProjNoFoundErr(String listId);

    /**
     *
     * @param projectId
     * @param userId
     * @return
     */
    List<MeasureList> searchListByProjIdUserId( String projectId, Integer userId);

    /**
     *
     * @param project_id
     * @param list_id
     * @param uuid
     * @param category_key
     * @return
     */
    List<MeasureZone> searchZoneByMeasureListIdRegionUuidCategoryKey(Integer project_id, Integer list_id, String uuid, String category_key);

    /**
     *
     * @param project_id
     * @param uuid
     * @return
     */
    List<MeasureZone> getZoneByUuid(Integer project_id, String uuid);

    /**
     *
     * @param project_id
     * @param list_id
     * @param uuid
     * @param uuid1
     * @param areaId
     * @param areaPathAndId
     * @param category_key
     * @param s
     * @param id
     * @param id1
     */
    void createZoneFromApp(Integer project_id, Integer list_id, String uuid, String uuid1, Integer areaId, String areaPathAndId, String category_key, String s, Integer id, Integer id1);

    /**
     *
     * @param currentProjectId
     * @param listIds
     * @return
     */
    List<MeasureSquadUser> searchMeasureSquadUserByListIds(Integer currentProjectId, Set<Integer> listIds);

    int updateMeasureList(MeasureList  measureList);

    void delete(Integer id);

    void updateFinishStatus(Map<String,Object>map);

    void updateCloseStatus(Map<String,Object>map);


    /**
     * @Description:
     * @param projId
     * @param Id
     * @return com.longfor.longjian.measure.po.zhijian2.measurelist
     * @author DDC
     * @date 2019/1/10 20:54
     **/
    MeasureList getMeasureListByProjIdAndId(Integer projId,Integer Id);

    /**
     *
     * @param projId
     * @param list_id
     * @return
     */
    List<MeasureSquad> searchOnlyMeasureSquadByProjIdAndListId(Integer projId, Integer list_id);

    /**
     *
     * @param projId
     * @param list_id
     * @return
     */
    MeasureList getNoFoundErr(Integer projId, Integer list_id);

    /**
     *
     * @param id
     * @param keySet
     * @return
     */
    List<MeasureZone> searchZoneByProjUuids(Integer id, Set<String> keySet);

    /**
     *
     * @param id
     * @param keySet
     * @return
     */
    List<MeasureSquad> searchByProjIdIdIn(Integer id, Set<Integer> keySet);

    /**
     *
     * @param projectId
     * @param id
     * @return
     */
    MeasureList GetByProjIdAndIdNoFoundErr(Integer projectId, Integer id);

    /**
     *
     * @param group_id
     * @param project_id
     * @param page
     * @param page_size
     * @param area_id
     * @param user_id_list
     * @param finish_status
     * @param name
     * @param category_key
     * @return
     */
    Map<String, Object> conditionSearch(Integer group_id, Integer project_id, Integer page, Integer page_size, String area_id, String user_id_list, Integer finish_status, String name, String category_key) throws Exception;

    MeasureList createMeasureList(int proj_id, String name, String area_type, Integer id, Integer id1, String root_category_key, String plan_begin_on, String plan_end_on);
}
