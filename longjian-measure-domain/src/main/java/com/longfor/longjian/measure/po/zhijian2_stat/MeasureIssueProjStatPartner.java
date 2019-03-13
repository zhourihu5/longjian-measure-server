package com.longfor.longjian.measure.po.zhijian2_stat;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "measure_issue_proj_stat_partner")
public class MeasureIssueProjStatPartner {
    @Id
    private Integer id;

    /**
     * 日期
     */
    private String day;

    /**
     * 集团ID
     */
    @Column(name = "group_id")
    private Integer groupId;

    /**
     * 公司ID
     */
    @Column(name = "team_id")
    private Integer teamId;

    /**
     * 项目ID
     */
    @Column(name = "proj_id")
    private Integer projId;

    /**
     * 标段ID
     */
    @Column(name = "section_id")
    private Integer sectionId;

    /**
     * 检查项路径
     */
    @Column(name = "category_path_and_key")
    private String categoryPathAndKey;

    /**
     * 项目伙伴ID
     */
    @Column(name = "project_company_id")
    private Integer projectCompanyId;

    /**
     * 合作伙伴ID
     */
    @Column(name = "team_group_company_id")
    private Integer teamGroupCompanyId;

    /**
     * 公司角色ID
     */
    @Column(name = "company_role_id")
    private Integer companyRoleId;

    /**
     * 检查项key
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
     * 创建时间
     */
    @Column(name = "create_at")
    private Double createAt;

    /**
     * 删除时间
     */
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
     * 获取日期
     *
     * @return day - 日期
     */
    public String getDay() {
        return day;
    }

    /**
     * 设置日期
     *
     * @param day 日期
     */
    public void setDay(String day) {
        this.day = day == null ? null : day.trim();
    }

    /**
     * 获取集团ID
     *
     * @return group_id - 集团ID
     */
    public Integer getGroupId() {
        return groupId;
    }

    /**
     * 设置集团ID
     *
     * @param groupId 集团ID
     */
    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    /**
     * 获取公司ID
     *
     * @return team_id - 公司ID
     */
    public Integer getTeamId() {
        return teamId;
    }

    /**
     * 设置公司ID
     *
     * @param teamId 公司ID
     */
    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    /**
     * 获取项目ID
     *
     * @return proj_id - 项目ID
     */
    public Integer getProjId() {
        return projId;
    }

    /**
     * 设置项目ID
     *
     * @param projId 项目ID
     */
    public void setProjId(Integer projId) {
        this.projId = projId;
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
     * 获取项目伙伴ID
     *
     * @return project_company_id - 项目伙伴ID
     */
    public Integer getProjectCompanyId() {
        return projectCompanyId;
    }

    /**
     * 设置项目伙伴ID
     *
     * @param projectCompanyId 项目伙伴ID
     */
    public void setProjectCompanyId(Integer projectCompanyId) {
        this.projectCompanyId = projectCompanyId;
    }

    /**
     * 获取合作伙伴ID
     *
     * @return team_group_company_id - 合作伙伴ID
     */
    public Integer getTeamGroupCompanyId() {
        return teamGroupCompanyId;
    }

    /**
     * 设置合作伙伴ID
     *
     * @param teamGroupCompanyId 合作伙伴ID
     */
    public void setTeamGroupCompanyId(Integer teamGroupCompanyId) {
        this.teamGroupCompanyId = teamGroupCompanyId;
    }

    /**
     * 获取公司角色ID
     *
     * @return company_role_id - 公司角色ID
     */
    public Integer getCompanyRoleId() {
        return companyRoleId;
    }

    /**
     * 设置公司角色ID
     *
     * @param companyRoleId 公司角色ID
     */
    public void setCompanyRoleId(Integer companyRoleId) {
        this.companyRoleId = companyRoleId;
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

    /**
     * 获取创建时间
     *
     * @return create_at - 创建时间
     */
    public Double getCreateAt() {
        return createAt;
    }

    /**
     * 设置创建时间
     *
     * @param createAt 创建时间
     */
    public void setCreateAt(Double createAt) {
        this.createAt = createAt;
    }

    /**
     * 获取删除时间
     *
     * @return delete_at - 删除时间
     */
    public Double getDeleteAt() {
        return deleteAt;
    }

    /**
     * 设置删除时间
     *
     * @param deleteAt 删除时间
     */
    public void setDeleteAt(Double deleteAt) {
        this.deleteAt = deleteAt;
    }
}