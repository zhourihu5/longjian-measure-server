package com.longfor.longjian.measure.po.zhijian2;

import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "measure_list")
public class MeasureList {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;

    /**
     * 实测任务名
     */
    private String name;

    /**
     * 所属项目Id project.id
     */
    @Column(name = "project_id")
    private Integer projectId;

    /**
     * 区域类型，多选，逗号分隔
     */
    @Column(name = "area_type")
    private String areaType;

    /**
     * 关闭状态：1、未关闭；2、已关闭
     */
    @Column(name = "close_status")
    private Integer closeStatus;

    /**
     * 完成状态：1、未完成；2、已完成
     */
    @Column(name = "finish_status")
    private Integer finishStatus;

    /**
     * 根任务类型Key category_v3.key
     */
    @Column(name = "root_category_key")
    private String rootCategoryKey;

    /**
     * 计划测量日期起
     */
    @Column(name = "plan_begin_on")
    private Date planBeginOn;

    /**
     * 计划测量日期止
     */
    @Column(name = "plan_end_on")
    private Date planEndOn;

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
     * 获取实测任务名
     *
     * @return name - 实测任务名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置实测任务名
     *
     * @param name 实测任务名
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
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
     * 获取区域类型，多选，逗号分隔
     *
     * @return area_type - 区域类型，多选，逗号分隔
     */
    public String getAreaType() {
        return areaType;
    }

    /**
     * 设置区域类型，多选，逗号分隔
     *
     * @param areaType 区域类型，多选，逗号分隔
     */
    public void setAreaType(String areaType) {
        this.areaType = areaType == null ? null : areaType.trim();
    }

    /**
     * 获取关闭状态：1、未关闭；2、已关闭
     *
     * @return close_status - 关闭状态：1、未关闭；2、已关闭
     */
    public Integer getCloseStatus() {
        return closeStatus;
    }

    /**
     * 设置关闭状态：1、未关闭；2、已关闭
     *
     * @param closeStatus 关闭状态：1、未关闭；2、已关闭
     */
    public void setCloseStatus(Integer closeStatus) {
        this.closeStatus = closeStatus;
    }

    /**
     * 获取完成状态：1、未完成；2、已完成
     *
     * @return finish_status - 完成状态：1、未完成；2、已完成
     */
    public Integer getFinishStatus() {
        return finishStatus;
    }

    /**
     * 设置完成状态：1、未完成；2、已完成
     *
     * @param finishStatus 完成状态：1、未完成；2、已完成
     */
    public void setFinishStatus(Integer finishStatus) {
        this.finishStatus = finishStatus;
    }

    /**
     * 获取根任务类型Key category_v3.key
     *
     * @return root_category_key - 根任务类型Key category_v3.key
     */
    public String getRootCategoryKey() {
        return rootCategoryKey;
    }

    /**
     * 设置根任务类型Key category_v3.key
     *
     * @param rootCategoryKey 根任务类型Key category_v3.key
     */
    public void setRootCategoryKey(String rootCategoryKey) {
        this.rootCategoryKey = rootCategoryKey == null ? null : rootCategoryKey.trim();
    }

    /**
     * 获取计划测量日期起
     *
     * @return plan_begin_on - 计划测量日期起
     */
    public Date getPlanBeginOn() {
        return planBeginOn;
    }

    /**
     * 设置计划测量日期起
     *
     * @param planBeginOn 计划测量日期起
     */
    public void setPlanBeginOn(Date planBeginOn) {
        this.planBeginOn = planBeginOn;
    }

    /**
     * 获取计划测量日期止
     *
     * @return plan_end_on - 计划测量日期止
     */
    public Date getPlanEndOn() {
        return planEndOn;
    }

    /**
     * 设置计划测量日期止
     *
     * @param planEndOn 计划测量日期止
     */
    public void setPlanEndOn(Date planEndOn) {
        this.planEndOn = planEndOn;
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