package com.longfor.longjian.measure.po.zhijian2_stat;

import javax.persistence.*;

@Table(name = "measure_result_pass_stat")
public class MeasureResultPassStat {
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

    @Column(name = "list_id")
    private Integer listId;

    @Column(name = "sender_id")
    private Integer senderId;

    @Column(name = "area_id")
    private Integer areaId;

    @Column(name = "area_path_and_id")
    private String areaPathAndId;

    @Column(name = "category_key")
    private String categoryKey;

    @Column(name = "category_path_and_key")
    private String categoryPathAndKey;

    @Column(name = "ok_total")
    private Integer okTotal;

    private Integer total;

    @Column(name = "result_passed_count")
    private Integer resultPassedCount;

    @Column(name = "result_count")
    private Integer resultCount;

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
     * @return sender_id
     */
    public Integer getSenderId() {
        return senderId;
    }

    /**
     * @param senderId
     */
    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    /**
     * @return area_id
     */
    public Integer getAreaId() {
        return areaId;
    }

    /**
     * @param areaId
     */
    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    /**
     * @return area_path_and_id
     */
    public String getAreaPathAndId() {
        return areaPathAndId;
    }

    /**
     * @param areaPathAndId
     */
    public void setAreaPathAndId(String areaPathAndId) {
        this.areaPathAndId = areaPathAndId == null ? null : areaPathAndId.trim();
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
     * @return ok_total
     */
    public Integer getOkTotal() {
        return okTotal;
    }

    /**
     * @param okTotal
     */
    public void setOkTotal(Integer okTotal) {
        this.okTotal = okTotal;
    }

    /**
     * @return total
     */
    public Integer getTotal() {
        return total;
    }

    /**
     * @param total
     */
    public void setTotal(Integer total) {
        this.total = total;
    }

    /**
     * @return result_passed_count
     */
    public Integer getResultPassedCount() {
        return resultPassedCount;
    }

    /**
     * @param resultPassedCount
     */
    public void setResultPassedCount(Integer resultPassedCount) {
        this.resultPassedCount = resultPassedCount;
    }

    /**
     * @return result_count
     */
    public Integer getResultCount() {
        return resultCount;
    }

    /**
     * @param resultCount
     */
    public void setResultCount(Integer resultCount) {
        this.resultCount = resultCount;
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