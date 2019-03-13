package com.longfor.longjian.measure.po.zhijian2_stat;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "measure_issue_status_count_20181101")
public class MeasureIssueStatusCount20181101 {
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
     * 检查项
     */
    @Column(name = "category_key")
    private String categoryKey;

    /**
     * 超期待指派
     */
    @Column(name = "overdue_to_assign")
    private Integer overdueToAssign;

    /**
     * 超期待整改
     */
    @Column(name = "overdue_to_reform")
    private Integer overdueToReform;

    /**
     * 超期待验收
     */
    @Column(name = "overdue_to_check")
    private Integer overdueToCheck;

    /**
     * 超期已验收
     */
    @Column(name = "overdue_checked")
    private Integer overdueChecked;

    /**
     * 未超期待指派
     */
    @Column(name = "intime_to_assign")
    private Integer intimeToAssign;

    /**
     * 未超期待整改
     */
    @Column(name = "intime_to_reform")
    private Integer intimeToReform;

    /**
     * 未超期待验收
     */
    @Column(name = "intime_to_check")
    private Integer intimeToCheck;

    /**
     * 未超期已验收
     */
    @Column(name = "intime_checked")
    private Integer intimeChecked;

    /**
     * 不限时待指派
     */
    @Column(name = "notset_to_assign")
    private Integer notsetToAssign;

    /**
     * 不限时待整改
     */
    @Column(name = "notset_to_reform")
    private Integer notsetToReform;

    /**
     * 不限时待验收
     */
    @Column(name = "notset_to_check")
    private Integer notsetToCheck;

    /**
     * 不限时已验收
     */
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
     * 获取检查项
     *
     * @return category_key - 检查项
     */
    public String getCategoryKey() {
        return categoryKey;
    }

    /**
     * 设置检查项
     *
     * @param categoryKey 检查项
     */
    public void setCategoryKey(String categoryKey) {
        this.categoryKey = categoryKey == null ? null : categoryKey.trim();
    }

    /**
     * 获取超期待指派
     *
     * @return overdue_to_assign - 超期待指派
     */
    public Integer getOverdueToAssign() {
        return overdueToAssign;
    }

    /**
     * 设置超期待指派
     *
     * @param overdueToAssign 超期待指派
     */
    public void setOverdueToAssign(Integer overdueToAssign) {
        this.overdueToAssign = overdueToAssign;
    }

    /**
     * 获取超期待整改
     *
     * @return overdue_to_reform - 超期待整改
     */
    public Integer getOverdueToReform() {
        return overdueToReform;
    }

    /**
     * 设置超期待整改
     *
     * @param overdueToReform 超期待整改
     */
    public void setOverdueToReform(Integer overdueToReform) {
        this.overdueToReform = overdueToReform;
    }

    /**
     * 获取超期待验收
     *
     * @return overdue_to_check - 超期待验收
     */
    public Integer getOverdueToCheck() {
        return overdueToCheck;
    }

    /**
     * 设置超期待验收
     *
     * @param overdueToCheck 超期待验收
     */
    public void setOverdueToCheck(Integer overdueToCheck) {
        this.overdueToCheck = overdueToCheck;
    }

    /**
     * 获取超期已验收
     *
     * @return overdue_checked - 超期已验收
     */
    public Integer getOverdueChecked() {
        return overdueChecked;
    }

    /**
     * 设置超期已验收
     *
     * @param overdueChecked 超期已验收
     */
    public void setOverdueChecked(Integer overdueChecked) {
        this.overdueChecked = overdueChecked;
    }

    /**
     * 获取未超期待指派
     *
     * @return intime_to_assign - 未超期待指派
     */
    public Integer getIntimeToAssign() {
        return intimeToAssign;
    }

    /**
     * 设置未超期待指派
     *
     * @param intimeToAssign 未超期待指派
     */
    public void setIntimeToAssign(Integer intimeToAssign) {
        this.intimeToAssign = intimeToAssign;
    }

    /**
     * 获取未超期待整改
     *
     * @return intime_to_reform - 未超期待整改
     */
    public Integer getIntimeToReform() {
        return intimeToReform;
    }

    /**
     * 设置未超期待整改
     *
     * @param intimeToReform 未超期待整改
     */
    public void setIntimeToReform(Integer intimeToReform) {
        this.intimeToReform = intimeToReform;
    }

    /**
     * 获取未超期待验收
     *
     * @return intime_to_check - 未超期待验收
     */
    public Integer getIntimeToCheck() {
        return intimeToCheck;
    }

    /**
     * 设置未超期待验收
     *
     * @param intimeToCheck 未超期待验收
     */
    public void setIntimeToCheck(Integer intimeToCheck) {
        this.intimeToCheck = intimeToCheck;
    }

    /**
     * 获取未超期已验收
     *
     * @return intime_checked - 未超期已验收
     */
    public Integer getIntimeChecked() {
        return intimeChecked;
    }

    /**
     * 设置未超期已验收
     *
     * @param intimeChecked 未超期已验收
     */
    public void setIntimeChecked(Integer intimeChecked) {
        this.intimeChecked = intimeChecked;
    }

    /**
     * 获取不限时待指派
     *
     * @return notset_to_assign - 不限时待指派
     */
    public Integer getNotsetToAssign() {
        return notsetToAssign;
    }

    /**
     * 设置不限时待指派
     *
     * @param notsetToAssign 不限时待指派
     */
    public void setNotsetToAssign(Integer notsetToAssign) {
        this.notsetToAssign = notsetToAssign;
    }

    /**
     * 获取不限时待整改
     *
     * @return notset_to_reform - 不限时待整改
     */
    public Integer getNotsetToReform() {
        return notsetToReform;
    }

    /**
     * 设置不限时待整改
     *
     * @param notsetToReform 不限时待整改
     */
    public void setNotsetToReform(Integer notsetToReform) {
        this.notsetToReform = notsetToReform;
    }

    /**
     * 获取不限时待验收
     *
     * @return notset_to_check - 不限时待验收
     */
    public Integer getNotsetToCheck() {
        return notsetToCheck;
    }

    /**
     * 设置不限时待验收
     *
     * @param notsetToCheck 不限时待验收
     */
    public void setNotsetToCheck(Integer notsetToCheck) {
        this.notsetToCheck = notsetToCheck;
    }

    /**
     * 获取不限时已验收
     *
     * @return notset_checked - 不限时已验收
     */
    public Integer getNotsetChecked() {
        return notsetChecked;
    }

    /**
     * 设置不限时已验收
     *
     * @param notsetChecked 不限时已验收
     */
    public void setNotsetChecked(Integer notsetChecked) {
        this.notsetChecked = notsetChecked;
    }
}