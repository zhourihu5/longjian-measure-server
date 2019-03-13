package com.longfor.longjian.measure.po.zhijian2;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

public class Area {
    /**
     * 自增id
     */
    @Id
    private Integer id;

    /**
     * 项目id
     */
    @Column(name = "project_id")
    private Integer projectId;

    /**
     * 区域名称
     */
    private String name;

    /**
     * 第三方系统id
     */
    @Column(name = "custom_code")
    private String customCode;

    /**
     * 上级所有路径上的区域id拼接的路径
     */
    private String path;

    /**
     * 区域类型。1:公共区域 ；2:楼栋 ；3:层 ；4:户 ；5:房间 ；6:楼层公区 ；7:别墅 ；99:其他
     */
    private Short type;

    /**
     * 户型id。关联：area_class.id
     */
    @Column(name = "area_class_id")
    private Integer areaClassId;

    /**
     * 是否不可编辑。0:可以编辑；1: 只能编辑名字； 2：不可编辑
     */
    @Column(name = "is_lock")
    private Short isLock;

    /**
     * 排序
     */
    @Column(name = "order_by")
    private Integer orderBy;

    /**
     * 户型图md5。关联：file_resource.md5
     */
    @Column(name = "drawing_md5")
    private String drawingMd5;

    /**
     * 父级id。关联：area.id
     */
    @Column(name = "father_id")
    private Integer fatherId;

    @Column(name = "create_at")
    private Date createAt;

    @Column(name = "update_at")
    private Date updateAt;

    @Column(name = "delete_at")
    private Date deleteAt;

    /**
     * 区域多边形坐标
     */
    private String location;

    /**
     * 获取自增id
     *
     * @return id - 自增id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置自增id
     *
     * @param id 自增id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取项目id
     *
     * @return project_id - 项目id
     */
    public Integer getProjectId() {
        return projectId;
    }

    /**
     * 设置项目id
     *
     * @param projectId 项目id
     */
    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    /**
     * 获取区域名称
     *
     * @return name - 区域名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置区域名称
     *
     * @param name 区域名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取第三方系统id
     *
     * @return custom_code - 第三方系统id
     */
    public String getCustomCode() {
        return customCode;
    }

    /**
     * 设置第三方系统id
     *
     * @param customCode 第三方系统id
     */
    public void setCustomCode(String customCode) {
        this.customCode = customCode == null ? null : customCode.trim();
    }

    /**
     * 获取上级所有路径上的区域id拼接的路径
     *
     * @return path - 上级所有路径上的区域id拼接的路径
     */
    public String getPath() {
        return path;
    }

    /**
     * 设置上级所有路径上的区域id拼接的路径
     *
     * @param path 上级所有路径上的区域id拼接的路径
     */
    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    /**
     * 获取区域类型。1:公共区域 ；2:楼栋 ；3:层 ；4:户 ；5:房间 ；6:楼层公区 ；7:别墅 ；99:其他
     *
     * @return type - 区域类型。1:公共区域 ；2:楼栋 ；3:层 ；4:户 ；5:房间 ；6:楼层公区 ；7:别墅 ；99:其他
     */
    public Short getType() {
        return type;
    }

    /**
     * 设置区域类型。1:公共区域 ；2:楼栋 ；3:层 ；4:户 ；5:房间 ；6:楼层公区 ；7:别墅 ；99:其他
     *
     * @param type 区域类型。1:公共区域 ；2:楼栋 ；3:层 ；4:户 ；5:房间 ；6:楼层公区 ；7:别墅 ；99:其他
     */
    public void setType(Short type) {
        this.type = type;
    }

    /**
     * 获取户型id。关联：area_class.id
     *
     * @return area_class_id - 户型id。关联：area_class.id
     */
    public Integer getAreaClassId() {
        return areaClassId;
    }

    /**
     * 设置户型id。关联：area_class.id
     *
     * @param areaClassId 户型id。关联：area_class.id
     */
    public void setAreaClassId(Integer areaClassId) {
        this.areaClassId = areaClassId;
    }

    /**
     * 获取是否不可编辑。0:可以编辑；1: 只能编辑名字； 2：不可编辑
     *
     * @return is_lock - 是否不可编辑。0:可以编辑；1: 只能编辑名字； 2：不可编辑
     */
    public Short getIsLock() {
        return isLock;
    }

    /**
     * 设置是否不可编辑。0:可以编辑；1: 只能编辑名字； 2：不可编辑
     *
     * @param isLock 是否不可编辑。0:可以编辑；1: 只能编辑名字； 2：不可编辑
     */
    public void setIsLock(Short isLock) {
        this.isLock = isLock;
    }

    /**
     * 获取排序
     *
     * @return order_by - 排序
     */
    public Integer getOrderBy() {
        return orderBy;
    }

    /**
     * 设置排序
     *
     * @param orderBy 排序
     */
    public void setOrderBy(Integer orderBy) {
        this.orderBy = orderBy;
    }

    /**
     * 获取户型图md5。关联：file_resource.md5
     *
     * @return drawing_md5 - 户型图md5。关联：file_resource.md5
     */
    public String getDrawingMd5() {
        return drawingMd5;
    }

    /**
     * 设置户型图md5。关联：file_resource.md5
     *
     * @param drawingMd5 户型图md5。关联：file_resource.md5
     */
    public void setDrawingMd5(String drawingMd5) {
        this.drawingMd5 = drawingMd5 == null ? null : drawingMd5.trim();
    }

    /**
     * 获取父级id。关联：area.id
     *
     * @return father_id - 父级id。关联：area.id
     */
    public Integer getFatherId() {
        return fatherId;
    }

    /**
     * 设置父级id。关联：area.id
     *
     * @param fatherId 父级id。关联：area.id
     */
    public void setFatherId(Integer fatherId) {
        this.fatherId = fatherId;
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

    /**
     * 获取区域多边形坐标
     *
     * @return location - 区域多边形坐标
     */
    public String getLocation() {
        return location;
    }

    /**
     * 设置区域多边形坐标
     *
     * @param location 区域多边形坐标
     */
    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }
}