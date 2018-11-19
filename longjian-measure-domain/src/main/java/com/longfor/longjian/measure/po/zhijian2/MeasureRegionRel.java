package com.longfor.longjian.measure.po.zhijian2;

import java.util.Date;
import javax.persistence.*;

@Table(name = "measure_region_rel")
public class MeasureRegionRel {
    @Id
    private Integer id;

    /**
     * 关联关系描述
     */
    private String desc;

    /**
     * 所属项目Id project.id
     */
    @Column(name = "project_id")
    private Integer projectId;

    /**
     * 描画区域列表 measure_region.id
     */
    @Column(name = "region_ids")
    private String regionIds;

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
     * 获取关联关系描述
     *
     * @return desc - 关联关系描述
     */
    public String getDesc() {
        return desc;
    }

    /**
     * 设置关联关系描述
     *
     * @param desc 关联关系描述
     */
    public void setDesc(String desc) {
        this.desc = desc == null ? null : desc.trim();
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
     * 获取描画区域列表 measure_region.id
     *
     * @return region_ids - 描画区域列表 measure_region.id
     */
    public String getRegionIds() {
        return regionIds;
    }

    /**
     * 设置描画区域列表 measure_region.id
     *
     * @param regionIds 描画区域列表 measure_region.id
     */
    public void setRegionIds(String regionIds) {
        this.regionIds = regionIds == null ? null : regionIds.trim();
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