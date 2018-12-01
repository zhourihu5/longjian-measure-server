package com.longfor.longjian.measure.domain.externalService.impl;

import com.longfor.longjian.measure.dao.zhijian2.MeasureListIssueMapper;
import com.longfor.longjian.measure.dao.zhijian2.MeasureZoneMapper;
import com.longfor.longjian.measure.domain.externalService.IMeasureListIssueService;
import com.longfor.longjian.measure.po.zhijian2.MeasureListIssue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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
}
