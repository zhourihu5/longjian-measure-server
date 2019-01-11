package com.longfor.longjian.measure.domain.externalService.impl;

import com.longfor.longjian.measure.dao.zhijian2.MeasureZoneMapper;
import com.longfor.longjian.measure.domain.externalService.IMeasureZoneService;
import com.longfor.longjian.measure.po.zhijian2.MeasureList;
import com.longfor.longjian.measure.po.zhijian2.MeasureListArea;
import com.longfor.longjian.measure.po.zhijian2.MeasureZone;
import com.longfor.longjian.measure.util.ExampleUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
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
    public List<MeasureZone> searchZoneUnscopedByListIdLastIdUpdateAtGt(Integer projectId, String listId, Integer lastId, Long timestamp, Integer limit, Integer start) {

        return measureZoneMapper.searchZoneUnscopedByListIdLastIdUpdateAtGt(projectId, listId, lastId, timestamp, limit, start);
    }

    @Override
    public List<MeasureZone> searchZoneUnscopedByListIdLastIdUpdateAtGt2(Integer projectId, Integer list_id, Integer last_id, Long timestamp, Integer start, Integer limit) {
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

    @Override
    public void delete(Integer id) {
        Example example = new Example(MeasureZone.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("listId",id);

        MeasureZone measureZone=new MeasureZone();
        measureZone.setListId(id);
        measureZone.setDeleteAt(new Date());
        measureZoneMapper.updateByExampleSelective(measureZone,example);
    }

    @Override
    public void updateStatus(Map<String, Object> map) {
        measureZoneMapper.updateStatus(map);
    }

    @Override
    public void delByUuidList(Map<String, Object> map) {
        measureZoneMapper.delByUuidList(map);
    }

    @Override
    public List<MeasureZone> selectByExample(Example example) {
        return measureZoneMapper.selectByExample(example);
    }

    @Override
    public List<MeasureZone> selectByProjectIdAndRegionUUIdIn(Integer project_id, List<String> region_uuid_list) {
        Example example = new Example(MeasureZone.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("projectId",project_id);
        criteria.andIn("regionUuid",region_uuid_list);
        ExampleUtil.addDeleteAtJudge(example);
        return measureZoneMapper.selectByExample(example);
    }

}
