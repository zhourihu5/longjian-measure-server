package com.longfor.longjian.measure.po.zhijian2;

import java.util.Date;
import javax.persistence.*;

@Table(name = "measure_list")
public class MeasureList {
    @Id
    private Integer id;

    @Column(name = "project_id")
    private Integer projectId;

    private String name;

    @Column(name = "finish_status")
    private Integer finishStatus;

    @Column(name = "close_status")
    private Integer closeStatus;

    @Column(name = "plan_begin_on")
    private Date planBeginOn;

    @Column(name = "plan_end_on")
    private Date planEndOn;

    @Column(name = "area_type")
    private String areaType;

    @Column(name = "root_category_key")
    private String rootCategoryKey;

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
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
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
     * @return plan_begin_on
     */
    public Date getPlanBeginOn() {
        return planBeginOn;
    }

    /**
     * @param planBeginOn
     */
    public void setPlanBeginOn(Date planBeginOn) {
        this.planBeginOn = planBeginOn;
    }

    /**
     * @return plan_end_on
     */
    public Date getPlanEndOn() {
        return planEndOn;
    }

    /**
     * @param planEndOn
     */
    public void setPlanEndOn(Date planEndOn) {
        this.planEndOn = planEndOn;
    }

    /**
     * @return area_type
     */
    public String getAreaType() {
        return areaType;
    }

    /**
     * @param areaType
     */
    public void setAreaType(String areaType) {
        this.areaType = areaType == null ? null : areaType.trim();
    }

    /**
     * @return root_category_key
     */
    public String getRootCategoryKey() {
        return rootCategoryKey;
    }

    /**
     * @param rootCategoryKey
     */
    public void setRootCategoryKey(String rootCategoryKey) {
        this.rootCategoryKey = rootCategoryKey == null ? null : rootCategoryKey.trim();
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