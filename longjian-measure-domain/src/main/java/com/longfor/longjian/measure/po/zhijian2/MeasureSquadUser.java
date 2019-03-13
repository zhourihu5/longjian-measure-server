package com.longfor.longjian.measure.po.zhijian2;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "measure_squad_user")
public class MeasureSquadUser {
    @Id
    private Integer id;

    /**
     * 所属清单id measure_list.id
     */
    @Column(name = "list_id")
    private Integer listId;

    /**
     * 所属项目id project.id
     */
    @Column(name = "project_id")
    private Integer projectId;

    /**
     * 测量小组id measure_squad.id
     */
    @Column(name = "squad_id")
    private Integer squadId;

    /**
     * 关联用户id zhijian2_apisvr.user.id
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
     * 获取测量小组id measure_squad.id
     *
     * @return squad_id - 测量小组id measure_squad.id
     */
    public Integer getSquadId() {
        return squadId;
    }

    /**
     * 设置测量小组id measure_squad.id
     *
     * @param squadId 测量小组id measure_squad.id
     */
    public void setSquadId(Integer squadId) {
        this.squadId = squadId;
    }

    /**
     * 获取关联用户id zhijian2_apisvr.user.id
     *
     * @return user_id - 关联用户id zhijian2_apisvr.user.id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置关联用户id zhijian2_apisvr.user.id
     *
     * @param userId 关联用户id zhijian2_apisvr.user.id
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