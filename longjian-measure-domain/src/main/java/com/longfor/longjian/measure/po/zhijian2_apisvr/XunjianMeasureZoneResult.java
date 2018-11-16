package com.longfor.longjian.measure.po.zhijian2_apisvr;

import java.util.Date;
import javax.persistence.*;

@Table(name = "xunjian_measure_zone_result")
public class XunjianMeasureZoneResult {
    @Id
    private Integer id;

    private String uuid;

    @Column(name = "zone_uuid")
    private String zoneUuid;

    @Column(name = "task_id")
    private Integer taskId;

    @Column(name = "proj_id")
    private Integer projId;

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

    @Column(name = "sender_id")
    private Integer senderId;

    @Column(name = "client_create_at")
    private Integer clientCreateAt;

    @Column(name = "create_at")
    private Date createAt;

    @Column(name = "update_at")
    private Date updateAt;

    @Column(name = "delete_at")
    private Date deleteAt;

    @Column(name = "root_category_id")
    private Integer rootCategoryId;

    @Column(name = "measure_group_data")
    private String measureGroupData;

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
     * @return uuid
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * @param uuid
     */
    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    /**
     * @return zone_uuid
     */
    public String getZoneUuid() {
        return zoneUuid;
    }

    /**
     * @param zoneUuid
     */
    public void setZoneUuid(String zoneUuid) {
        this.zoneUuid = zoneUuid == null ? null : zoneUuid.trim();
    }

    /**
     * @return task_id
     */
    public Integer getTaskId() {
        return taskId;
    }

    /**
     * @param taskId
     */
    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
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
     * @return client_create_at
     */
    public Integer getClientCreateAt() {
        return clientCreateAt;
    }

    /**
     * @param clientCreateAt
     */
    public void setClientCreateAt(Integer clientCreateAt) {
        this.clientCreateAt = clientCreateAt;
    }

    /**
     * @return create_at
     */
    public Date getCreateAt() {
        return createAt;
    }

    /**
     * @param createAt
     */
    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    /**
     * @return update_at
     */
    public Date getUpdateAt() {
        return updateAt;
    }

    /**
     * @param updateAt
     */
    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    /**
     * @return delete_at
     */
    public Date getDeleteAt() {
        return deleteAt;
    }

    /**
     * @param deleteAt
     */
    public void setDeleteAt(Date deleteAt) {
        this.deleteAt = deleteAt;
    }

    /**
     * @return root_category_id
     */
    public Integer getRootCategoryId() {
        return rootCategoryId;
    }

    /**
     * @param rootCategoryId
     */
    public void setRootCategoryId(Integer rootCategoryId) {
        this.rootCategoryId = rootCategoryId;
    }

    /**
     * @return measure_group_data
     */
    public String getMeasureGroupData() {
        return measureGroupData;
    }

    /**
     * @param measureGroupData
     */
    public void setMeasureGroupData(String measureGroupData) {
        this.measureGroupData = measureGroupData == null ? null : measureGroupData.trim();
    }
}