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
    public List<Map<String, Object>> StatMearureZoneResultSquadTotalCountByListIdGroupByCategoryKey(Integer measure_list_id) {
        return measureZoneResultMapper.StatMearureZoneResultSquadTotalCountByListIdGroupByCategoryKey(measure_list_id);
    }
}
