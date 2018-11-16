package com.longfor.longjian.measure.po.zhijian2_stat;

import javax.persistence.*;

@Table(name = "measure_issue_status_count_20180701")
public class MeasureIssueStatusCount {
    @Id
    private Integer id;

    @Column(name = "project_id")
    private Integer projectId;

    @Column(name = "section_id")
    private Integer sectionId;

    @Column(name = "building_id")
    private Integer buildingId;

    @Column(name = "floor_id")
    private Integer floorId;

    @Column(name = "category_path_and_key")
    private String categoryPathAndKey;

    @Column(name = "list_id")
    private Integer listId;

    @Column(name = "squad_id")
    private Integer squadId;

    @Column(name = "category_key")
    private String categoryKey;

    @Column(name = "overdue_to_assign")
    private Integer overdueToAssign;

    @Column(name = "overdue_to_reform")
    private Integer overdueToReform;

    @Column(name = "overdue_to_check")
    private Integer overdueToCheck;

    @Column(name = "overdue_checked")
    private Integer overdueChecked;

    @Column(name = "intime_to_assign")
    private Integer intimeToAssign;

    @Column(name = "intime_to_reform")
    private Integer intimeToReform;

    @Column(name = "intime_to_check")
    private Integer intimeToCheck;

    @Column(name = "intime_checked")
    private Integer intimeChecked;

    @Column(name = "notset_to_assign")
    private Integer notsetToAssign;

    @Column(name = "notset_to_reform")
    private Integer notsetToReform;

    @Column(name = "notset_to_check")
    private Integer notsetToCheck;

    @Column(name = "notset_checked")
    private Integer notsetChecked;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return project_id
     */
    public Integer getProjectId() {
        return projectId;
    }

    /**
     * @param projectId
     */
    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    /**
     * @return section_id
     */
    public Integer getSectionId() {
        return sectionId;
    }

    /**
     * @param sectionId
     */
    public void setSectionId(Integer sectionId) {
        this.sectionId = sectionId;
    }

    /**
     * @return building_id
     */
    public Integer getBuildingId() {
        return buildingId;
    }

    /**
     * @param buildingId
     */
    public void setBuildingId(Integer buildingId) {
        this.buildingId = buildingId;
    }

    /**
     * @return floor_id
     */
    public Integer getFloorId() {
        return floorId;
    }

    /**
     * @param floorId
     */
    public void setFloorId(Integer floorId) {
        this.floorId = floorId;
    }

    /**
     * @return category_path_and_key
     */
    public String getCategoryPathAndKey() {
        return categoryPathAndKey;
    }

    /**
     * @param categoryPathAndKey
     */
    public void setCategoryPathAndKey(String categoryPathAndKey) {
        this.categoryPathAndKey = categoryPathAndKey == null ? null : categoryPathAndKey.trim();
    }

    /**
     * @return list_id
     */
    public Integer getListId() {
        return listId;
    }

    /**
     * @param listId
     */
    public void setListId(Integer listId) {
        this.listId = listId;
    }

    /**
     * @return squad_id
     */
    public Integer getSquadId() {
        return squadId;
    }

    /**
     * @param squadId
     */
    public void setSquadId(Integer squadId) {
        this.squadId = squadId;
    }

    /**
     * @return category_key
     */
    public String getCategoryKey() {
        return categoryKey;
    }

    /**
     * @param categoryKey
     */
    public void setCategoryKey(String categoryKey) {
        this.categoryKey = categoryKey == null ? null : categoryKey.trim();
    }

    /**
     * @return overdue_to_assign
     */
    public Integer getOverdueToAssign() {
        return overdueToAssign;
    }

    /**
     * @param overdueToAssign
     */
    public void setOverdueToAssign(Integer overdueToAssign) {
        this.overdueToAssign = overdueToAssign;
    }

    /**
     * @return overdue_to_reform
     */
    public Integer getOverdueToReform() {
        return overdueToReform;
    }

    /**
     * @param overdueToReform
     */
    public void setOverdueToReform(Integer overdueToReform) {
        this.overdueToReform = overdueToReform;
    }

    /**
     * @return overdue_to_check
     */
    public Integer getOverdueToCheck() {
        return overdueToCheck;
    }

    /**
     * @param overdueToCheck
     */
    public void setOverdueToCheck(Integer overdueToCheck) {
        this.overdueToCheck = overdueToCheck;
    }

    /**
     * @return overdue_checked
     */
    public Integer getOverdueChecked() {
        return overdueChecked;
    }

    /**
     * @param overdueChecked
     */
    public void setOverdueChecked(Integer overdueChecked) {
        this.overdueChecked = overdueChecked;
    }

    /**
     * @return intime_to_assign
     */
    public Integer getIntimeToAssign() {
        return intimeToAssign;
    }

    /**
     * @param intimeToAssign
     */
    public void setIntimeToAssign(Integer intimeToAssign) {
        this.intimeToAssign = intimeToAssign;
    }

    /**
     * @return intime_to_reform
     */
    public Integer getIntimeToReform() {
        return intimeToReform;
    }

    /**
     * @param intimeToReform
     */
    public void setIntimeToReform(Integer intimeToReform) {
        this.intimeToReform = intimeToReform;
    }

    /**
     * @return intime_to_check
     */
    public Integer getIntimeToCheck() {
        return intimeToCheck;
    }

    /**
     * @param intimeToCheck
     */
    public void setIntimeToCheck(Integer intimeToCheck) {
        this.intimeToCheck = intimeToCheck;
    }

    /**
     * @return intime_checked
     */
    public Integer getIntimeChecked() {
        return intimeChecked;
    }

    /**
     * @param intimeChecked
     */
    public void setIntimeChecked(Integer intimeChecked) {
        this.intimeChecked = intimeChecked;
    }

    /**
     * @return notset_to_assign
     */
    public Integer getNotsetToAssign() {
        return notsetToAssign;
    }

    /**
     * @param notsetToAssign
     */
    public void setNotsetToAssign(Integer notsetToAssign) {
        this.notsetToAssign = notsetToAssign;
    }

    /**
     * @return notset_to_reform
     */
    public Integer getNotsetToReform() {
        return notsetToReform;
    }

    /**
     * @param notsetToReform
     */
    public void setNotsetToReform(Integer notsetToReform) {
        this.notsetToReform = notsetToReform;
    }

    /**
     * @return notset_to_check
     */
    public Integer getNotsetToCheck() {
        return notsetToCheck;
    }

    /**
     * @param notsetToCheck
     */
    public void setNotsetToCheck(Integer notsetToCheck) {
        this.notsetToCheck = notsetToCheck;
    }

    /**
     * @return notset_checked
     */
    public Integer getNotsetChecked() {
        return notsetChecked;
    }

    /**
     * @param notsetChecked
     */
    public void setNotsetChecked(Integer notsetChecked) {
        this.notsetChecked = notsetChecked;
    }
}