package com.longfor.longjian.measure.po.zhijian2;

import java.util.Date;
import javax.persistence.*;

@Table(name = "xunjian_measure_zone")
public class XunjianMeasureZone {
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
     * 区域ID,关联area表的id
     */
    @Column(name = "area_id")
    private Integer areaId;

    /**
     * 区域路径和ID
     */
    @Column(name = "area_path_and_id")
    private String areaPathAndId;

    /**
     * 检查项KEY,关联category_v3表的key
     */
    @Column(name = "category_key")
    private String categoryKey;

    /**
     * 检查项路径和KEY
     */
    @Column(name = "category_path_and_key")
    private String categoryPathAndKey;

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
     * 客户端创建时间
     */
    @Column(name = "client_create_at")
    private Integer clientCreateAt;

    /**
     * 创建人,关联zhijian_apisvr库user表的id
     */
    @Column(name = "sender_id")
    private Integer senderId;

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
     * 根检查项ID,关联category_v3表的root_category_id
     */
    @Column(name = "root_category_id")
    private Integer rootCategoryId;

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
     * 获取区域路径和ID
     *
     * @return area_path_and_id - 区域路径和ID
     */
    public String getAreaPathAndId() {
        return areaPathAndId;
    }

    /**
     * 设置区域路径和ID
     *
     * @param areaPathAndId 区域路径和ID
     */
    public void setAreaPathAndId(String areaPathAndId) {
        this.areaPathAndId = areaPathAndId == null ? null : areaPathAndId.trim();
    }

    /**
     * 获取检查项KEY,关联category_v3表的key
     *
     * @return category_key - 检查项KEY,关联category_v3表的key
     */
    public String getCategoryKey() {
        return categoryKey;
    }

    /**
     * 设置检查项KEY,关联category_v3表的key
     *
     * @param categoryKey 检查项KEY,关联category_v3表的key
     */
    public void setCategoryKey(String categoryKey) {
        this.categoryKey = categoryKey == null ? null : categoryKey.trim();
    }

    /**
     * 获取检查项路径和KEY
     *
     * @return category_path_and_key - 检查项路径和KEY
     */
    public String getCategoryPathAndKey() {
        return categoryPathAndKey;
    }

    /**
     * 设置检查项路径和KEY
     *
     * @param categoryPathAndKey 检查项路径和KEY
     */
    public void setCategoryPathAndKey(String categoryPathAndKey) {
        this.categoryPathAndKey = categoryPathAndKey == null ? null : categoryPathAndKey.trim();
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
     * 获取根检查项ID,关联category_v3表的root_category_id
     *
     * @return root_category_id - 根检查项ID,关联category_v3表的root_category_id
     */
    public Integer getRootCategoryId() {
        return rootCategoryId;
    }

    /**
     * 设置根检查项ID,关联category_v3表的root_category_id
     *
     * @param rootCategoryId 根检查项ID,关联category_v3表的root_category_id
     */
    public void setRootCategoryId(Integer rootCategoryId) {
        this.rootCategoryId = rootCategoryId;
    }
}