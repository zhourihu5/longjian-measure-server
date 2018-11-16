package com.longfor.longjian.measure.po.zhijian2_stat;

import javax.persistence.*;

@Table(name = "measure_zone_status_count_20180701")
public class MeasureZoneStatusCount {
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

    @Column(name = "total_zone_count")
    private Integer totalZoneCount;

    @Column(name = "finished_zone_count")
    private Integer finishedZoneCount;

    @Column(name = "issue_zone_count")
    private Integer issueZoneCount;

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
}