package com.longfor.longjian.measure.domain.externalService.impl;

import com.longfor.longjian.measure.dao.zhijian2.MeasureListIssueMapper;
import com.longfor.longjian.measure.dao.zhijian2.MeasureZoneMapper;
import com.longfor.longjian.measure.domain.externalService.IMeasureListIssueService;
import com.longfor.longjian.measure.po.zhijian2.MeasureListIssue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MeasureListIssueServiceImpl implements IMeasureListIssueService {
    @Autowired
    private MeasureListIssueMapper measureListIssueMapper;
    @Autowired
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
    public List<Map<String, Object>> searchMeasureListIssueTrend(Integer project_id, Integer measure_list_id, String startTime, String endTime, String UNCLOSECODE) {
        List<Map<String, Object>> result = new ArrayList<>();
        List<Map<String, Object>> newCountList = measureListIssueMapper.searchMeasureListIssueTrendNewCountList(project_id,measure_list_id,startTime,endTime,UNCLOSECODE);
        List<Map<String, Object>> trendReformList = measureListIssueMapper.searchMeasureListIssueTrendReformList(project_id,measure_list_id,startTime,endTime,UNCLOSECODE);
        newCountList.forEach(newCount -> {
            Map<String, Object> map = new HashMap<>();
            map.put("date",newCount.get("days").toString());
            map.put("new_count",Integer.parseInt(newCount.get("new_count").toString()));
            result.add(map);
        });
        //数据合并
        trendReformList.forEach(trendReform -> {
            boolean flag = true;
            for (Map<String ,Object> r:result
                 ) {
                if (r.get("date").equals(trendReform.get("days").toString())){
                    r.put("reform_count",Integer.parseInt(trendReform.get("reform_count").toString()));
                    flag = false;
                    break;
                }
            }
            if (flag){
                Map<String, Object> map = new HashMap<>();
                map.put("date",trendReform.get("days").toString());
                map.put("reform_count",Integer.parseInt(trendReform.get("reform_count").toString()));
                result.add(map);
            }
        });
        //数据排序 TODO
        //往中间补全信息 TODO
//        Collections.sort(humans, Comparator.comparing(Human::getName));
        return result;
    }
}
