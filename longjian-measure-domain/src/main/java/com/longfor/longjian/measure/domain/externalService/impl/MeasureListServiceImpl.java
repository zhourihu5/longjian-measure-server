package com.longfor.longjian.measure.domain.externalService.impl;

import com.longfor.longjian.measure.dao.zhijian2.MeasureListMapper;
import com.longfor.longjian.measure.dao.zhijian2.MeasureSquadUserMapper;
import com.longfor.longjian.measure.dao.zhijian2.MeasureZoneMapper;
import com.longfor.longjian.measure.domain.externalService.IMeasureListService;
import com.longfor.longjian.measure.po.zhijian2.MeasureList;
import com.longfor.longjian.measure.po.zhijian2.MeasureSquadUser;
import com.longfor.longjian.measure.po.zhijian2.MeasureZone;
import org.bouncycastle.cms.PasswordRecipientId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private  MeasureSquadUserMapper measureSquadUserMapper;
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
        return measureListMapper.searchListByProjIdUserId(projectId,userId);
    }

    @Override
    public List<MeasureZone> searchZoneByMeasureListIdRegionUuidCategoryKey(Integer project_id, Integer list_id, String uuid, String category_key) {
        return measureZoneMapper.searchByCondition(project_id,list_id,uuid,category_key);
    }

    @Override
    public MeasureZone getZoneByUuid(Integer project_id, String uuid) {
        return measureZoneMapper.getZoneByUuid(project_id,uuid);
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
        return measureSquadUserMapper.searchMeasureSquadUserByListIds(currentProjectId,listIds);
    }

    @Override
    public int setStatus(MeasureList measureList) {
        return  measureListMapper.updateByPrimaryKey(measureList);
    }
}
