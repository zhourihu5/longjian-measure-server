package com.longfor.longjian.measure.po.zhijian2;

import java.util.Date;
import javax.persistence.*;

@Table(name = "measure_repairer_user")
public class MeasureRepairerUser {
    @Id
    private Integer id;

    /**
     * 所属清单Id
     */
    @Column(name = "list_id")
    private Integer listId;

    /**
     * 所属项目id project.id
     */
    @Column(name = "project_id")
    private Integer projectId;

    /**
     * 角色类型
     */
    @Column(name = "role_type")
    private Integer roleType;

    /**
     * 关联用户Id zhijian2_apisvr.user.id
     */
    @Column(name = "user_id")
    private Integer userId;

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
     * 获取所属清单Id
     *
     * @return list_id - 所属清单Id
     */
    public Integer getListId() {
        return listId;
    }

    /**
     * 设置所属清单Id
     *
     * @param listId 所属清单Id
     */
    public void setListId(Integer listId) {
        this.listId = listId;
    }

    /**
     * 获取所属项目id project.id
     *
     * @return project_id - 所属项目id project.id
     */
    public Integer getProjectId() {
        return projectId;
    }

    /**
     * 设置所属项目id project.id
     *
     * @param projectId 所属项目id project.id
     */
    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    /**
     * 获取角色类型
     *
     * @return role_type - 角色类型
     */
    public Integer getRoleType() {
        return roleType;
    }

    /**
     * 设置角色类型
     *
     * @param roleType 角色类型
     */
    public void setRoleType(Integer roleType) {
        this.roleType = roleType;
    }

    /**
     * 获取关联用户Id zhijian2_apisvr.user.id
     *
     * @return user_id - 关联用户Id zhijian2_apisvr.user.id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置关联用户Id zhijian2_apisvr.user.id
     *
     * @param userId 关联用户Id zhijian2_apisvr.user.id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
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