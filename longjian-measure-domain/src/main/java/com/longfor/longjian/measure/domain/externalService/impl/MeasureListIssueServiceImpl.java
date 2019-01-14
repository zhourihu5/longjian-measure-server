package com.longfor.longjian.measure.domain.externalService.impl;

import com.longfor.longjian.measure.dao.zhijian2.MeasureListIssueMapper;
import com.longfor.longjian.measure.dao.zhijian2.MeasureZoneMapper;
import com.longfor.longjian.measure.domain.externalService.IMeasureListIssueService;
import com.longfor.longjian.measure.po.zhijian2.Area;
import com.longfor.longjian.measure.po.zhijian2.CategoryV3;
import com.longfor.longjian.measure.po.zhijian2.MeasureListIssue;
import com.longfor.longjian.measure.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.*;

@Service
@Slf4j
public class MeasureListIssueServiceImpl implements IMeasureListIssueService {
    @Resource
    private MeasureListIssueMapper measureListIssueMapper;
    @Resource
    private MeasureZoneMapper measureZoneMapper;

    @Override
    public Integer countByMeasureListId(String id) {
        MeasureListIssue measureListIssue = new MeasureListIssue();
        measureListIssue.setListId(Integer.parseInt(id));
        return measureListIssueMapper.selectCount(measureListIssue);
    }

    @Override
    public Map<String,Object> getMeasureListIssueBrief(Integer project_id, Integer measure_list_id, String UNCLOSECODE, String REPAIRABLE, String NOREPAIRABLE, String NOTENOASSIGN, String ASSIGNNOREFORM, String REFORMNOCHECK, String CHECKYES) {
        Map<String,Object> map = new HashMap<>();
        int zoneCount = measureListIssueMapper.getZoneCount(project_id,measure_list_id,UNCLOSECODE);
        map.put("zone_count",zoneCount);
        map.put("repairable_count",measureListIssueMapper.getCountByTyp(project_id,measure_list_id,REPAIRABLE));
        map.put("unrepairable_count",measureListIssueMapper.getCountByTyp(project_id,measure_list_id,NOREPAIRABLE));
        map.put("note_no_assign",measureListIssueMapper.getCountByStatus(project_id,measure_list_id,NOTENOASSIGN));
        map.put("assign_no_reform",measureListIssueMapper.getCountByStatus(project_id,measure_list_id,ASSIGNNOREFORM));
        map.put("reform_no_check",measureListIssueMapper.getCountByStatus(project_id,measure_list_id,REFORMNOCHECK));
        map.put("check_yes",measureListIssueMapper.getCountByStatus(project_id,measure_list_id,CHECKYES));
        //查找所有测区
        int count = measureZoneMapper.searchTotalByProjectIdAndMeasureListId(project_id,new int[]{measure_list_id});
        String zonePercentage = String.format("%.2f",(double)zoneCount/count);
        if ("0.00".equals(zonePercentage) || zoneCount == 0 || count == 0){
            map.put("zone_percentage","0");
        }else{
            map.put("zone_percentage",zonePercentage);
        }
        return map;
    }

    @Override
    public List<Map<String, Object>> searchMeasureListIssueTrend(Integer project_id, Integer measure_list_id, String startTime, String endTime, String UNCLOSECODE) throws ParseException {
        List<Map<String, Object>> result = new ArrayList<>();
        List<Map<String, Object>> newCountList = measureListIssueMapper.searchMeasureListIssueTrendNewCountList(project_id,measure_list_id,startTime,endTime,UNCLOSECODE);
        List<Map<String, Object>> trendReformList = measureListIssueMapper.searchMeasureListIssueTrendReformList(project_id,measure_list_id,startTime,endTime,UNCLOSECODE);
        //数据合并，把两个查询出来的时间合并到开始到结束时间，数据是空就记0
        do {
            Map<String, Object> map = new HashMap<>();
            map.put("date", startTime);
            boolean newCountListFlag = false;
            for (Map<String ,Object> newCount:newCountList
                 ) {
                if (startTime.equals(newCount.get("days").toString())) {
                    map.put("new_count", Integer.parseInt(newCount.get("new_count").toString()));
                    newCountListFlag = true;
                    break;
                }
            }
            if (!newCountListFlag){
                map.put("new_count",0);
            }
            boolean trendReformListFlag = false;
            for (Map<String ,Object> trendReform:trendReformList
            ) {
                if (startTime.equals(trendReform.get("days").toString())) {
                    map.put("reform_count",Integer.parseInt(trendReform.get("reform_count").toString()));
                    trendReformListFlag = true;
                    break;
                }
            }
            if (!newCountListFlag){
                map.put("reform_count",0);
            }
            result.add(map);
            startTime = DateUtil.getShortDateStringByStringDate(startTime,1);
        }while (DateUtil.getLongFromShortString(startTime) <= DateUtil.getLongFromShortString(endTime));
//        Collections.sort(humans, Comparator.comparing(Human::getName));
        return result;
    }

    @Override
    public Integer countMeasureListIssueDistributionCategory(Integer project_id, Integer measure_list_id, String UNCLOSECODE) {
        return measureListIssueMapper.countMeasureListIssueDistributionCategory(project_id,measure_list_id,UNCLOSECODE);
    }

    @Override
    public List<MeasureListIssue> searchMeasureListIssueDistributionCategory(Integer project_id, Integer measure_list_id, String unclosecode) {
        return measureListIssueMapper.searchMeasureListIssueDistributionCategory(project_id, measure_list_id, unclosecode);
    }

    @Override
    public List<Map<String, Object>> getMeasureListIssueStatusMapByListIdsAndCategoryKeyAndAreaId(String[] listIds, CategoryV3 categoryV3, Area area, String closedcode) {
        String cateChildPath = categoryV3.getPath() + categoryV3.getKey() + "/";
        String areaChilePath = area.getPath() + area.getId() + "/";
        return measureListIssueMapper.getMeasureListIssueStatusMapByListIdsAndCategoryKeyAndAreaId(listIds,cateChildPath,areaChilePath,closedcode);
    }

    @Override
    public List<MeasureListIssue> searchIssueListByListIdLastIdTimestampGt(Integer list_id, Integer last_id, Long timestamp, Integer start, Integer pageSize) {
        return measureListIssueMapper.searchIssueListByListIdLastIdTimestampGt(list_id,last_id,timestamp,start,pageSize);
    }

    @Override
    public List<MeasureListIssue> searchMeasueListIssueInZoneUuids(Set<String> zoneUuids) {
        return measureListIssueMapper.searchMeasueListIssueInZoneUuids(zoneUuids);
    }

    @Override
    public void insertMeasureListIssueObject(MeasureListIssue issue) {
        try{
            boolean has = measureListIssueMapper.getByUuidUnscoped(issue.getUuid()) == null ? false:true;
            if (has) {
                //如果存在
                //如果是已被删除的，应该舍弃掉
                //如果是未被删除的，应该快速失败，而不执行update
                Exception err =  new Exception("测区已存在爆点整改信息");
                log.warn(err.getMessage());
                throw err;
            }
            measureListIssueMapper.insert(issue);
        }catch (Exception e){
            log.warn(e.getMessage());
        }
    }

    @Override
    public void updateFullNoAffectedErr(MeasureListIssue issue) {
        measureListIssueMapper.updateByPrimaryKey(issue);
    }

    @Override
    public Integer searchMeasureListIssueByCloseStatusAndStatusAndCategoryPathAndKeyLike(Integer project_id, Integer measure_list_id, String unclosecode, String categoryPathAndKey, Integer status) {
        return measureListIssueMapper.searchMeasureListIssueByCloseStatusAndStatusAndCategoryPathAndKeyLike(project_id, measure_list_id, unclosecode, categoryPathAndKey,status);
    }

    @Override
    public MeasureListIssue GetIssueByProjectIdAndUuid(Integer projectId, String uuid) {
        return measureListIssueMapper.GetIssueByProjectIdAndUuid(projectId, uuid);
    }

    @Override
    public void deletedByUpdateDeletedAt(Integer project_id, String uuid) {
        MeasureListIssue measureListIssue = new MeasureListIssue();
        measureListIssue.setDeleteAt(new Date());

        Example example = new Example(MeasureListIssue.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("projectId",project_id);
        criteria.andEqualTo("uuid",uuid);

        measureListIssueMapper.updateByExampleSelective(measureListIssue,example);
    }
}
