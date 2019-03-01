package com.longfor.longjian.measure.app.commonentity;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
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

    public void appendCreated(String uuid, Integer projId, Integer listId, Integer checkerId, Integer repairerId, Integer areaId,
                              String areaPathAndId, String categoryKey, String categoryPathAndKey, Integer senderId,
                              Date timeAt) {
        MeasureIssue measureIssue = createMeasureIssue(uuid, projId, listId, checkerId, repairerId, areaId,
                areaPathAndId, categoryKey, categoryPathAndKey, senderId,
                timeAt);
        created_issues.add(measureIssue);
    }

    public void appendAssigned(String uuid, Integer projId, Integer listId, Integer checkerId, Integer repairerId, Integer areaId,
                               String areaPathAndId, String categoryKey, String categoryPathAndKey, Integer senderId,
                               Date timeAt) {
        MeasureIssue measureIssue = createMeasureIssue(uuid, projId, listId, checkerId, repairerId, areaId,
                areaPathAndId, categoryKey, categoryPathAndKey, senderId,
                timeAt);
        assigned_issues.add(measureIssue);
    }

    public void appendReformed(String uuid, Integer projId, Integer listId, Integer checkerId, Integer repairerId, Integer areaId,
                               String areaPathAndId, String categoryKey, String categoryPathAndKey, Integer senderId,
                               Date timeAt) {
        MeasureIssue measureIssue = createMeasureIssue(uuid, projId, listId, checkerId, repairerId, areaId,
                areaPathAndId, categoryKey, categoryPathAndKey, senderId,
                timeAt);
        reformed_issues.add(measureIssue);
    }

    public void appendChecked(String uuid, Integer projId, Integer listId, Integer checkerId, Integer repairerId, Integer areaId,
                              String areaPathAndId, String categoryKey, String categoryPathAndKey, Integer senderId,
                              Date timeAt) {
        MeasureIssue measureIssue = createMeasureIssue(uuid, projId, listId, checkerId, repairerId, areaId,
                areaPathAndId, categoryKey, categoryPathAndKey, senderId,
                timeAt);
        checked_issues.add(measureIssue);
    }

    private MeasureIssue createMeasureIssue(String uuid, Integer projId, Integer listId, Integer checkerId, Integer repairerId, Integer areaId,
                                              String areaPathAndId, String categoryKey, String categoryPathAndKey, Integer senderId,
                                              Date timeAt) {
        MeasureIssue measureIssue = new MeasureIssue();
        measureIssue.setUuid(uuid);
        measureIssue.setProj_id(projId);
        measureIssue.setList_id(listId);
        measureIssue.setChecker_id(checkerId);
        measureIssue.setRepairer_id(repairerId);
        measureIssue.setArea_id(areaId);
        measureIssue.setArea_path_and_id(areaPathAndId);
        measureIssue.setCategory_key(categoryKey);
        measureIssue.setCategory_path_and_key(categoryPathAndKey);
        measureIssue.setSender_id(senderId);
        measureIssue.setTimestamp(timeAt.getTime());
        return measureIssue;
    }
}
