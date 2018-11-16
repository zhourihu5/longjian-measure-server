package com.longfor.longjian.measure.po.zhijian2;

import java.util.Date;
import javax.persistence.*;

@Table(name = "xunjian_measure_zone_result")
public class XunjianMeasureZoneResult {
    /**
     * 编号
     */
    @Id
    private Integer id;

    /**
     * UUID
     */
    private String uuid;

    /**
     * 实测区域UUID,关联xunjian_measure_zone表的uuid
     */
    @Column(name = "zone_uuid")
    private String zoneUuid;

    /**
     * 任务ID,关联xunjian_task表的id
     */
    @Column(name = "task_id")
    private Integer taskId;

    /**
     * 项目ID,关联project表的id
     */
    @Column(name = "proj_id")
    private Integer projId;

    /**
     * 区域ID,关联area表的id
     */
    @Column(name = "area_id")
    private Integer areaId;

    /**
     * area的完整路径
     */
    @Column(name = "area_path_and_id")
    private String areaPathAndId;

    /**
     * 检查项KEY,关联category_id表的key
     */
    @Column(name = "category_key")
    private String categoryKey;

    /**
     * 检查项的完整路径
     */
    @Column(name = "category_path_and_key")
    private String categoryPathAndKey;

    /**
     * 实测合格点数
     */
    @Column(name = "ok_total")
    private Integer okTotal;

    /**
     * 实测点数
     */
    private Integer total;

    /**
     * 创建人,关联zhijian_apisvr库user表的id
     */
    @Column(name = "sender_id")
    private Integer senderId;

    /**
     * 客户端创建时间
     */
    @Column(name = "client_create_at")
    private Integer clientCreateAt;

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
     * 顶层检查项的id,关联category_v3表的root_cateogry_id
     */
    @Column(name = "root_category_id")
    private Integer rootCategoryId;

    /**
     * 测组数据以json的格式按顺序存储
     */
    @Column(name = "measure_group_data")
    private String measureGroupData;

    /**
     * 获取编号
     *
     * @return id - 编号
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置编号
     *
     * @param id 编号
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取UUID
     *
     * @return uuid - UUID
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * 设置UUID
     *
     * @param uuid UUID
     */
    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    /**
     * 获取实测区域UUID,关联xunjian_measure_zone表的uuid
     *
     * @return zone_uuid - 实测区域UUID,关联xunjian_measure_zone表的uuid
     */
    public String getZoneUuid() {
        return zoneUuid;
    }

    /**
     * 设置实测区域UUID,关联xunjian_measure_zone表的uuid
     *
     * @param zoneUuid 实测区域UUID,关联xunjian_measure_zone表的uuid
     */
    public void setZoneUuid(String zoneUuid) {
        this.zoneUuid = zoneUuid == null ? null : zoneUuid.trim();
    }

    /**
     * 获取任务ID,关联xunjian_task表的id
     *
     * @return task_id - 任务ID,关联xunjian_task表的id
     */
    public Integer getTaskId() {
        return taskId;
    }

    /**
     * 设置任务ID,关联xunjian_task表的id
     *
     * @param taskId 任务ID,关联xunjian_task表的id
     */
    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    /**
     * 获取项目ID,关联project表的id
     *
     * @return proj_id - 项目ID,关联project表的id
     */
    public Integer getProjId() {
        return projId;
    }

    /**
     * 设置项目ID,关联project表的id
     *
     * @param projId 项目ID,关联project表的id
     */
    public void setProjId(Integer projId) {
        this.projId = projId;
    }

    /**
     * 获取区域ID,关联area表的id
     *
     * @return area_id - 区域ID,关联area表的id
     */
    public Integer getAreaId() {
        return areaId;
    }

    /**
     * 设置区域ID,关联area表的id
     *
     * @param areaId 区域ID,关联area表的id
     */
    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    /**
     * 获取area的完整路径
     *
     * @return area_path_and_id - area的完整路径
     */
    public String getAreaPathAndId() {
        return areaPathAndId;
    }

    /**
     * 设置area的完整路径
     *
     * @param areaPathAndId area的完整路径
     */
    public void setAreaPathAndId(String areaPathAndId) {
        this.areaPathAndId = areaPathAndId == null ? null : areaPathAndId.trim();
    }

    /**
     * 获取检查项KEY,关联category_id表的key
     *
     * @return category_key - 检查项KEY,关联category_id表的key
     */
    public String getCategoryKey() {
        return categoryKey;
    }

    /**
     * 设置检查项KEY,关联category_id表的key
     *
     * @param categoryKey 检查项KEY,关联category_id表的key
     */
    public void setCategoryKey(String categoryKey) {
        this.categoryKey = categoryKey == null ? null : categoryKey.trim();
    }

    /**
     * 获取检查项的完整路径
     *
     * @return category_path_and_key - 检查项的完整路径
     */
    public String getCategoryPathAndKey() {
        return categoryPathAndKey;
    }

    /**
     * 设置检查项的完整路径
     *
     * @param categoryPathAndKey 检查项的完整路径
     */
    public void setCategoryPathAndKey(String categoryPathAndKey) {
        this.categoryPathAndKey = categoryPathAndKey == null ? null : categoryPathAndKey.trim();
    }

    /**
     * 获取实测合格点数
     *
     * @return ok_total - 实测合格点数
     */
    public Integer getOkTotal() {
        return okTotal;
    }

    /**
     * 设置实测合格点数
     *
     * @param okTotal 实测合格点数
     */
    public void setOkTotal(Integer okTotal) {
        this.okTotal = okTotal;
    }

    /**
     * 获取实测点数
     *
     * @return total - 实测点数
     */
    public Integer getTotal() {
        return total;
    }

    /**
     * 设置实测点数
     *
     * @param total 实测点数
     */
    public void setTotal(Integer total) {
        this.total = total;
    }

    /**
     * 获取创建人,关联zhijian_apisvr库user表的id
     *
     * @return sender_id - 创建人,关联zhijian_apisvr库user表的id
     */
    public Integer getSenderId() {
        return senderId;
    }

    /**
     * 设置创建人,关联zhijian_apisvr库user表的id
     *
     * @param senderId 创建人,关联zhijian_apisvr库user表的id
     */
    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    /**
     * 获取客户端创建时间
     *
     * @return client_create_at - 客户端创建时间
     */
    public Integer getClientCreateAt() {
        return clientCreateAt;
    }

    /**
     * 设置客户端创建时间
     *
     * @param clientCreateAt 客户端创建时间
     */
    public void setClientCreateAt(Integer clientCreateAt) {
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

    /**
     * 获取顶层检查项的id,关联category_v3表的root_cateogry_id
     *
     * @return root_category_id - 顶层检查项的id,关联category_v3表的root_cateogry_id
     */
    public Integer getRootCategoryId() {
        return rootCategoryId;
    }

    /**
     * 设置顶层检查项的id,关联category_v3表的root_cateogry_id
     *
     * @param rootCategoryId 顶层检查项的id,关联category_v3表的root_cateogry_id
     */
    public void setRootCategoryId(Integer rootCategoryId) {
        this.rootCategoryId = rootCategoryId;
    }

    /**
     * 获取测组数据以json的格式按顺序存储
     *
     * @return measure_group_data - 测组数据以json的格式按顺序存储
     */
    public String getMeasureGroupData() {
        return measureGroupData;
    }

    /**
     * 设置测组数据以json的格式按顺序存储
     *
     * @param measureGroupData 测组数据以json的格式按顺序存储
     */
    public void setMeasureGroupData(String measureGroupData) {
        this.measureGroupData = measureGroupData == null ? null : measureGroupData.trim();
    }
}