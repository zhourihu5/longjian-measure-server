package com.longfor.longjian.measure.domain.externalService.impl;

import com.longfor.longjian.measure.dao.zhijian2.MeasureZoneResultMapper;
import com.longfor.longjian.measure.domain.externalService.IMeasureZoneResultService;
import com.longfor.longjian.measure.po.zhijian2.MeasureZoneResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MeasureZoneResultServiceImpl implements IMeasureZoneResultService {

    @Autowired
    MeasureZoneResultMapper measureZoneResultMapper;

    @Override
    public List<Map<String,Object>> statMeasureListZoneResultCountByListIdGroupBySquad(Integer measure_list_id) {
        return measureZoneResultMapper.statMeasureListZoneResultCountByListIdGroupBySquad(measure_list_id);
    }

    @Override
    public List<Map<String,Object>> statMearureZoneResultSquadTotalCountByListIdCategoryKey(Integer measure_list_id, String categoryKey) {
        return measureZoneResultMapper.statMearureZoneResultSquadTotalCountByListIdCategoryKey(measure_list_id,categoryKey);
    }

    @Override
    public List<Map<String, Object>> statMearureZoneResultSquadTotalCountByListIdGroupByCategoryKey(Integer measure_list_id) {
        return measureZoneResultMapper.statMearureZoneResultSquadTotalCountByListIdGroupByCategoryKey(measure_list_id);
    }

    @Override
    public List<Map<String, Object>> getPassPercentDiffCategoryKeyListByMeasureListId(Integer measure_list_id) {
        return measureZoneResultMapper.getPassPercentDiffCategoryKeyListByMeasureListId(measure_list_id);
    }

    @Override
    public List<Map<String, Object>> getSquadsZoneResultPassPercentByListIdAndCategoryKey(Integer measure_list_id, String category_key, String CLOSEDCODE) {
        return measureZoneResultMapper.getSquadsZoneResultPassPercentByListIdAndCategoryKey(measure_list_id,category_key,CLOSEDCODE);
    }

    @Override
    public List<Map<String, Object>> getSquadPassPercentSmallestCategoryKeyListByMeasureListId(Integer measure_list_id) {
        return measureZoneResultMapper.getSquadPassPercentSmallestCategoryKeyListByMeasureListId(measure_list_id);
    }

    @Override
    public List<MeasureZoneResult> getSubActiveMeasureCategoryZonesByListIdCategoryKey(Integer project_id, Integer measure_list_id, String category_key) {
        return measureZoneResultMapper.getSubActiveMeasureCategoryZonesByListIdCategoryKey(project_id,measure_list_id,category_key);
    }

    @Override
    public Map<String, Object> getMeasureZoneResultPassPercentageByListIdsAndCategoryKeyAndAreaId(String[] listIds, String key, String areaId) {
        return measureZoneResultMapper.getMeasureZoneResultPassPercentageByListIdsAndCategoryKeyAndAreaId(listIds,key,areaId);
    }
    @Override
    public Integer countMeasureZoneByListIdsAndCategoryKeyAndAreaId(Integer project_id, String[] listIds, String key, String areaId) {
        return measureZoneResultMapper.countMeasureZoneByListIdsAndCategoryKeyAndAreaId(project_id,listIds,key,areaId);
    }

    @Override
    public List<MeasureZoneResult> searchResultUnscopedByListIdLastIdUpdateAtGt(Integer projectId, String listId, Integer lastId, Integer timestamp, Integer limit, Integer start) {
        return measureZoneResultMapper.searchResultUnscopedByListIdLastIdUpdateAtGt(projectId, listId, lastId, timestamp, limit, start);
    }
    @Override
    public Integer getCountResultUnscopedByListIdLastIdUpdateAtGt(Integer projectId, Integer list_id, Integer timestamp) {
        return measureZoneResultMapper.getCountResultUnscopedByListIdLastIdUpdateAtGt(projectId,list_id,timestamp);
    }

    @Override
    public List<MeasureZoneResult> getByProjIdListIdZoneUuidSquadId(Integer project_id, Integer list_id, String zone_uuid, Integer squad_id) {
        return measureZoneResultMapper.getByProjIdListIdZoneUuidSquadId(project_id, list_id, zone_uuid, squad_id);
    }

    @Override
    public MeasureZoneResult getByUuid(Integer projId, String uuid) {
        return measureZoneResultMapper.getByUuid(projId,uuid);
    }

    @Override
    public int insertObjectNoAffectedErr(MeasureZoneResult zoneResult) {
        return measureZoneResultMapper.insertSelective(zoneResult);
    }
}
