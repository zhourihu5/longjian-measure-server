package com.longfor.longjian.measure.po.zhijian2_stat;

import javax.persistence.*;

@Table(name = "measure_issue_proj_stat_partner")
public class MeasureIssueProjStatPartner {
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

    @Column(name = "category_path_and_key")
    private String categoryPathAndKey;

    @Column(name = "project_company_id")
    private Integer projectCompanyId;

    @Column(name = "team_group_company_id")
    private Integer teamGroupCompanyId;

    @Column(name = "company_role_id")
    private Integer companyRoleId;

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
     * @return project_company_id
     */
    public Integer getProjectCompanyId() {
        return projectCompanyId;
    }

    /**
     * @param projectCompanyId
     */
    public void setProjectCompanyId(Integer projectCompanyId) {
        this.projectCompanyId = projectCompanyId;
    }

    /**
     * @return team_group_company_id
     */
    public Integer getTeamGroupCompanyId() {
        return teamGroupCompanyId;
    }

    /**
     * @param teamGroupCompanyId
     */
    public void setTeamGroupCompanyId(Integer teamGroupCompanyId) {
        this.teamGroupCompanyId = teamGroupCompanyId;
    }

    /**
     * @return company_role_id
     */
    public Integer getCompanyRoleId() {
        return companyRoleId;
    }

    /**
     * @param companyRoleId
     */
    public void setCompanyRoleId(Integer companyRoleId) {
        this.companyRoleId = companyRoleId;
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