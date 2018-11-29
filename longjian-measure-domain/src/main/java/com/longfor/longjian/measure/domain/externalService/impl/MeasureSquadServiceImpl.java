package com.longfor.longjian.measure.domain.externalService.impl;

import com.longfor.longjian.measure.dao.zhijian2.MeasureSquadMapper;
import com.longfor.longjian.measure.domain.externalService.IMeasureSquadService;
import com.longfor.longjian.measure.po.zhijian2.MeasureSquad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MeasureSquadServiceImpl implements IMeasureSquadService {
    @Autowired
    private MeasureSquadMapper measureSquadMapper;
    @Override
    public List<MeasureSquad> searchOnlyMeasureSquadByProjIdAndListId(Integer project_id, Integer measure_list_id) {
        MeasureSquad measureSquad = new MeasureSquad();
        measureSquad.setProjectId(project_id);
        measureSquad.setDeleteAt(null);
        measureSquad.setListId(measure_list_id);
        return measureSquadMapper.select(measureSquad);
    }
}