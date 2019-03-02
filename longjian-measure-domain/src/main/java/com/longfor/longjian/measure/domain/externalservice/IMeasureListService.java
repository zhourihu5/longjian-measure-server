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
     * @param finishStatus
     * @param q
     * @param projectId
     * @param categoryPathAndKey
     * @param areaPathAndId
     * @param userIds
     * @return
     */
    Integer getTotalMeasure(Integer finishStatus, String q, Integer projectId, String categoryPathAndKey, String areaPathAndId, String[] userIds);

    /**
     *
     * @param projectId
     * @return
     */
    List<Map<String,Object>> searchByProjectId(Integer projectId);

    /**
     *
     * @param projectId
     * @param measureListId
     * @return
     */
    MeasureList searchByProjectIdAndMeasureListId(Integer projectId, Integer measureListId);
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
     * @param projectId
     * @param listId
     * @param uuid
     * @param categoryKey
     * @return
     */
    List<MeasureZone> searchZoneByMeasureListIdRegionUuidCategoryKey(Integer projectId, Integer listId, String uuid, String categoryKey);

    /**
     *
     * @param projectId
     * @param uuid
     * @return
     */
    List<MeasureZone> getZoneByUuid(Integer projectId, String uuid);

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
     * @param id
     * @return com.longfor.longjian.measure.po.zhijian2.measurelist
     * @author DDC
     * @date 2019/1/10 20:54
     **/
    MeasureList getMeasureListByProjIdAndId(Integer projId,Integer id);

    /**
     *
     * @param projId
     * @param listId
     * @return
     */
    List<MeasureSquad> searchOnlyMeasureSquadByProjIdAndListId(Integer projId, Integer listId);

    /**
     *
     * @param projId
     * @param listId
     * @return
     */
    MeasureList getNoFoundErr(Integer projId, Integer listId);

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
     *SS
     * @param vo
     * @return
     * @throws LjBaseRuntimeException
     */
    Map<String, Object> conditionSearch(ConditionSearchVo vo) throws LjBaseRuntimeException;

    /**
     * SS
     * @param vo
     * @return
     */
    MeasureList createMeasureList(CreateMeasureListVo vo );
}
