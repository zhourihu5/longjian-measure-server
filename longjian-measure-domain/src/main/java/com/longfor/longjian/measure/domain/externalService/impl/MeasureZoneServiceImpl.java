package com.longfor.longjian.measure.domain.externalService.impl;

import com.longfor.longjian.measure.dao.zhijian2.MeasureZoneMapper;
import com.longfor.longjian.measure.domain.externalService.IMeasureZoneService;
import com.longfor.longjian.measure.po.zhijian2.MeasureZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class MeasureZoneServiceImpl implements IMeasureZoneService {

    @Autowired
    private MeasureZoneMapper measureZoneMapper;

    @Override
    public Integer searchTotalByProjectIdAndMeasureListId(Integer project_id, int[] ints) {
        return measureZoneMapper.searchTotalByProjectIdAndMeasureListId(project_id, ints);
    }


    @Override
    public Integer getMeasureZoneCountByListIdCategoryKey(Integer project_id, Integer measure_list_id, String subKey) {
        return measureZoneMapper.getMeasureZoneCountByListIdCategoryKey(project_id, measure_list_id, subKey);
    }

    @Override
    public List<Map<String, Object>> searchZoneUnscopedByListIdLastIdUpdateAtGt(Integer projectId, String listId, Integer lastId, Long timestamp, Integer limit, Integer start) {

        return measureZoneMapper.searchZoneUnscopedByListIdLastIdUpdateAtGt(projectId, listId, lastId, timestamp, limit, start);
    }

    @Override
    public List<Map<String, Object>> searchZoneUnscopedByListIdLastIdUpdateAtGt2(Integer projectId, Integer list_id, Integer last_id, Long timestamp, Integer start, Integer limit) {
        return measureZoneMapper.searchZoneUnscopedByListIdLastIdUpdateAtGt2(projectId, list_id, last_id, timestamp, start, limit);
    }

    @Override
    public Integer getCountZoneUnscopedByListIdUpdateAtGt(Integer projectId, Integer list_id, Long timestamp) {
        return measureZoneMapper.getCountZoneUnscopedByListIdUpdateAtGt(projectId,list_id,timestamp);
    }

    @Override
    public List<MeasureZone> searchZoneByUuid(Integer projId, Set<String> zoneUuids) {
        return measureZoneMapper.searchZoneByUuid(projId,zoneUuids);
    }

}
