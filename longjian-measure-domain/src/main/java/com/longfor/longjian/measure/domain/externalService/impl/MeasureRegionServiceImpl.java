package com.longfor.longjian.measure.domain.externalService.impl;

import com.longfor.longjian.measure.dao.zhijian2.MeasureRegionMapper;
import com.longfor.longjian.measure.domain.externalService.IMeasureRegionService;
import com.longfor.longjian.measure.po.zhijian2.MeasureRegion;
import com.longfor.longjian.measure.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * wangxs
 * 2018-11-22
 */
@Service
public class MeasureRegionServiceImpl implements IMeasureRegionService {

    @Autowired
    private MeasureRegionMapper measureRegionMapper;

    @Override
    public List<Map<String, Object>> getProjMeasureRegionByAreaId(Integer project_id, Integer area_id) {
        return measureRegionMapper.getProjMeasureRegionByAreaId(project_id,area_id);
    }

    @Override
    public List<MeasureRegion> searchUnscopedByProjIdUpdateAtGt(String projectId, String updateAtGte) {
        return measureRegionMapper.searchUnscopedByProjIdUpdateAtGt(projectId,updateAtGte);
    }

    @Override
    public List<MeasureRegion> searchUnscopedByProjIdLastIdUpdateAtGt(Integer project_id, Integer last_id, Long timestamp, Integer measureApiGetPerTime, Integer start) {
        List<MeasureRegion> measureRegions = measureRegionMapper.searchUnscopedByProjIdLastIdUpdateAtGt(project_id,last_id,timestamp,measureApiGetPerTime,start);
        return measureRegions;
    }

    @Override
    public Integer getCountUnscopedByProjIdUpdateAtGt(Integer project_id, Long timestamp) {
        return measureRegionMapper.getCountUnscopedByProjIdUpdateAtGt(project_id,timestamp);
    }

    @Override
    public List<MeasureRegion> searchByUuids(Integer projId, Set<String> uuids) {
        if (uuids == null || uuids.size() == 0){
            return new ArrayList<>();
        }
        return measureRegionMapper.searchByUuids(projId,uuids);
    }

    @Override
    public List<MeasureRegion> createRegionsFromRegionStructList(Integer projectId,List<MeasureRegion> measureRegions) throws Exception {
        Set<Integer> areaIds = new HashSet<>();
        measureRegions.forEach(measureRegion -> {
            areaIds.add(measureRegion.getAreaId());
        });
        List<MeasureRegion> measureRegionLists = new ArrayList<>();
        try{
            Map<Integer,Integer> indexMap= measureRegionMapper.searchMeasureRegionAreaMaxIndexByAreaIdList(projectId,areaIds);
            measureRegions.forEach(measureRegion -> {
                if(indexMap.get(measureRegion.getAreaId())==null){
                    Integer maxIndex = indexMap.get(measureRegion.getAreaId());
                    maxIndex = 0;
                }
            });
            measureRegions.forEach(measureRegion -> {
                MeasureRegion newMeasureRegion = new MeasureRegion();
                newMeasureRegion.setUuid(measureRegion.getUuid());
                newMeasureRegion.setRegionIndex(indexMap.get(measureRegion.getAreaId()));
                newMeasureRegion.setAreaId(measureRegion.getAreaId());
                newMeasureRegion.setSrcType(measureRegion.getSrcType());
                newMeasureRegion.setAreaPathAndId(measureRegion.getAreaPathAndId());
                newMeasureRegion.setDrawingMd5(measureRegion.getDrawingMd5());
                newMeasureRegion.setPolygon(measureRegion.getPolygon());
                measureRegionLists.add(newMeasureRegion);
            });
            measureRegionMapper.InsertObjectsNoAffectedErr(measureRegionLists);
        }catch (Exception e){
            throw new Exception(e);
        }
        return measureRegionLists;
    }

    @Override
    public MeasureRegion searchByUuid(Integer project_id, String uuid) {
        return measureRegionMapper.getByConditionNoFoundErr(project_id,uuid);
    }
}
