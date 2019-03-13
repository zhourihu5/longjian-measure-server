package com.longfor.longjian.measure.po.zhijian2_stat;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "measure_zone_proj_stat_partner_user")
public class MeasureZoneProjStatPartnerUser {
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
     * 检查项key
     */
    @Column(name = "category_key")
    private String categoryKey;

    /**
     * 检查项路径
     */
    @Column(name = "category_path_and_key")
    private String categoryPathAndKey;

    /**
     * 合作伙伴ID
     */
    @Column(name = "team_group_company_id")
    private Integer teamGroupCompanyId;

    /**
     * 项目合作伙伴ID
     */
    @Column(name = "project_company_id")
    private Integer projectCompanyId;

    /**
     * 公司角色ID
     */
    @Column(name = "company_role_id")
    private Integer companyRoleId;

    /**
     * 合作伙伴人员ID
     */
    @Column(name = "partner_user_id")
    private Integer partnerUserId;

    /**
     * 用户ID
     */
    @Column(name = "user_id")
    private Integer userId;

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
     * 获取项目合作伙伴ID
     *
     * @return project_company_id - 项目合作伙伴ID
     */
    public Integer getProjectCompanyId() {
        return projectCompanyId;
    }

    /**
     * 设置项目合作伙伴ID
     *
     * @param projectCompanyId 项目合作伙伴ID
     */
    public void setProjectCompanyId(Integer projectCompanyId) {
        this.projectCompanyId = projectCompanyId;
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
     * 获取合作伙伴人员ID
     *
     * @return partner_user_id - 合作伙伴人员ID
     */
    public Integer getPartnerUserId() {
        return partnerUserId;
    }

    /**
     * 设置合作伙伴人员ID
     *
     * @param partnerUserId 合作伙伴人员ID
     */
    public void setPartnerUserId(Integer partnerUserId) {
        this.partnerUserId = partnerUserId;
    }

    /**
     * 获取用户ID
     *
     * @return user_id - 用户ID
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置用户ID
     *
     * @param userId 用户ID
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
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