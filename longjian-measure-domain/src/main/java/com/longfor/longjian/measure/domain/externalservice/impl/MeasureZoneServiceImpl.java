package com.longfor.longjian.measure.domain.externalservice.impl;

import com.longfor.gaia.gfs.data.mybatis.datasource.LFAssignDataSource;
import com.longfor.longjian.measure.dao.zhijian2.MeasureZoneMapper;
import com.longfor.longjian.measure.domain.externalservice.IMeasureZoneService;
import com.longfor.longjian.measure.po.zhijian2.MeasureZone;
import com.longfor.longjian.measure.util.ExampleUtil;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class MeasureZoneServiceImpl implements IMeasureZoneService {

    @Resource
    private MeasureZoneMapper measureZoneMapper;
    private static final String PROJECTID = "projectId";
    @Override
    public Integer searchTotalByProjectIdAndMeasureListId(Integer projectId, int[] ints) {
        return measureZoneMapper.searchTotalByProjectIdAndMeasureListId(projectId, ints);
    }


    @Override
    public Integer getMeasureZoneCountByListIdCategoryKey(Integer projectId, Integer measureListId, String subKey) {
        return measureZoneMapper.getMeasureZoneCountByListIdCategoryKey(projectId, measureListId, subKey);
    }

    @Override
    public List<MeasureZone> searchZoneUnscopedByListIdLastIdUpdateAtGt(Integer projectId, String listId, Integer lastId, Long timestamp, Integer limit, Integer start) {

        return measureZoneMapper.searchZoneUnscopedByListIdLastIdUpdateAtGt(projectId, listId, lastId, timestamp, limit, start);
    }

    @Override
    public List<MeasureZone> searchZoneUnscopedByListIdLastIdUpdateAtGt2(Integer projectId, Integer listId, Integer lastId, Long timestamp, Integer start, Integer limit) {
        return measureZoneMapper.searchZoneUnscopedByListIdLastIdUpdateAtGt2(projectId, listId, lastId, timestamp, start, limit);
    }

    @Override
    public Integer getCountZoneUnscopedByListIdUpdateAtGt(Integer projectId, Integer listId, Long timestamp) {
        return measureZoneMapper.getCountZoneUnscopedByListIdUpdateAtGt(projectId,listId,timestamp);
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
        measureZone.setUpdateAt(new Date());
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
    @LFAssignDataSource("zhijian2")
    @Override
    public List<MeasureZone> searchZoneByProjUuids(Integer projectId, List<String> zoneUuids) {
        Example example = new Example(MeasureZone.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo(PROJECTID,projectId);
        criteria.andIn("uuid",zoneUuids);
        criteria.andIsNull("deleteAt");
        return measureZoneMapper.selectByExample(example);
    }

    @Override
    public List<MeasureZone> selectByProjectIdAndRegionUUIdIn(Integer projectId, List<String> regionUuidList) {
        Example example = new Example(MeasureZone.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo(PROJECTID,projectId);
        criteria.andIn("regionUuid",regionUuidList);
        ExampleUtil.addDeleteAtJudge(example);
        return measureZoneMapper.selectByExample(example);
    }

    @Override
    public MeasureZone getZoneByUuid(Integer projId, String uuid) {
        return  measureZoneMapper.getZoneByUuid(projId,uuid);
    }

    @Override
    public MeasureZone getByProjIdAndIdNoFoundErr(Integer projectId,Integer id) {
        return measureZoneMapper.GetByCondition(projectId,id);
    }

    @Override
    public Integer getMeasureListCategoryCountAndCheckItemCount(Integer measureListId) {
        return measureZoneMapper.GetMeasureListCategoryCount(measureListId);
    }

    @Override
    public Integer getMeasureListBuildingCountAndRegionCount(Integer measureListId) {
        return measureZoneMapper.GetMeasureListBuildingCount(measureListId);
    }

    @Override
    @LFAssignDataSource("zhijian2")
    public List<MeasureZone> searchByListId(Integer projId, Integer listId) {
        Example example =new Example(MeasureZone.class);
        example.createCriteria().andEqualTo(PROJECTID,projId).andEqualTo("listId",listId);
        ExampleUtil.addDeleteAtJudge(example);
        return measureZoneMapper.selectByExample(example);
    }

    @Override
    public void insertMany(List<MeasureZone> insertZoneList) {
        if (insertZoneList == null|| insertZoneList.isEmpty()){
            return;
        }
        measureZoneMapper.insertList(insertZoneList);
    }

    @Override
    public int selectCountByExample(Example example) {
        return measureZoneMapper.selectCountByExample(example);
    }
}
