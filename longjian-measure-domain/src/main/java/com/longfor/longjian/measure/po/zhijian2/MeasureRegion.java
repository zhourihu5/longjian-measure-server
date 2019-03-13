package com.longfor.longjian.measure.po.zhijian2;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "measure_region")
public class MeasureRegion {
    @GeneratedValue(generator = "JDBC")
    @Id
    private Integer id;

    /**
     * 所属项目id project.id
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
     * 图纸md5
     */
    @Column(name = "drawing_md5")
    private String drawingMd5;

    /**
     * 点
     */
    private String polygon;

    /**
     * 项目下描画区域序号
     */
    @Column(name = "region_index")
    private Integer regionIndex;

    /**
     * 所属描画区域关联关系Id measure_region_rel.id
     */
    @Column(name = "rel_id")
    private Integer relId;

    /**
     * 来源类型：1，后台添加；2，客户端添加
     */
    @Column(name = "src_type")
    private Integer srcType;

    /**
     * uuid
     */
    private String uuid;

    /**
     * 标签id列表以逗号分隔 measure_tag.id
     */
    @Column(name = "tag_id_list")
    private String tagIdList;

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
     * 获取图纸md5
     *
     * @return drawing_md5 - 图纸md5
     */
    public String getDrawingMd5() {
        return drawingMd5;
    }

    /**
     * 设置图纸md5
     *
     * @param drawingMd5 图纸md5
     */
    public void setDrawingMd5(String drawingMd5) {
        this.drawingMd5 = drawingMd5 == null ? null : drawingMd5.trim();
    }

    /**
     * 获取点
     *
     * @return polygon - 点
     */
    public String getPolygon() {
        return polygon;
    }

    /**
     * 设置点
     *
     * @param polygon 点
     */
    public void setPolygon(String polygon) {
        this.polygon = polygon == null ? null : polygon.trim();
    }

    /**
     * 获取项目下描画区域序号
     *
     * @return region_index - 项目下描画区域序号
     */
    public Integer getRegionIndex() {
        return regionIndex;
    }

    /**
     * 设置项目下描画区域序号
     *
     * @param regionIndex 项目下描画区域序号
     */
    public void setRegionIndex(Integer regionIndex) {
        this.regionIndex = regionIndex;
    }

    /**
     * 获取所属描画区域关联关系Id measure_region_rel.id
     *
     * @return rel_id - 所属描画区域关联关系Id measure_region_rel.id
     */
    public Integer getRelId() {
        return relId;
    }

    /**
     * 设置所属描画区域关联关系Id measure_region_rel.id
     *
     * @param relId 所属描画区域关联关系Id measure_region_rel.id
     */
    public void setRelId(Integer relId) {
        this.relId = relId;
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
     * 获取标签id列表以逗号分隔 measure_tag.id
     *
     * @return tag_id_list - 标签id列表以逗号分隔 measure_tag.id
     */
    public String getTagIdList() {
        return tagIdList;
    }

    /**
     * 设置标签id列表以逗号分隔 measure_tag.id
     *
     * @param tagIdList 标签id列表以逗号分隔 measure_tag.id
     */
    public void setTagIdList(String tagIdList) {
        this.tagIdList = tagIdList == null ? null : tagIdList.trim();
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