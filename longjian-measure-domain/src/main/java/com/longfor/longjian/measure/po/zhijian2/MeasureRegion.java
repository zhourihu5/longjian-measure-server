package com.longfor.longjian.measure.po.zhijian2;

import java.util.Date;
import javax.persistence.*;

@Table(name = "measure_region")
public class MeasureRegion {
    @Id
    private Integer id;

    private String uuid;

    @Column(name = "region_index")
    private Integer regionIndex;

    @Column(name = "project_id")
    private Integer projectId;

    @Column(name = "area_id")
    private Integer areaId;

    @Column(name = "rel_id")
    private Integer relId;

    @Column(name = "src_type")
    private Integer srcType;

    @Column(name = "area_path_and_id")
    private String areaPathAndId;

    @Column(name = "drawing_md5")
    private String drawingMd5;

    @Column(name = "create_at")
    private Date createAt;

    @Column(name = "update_at")
    private Date updateAt;

    @Column(name = "delete_at")
    private Date deleteAt;

    @Column(name = "tag_id_list")
    private String tagIdList;

    private String polygon;

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
     * @return uuid
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * @param uuid
     */
    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    /**
     * @return region_index
     */
    public Integer getRegionIndex() {
        return regionIndex;
    }

    /**
     * @param regionIndex
     */
    public void setRegionIndex(Integer regionIndex) {
        this.regionIndex = regionIndex;
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
     * @return area_id
     */
    public Integer getAreaId() {
        return areaId;
    }

    /**
     * @param areaId
     */
    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    /**
     * @return rel_id
     */
    public Integer getRelId() {
        return relId;
    }

    /**
     * @param relId
     */
    public void setRelId(Integer relId) {
        this.relId = relId;
    }

    /**
     * @return src_type
     */
    public Integer getSrcType() {
        return srcType;
    }

    /**
     * @param srcType
     */
    public void setSrcType(Integer srcType) {
        this.srcType = srcType;
    }

    /**
     * @return area_path_and_id
     */
    public String getAreaPathAndId() {
        return areaPathAndId;
    }

    /**
     * @param areaPathAndId
     */
    public void setAreaPathAndId(String areaPathAndId) {
        this.areaPathAndId = areaPathAndId == null ? null : areaPathAndId.trim();
    }

    /**
     * @return drawing_md5
     */
    public String getDrawingMd5() {
        return drawingMd5;
    }

    /**
     * @param drawingMd5
     */
    public void setDrawingMd5(String drawingMd5) {
        this.drawingMd5 = drawingMd5 == null ? null : drawingMd5.trim();
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
     * @return tag_id_list
     */
    public String getTagIdList() {
        return tagIdList;
    }

    /**
     * @param tagIdList
     */
    public void setTagIdList(String tagIdList) {
        this.tagIdList = tagIdList == null ? null : tagIdList.trim();
    }

    /**
     * @return polygon
     */
    public String getPolygon() {
        return polygon;
    }

    /**
     * @param polygon
     */
    public void setPolygon(String polygon) {
        this.polygon = polygon == null ? null : polygon.trim();
    }
}