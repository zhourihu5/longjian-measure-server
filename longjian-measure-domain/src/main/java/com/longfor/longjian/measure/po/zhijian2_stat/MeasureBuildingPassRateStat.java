package com.longfor.longjian.measure.po.zhijian2_stat;

import javax.persistence.*;

@Table(name = "measure_building_pass_rate_stat")
public class MeasureBuildingPassRateStat {
    @Id
    private Integer id;

    @Column(name = "tf_type")
    private Integer tfType;

    @Column(name = "tf_year")
    private Integer tfYear;

    @Column(name = "tf_idx")
    private Integer tfIdx;

    @Column(name = "proj_id")
    private Integer projId;

    @Column(name = "root_category_key")
    private String rootCategoryKey;

    private Integer version;

    @Column(name = "building_id")
    private Integer buildingId;

    @Column(name = "ok_total")
    private Integer okTotal;

    private Integer total;

    @Column(name = "ok_rate")
    private Float okRate;

    @Column(name = "result_passed_count")
    private Integer resultPassedCount;

    @Column(name = "result_count")
    private Integer resultCount;

    @Column(name = "result_passed_rate")
    private Float resultPassedRate;

    private Integer rank;

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
     * @return tf_type
     */
    public Integer getTfType() {
        return tfType;
    }

    /**
     * @param tfType
     */
    public void setTfType(Integer tfType) {
        this.tfType = tfType;
    }

    /**
     * @return tf_year
     */
    public Integer getTfYear() {
        return tfYear;
    }

    /**
     * @param tfYear
     */
    public void setTfYear(Integer tfYear) {
        this.tfYear = tfYear;
    }

    /**
     * @return tf_idx
     */
    public Integer getTfIdx() {
        return tfIdx;
    }

    /**
     * @param tfIdx
     */
    public void setTfIdx(Integer tfIdx) {
        this.tfIdx = tfIdx;
    }

    /**
     * @return proj_id
     */
    public Integer getProjId() {
        return projId;
    }

    /**
     * @param projId
     */
    public void setProjId(Integer projId) {
        this.projId = projId;
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
     * @return version
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * @param version
     */
    public void setVersion(Integer version) {
        this.version = version;
    }

    /**
     * @return building_id
     */
    public Integer getBuildingId() {
        return buildingId;
    }

    /**
     * @param buildingId
     */
    public void setBuildingId(Integer buildingId) {
        this.buildingId = buildingId;
    }

    /**
     * @return ok_total
     */
    public Integer getOkTotal() {
        return okTotal;
    }

    /**
     * @param okTotal
     */
    public void setOkTotal(Integer okTotal) {
        this.okTotal = okTotal;
    }

    /**
     * @return total
     */
    public Integer getTotal() {
        return total;
    }

    /**
     * @param total
     */
    public void setTotal(Integer total) {
        this.total = total;
    }

    /**
     * @return ok_rate
     */
    public Float getOkRate() {
        return okRate;
    }

    /**
     * @param okRate
     */
    public void setOkRate(Float okRate) {
        this.okRate = okRate;
    }

    /**
     * @return result_passed_count
     */
    public Integer getResultPassedCount() {
        return resultPassedCount;
    }

    /**
     * @param resultPassedCount
     */
    public void setResultPassedCount(Integer resultPassedCount) {
        this.resultPassedCount = resultPassedCount;
    }

    /**
     * @return result_count
     */
    public Integer getResultCount() {
        return resultCount;
    }

    /**
     * @param resultCount
     */
    public void setResultCount(Integer resultCount) {
        this.resultCount = resultCount;
    }

    /**
     * @return result_passed_rate
     */
    public Float getResultPassedRate() {
        return resultPassedRate;
    }

    /**
     * @param resultPassedRate
     */
    public void setResultPassedRate(Float resultPassedRate) {
        this.resultPassedRate = resultPassedRate;
    }

    /**
     * @return rank
     */
    public Integer getRank() {
        return rank;
    }

    /**
     * @param rank
     */
    public void setRank(Integer rank) {
        this.rank = rank;
    }
}