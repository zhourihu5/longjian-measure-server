package com.longfor.longjian.measure.po.zhijian2;

import java.util.Date;
import javax.persistence.*;

@Table(name = "measure_zone")
public class MeasureZone {
    @Id
    private Integer id;

    private String uuid;

    @Column(name = "project_id")
    private Integer projectId;

    @Column(name = "list_id")
    private Integer listId;

    @Column(name = "region_uuid")
    private String regionUuid;

    @Column(name = "area_id")
    private Integer areaId;

    @Column(name = "area_path_and_id")
    private String areaPathAndId;

    @Column(name = "category_key")
    private String categoryKey;

    @Column(name = "category_path_and_key")
    private String categoryPathAndKey;

    @Column(name = "src_type")
    private Integer srcType;

    @Column(name = "finish_status")
    private Integer finishStatus;

    @Column(name = "close_status")
    private Integer closeStatus;

    @Column(name = "create_at")
    private Date createAt;

    @Column(name = "update_at")
    private Date updateAt;

    @Column(name = "delete_at")
    private Date deleteAt;

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
     * @return region_uuid
     */
    public String getRegionUuid() {
        return regionUuid;
    }

    /**
     * @param regionUuid
     */
    public void setRegionUuid(String regionUuid) {
        this.regionUuid = regionUuid == null ? null : regionUuid.trim();
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
     * @return src_type
     */
    public Integer getSrcType() {
        return srcType;
    }

    /**
     * @param srcType
     */
    public void setSrcType(Integer srcType) {
        this.srcType = srcType;
    }

    /**
     * @return finish_status
     */
    public Integer getFinishStatus() {
        return finishStatus;
    }

    /**
     * @param finishStatus
     */
    public void setFinishStatus(Integer finishStatus) {
        this.finishStatus = finishStatus;
    }

    /**
     * @return close_status
     */
    public Integer getCloseStatus() {
        return closeStatus;
    }

    /**
     * @param closeStatus
     */
    public void setCloseStatus(Integer closeStatus) {
        this.closeStatus = closeStatus;
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
}