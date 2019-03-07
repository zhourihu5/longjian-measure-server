package com.longfor.longjian.measure.domain.externalservice.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.longfor.longjian.common.exception.LjBaseRuntimeException;
import com.longfor.longjian.measure.dao.zhijian2.MeasureRegionMapper;
import com.longfor.longjian.measure.domain.externalservice.IMeasureRegionService;
import com.longfor.longjian.measure.po.zhijian2.MeasureRegion;
import com.longfor.longjian.measure.util.ExampleUtil;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * wangxs
 * 2018-11-22
 */
@Service
public class MeasureRegionServiceImpl implements IMeasureRegionService {

    @Resource
    private MeasureRegionMapper measureRegionMapper;
    private static final String PROJECTID = "projectId";
    private static final String DELETEAT = "deleteAt";
    @Override
    public List<Map<String, Object>> getProjMeasureRegionByAreaId(Integer projectId, Integer areaId) {
        return measureRegionMapper.getProjMeasureRegionByAreaId(projectId, areaId);
    }

    @Override
    public List<MeasureRegion> searchUnscopedByProjIdUpdateAtGt(String projectId, String updateAtGte) {
        return measureRegionMapper.searchUnscopedByProjIdUpdateAtGt(projectId, updateAtGte);
    }

    @Override
    public List<MeasureRegion> searchUnscopedByProjIdLastIdUpdateAtGt(Integer projectId, Integer lastId, Long timestamp, Integer measureApiGetPerTime, Integer start) {
        return measureRegionMapper.searchUnscopedByProjIdLastIdUpdateAtGt(projectId, lastId, timestamp, measureApiGetPerTime, start);
    }

    @Override
    public Integer getCountUnscopedByProjIdUpdateAtGt(Integer projectId, Long timestamp) {
        return measureRegionMapper.getCountUnscopedByProjIdUpdateAtGt(projectId, timestamp);
    }

    @Override
    public List<MeasureRegion> searchByUuids(Integer projId, Set<String> uuids) {
        if (uuids == null || uuids.isEmpty()) {
            return new ArrayList<>();
        }
        return measureRegionMapper.searchByUuids(projId, uuids);
    }

    @Override
    public List<MeasureRegion> createRegionsFromRegionStructList(Integer projectId, List<MeasureRegion> measureRegions) throws LjBaseRuntimeException {
        Set<Integer> areaIds = new HashSet<>();
        measureRegions.forEach(measureRegion -> areaIds.add(measureRegion.getAreaId()));
        List<MeasureRegion> measureRegionLists = new ArrayList<>();
        if(!areaIds.isEmpty()){
            try {
                Map<String, Integer> indexMap = measureRegionMapper.searchMeasureRegionAreaMaxIndexByAreaIdList(projectId, areaIds);
                Integer areaId = indexMap.get("area_id");
                Integer maxindex = indexMap.get("max_index");
                Map<Integer, Integer> indexMap2 = Maps.newHashMap();
                indexMap2.put(areaId,maxindex);
                measureRegions.forEach(measureRegion -> {
                    if (indexMap2.get(measureRegion.getAreaId()) == null) {
                        Integer maxIndex = indexMap2.get(measureRegion.getAreaId());
                        maxIndex = 0;
                    }
                });
                measureRegions.forEach(measureRegion -> {
                    MeasureRegion newMeasureRegion = new MeasureRegion();
                    newMeasureRegion.setUuid(measureRegion.getUuid());
                    newMeasureRegion.setRegionIndex(indexMap2.get(measureRegion.getAreaId())+1);
                    indexMap2.put(areaId,indexMap2.get(measureRegion.getAreaId())+1);
                    newMeasureRegion.setProjectId(measureRegion.getProjectId());
                    newMeasureRegion.setAreaId(measureRegion.getAreaId());
                    newMeasureRegion.setRelId(measureRegion.getRelId() == null ? 0 :measureRegion.getRelId());
                    newMeasureRegion.setSrcType(measureRegion.getSrcType());
                    newMeasureRegion.setTagIdList(measureRegion.getTagIdList() == null ? "": measureRegion.getTagIdList());
                    newMeasureRegion.setAreaPathAndId(measureRegion.getAreaPathAndId());
                    newMeasureRegion.setDrawingMd5(measureRegion.getDrawingMd5());
                    newMeasureRegion.setPolygon(measureRegion.getPolygon());
                    newMeasureRegion.setCreateAt(new Date());
                    newMeasureRegion.setUpdateAt(new Date());
                    measureRegionLists.add(newMeasureRegion);
                });
                measureRegionMapper.insertList(measureRegionLists);
            } catch (Exception e) {
                throw new LjBaseRuntimeException(-9999,e+"");
            }
        }
        return measureRegionLists;
    }

    @Override
    public MeasureRegion searchByUuid(Integer projectId, String uuid) {
        Example example = new Example(MeasureRegion.class);
        example.createCriteria().andEqualTo(PROJECTID, projectId).andEqualTo("uuid", uuid);
        return measureRegionMapper.selectOneByExample(example);
    }

    @Override
    public MeasureRegion searchByProjIdAndRegionUuid(Integer projId, String regionUuid) {
        Example example = new Example(MeasureRegion.class);
        example.createCriteria().andEqualTo(PROJECTID, projId).andEqualTo("uuid", regionUuid);
        List<MeasureRegion> measureRegions = measureRegionMapper.selectByExample(example);
        if (!measureRegions.isEmpty()) {
            return measureRegions.get(0);
        }
        return null;
    }

    @Override
    public List<MeasureRegion> searchByProjIdAndRelId(Integer projectId, Integer relId) {
        Example example = new Example(MeasureRegion.class);
        example.createCriteria().andEqualTo(PROJECTID, projectId).andEqualTo("relId", relId);
        return measureRegionMapper.selectByExample(example);
    }

    @Override
    public List<Map<String, Object>> getMaxRegionIndexGroupByAreaIdNoDeleted(Integer projectId, List areaIdList) {
        return measureRegionMapper.getMaxRegionIndexGroupByAreaIdNoDeleted(projectId, areaIdList);
    }

    @Override
    public MeasureRegion save(MeasureRegion model) {
        model.setCreateAt(new Date());
        model.setUpdateAt(new Date());
        measureRegionMapper.insertSelective(model);
        return model;
    }

    @Override
    public MeasureRegion update(MeasureRegion mode) {
        mode.setUpdateAt(new Date());
        measureRegionMapper.updateByPrimaryKeySelective(mode);
        return null;
    }

    @Override
    public List<MeasureRegion> selectByExample(Example example) {
        return measureRegionMapper.selectByExample(example);
    }

    @Override
    public List<MeasureRegion> searchByProjUuids(Integer projectId, List<String> regionUuids) {
        Set<String> regionUuidSet = Sets.newHashSet();
        regionUuidSet.addAll(regionUuids);
        if (regionUuidSet.isEmpty()) {
            return Lists.newArrayList();
        }
        Example example = new Example(MeasureRegion.class);
        example.createCriteria().andEqualTo(PROJECTID, projectId).andIn("uuid", regionUuids).andIsNull(DELETEAT);
        return measureRegionMapper.selectByExample(example);
    }

    @Override
    public void updateByProjectIdAndIdInNoDeleted(Integer projectId, List regionIds, String polygon, String tagIdList) {
        MeasureRegion measureRegion = new MeasureRegion();
        measureRegion.setPolygon(polygon);
        measureRegion.setUpdateAt(new Date());
        measureRegion.setTagIdList(tagIdList);

        Example example = new Example(MeasureRegion.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo(PROJECTID, projectId);
        criteria.andIn("id", regionIds);
        criteria.andIsNull(DELETEAT);
        measureRegionMapper.updateByExampleSelective(measureRegion, example);
    }

    @Override
    public List<MeasureRegion> selectByProjectIdAndIdNoDeleted(Integer projectId, List<Integer> regionIdList) {
        Example example = new Example(MeasureRegion.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo(PROJECTID, projectId);
        criteria.andIn("id", regionIdList);
        criteria.andIsNull(DELETEAT);
        return measureRegionMapper.selectByExample(example);
    }

    @Override
    public void delete(Integer projectId, List<Integer> regionIdList) {
        MeasureRegion measureRegion = new MeasureRegion();
        measureRegion.setDeleteAt(new Date());
        measureRegion.setUpdateAt(new Date());
        Example example = new Example(MeasureRegion.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo(PROJECTID, projectId);
        criteria.andIn("id", regionIdList);

        measureRegionMapper.updateByExampleSelective(measureRegion, example);
    }

    @Override
    public MeasureRegion getByUuid(Integer projId, String uuid) {
        return measureRegionMapper.GetByUuid(projId, uuid);
    }

    @Override
    public List<MeasureRegion> searchUnscopedByProjIdUpdateAtGt2(Integer projId, String timeFmt) throws ParseException {
        Example example = new Example(MeasureRegion.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo(PROJECTID, projId);
        if(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(timeFmt).getTime() > 0){
            criteria.andGreaterThan("updateAt", timeFmt);
        }
        return measureRegionMapper.selectByExample(example);
    }

    @Override
    public List<MeasureRegion> searchByIdAndAreaIdAndProjectIdNoDeleted(List<String> regionIdList, List<String> copyAreaIdList, int projId) {
        Example example = new Example(MeasureRegion.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo(PROJECTID, projId);
        criteria.andIn("id", regionIdList);
        criteria.andIn("areaId", copyAreaIdList);
        ExampleUtil.addDeleteAtJudge(example);
        return measureRegionMapper.selectByExample(example);
    }
}