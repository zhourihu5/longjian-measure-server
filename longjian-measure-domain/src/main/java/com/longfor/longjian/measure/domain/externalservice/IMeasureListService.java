package com.longfor.longjian.measure.domain.externalservice;

import com.longfor.longjian.common.exception.LjBaseRuntimeException;
import com.longfor.longjian.measure.po.zhijian2.MeasureList;
import com.longfor.longjian.measure.po.zhijian2.MeasureSquad;
import com.longfor.longjian.measure.po.zhijian2.MeasureSquadUser;
import com.longfor.longjian.measure.po.zhijian2.MeasureZone;
import com.longfor.longjian.measure.vo.ConditionSearchVo;
import com.longfor.longjian.measure.vo.CreateMeasureListVo;
import com.longfor.longjian.measure.vo.CreateZoneFromAppVo;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IMeasureListService {
    /**
     *
     * @param map
     * @return
     */
    List<Map<String,Object>> getMeasureList(Map<String,Object> map);

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
     * @param vo
     */
    void createZoneFromApp(CreateZoneFromAppVo vo);

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
    MeasureList getByProjIdAndIdNoFoundErr(Integer projectId, Integer id);

    /**
     *
     * @param vo
     * @return
     * @throws LjBaseRuntimeException
     */
    Map<String, Object> conditionSearch(ConditionSearchVo vo) throws LjBaseRuntimeException;

    MeasureList createMeasureList(CreateMeasureListVo vo );
}
