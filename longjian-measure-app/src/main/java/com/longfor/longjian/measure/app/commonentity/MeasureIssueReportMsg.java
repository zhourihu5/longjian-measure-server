package com.longfor.longjian.measure.app.commonentity;

import com.longfor.longjian.measure.app.vo.AppendVo;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@SuppressWarnings("squid:S1068")
public class MeasureIssueReportMsg {

    @Data
    class MeasureIssue{
        /**
         * Uuid               string `json:"uuid"`
         * 	ProjId             int    `json:"proj_id"`
         * 	ListId             int    `json:"list_id"`
         * 	CheckerId          int    `json:"checker_id"`
         * 	RepairerId         int    `json:"repairer_id"`
         * 	AreaId             int    `json:"area_id"`
         * 	AreaPathAndId      string `json:"area_path_and_id"`
         * 	CategoryKey        string `json:"category_key"`
         * 	CategoryPathAndKey string `json:"category_path_and_key"`
         * 	SenderId           int    `json:"sender_id"`
         * 	Timestamp          int64  `json:"timestamp"`
         */
        private String uuid;
        private Integer proj_id;
        private Integer list_id;
        private Integer checker_id;
        private Integer repairer_id;
        private Integer area_id;
        private String area_path_and_id;
        private String category_key;
        private String category_path_and_key;
        private Integer sender_id;
        private Long timestamp;


    }

    private List<MeasureIssue> created_issues;
    private List<MeasureIssue> assigned_issues;
    private List<MeasureIssue> reformed_issues;
    private List<MeasureIssue> checked_issues;

    public MeasureIssueReportMsg(){
        created_issues = new ArrayList<>();
        assigned_issues = new ArrayList<>();
        reformed_issues = new ArrayList<>();
        checked_issues = new ArrayList<>();
    }

    public void appendCreated(AppendVo appendVo) {
        MeasureIssue measureIssue = createMeasureIssue(appendVo);
        created_issues.add(measureIssue);
    }

    public void appendAssigned(AppendVo appendVo) {
        MeasureIssue measureIssue = createMeasureIssue(appendVo);
        assigned_issues.add(measureIssue);
    }

    public void appendReformed(AppendVo appendVo) {
        MeasureIssue measureIssue = createMeasureIssue(appendVo);
        reformed_issues.add(measureIssue);
    }

    public void appendChecked(AppendVo appendVo) {
        MeasureIssue measureIssue = createMeasureIssue(appendVo);
        checked_issues.add(measureIssue);
    }

    private MeasureIssue createMeasureIssue(AppendVo appendVo) {
        MeasureIssue measureIssue = new MeasureIssue();
        measureIssue.setUuid(appendVo.getUuid());
        measureIssue.setProj_id(appendVo.getProjId());
        measureIssue.setList_id(appendVo.getListId());
        measureIssue.setChecker_id(appendVo.getCheckerId());
        measureIssue.setRepairer_id(appendVo.getRepairerId());
        measureIssue.setArea_id(appendVo.getAreaId());
        measureIssue.setArea_path_and_id(appendVo.getAreaPathAndId());
        measureIssue.setCategory_key(appendVo.getCategoryKey());
        measureIssue.setCategory_path_and_key(appendVo.getCategoryPathAndKey());
        measureIssue.setSender_id(appendVo.getSenderId());
        measureIssue.setTimestamp(appendVo.getTimeAt().getTime());
        return measureIssue;
    }
}
