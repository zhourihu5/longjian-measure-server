package com.longfor.longjian.measure.po.zhijian2_stat;

import javax.persistence.*;

@Table(name = "measure_zone_status_count_20181101")
public class MeasureZoneStatusCount20181101 {
    @Id
    private Integer id;

    /**
     * 项目ID
     */
    @Column(name = "project_id")
    private Integer projectId;

    /**
     * 标段ID
     */
    @Column(name = "section_id")
    private Integer sectionId;

    /**
     * 楼栋ID
     */
    @Column(name = "building_id")
    private Integer buildingId;

    /**
     * 楼层ID
     */
    @Column(name = "floor_id")
    private Integer floorId;

    /**
     * 检查项路径
     */
    @Column(name = "category_path_and_key")
    private String categoryPathAndKey;

    /**
     * 任务ID
     */
    @Column(name = "list_id")
    private Integer listId;

    /**
     * 测量小组ID
     */
    @Column(name = "squad_id")
    private Integer squadId;

    /**
     * 检查项key
     */
    @Column(name = "category_key")
    private String categoryKey;

    /**
     * 测区总数
     */
    @Column(name = "total_zone_count")
    private Integer totalZoneCount;

    /**
     * 已完成测区数
     */
    @Column(name = "finished_zone_count")
    private Integer finishedZoneCount;

    /**
     * 有问题的测区数
     */
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
     * 获取项目ID
     *
     * @return project_id - 项目ID
     */
    public Integer getProjectId() {
        return projectId;
    }

    /**
     * 设置项目ID
     *
     * @param projectId 项目ID
     */
    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    /**
     * 获取标段ID
     *
     * @return section_id - 标段ID
     */
    public Integer getSectionId() {
        return sectionId;
    }

    /**
     * 设置标段ID
     *
     * @param sectionId 标段ID
     */
    public void setSectionId(Integer sectionId) {
        this.sectionId = sectionId;
    }

    /**
     * 获取楼栋ID
     *
     * @return building_id - 楼栋ID
     */
    public Integer getBuildingId() {
        return buildingId;
    }

    /**
     * 设置楼栋ID
     *
     * @param buildingId 楼栋ID
     */
    public void setBuildingId(Integer buildingId) {
        this.buildingId = buildingId;
    }

    /**
     * 获取楼层ID
     *
     * @return floor_id - 楼层ID
     */
    public Integer getFloorId() {
        return floorId;
    }

    /**
     * 设置楼层ID
     *
     * @param floorId 楼层ID
     */
    public void setFloorId(Integer floorId) {
        this.floorId = floorId;
    }

    /**
     * 获取检查项路径
     *
     * @return category_path_and_key - 检查项路径
     */
    public String getCategoryPathAndKey() {
        return categoryPathAndKey;
    }

    /**
     * 设置检查项路径
     *
     * @param categoryPathAndKey 检查项路径
     */
    public void setCategoryPathAndKey(String categoryPathAndKey) {
        this.categoryPathAndKey = categoryPathAndKey == null ? null : categoryPathAndKey.trim();
    }

    /**
     * 获取任务ID
     *
     * @return list_id - 任务ID
     */
    public Integer getListId() {
        return listId;
    }

    /**
     * 设置任务ID
     *
     * @param listId 任务ID
     */
    public void setListId(Integer listId) {
        this.listId = listId;
    }

    /**
     * 获取测量小组ID
     *
     * @return squad_id - 测量小组ID
     */
    public Integer getSquadId() {
        return squadId;
    }

    /**
     * 设置测量小组ID
     *
     * @param squadId 测量小组ID
     */
    public void setSquadId(Integer squadId) {
        this.squadId = squadId;
    }

    /**
     * 获取检查项key
     *
     * @return category_key - 检查项key
     */
    public String getCategoryKey() {
        return categoryKey;
    }

    /**
     * 设置检查项key
     *
     * @param categoryKey 检查项key
     */
    public void setCategoryKey(String categoryKey) {
        this.categoryKey = categoryKey == null ? null : categoryKey.trim();
    }

    /**
     * 获取测区总数
     *
     * @return total_zone_count - 测区总数
     */
    public Integer getTotalZoneCount() {
        return totalZoneCount;
    }

    /**
     * 设置测区总数
     *
     * @param totalZoneCount 测区总数
     */
    public void setTotalZoneCount(Integer totalZoneCount) {
        this.totalZoneCount = totalZoneCount;
    }

    /**
     * 获取已完成测区数
     *
     * @return finished_zone_count - 已完成测区数
     */
    public Integer getFinishedZoneCount() {
        return finishedZoneCount;
    }

    /**
     * 设置已完成测区数
     *
     * @param finishedZoneCount 已完成测区数
     */
    public void setFinishedZoneCount(Integer finishedZoneCount) {
        this.finishedZoneCount = finishedZoneCount;
    }

    /**
     * 获取有问题的测区数
     *
     * @return issue_zone_count - 有问题的测区数
     */
    public Integer getIssueZoneCount() {
        return issueZoneCount;
    }

    /**
     * 设置有问题的测区数
     *
     * @param issueZoneCount 有问题的测区数
     */
    public void setIssueZoneCount(Integer issueZoneCount) {
        this.issueZoneCount = issueZoneCount;
    }
}