package com.longfor.longjian.measure.domain.externalService.impl;

import com.longfor.longjian.measure.dao.zhijian2.MeasureZoneResultMapper;
import com.longfor.longjian.measure.domain.externalService.IMeasureZoneResultService;
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
}
