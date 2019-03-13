package com.longfor.longjian.measure.po.zhijian2;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "measure_zone")
public class MeasureZone {
    @Id
    private Integer id;

    /**
     * 所属项目Id project.id
     */
    @Column(name = "project_id")
    private Integer projectId;

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
     * uuid
     */
    private String uuid;

    /**
     * 任务类型Key category_v3.key
     */
    @Column(name = "category_key")
    private String categoryKey;

    /**
     * 任务类型路径
     */
    @Column(name = "category_path_and_key")
    private String categoryPathAndKey;

    /**
     * 关闭状态：1、打开；2、关闭
     */
    @Column(name = "close_status")
    private Integer closeStatus;

    /**
     * 完成状态：1、进行中；2、已完成
     */
    @Column(name = "finish_status")
    private Integer finishStatus;

    /**
     * 所属清单Id measure_list.id
     */
    @Column(name = "list_id")
    private Integer listId;

    /**
     * 所含描画区域Id measure_region.uuid
     */
    @Column(name = "region_uuid")
    private String regionUuid;

    /**
     * 来源类型：1，后台添加；2，客户端添加
     */
    @Column(name = "src_type")
    private Integer srcType;

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
     * 获取uuid
     *
     * @return uuid - uuid
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * 设置uuid
     *
     * @param uuid uuid
     */
    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    /**
     * 获取任务类型Key category_v3.key
     *
     * @return category_key - 任务类型Key category_v3.key
     */
    public String getCategoryKey() {
        return categoryKey;
    }

    /**
     * 设置任务类型Key category_v3.key
     *
     * @param categoryKey 任务类型Key category_v3.key
     */
    public void setCategoryKey(String categoryKey) {
        this.categoryKey = categoryKey == null ? null : categoryKey.trim();
    }

    /**
     * 获取任务类型路径
     *
     * @return category_path_and_key - 任务类型路径
     */
    public String getCategoryPathAndKey() {
        return categoryPathAndKey;
    }

    /**
     * 设置任务类型路径
     *
     * @param categoryPathAndKey 任务类型路径
     */
    public void setCategoryPathAndKey(String categoryPathAndKey) {
        this.categoryPathAndKey = categoryPathAndKey == null ? null : categoryPathAndKey.trim();
    }

    /**
     * 获取关闭状态：1、打开；2、关闭
     *
     * @return close_status - 关闭状态：1、打开；2、关闭
     */
    public Integer getCloseStatus() {
        return closeStatus;
    }

    /**
     * 设置关闭状态：1、打开；2、关闭
     *
     * @param closeStatus 关闭状态：1、打开；2、关闭
     */
    public void setCloseStatus(Integer closeStatus) {
        this.closeStatus = closeStatus;
    }

    /**
     * 获取完成状态：1、进行中；2、已完成
     *
     * @return finish_status - 完成状态：1、进行中；2、已完成
     */
    public Integer getFinishStatus() {
        return finishStatus;
    }

    /**
     * 设置完成状态：1、进行中；2、已完成
     *
     * @param finishStatus 完成状态：1、进行中；2、已完成
     */
    public void setFinishStatus(Integer finishStatus) {
        this.finishStatus = finishStatus;
    }

    /**
     * 获取所属清单Id measure_list.id
     *
     * @return list_id - 所属清单Id measure_list.id
     */
    public Integer getListId() {
        return listId;
    }

    /**
     * 设置所属清单Id measure_list.id
     *
     * @param listId 所属清单Id measure_list.id
     */
    public void setListId(Integer listId) {
        this.listId = listId;
    }

    /**
     * 获取所含描画区域Id measure_region.uuid
     *
     * @return region_uuid - 所含描画区域Id measure_region.uuid
     */
    public String getRegionUuid() {
        return regionUuid;
    }

    /**
     * 设置所含描画区域Id measure_region.uuid
     *
     * @param regionUuid 所含描画区域Id measure_region.uuid
     */
    public void setRegionUuid(String regionUuid) {
        this.regionUuid = regionUuid == null ? null : regionUuid.trim();
    }

    /**
     * 获取来源类型：1，后台添加；2，客户端添加
     *
     * @return src_type - 来源类型：1，后台添加；2，客户端添加
     */
    public Integer getSrcType() {
        return srcType;
    }

    /**
     * 设置来源类型：1，后台添加；2，客户端添加
     *
     * @param srcType 来源类型：1，后台添加；2，客户端添加
     */
    public void setSrcType(Integer srcType) {
        this.srcType = srcType;
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