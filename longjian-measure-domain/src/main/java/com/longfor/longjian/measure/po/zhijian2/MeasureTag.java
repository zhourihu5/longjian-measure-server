package com.longfor.longjian.measure.po.zhijian2;

import java.util.Date;
import javax.persistence.*;

@Table(name = "measure_tag")
public class MeasureTag {
    @Id
    private Integer id;

    /**
     * 标签名
     */
    private String name;

    /**
     * 集团id zhijian2_apisvr.team.team_id
     */
    @Column(name = "group_id")
    private Integer groupId;

    /**
     * 项目id project.id
     */
    @Column(name = "proj_id")
    private Integer projId;

    /**
     * 归属类型, 1: 项目id 2: 集团id 3: 公司id
     */
    private Integer ownership;

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
     * 获取标签名
     *
     * @return name - 标签名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置标签名
     *
     * @param name 标签名
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取集团id zhijian2_apisvr.team.team_id
     *
     * @return group_id - 集团id zhijian2_apisvr.team.team_id
     */
    public Integer getGroupId() {
        return groupId;
    }

    /**
     * 设置集团id zhijian2_apisvr.team.team_id
     *
     * @param groupId 集团id zhijian2_apisvr.team.team_id
     */
    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    /**
     * 获取项目id project.id
     *
     * @return proj_id - 项目id project.id
     */
    public Integer getProjId() {
        return projId;
    }

    /**
     * 设置项目id project.id
     *
     * @param projId 项目id project.id
     */
    public void setProjId(Integer projId) {
        this.projId = projId;
    }

    /**
     * 获取归属类型, 1: 项目id 2: 集团id 3: 公司id
     *
     * @return ownership - 归属类型, 1: 项目id 2: 集团id 3: 公司id
     */
    public Integer getOwnership() {
        return ownership;
    }

    /**
     * 设置归属类型, 1: 项目id 2: 集团id 3: 公司id
     *
     * @param ownership 归属类型, 1: 项目id 2: 集团id 3: 公司id
     */
    public void setOwnership(Integer ownership) {
        this.ownership = ownership;
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