package com.longfor.longjian.measure.domain.externalService.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.longfor.longjian.measure.dao.zhijian2.MeasureRegionMapper;
import com.longfor.longjian.measure.domain.externalService.IMeasureRegionService;
import com.longfor.longjian.measure.po.zhijian2.MeasureRegion;
import com.longfor.longjian.measure.util.ExampleUtil;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
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
    public List<Map<String, Object>> getProjMeasureRegionByAreaId(Integer project_id, Integer area_id) {
        return measureRegionMapper.getProjMeasureRegionByAreaId(project_id, area_id);
    }

    @Override
    public List<MeasureRegion> searchUnscopedByProjIdUpdateAtGt(String projectId, String updateAtGte) {
        return measureRegionMapper.searchUnscopedByProjIdUpdateAtGt(projectId, updateAtGte);
    }

    @Override
    public List<MeasureRegion> searchUnscopedByProjIdLastIdUpdateAtGt(Integer project_id, Integer last_id, Long timestamp, Integer measureApiGetPerTime, Integer start) {
        List<MeasureRegion> measureRegions = measureRegionMapper.searchUnscopedByProjIdLastIdUpdateAtGt(project_id, last_id, timestamp, measureApiGetPerTime, start);
        return measureRegions;
    }

    @Override
    public Integer getCountUnscopedByProjIdUpdateAtGt(Integer project_id, Long timestamp) {
        return measureRegionMapper.getCountUnscopedByProjIdUpdateAtGt(project_id, timestamp);
    }

    @Override
    public List<MeasureRegion> searchByUuids(Integer projId, Set<String> uuids) {
        if (uuids == null || uuids.size() == 0) {
            return new ArrayList<>();
        }
        return measureRegionMapper.searchByUuids(projId, uuids);
    }

    @Override
    public List<MeasureRegion> createRegionsFromRegionStructList(Integer projectId, List<MeasureRegion> measureRegions) throws Exception {
        Set<Integer> areaIds = new HashSet<>();
        measureRegions.forEach(measureRegion -> {
            areaIds.add(measureRegion.getAreaId());
        });
        List<MeasureRegion> measureRegionLists = new ArrayList<>();
        if(areaIds.size()>0){
            try {
                Map<String, Integer> indexMap = measureRegionMapper.searchMeasureRegionAreaMaxIndexByAreaIdList(projectId, areaIds);
                Integer area_id = indexMap.get("area_id");
                Integer max_index = indexMap.get("max_index");
                Map<Integer, Integer> indexMap2 = Maps.newHashMap();
                indexMap2.put(area_id,max_index);
                measureRegions.forEach(measureRegion -> {
                    if (indexMap2.get(measureRegion.getAreaId()) == null) {
                        Integer maxIndex = indexMap2.get(measureRegion.getAreaId());
                        maxIndex = 0;
                    }
                });
                measureRegions.forEach(measureRegion -> {
                    MeasureRegion newMeasureRegion = new MeasureRegion();
                    newMeasureRegion.setUuid(measureRegion.getUuid());
                    newMeasureRegion.setRegionIndex(indexMap2.get(measureRegion.getAreaId()));
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
                throw new Exception(e);
            }
        }
        return measureRegionLists;
    }

    @Override
    public MeasureRegion searchByUuid(Integer project_id, String uuid) {
        Example example = new Example(MeasureRegion.class);
        example.createCriteria().andEqualTo(PROJECTID, project_id).andEqualTo("uuid", uuid);
        return measureRegionMapper.selectOneByExample(example);
    }

    @Override
    public MeasureRegion searchByProjIdAndRegionUuid(Integer proj_id, String region_uuid) {
        Example example = new Example(MeasureRegion.class);
        example.createCriteria().andEqualTo(PROJECTID, proj_id).andEqualTo("uuid", region_uuid);
        List<MeasureRegion> measureRegions = measureRegionMapper.selectByExample(example);
        if (!measureRegions.isEmpty()) {
            MeasureRegion region = measureRegions.get(0);
            return region;
        }
        return null;
    }

    @Override
    public List<MeasureRegion> searchByProjIdAndRelId(Integer proj_id, Integer relId) {
        Example example = new Example(MeasureRegion.class);
        example.createCriteria().andEqualTo(PROJECTID, proj_id).andEqualTo("relId", relId);
        return measureRegionMapper.selectByExample(example);
    }

    @Override
    public List<Map<String, Object>> getMaxRegionIndexGroupByAreaIdNoDeleted(Integer project_id, List area_id_list) {
        return measureRegionMapper.getMaxRegionIndexGroupByAreaIdNoDeleted(project_id, area_id_list);
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
    public List<MeasureRegion> searchByProjUuids(Integer project_id, List<String> regionUuids) {
        Set<String> regionUuidSet = Sets.newHashSet();
        regionUuidSet.addAll(regionUuids);
        if (regionUuidSet.isEmpty()) {
            return Lists.newArrayList();
        }
        Example example = new Example(MeasureRegion.class);
        example.createCriteria().andEqualTo(PROJECTID, project_id).andIn("uuid", regionUuids).andIsNull(DELETEAT);
        return measureRegionMapper.selectByExample(example);
    }

    @Override
    public void updateByProjectIdAndIdInNoDeleted(Integer project_id, List region_ids, String polygon, String tag_id_list) {
        MeasureRegion measureRegion = new MeasureRegion();
        measureRegion.setPolygon(polygon);
        measureRegion.setUpdateAt(new Date());
        measureRegion.setTagIdList(tag_id_list);

        Example example = new Example(MeasureRegion.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo(PROJECTID, project_id);
        criteria.andIn("id", region_ids);
        criteria.andIsNull(DELETEAT);
        measureRegionMapper.updateByExampleSelective(measureRegion, example);
    }

    @Override
    public List<MeasureRegion> selectByProjectIdAndIdNoDeleted(Integer project_id, List<Integer> region_id_list) {
        Example example = new Example(MeasureRegion.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo(PROJECTID, project_id);
        criteria.andIn("id", region_id_list);
        criteria.andIsNull(DELETEAT);
        return measureRegionMapper.selectByExample(example);
    }

    @Override
    public void delete(Integer project_id, List<Integer> region_id_list) {
        MeasureRegion measureRegion = new MeasureRegion();
        measureRegion.setDeleteAt(new Date());
        measureRegion.setUpdateAt(new Date());
        Example example = new Example(MeasureRegion.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo(PROJECTID, project_id);
        criteria.andIn("id", region_id_list);

        measureRegionMapper.updateByExampleSelective(measureRegion, example);
    }

    @Override
    public MeasureRegion GetByUuid(Integer projId, String uuid) {
        return measureRegionMapper.GetByUuid(projId, uuid);
    }

    @Override
    public List<MeasureRegion> searchUnscopedByProjIdUpdateAtGt2(Integer projId, String timeFmt) {
        Example example = new Example(MeasureRegion.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo(PROJECTID, projId);
        criteria.andGreaterThan("updateAt", timeFmt);
        return measureRegionMapper.selectByExample(example);
    }

    @Override
    public List<MeasureRegion> searchByIdAndAreaIdAndProjectIdNoDeleted(List<String> region_id_list, List<String> copy_area_id_list, int proj_id) {
        Example example = new Example(MeasureRegion.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo(PROJECTID, proj_id);
        criteria.andIn("id", region_id_list);
        criteria.andIn("areaId", copy_area_id_list);
        ExampleUtil.addDeleteAtJudge(example);
        return measureRegionMapper.selectByExample(example);
    }
}