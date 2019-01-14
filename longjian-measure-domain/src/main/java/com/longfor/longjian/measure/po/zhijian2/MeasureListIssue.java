package com.longfor.longjian.measure.po.zhijian2;

import java.util.Date;
import javax.persistence.*;

@Table(name = "measure_list_issue")
public class MeasureListIssue {
    @Id
    private Integer id;

    /**
     * 所在区域id area.id
     */
    @Column(name = "area_id")
    private Integer areaId;

    /**
     * 区域路径
     */
    @Column(name = "area_path_and_id")
    private String areaPathAndId;

    /**
     * 产生Issue时的现场照片,多个用半角逗号,分隔
     */
    @Column(name = "attachment_md5_list")
    private String attachmentMd5List;

    /**
     * 任务类型KEY category_v3.key
     */
    @Column(name = "category_key")
    private String categoryKey;

    /**
     * 任务类型Path和Key
     */
    @Column(name = "category_path_and_key")
    private String categoryPathAndKey;

    /**
     * 修复人id zhijian2_apisvr.user.id
     */
    @Column(name = "repairer_id")
    private Integer repairerId;

    /**
     * 提交人id zhijian2_apisvr.user.id
     */
    @Column(name = "sender_id")
    private Integer senderId;

    /**
     * 所属测试小组id measure_squad.id
     */
    @Column(name = "squad_id")
    private Integer squadId;

    /**
     * 1 开启 2 关闭
     */
    @Column(name = "close_status")
    private Integer closeStatus;

    /**
     * 关闭时间
     */
    @Column(name = "close_time")
    private Integer closeTime;

    /**
     * 关闭人
     */
    @Column(name = "close_user")
    private Integer closeUser;

    /**
     * 问题严重情况 1 严重 2 较差 3 轻微
     */
    @Column(name = "`condition`")
    private Integer condition;

    /**
     * 问题描述
     */
    @Column(name="`desc`")
    private String desc;

    /**
     * 图纸文件Md5
     */
    @Column(name = "drawing_md5")
    private String drawingMd5;

    /**
     * 消项人
     */
    @Column(name = "destroy_at")
    private Integer destroyAt;

    /**
     * 消项时间 zhijian2_apisvr.user.id
     */
    @Column(name = "destroy_user")
    private Integer destroyUser;

    /**
     * 计划完成时间
     */
    @Column(name = "plan_end_on")
    private Integer planEndOn;

    /**
     * 实际完成时间
     */
    @Column(name = "end_on")
    private Integer endOn;

    /**
     * 最后指派人 zhijian2_apisvr.user.id
     */
    @Column(name = "last_assigner")
    private Integer lastAssigner;

    /**
     * 最后指派时间
     */
    @Column(name = "last_assigner_at")
    private Integer lastAssignerAt;

    /**
     * 实测清单ID measure_list.id
     */
    @Column(name = "list_id")
    private Integer listId;

    /**
     * 在图纸上的位置X
     */
    @Column(name = "pos_x")
    private Integer posX;

    /**
     * 在图纸上的位置Y
     */
    @Column(name = "pos_y")
    private Integer posY;

    /**
     * 所属项目Id project.id
     */
    @Column(name = "project_id")
    private Integer projectId;

    /**
     * 状态：1 已记录未分配 2已分配未整改 3已整改未验收 4已验收 5已关闭
     */
    private Integer status;

    /**
     * 问题类型：1普通问题 2难以整改问题 3重大问题 4普通记录(保留)
     */
    private Integer typ;

    /**
     * 唯一编号
     */
    private String uuid;

    /**
     * 所含描画区域uuid measure_region.uuid
     */
    @Column(name = "region_uuid")
    private String regionUuid;

    /**
     * 测区uuid measure_zone.uuid
     */
    @Column(name = "zone_uuid")
    private String zoneUuid;

    /**
     * 客户端记录时间
     */
    @Column(name = "client_create_at")
    private Date clientCreateAt;

    /**
     * 创建时间
     */
    @Column(name = "create_at")
    private Date createAt;

    /**
     * 更新时间
     */
    @Column(name = "update_at")
    private Date updateAt;

    /**
     * 删除时间
     */
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
     * 获取所在区域id area.id
     *
     * @return area_id - 所在区域id area.id
     */
    public Integer getAreaId() {
        return areaId;
    }

    /**
     * 设置所在区域id area.id
     *
     * @param areaId 所在区域id area.id
     */
    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    /**
     * 获取区域路径
     *
     * @return area_path_and_id - 区域路径
     */
    public String getAreaPathAndId() {
        return areaPathAndId;
    }

    /**
     * 设置区域路径
     *
     * @param areaPathAndId 区域路径
     */
    public void setAreaPathAndId(String areaPathAndId) {
        this.areaPathAndId = areaPathAndId == null ? null : areaPathAndId.trim();
    }

    /**
     * 获取产生Issue时的现场照片,多个用半角逗号,分隔
     *
     * @return attachment_md5_list - 产生Issue时的现场照片,多个用半角逗号,分隔
     */
    public String getAttachmentMd5List() {
        return attachmentMd5List;
    }

    /**
     * 设置产生Issue时的现场照片,多个用半角逗号,分隔
     *
     * @param attachmentMd5List 产生Issue时的现场照片,多个用半角逗号,分隔
     */
    public void setAttachmentMd5List(String attachmentMd5List) {
        this.attachmentMd5List = attachmentMd5List == null ? null : attachmentMd5List.trim();
    }

    /**
     * 获取任务类型KEY category_v3.key
     *
     * @return category_key - 任务类型KEY category_v3.key
     */
    public String getCategoryKey() {
        return categoryKey;
    }

    /**
     * 设置任务类型KEY category_v3.key
     *
     * @param categoryKey 任务类型KEY category_v3.key
     */
    public void setCategoryKey(String categoryKey) {
        this.categoryKey = categoryKey == null ? null : categoryKey.trim();
    }

    /**
     * 获取任务类型Path和Key
     *
     * @return category_path_and_key - 任务类型Path和Key
     */
    public String getCategoryPathAndKey() {
        return categoryPathAndKey;
    }

    /**
     * 设置任务类型Path和Key
     *
     * @param categoryPathAndKey 任务类型Path和Key
     */
    public void setCategoryPathAndKey(String categoryPathAndKey) {
        this.categoryPathAndKey = categoryPathAndKey == null ? null : categoryPathAndKey.trim();
    }

    /**
     * 获取修复人id zhijian2_apisvr.user.id
     *
     * @return repairer_id - 修复人id zhijian2_apisvr.user.id
     */
    public Integer getRepairerId() {
        return repairerId;
    }

    /**
     * 设置修复人id zhijian2_apisvr.user.id
     *
     * @param repairerId 修复人id zhijian2_apisvr.user.id
     */
    public void setRepairerId(Integer repairerId) {
        this.repairerId = repairerId;
    }

    /**
     * 获取提交人id zhijian2_apisvr.user.id
     *
     * @return sender_id - 提交人id zhijian2_apisvr.user.id
     */
    public Integer getSenderId() {
        return senderId;
    }

    /**
     * 设置提交人id zhijian2_apisvr.user.id
     *
     * @param senderId 提交人id zhijian2_apisvr.user.id
     */
    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    /**
     * 获取所属测试小组id measure_squad.id
     *
     * @return squad_id - 所属测试小组id measure_squad.id
     */
    public Integer getSquadId() {
        return squadId;
    }

    /**
     * 设置所属测试小组id measure_squad.id
     *
     * @param squadId 所属测试小组id measure_squad.id
     */
    public void setSquadId(Integer squadId) {
        this.squadId = squadId;
    }

    /**
     * 获取1 开启 2 关闭
     *
     * @return close_status - 1 开启 2 关闭
     */
    public Integer getCloseStatus() {
        return closeStatus;
    }

    /**
     * 设置1 开启 2 关闭
     *
     * @param closeStatus 1 开启 2 关闭
     */
    public void setCloseStatus(Integer closeStatus) {
        this.closeStatus = closeStatus;
    }

    /**
     * 获取关闭时间
     *
     * @return close_time - 关闭时间
     */
    public Integer getCloseTime() {
        return closeTime;
    }

    /**
     * 设置关闭时间
     *
     * @param closeTime 关闭时间
     */
    public void setCloseTime(Integer closeTime) {
        this.closeTime = closeTime;
    }

    /**
     * 获取关闭人
     *
     * @return close_user - 关闭人
     */
    public Integer getCloseUser() {
        return closeUser;
    }

    /**
     * 设置关闭人
     *
     * @param closeUser 关闭人
     */
    public void setCloseUser(Integer closeUser) {
        this.closeUser = closeUser;
    }

    /**
     * 获取问题严重情况 1 严重 2 较差 3 轻微
     *
     * @return condition - 问题严重情况 1 严重 2 较差 3 轻微
     */
    public Integer getCondition() {
        return condition;
    }

    /**
     * 设置问题严重情况 1 严重 2 较差 3 轻微
     *
     * @param condition 问题严重情况 1 严重 2 较差 3 轻微
     */
    public void setCondition(Integer condition) {
        this.condition = condition;
    }

    /**
     * 获取问题描述
     *
     * @return desc - 问题描述
     */
    public String getDesc() {
        return desc;
    }

    /**
     * 设置问题描述
     *
     * @param desc 问题描述
     */
    public void setDesc(String desc) {
        this.desc = desc == null ? null : desc.trim();
    }

    /**
     * 获取图纸文件Md5
     *
     * @return drawing_md5 - 图纸文件Md5
     */
    public String getDrawingMd5() {
        return drawingMd5;
    }

    /**
     * 设置图纸文件Md5
     *
     * @param drawingMd5 图纸文件Md5
     */
    public void setDrawingMd5(String drawingMd5) {
        this.drawingMd5 = drawingMd5 == null ? null : drawingMd5.trim();
    }

    /**
     * 获取消项人
     *
     * @return destroy_at - 消项人
     */
    public Integer getDestroyAt() {
        return destroyAt;
    }

    /**
     * 设置消项人
     *
     * @param destroyAt 消项人
     */
    public void setDestroyAt(Integer destroyAt) {
        this.destroyAt = destroyAt;
    }

    /**
     * 获取消项时间 zhijian2_apisvr.user.id
     *
     * @return destroy_user - 消项时间 zhijian2_apisvr.user.id
     */
    public Integer getDestroyUser() {
        return destroyUser;
    }

    /**
     * 设置消项时间 zhijian2_apisvr.user.id
     *
     * @param destroyUser 消项时间 zhijian2_apisvr.user.id
     */
    public void setDestroyUser(Integer destroyUser) {
        this.destroyUser = destroyUser;
    }

    /**
     * 获取计划完成时间
     *
     * @return plan_end_on - 计划完成时间
     */
    public Integer getPlanEndOn() {
        return planEndOn;
    }

    /**
     * 设置计划完成时间
     *
     * @param planEndOn 计划完成时间
     */
    public void setPlanEndOn(Integer planEndOn) {
        this.planEndOn = planEndOn;
    }

    /**
     * 获取实际完成时间
     *
     * @return end_on - 实际完成时间
     */
    public Integer getEndOn() {
        return endOn;
    }

    /**
     * 设置实际完成时间
     *
     * @param endOn 实际完成时间
     */
    public void setEndOn(Integer endOn) {
        this.endOn = endOn;
    }

    /**
     * 获取最后指派人 zhijian2_apisvr.user.id
     *
     * @return last_assigner - 最后指派人 zhijian2_apisvr.user.id
     */
    public Integer getLastAssigner() {
        return lastAssigner;
    }

    /**
     * 设置最后指派人 zhijian2_apisvr.user.id
     *
     * @param lastAssigner 最后指派人 zhijian2_apisvr.user.id
     */
    public void setLastAssigner(Integer lastAssigner) {
        this.lastAssigner = lastAssigner;
    }

    /**
     * 获取最后指派时间
     *
     * @return last_assigner_at - 最后指派时间
     */
    public Integer getLastAssignerAt() {
        return lastAssignerAt;
    }

    /**
     * 设置最后指派时间
     *
     * @param lastAssignerAt 最后指派时间
     */
    public void setLastAssignerAt(Integer lastAssignerAt) {
        this.lastAssignerAt = lastAssignerAt;
    }

    /**
     * 获取实测清单ID measure_list.id
     *
     * @return list_id - 实测清单ID measure_list.id
     */
    public Integer getListId() {
        return listId;
    }

    /**
     * 设置实测清单ID measure_list.id
     *
     * @param listId 实测清单ID measure_list.id
     */
    public void setListId(Integer listId) {
        this.listId = listId;
    }

    /**
     * 获取在图纸上的位置X
     *
     * @return pos_x - 在图纸上的位置X
     */
    public Integer getPosX() {
        return posX;
    }

    /**
     * 设置在图纸上的位置X
     *
     * @param posX 在图纸上的位置X
     */
    public void setPosX(Integer posX) {
        this.posX = posX;
    }

    /**
     * 获取在图纸上的位置Y
     *
     * @return pos_y - 在图纸上的位置Y
     */
    public Integer getPosY() {
        return posY;
    }

    /**
     * 设置在图纸上的位置Y
     *
     * @param posY 在图纸上的位置Y
     */
    public void setPosY(Integer posY) {
        this.posY = posY;
    }

    /**
     * 获取所属项目Id project.id
     *
     * @return project_id - 所属项目Id project.id
     */
    public Integer getProjectId() {
        return projectId;
    }

    /**
     * 设置所属项目Id project.id
     *
     * @param projectId 所属项目Id project.id
     */
    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    /**
     * 获取状态：1 已记录未分配 2已分配未整改 3已整改未验收 4已验收 5已关闭
     *
     * @return status - 状态：1 已记录未分配 2已分配未整改 3已整改未验收 4已验收 5已关闭
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态：1 已记录未分配 2已分配未整改 3已整改未验收 4已验收 5已关闭
     *
     * @param status 状态：1 已记录未分配 2已分配未整改 3已整改未验收 4已验收 5已关闭
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取问题类型：1普通问题 2难以整改问题 3重大问题 4普通记录(保留)
     *
     * @return typ - 问题类型：1普通问题 2难以整改问题 3重大问题 4普通记录(保留)
     */
    public Integer getTyp() {
        return typ;
    }

    /**
     * 设置问题类型：1普通问题 2难以整改问题 3重大问题 4普通记录(保留)
     *
     * @param typ 问题类型：1普通问题 2难以整改问题 3重大问题 4普通记录(保留)
     */
    public void setTyp(Integer typ) {
        this.typ = typ;
    }

    /**
     * 获取唯一编号
     *
     * @return uuid - 唯一编号
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * 设置唯一编号
     *
     * @param uuid 唯一编号
     */
    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    /**
     * 获取所含描画区域uuid measure_region.uuid
     *
     * @return region_uuid - 所含描画区域uuid measure_region.uuid
     */
    public String getRegionUuid() {
        return regionUuid;
    }

    /**
     * 设置所含描画区域uuid measure_region.uuid
     *
     * @param regionUuid 所含描画区域uuid measure_region.uuid
     */
    public void setRegionUuid(String regionUuid) {
        this.regionUuid = regionUuid == null ? null : regionUuid.trim();
    }

    /**
     * 获取测区uuid measure_zone.uuid
     *
     * @return zone_uuid - 测区uuid measure_zone.uuid
     */
    public String getZoneUuid() {
        return zoneUuid;
    }

    /**
     * 设置测区uuid measure_zone.uuid
     *
     * @param zoneUuid 测区uuid measure_zone.uuid
     */
    public void setZoneUuid(String zoneUuid) {
        this.zoneUuid = zoneUuid == null ? null : zoneUuid.trim();
    }

    /**
     * 获取客户端记录时间
     *
     * @return client_create_at - 客户端记录时间
     */
    public Date getClientCreateAt() {
        return clientCreateAt;
    }

    /**
     * 设置客户端记录时间
     *
     * @param clientCreateAt 客户端记录时间
     */
    public void setClientCreateAt(Date clientCreateAt) {
        this.clientCreateAt = clientCreateAt;
    }

    /**
     * 获取创建时间
     *
     * @return create_at - 创建时间
     */
    public Date getCreateAt() {
        return createAt;
    }

    /**
     * 设置创建时间
     *
     * @param createAt 创建时间
     */
    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    /**
     * 获取更新时间
     *
     * @return update_at - 更新时间
     */
    public Date getUpdateAt() {
        return updateAt;
    }

    /**
     * 设置更新时间
     *
     * @param updateAt 更新时间
     */
    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    /**
     * 获取删除时间
     *
     * @return delete_at - 删除时间
     */
    public Date getDeleteAt() {
        return deleteAt;
    }

    /**
     * 设置删除时间
     *
     * @param deleteAt 删除时间
     */
    public void setDeleteAt(Date deleteAt) {
        this.deleteAt = deleteAt;
    }
}