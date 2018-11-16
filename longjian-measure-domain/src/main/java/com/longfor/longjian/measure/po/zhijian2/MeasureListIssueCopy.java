package com.longfor.longjian.measure.po.zhijian2;

import java.util.Date;
import javax.persistence.*;

@Table(name = "measure_list_issue_copy")
public class MeasureListIssueCopy {
    @Id
    private Integer id;

    private String uuid;

    @Column(name = "project_id")
    private Integer projectId;

    @Column(name = "category_key")
    private String categoryKey;

    @Column(name = "category_path_and_key")
    private String categoryPathAndKey;

    @Column(name = "list_id")
    private Integer listId;

    @Column(name = "zone_uuid")
    private String zoneUuid;

    @Column(name = "region_uuid")
    private String regionUuid;

    @Column(name = "squad_id")
    private Integer squadId;

    @Column(name = "sender_id")
    private Integer senderId;

    @Column(name = "repairer_id")
    private Integer repairerId;

    @Column(name = "area_id")
    private Integer areaId;

    @Column(name = "area_path_and_id")
    private String areaPathAndId;

    private Integer typ;

    private Integer status;

    @Column(name = "plan_end_on")
    private Integer planEndOn;

    @Column(name = "end_on")
    private Integer endOn;

    @Column(name = "drawing_md5")
    private String drawingMd5;

    @Column(name = "pos_x")
    private Integer posX;

    @Column(name = "pos_y")
    private Integer posY;

    private String desc;

    private Integer condition;

    @Column(name = "attachment_md5_list")
    private String attachmentMd5List;

    @Column(name = "last_assigner")
    private Integer lastAssigner;

    @Column(name = "last_assigner_at")
    private Integer lastAssignerAt;

    @Column(name = "destroy_user")
    private Integer destroyUser;

    @Column(name = "destroy_at")
    private Integer destroyAt;

    @Column(name = "close_status")
    private Byte closeStatus;

    @Column(name = "close_user")
    private Integer closeUser;

    @Column(name = "close_time")
    private Integer closeTime;

    @Column(name = "client_create_at")
    private Date clientCreateAt;

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
     * @return repairer_id
     */
    public Integer getRepairerId() {
        return repairerId;
    }

    /**
     * @param repairerId
     */
    public void setRepairerId(Integer repairerId) {
        this.repairerId = repairerId;
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
     * @return typ
     */
    public Integer getTyp() {
        return typ;
    }

    /**
     * @param typ
     */
    public void setTyp(Integer typ) {
        this.typ = typ;
    }

    /**
     * @return status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @return plan_end_on
     */
    public Integer getPlanEndOn() {
        return planEndOn;
    }

    /**
     * @param planEndOn
     */
    public void setPlanEndOn(Integer planEndOn) {
        this.planEndOn = planEndOn;
    }

    /**
     * @return end_on
     */
    public Integer getEndOn() {
        return endOn;
    }

    /**
     * @param endOn
     */
    public void setEndOn(Integer endOn) {
        this.endOn = endOn;
    }

    /**
     * @return drawing_md5
     */
    public String getDrawingMd5() {
        return drawingMd5;
    }

    /**
     * @param drawingMd5
     */
    public void setDrawingMd5(String drawingMd5) {
        this.drawingMd5 = drawingMd5 == null ? null : drawingMd5.trim();
    }

    /**
     * @return pos_x
     */
    public Integer getPosX() {
        return posX;
    }

    /**
     * @param posX
     */
    public void setPosX(Integer posX) {
        this.posX = posX;
    }

    /**
     * @return pos_y
     */
    public Integer getPosY() {
        return posY;
    }

    /**
     * @param posY
     */
    public void setPosY(Integer posY) {
        this.posY = posY;
    }

    /**
     * @return desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * @param desc
     */
    public void setDesc(String desc) {
        this.desc = desc == null ? null : desc.trim();
    }

    /**
     * @return condition
     */
    public Integer getCondition() {
        return condition;
    }

    /**
     * @param condition
     */
    public void setCondition(Integer condition) {
        this.condition = condition;
    }

    /**
     * @return attachment_md5_list
     */
    public String getAttachmentMd5List() {
        return attachmentMd5List;
    }

    /**
     * @param attachmentMd5List
     */
    public void setAttachmentMd5List(String attachmentMd5List) {
        this.attachmentMd5List = attachmentMd5List == null ? null : attachmentMd5List.trim();
    }

    /**
     * @return last_assigner
     */
    public Integer getLastAssigner() {
        return lastAssigner;
    }

    /**
     * @param lastAssigner
     */
    public void setLastAssigner(Integer lastAssigner) {
        this.lastAssigner = lastAssigner;
    }

    /**
     * @return last_assigner_at
     */
    public Integer getLastAssignerAt() {
        return lastAssignerAt;
    }

    /**
     * @param lastAssignerAt
     */
    public void setLastAssignerAt(Integer lastAssignerAt) {
        this.lastAssignerAt = lastAssignerAt;
    }

    /**
     * @return destroy_user
     */
    public Integer getDestroyUser() {
        return destroyUser;
    }

    /**
     * @param destroyUser
     */
    public void setDestroyUser(Integer destroyUser) {
        this.destroyUser = destroyUser;
    }

    /**
     * @return destroy_at
     */
    public Integer getDestroyAt() {
        return destroyAt;
    }

    /**
     * @param destroyAt
     */
    public void setDestroyAt(Integer destroyAt) {
        this.destroyAt = destroyAt;
    }

    /**
     * @return close_status
     */
    public Byte getCloseStatus() {
        return closeStatus;
    }

    /**
     * @param closeStatus
     */
    public void setCloseStatus(Byte closeStatus) {
        this.closeStatus = closeStatus;
    }

    /**
     * @return close_user
     */
    public Integer getCloseUser() {
        return closeUser;
    }

    /**
     * @param closeUser
     */
    public void setCloseUser(Integer closeUser) {
        this.closeUser = closeUser;
    }

    /**
     * @return close_time
     */
    public Integer getCloseTime() {
        return closeTime;
    }

    /**
     * @param closeTime
     */
    public void setCloseTime(Integer closeTime) {
        this.closeTime = closeTime;
    }

    /**
     * @return client_create_at
     */
    public Date getClientCreateAt() {
        return clientCreateAt;
    }

    /**
     * @param clientCreateAt
     */
    public void setClientCreateAt(Date clientCreateAt) {
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
}