package com.longfor.longjian.measure.domain.externalService.impl;

import com.longfor.longjian.measure.dao.zhijian2.AreaMapper;
import com.longfor.longjian.measure.domain.externalService.IAreaService;
import com.longfor.longjian.measure.po.zhijian2.Area;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AreaServiceImpl implements IAreaService {

    @Autowired
    AreaMapper areaMapper;

    @Override
    public Area getAreaByProjIdAndAreaId(Integer project_id, Integer area_id) {
        Area area = new Area();
        area.setId(area_id);
        area.setProjectId(project_id);
        area.setDeleteAt(null);
        return areaMapper.selectOne(area);
    }

    @Override
    public List<Map<String, Object>> getProMeasureAreaListByFatherId(String project_id, String area_id) {
        return areaMapper.selectByFatherId(project_id == null?"":project_id,area_id == null?"":area_id);
    }
}
