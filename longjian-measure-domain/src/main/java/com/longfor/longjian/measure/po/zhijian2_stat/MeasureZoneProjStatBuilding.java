package com.longfor.longjian.measure.po.zhijian2_stat;

import javax.persistence.*;

@Table(name = "measure_zone_proj_stat_building")
public class MeasureZoneProjStatBuilding {
    @Id
    private Integer id;

    private String day;

    @Column(name = "group_id")
    private Integer groupId;

    @Column(name = "team_id")
    private Integer teamId;

    @Column(name = "proj_id")
    private Integer projId;

    @Column(name = "section_id")
    private Integer sectionId;

    @Column(name = "building_id")
    private Integer buildingId;

    @Column(name = "category_path_and_key")
    private String categoryPathAndKey;

    @Column(name = "category_key")
    private String categoryKey;

    @Column(name = "total_zone_count")
    private Integer totalZoneCount;

    @Column(name = "finished_zone_count")
    private Integer finishedZoneCount;

    @Column(name = "issue_zone_count")
    private Integer issueZoneCount;

    @Column(name = "create_at")
    private Double createAt;

    @Column(name = "delete_at")
    private Double deleteAt;

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
     * @return day
     */
    public String getDay() {
        return day;
    }

    /**
     * @param day
     */
    public void setDay(String day) {
        this.day = day == null ? null : day.trim();
    }

    /**
     * @return group_id
     */
    public Integer getGroupId() {
        return groupId;
    }

    /**
     * @param groupId
     */
    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    /**
     * @return team_id
     */
    public Integer getTeamId() {
        return teamId;
    }

    /**
     * @param teamId
     */
    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    /**
     * @return proj_id
     */
    public Integer getProjId() {
        return projId;
    }

    /**
     * @param projId
     */
    public void setProjId(Integer projId) {
        this.projId = projId;
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
     * @return total_zone_count
     */
    public Integer getTotalZoneCount() {
        return totalZoneCount;
    }

    /**
     * @param totalZoneCount
     */
    public void setTotalZoneCount(Integer totalZoneCount) {
        this.totalZoneCount = totalZoneCount;
    }

    /**
     * @return finished_zone_count
     */
    public Integer getFinishedZoneCount() {
        return finishedZoneCount;
    }

    /**
     * @param finishedZoneCount
     */
    public void setFinishedZoneCount(Integer finishedZoneCount) {
        this.finishedZoneCount = finishedZoneCount;
    }

    /**
     * @return issue_zone_count
     */
    public Integer getIssueZoneCount() {
        return issueZoneCount;
    }

    /**
     * @param issueZoneCount
     */
    public void setIssueZoneCount(Integer issueZoneCount) {
        this.issueZoneCount = issueZoneCount;
    }

    /**
     * @return create_at
     */
    public Double getCreateAt() {
        return createAt;
    }

    /**
     * @param createAt
     */
    public void setCreateAt(Double createAt) {
        this.createAt = createAt;
    }

    /**
     * @return delete_at
     */
    public Double getDeleteAt() {
        return deleteAt;
    }

    /**
     * @param deleteAt
     */
    public void setDeleteAt(Double deleteAt) {
        this.deleteAt = deleteAt;
    }
}