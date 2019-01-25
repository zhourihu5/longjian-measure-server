package com.longfor.longjian.measure.domain.externalService.impl;

import com.longfor.longjian.measure.dao.zhijian2.MeasureListMapper;
import com.longfor.longjian.measure.dao.zhijian2.MeasureSquadMapper;
import com.longfor.longjian.measure.dao.zhijian2.MeasureSquadUserMapper;
import com.longfor.longjian.measure.dao.zhijian2.MeasureZoneMapper;
import com.longfor.longjian.measure.domain.externalService.IMeasureListService;
import com.longfor.longjian.measure.po.zhijian2.MeasureList;
import com.longfor.longjian.measure.po.zhijian2.MeasureSquad;
import com.longfor.longjian.measure.po.zhijian2.MeasureSquadUser;
import com.longfor.longjian.measure.po.zhijian2.MeasureZone;
import org.bouncycastle.cms.PasswordRecipientId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class MeasureListServiceImpl implements IMeasureListService {

    @Autowired
    private MeasureListMapper measureListMapper;
    @Autowired
    private MeasureZoneMapper measureZoneMapper;
    @Autowired
    private MeasureSquadUserMapper measureSquadUserMapper;
    @Resource
    private MeasureSquadMapper measureSquadMapper;

    @Override
    public List<Map<String, Object>> getMeasureList(Integer finish_status, String q, Integer project_id, String categoryPathAndKey, String areaPathAndId, String[] userIds, Integer page, Integer page_size) {
        page = page - 1;
        return measureListMapper.getMeasureList(finish_status, q, project_id, categoryPathAndKey, areaPathAndId, userIds, page, page_size);
    }

    @Override
    public Integer getTotalMeasure(Integer finish_status, String q, Integer project_id, String categoryPathAndKey, String areaPathAndId, String[] userIds) {
        return measureListMapper.getTotalMeasure(finish_status, q, project_id, categoryPathAndKey, areaPathAndId, userIds);
    }

    @Override
    public List<Map<String, Object>> searchByProjectId(Integer project_id) {
        return measureListMapper.searchByProjectId(project_id);
    }

    @Override
    public MeasureList searchByProjectIdAndMeasureListId(Integer project_id, Integer measure_list_id) {
        MeasureList measureList = new MeasureList();
        measureList.setId(measure_list_id);
        measureList.setProjectId(project_id);
        measureList.setDeleteAt(null);
        return measureListMapper.selectOne(measureList);
    }

    @Override
    public MeasureList getNoProjNoFoundErr(String listId) {
        return measureListMapper.getNoProjNoFoundErr(listId);
    }

    @Override
    public List<MeasureList> searchListByProjIdUserId(String projectId, Integer userId) {
        return measureListMapper.searchListByProjIdUserId(projectId, userId);
    }

    @Override
    public List<MeasureZone> searchZoneByMeasureListIdRegionUuidCategoryKey(Integer project_id, Integer list_id, String uuid, String category_key) {
        return measureZoneMapper.searchByCondition(project_id, list_id, uuid, category_key);
    }

    @Override
    public MeasureZone getZoneByUuid(Integer project_id, String uuid) {
        return measureZoneMapper.getZoneByUuid(project_id, uuid);
    }

    @Override
    public void createZoneFromApp(Integer project_id, Integer list_id, String uuid, String regionUuid, Integer areaId, String areaPathAndId, String category_key, String categoryPathAndKey, Integer finishId, Integer closeId) {
        MeasureZone measureZone = new MeasureZone();
        measureZone.setProjectId(project_id);
        measureZone.setListId(list_id);
        measureZone.setUuid(uuid);
        measureZone.setRegionUuid(regionUuid);
        measureZone.setAreaId(areaId);
        measureZone.setAreaPathAndId(areaPathAndId);
        measureZone.setCategoryKey(category_key);
        measureZone.setCategoryPathAndKey(categoryPathAndKey);
        measureZone.setFinishStatus(finishId);
        measureZone.setCloseStatus(closeId);
        measureZoneMapper.insert(measureZone);
    }

    @Override
    public List<MeasureSquadUser> searchMeasureSquadUserByListIds(Integer currentProjectId, Set<Integer> listIds) {
        return measureSquadUserMapper.searchMeasureSquadUserByListIds(currentProjectId, listIds);
    }

    @Override
    public int updateMeasureList(MeasureList measureList) {
        Example example = new Example(MeasureList.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", measureList.getId());
        criteria.andIsNull("deleteAt");
        return measureListMapper.updateByExampleSelective(measureList, example);
    }

    @Override
    public void delete(Integer id) {
        Example example = new Example(MeasureList.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", id);

        MeasureList measureList = new MeasureList();
        measureList.setId(id);
        measureList.setDeleteAt(new Date());

        measureListMapper.updateByExampleSelective(measureList, example);
    }

    @Override
    public void updateFinishStatus(Map<String, Object> map) {
        measureListMapper.updateFinishStatus(map);
    }

    @Override
    public MeasureList getMeasureListByProjIdAndId(Integer projId, Integer Id) {
        return measureListMapper.getMeasureListByProjIdAndId(projId, Id);
    }

    @Override
    public void updateCloseStatus(Map<String, Object> map) {
        measureListMapper.updateCloseStatus(map);
    }

    @Override
    public List<MeasureSquad> searchOnlyMeasureSquadByProjIdAndListId(Integer projId, Integer list_id) {
        Example example = new Example(MeasureSquad.class);
        example.createCriteria().andEqualTo("projectId", projId).andEqualTo("listId", list_id);
        return measureSquadMapper.selectByExample(example);
    }

    @Override
    public MeasureList getNoFoundErr(Integer projId, Integer list_id) {
        Example example = new Example(MeasureList.class);
        example.createCriteria().andEqualTo("projectId", projId).andEqualTo("id", list_id);
        return measureListMapper.selectOneByExample(example);
    }

    @Override
    public List<MeasureZone> searchZoneByProjUuids(Integer projectId, Set<String> keySet) {
        Example example = new Example(MeasureZone.class);
        example.createCriteria().andEqualTo("projectId", projectId).andIn("uuid", keySet);
        return measureZoneMapper.selectByExample(example);
    }

    @Override
    public List<MeasureSquad> searchByProjIdIdIn(Integer projId, Set<Integer> keySet) {
        Example example = new Example(MeasureSquad.class);
        example.createCriteria().andEqualTo("projectId", projId).andIn("id", keySet);
        return measureSquadMapper.selectByExample(example);
    }

    @Override
    public MeasureList GetByProjIdAndIdNoFoundErr(Integer projectId, Integer id) {
        Example example = new Example(MeasureList.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("projectId", projectId).andEqualTo("id",id);
        return measureListMapper.selectOneByExample(example);
    }
}
