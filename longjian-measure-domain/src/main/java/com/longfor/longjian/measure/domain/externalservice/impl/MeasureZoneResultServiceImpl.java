package com.longfor.longjian.measure.domain.externalservice.impl;

import com.longfor.longjian.measure.dao.zhijian2.MeasureZoneResultMapper;
import com.longfor.longjian.measure.domain.externalservice.IMeasureZoneResultService;
import com.longfor.longjian.measure.po.zhijian2.MeasureZoneResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class MeasureZoneResultServiceImpl implements IMeasureZoneResultService {

    @Resource
    MeasureZoneResultMapper measureZoneResultMapper;
    private static final String LISTID = "listId";
    @Override
    public List<Map<String,Object>> statMeasureListZoneResultCountByListIdGroupBySquad(Integer measureListId) {
        return measureZoneResultMapper.statMeasureListZoneResultCountByListIdGroupBySquad(measureListId);
    }

    @Override
    public List<Map<String,Object>> statMearureZoneResultSquadTotalCountByListIdCategoryKey(Integer measureListId, String categoryKey) {
        return measureZoneResultMapper.statMearureZoneResultSquadTotalCountByListIdCategoryKey(measureListId,categoryKey);
    }

    @Override
    public List<Map<String, Object>> statMearureZoneResultSquadTotalCountByListIdGroupByCategoryKey(Integer measureListId) {
        return measureZoneResultMapper.statMearureZoneResultSquadTotalCountByListIdGroupByCategoryKey(measureListId);
    }

    @Override
    public List<Map<String, Object>> getPassPercentDiffCategoryKeyListByMeasureListId(Integer measureListId) {
        return measureZoneResultMapper.getPassPercentDiffCategoryKeyListByMeasureListId(measureListId);
    }

    @Override
    public List<Map<String, Object>> getSquadsZoneResultPassPercentByListIdAndCategoryKey(Integer measureListId, String categoryKey, String CLOSEDCODE) {
        return measureZoneResultMapper.getSquadsZoneResultPassPercentByListIdAndCategoryKey(measureListId,categoryKey,CLOSEDCODE);
    }

    @Override
    public List<Map<String, Object>> getSquadPassPercentSmallestCategoryKeyListByMeasureListId(Integer measureListId) {
        return measureZoneResultMapper.getSquadPassPercentSmallestCategoryKeyListByMeasureListId(measureListId);
    }

    @Override
    public List<MeasureZoneResult> getSubActiveMeasureCategoryZonesByListIdCategoryKey(Integer projectId, Integer measureListId, String category_key) {
        return measureZoneResultMapper.getSubActiveMeasureCategoryZonesByListIdCategoryKey(projectId,measureListId,category_key);
    }

    @Override
    public Map<String, Object> getMeasureZoneResultPassPercentageByListIdsAndCategoryKeyAndAreaId(String[] listIds, String key, String areaId) {
        return measureZoneResultMapper.getMeasureZoneResultPassPercentageByListIdsAndCategoryKeyAndAreaId(listIds,key,areaId);
    }
    @Override
    public Integer countMeasureZoneByListIdsAndCategoryKeyAndAreaId(Integer projectId, String[] listIds, String key, String areaId) {
        return measureZoneResultMapper.countMeasureZoneByListIdsAndCategoryKeyAndAreaId(projectId,listIds,key,areaId);
    }

    @Override
    public List<MeasureZoneResult> searchResultUnscopedByListIdLastIdUpdateAtGt(Integer projectId, String listId, Integer lastId, Integer timestamp, Integer limit, Integer start) {
        return measureZoneResultMapper.searchResultUnscopedByListIdLastIdUpdateAtGt(projectId, listId, lastId, timestamp, limit, start);
    }
    @Override
    public Integer getCountResultUnscopedByListIdLastIdUpdateAtGt(Integer projectId, Integer listId, String timestamp) {
        return measureZoneResultMapper.getCountResultUnscopedByListIdLastIdUpdateAtGt(projectId,listId,timestamp);
    }

    @Override
    public List<MeasureZoneResult> getByProjIdListIdZoneUuidSquadId(Integer projectId, Integer listId, String zoneUuid, Integer squadId) {
        return measureZoneResultMapper.getByProjIdListIdZoneUuidSquadId(projectId, listId, zoneUuid, squadId);
    }

    @Override
    public MeasureZoneResult getByUuid(Integer projId, String uuid) {
        return measureZoneResultMapper.getByUuid(projId,uuid);
    }

    @Override
    public int insertObjectNoAffectedErr(MeasureZoneResult zoneResult) {
        return measureZoneResultMapper.insertSelective(zoneResult);
    }

    @Override
    public void delete(Integer id) {
        Example example = new Example(MeasureZoneResult.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo(LISTID,id);

        MeasureZoneResult measureZoneResult=new MeasureZoneResult();
        measureZoneResult.setListId(id);
        measureZoneResult.setDeleteAt(new Date());
        measureZoneResult.setUpdateAt(new Date());
        measureZoneResultMapper.updateByExampleSelective(measureZoneResult,example);
    }

    @Override
    public List<MeasureZoneResult> selectByExample(Example example) {
        return measureZoneResultMapper.selectByExample(example);
    }

    @Override
    public void delByUuidList(Map<String, Object> map) {
        measureZoneResultMapper.delByUuidList(map);
    }

    @Override
    public void delBySquadIdUuid(Map<String, Object> map) {
        measureZoneResultMapper.delBySquadIdUuid(map);
    }

    @Override
    public List<MeasureZoneResult> SearchZoneResultByProjIdZoneUuid(Integer ProjectId, String uuid) {
        return measureZoneResultMapper.SearchZoneResultByProjIdZoneUuid(ProjectId,uuid);
    }

    @Override
    public List<MeasureZoneResult> searchByListId(Integer projId, Integer listId) {
        Example example =new Example(MeasureZoneResult.class);
        example.createCriteria().andEqualTo("projectId",projId).andEqualTo(LISTID,listId);
        return measureZoneResultMapper.selectByExample(example);
    }

    @Override
    public List<MeasureZoneResult> searchByProjIdListId(Integer projId, Integer id) {
        Example example =new Example(MeasureZoneResult.class);
        example.createCriteria().andEqualTo("projectId",projId).andEqualTo(LISTID,id);
        return measureZoneResultMapper.selectByExample(example);
    }
}
