package com.longfor.longjian.measure.po.zhijian2;

import tk.mybatis.mapper.annotation.KeySql;

import java.util.Date;
import javax.persistence.*;

@Table(name = "measure_squad")
public class MeasureSquad {
    @Id
    private Integer id;

    /**
     * 所属清单id measure_list.id
     */
    @Column(name = "list_id")
    private Integer listId;

    /**
     * 所属项目Id project.id
     */
    @Column(name = "project_id")
    private Integer projectId;

    /**
     * 组名
     */
    private String name;

    /**
     * 计划检查率
     */
    @Column(name = "plan_rate")
    private Integer planRate;

    /**
     * 实际检查率(可能非实时)
     */
    private Integer rate;

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
     * 获取所属清单id measure_list.id
     *
     * @return list_id - 所属清单id measure_list.id
     */
    public Integer getListId() {
        return listId;
    }

    /**
     * 设置所属清单id measure_list.id
     *
     * @param listId 所属清单id measure_list.id
     */
    public void setListId(Integer listId) {
        this.listId = listId;
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
     * 获取组名
     *
     * @return name - 组名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置组名
     *
     * @param name 组名
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取计划检查率
     *
     * @return plan_rate - 计划检查率
     */
    public Integer getPlanRate() {
        return planRate;
    }

    /**
     * 设置计划检查率
     *
     * @param planRate 计划检查率
     */
    public void setPlanRate(Integer planRate) {
        this.planRate = planRate;
    }

    /**
     * 获取实际检查率(可能非实时)
     *
     * @return rate - 实际检查率(可能非实时)
     */
    public Integer getRate() {
        return rate;
    }

    /**
     * 设置实际检查率(可能非实时)
     *
     * @param rate 实际检查率(可能非实时)
     */
    public void setRate(Integer rate) {
        this.rate = rate;
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