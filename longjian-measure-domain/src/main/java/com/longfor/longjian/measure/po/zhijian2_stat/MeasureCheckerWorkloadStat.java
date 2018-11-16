package com.longfor.longjian.measure.po.zhijian2_stat;

import javax.persistence.*;

@Table(name = "measure_checker_workload_stat")
public class MeasureCheckerWorkloadStat {
    @Id
    private Integer id;

    @Column(name = "tf_type")
    private Integer tfType;

    @Column(name = "tf_year")
    private Integer tfYear;

    @Column(name = "tf_idx")
    private Integer tfIdx;

    @Column(name = "proj_id")
    private Integer projId;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "list_id")
    private Integer listId;

    @Column(name = "category_key")
    private String categoryKey;

    @Column(name = "category_path_and_key")
    private String categoryPathAndKey;

    @Column(name = "checked_count")
    private Integer checkedCount;

    @Column(name = "issue_created_count")
    private Integer issueCreatedCount;

    @Column(name = "issue_assigned_count")
    private Integer issueAssignedCount;

    @Column(name = "issue_checked_count")
    private Integer issueCheckedCount;

    @Column(name = "create_at")
    private Double createAt;

    @Column(name = "update_at")
    private Double updateAt;

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
     * @return tf_type
     */
    public Integer getTfType() {
        return tfType;
    }

    /**
     * @param tfType
     */
    public void setTfType(Integer tfType) {
        this.tfType = tfType;
    }

    /**
     * @return tf_year
     */
    public Integer getTfYear() {
        return tfYear;
    }

    /**
     * @param tfYear
     */
    public void setTfYear(Integer tfYear) {
        this.tfYear = tfYear;
    }

    /**
     * @return tf_idx
     */
    public Integer getTfIdx() {
        return tfIdx;
    }

    /**
     * @param tfIdx
     */
    public void setTfIdx(Integer tfIdx) {
        this.tfIdx = tfIdx;
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
     * @return user_id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
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
     * @return checked_count
     */
    public Integer getCheckedCount() {
        return checkedCount;
    }

    /**
     * @param checkedCount
     */
    public void setCheckedCount(Integer checkedCount) {
        this.checkedCount = checkedCount;
    }

    /**
     * @return issue_created_count
     */
    public Integer getIssueCreatedCount() {
        return issueCreatedCount;
    }

    /**
     * @param issueCreatedCount
     */
    public void setIssueCreatedCount(Integer issueCreatedCount) {
        this.issueCreatedCount = issueCreatedCount;
    }

    /**
     * @return issue_assigned_count
     */
    public Integer getIssueAssignedCount() {
        return issueAssignedCount;
    }

    /**
     * @param issueAssignedCount
     */
    public void setIssueAssignedCount(Integer issueAssignedCount) {
        this.issueAssignedCount = issueAssignedCount;
    }

    /**
     * @return issue_checked_count
     */
    public Integer getIssueCheckedCount() {
        return issueCheckedCount;
    }

    /**
     * @param issueCheckedCount
     */
    public void setIssueCheckedCount(Integer issueCheckedCount) {
        this.issueCheckedCount = issueCheckedCount;
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
     * @return update_at
     */
    public Double getUpdateAt() {
        return updateAt;
    }

    /**
     * @param updateAt
     */
    public void setUpdateAt(Double updateAt) {
        this.updateAt = updateAt;
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