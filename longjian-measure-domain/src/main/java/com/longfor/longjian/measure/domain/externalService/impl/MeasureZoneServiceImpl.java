package com.longfor.longjian.measure.domain.externalService.impl;

import com.longfor.longjian.measure.dao.zhijian2.MeasureZoneMapper;
import com.longfor.longjian.measure.domain.externalService.IMeasureZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MeasureZoneServiceImpl implements IMeasureZoneService {

    @Autowired
    private MeasureZoneMapper measureZoneMapper;

    @Override
    public Integer searchTotalByProjectIdAndMeasureListId(Integer project_id, int[] ints) {
        return measureZoneMapper.searchTotalByProjectIdAndMeasureListId(project_id,ints);
    }

    @Override
    public Integer getMeasureZoneCountByListIdCategoryKey(Integer project_id, Integer measure_list_id, String subKey) {
        return measureZoneMapper.getMeasureZoneCountByListIdCategoryKey(project_id,measure_list_id,subKey);
    }
}
