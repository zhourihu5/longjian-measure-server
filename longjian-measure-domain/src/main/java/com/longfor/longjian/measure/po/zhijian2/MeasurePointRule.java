package com.longfor.longjian.measure.po.zhijian2;

import javax.persistence.*;

@Table(name = "measure_point_rule")
public class MeasurePointRule {
    private String key;

    private String name;

    @Column(name = "point_type")
    private Integer pointType;

    @Column(name = "count_min")
    private Integer countMin;

    @Column(name = "count_max")
    private Integer countMax;

    @Column(name = "count_init")
    private Integer countInit;

    @Column(name = "data_type")
    private Integer dataType;

    @Column(name = "design_value_reqd")
    private Boolean designValueReqd;

    @Column(name = "allow_range")
    private String allowRange;

    /**
     * @return key
     */
    public String getKey() {
        return key;
    }

    /**
     * @param key
     */
    public void setKey(String key) {
        this.key = key == null ? null : key.trim();
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * @return point_type
     */
    public Integer getPointType() {
        return pointType;
    }

    /**
     * @param pointType
     */
    public void setPointType(Integer pointType) {
        this.pointType = pointType;
    }

    /**
     * @return count_min
     */
    public Integer getCountMin() {
        return countMin;
    }

    /**
     * @param countMin
     */
    public void setCountMin(Integer countMin) {
        this.countMin = countMin;
    }

    /**
     * @return count_max
     */
    public Integer getCountMax() {
        return countMax;
    }

    /**
     * @param countMax
     */
    public void setCountMax(Integer countMax) {
        this.countMax = countMax;
    }

    /**
     * @return count_init
     */
    public Integer getCountInit() {
        return countInit;
    }

    /**
     * @param countInit
     */
    public void setCountInit(Integer countInit) {
        this.countInit = countInit;
    }

    /**
     * @return data_type
     */
    public Integer getDataType() {
        return dataType;
    }

    /**
     * @param dataType
     */
    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }

    /**
     * @return design_value_reqd
     */
    public Boolean getDesignValueReqd() {
        return designValueReqd;
    }

    /**
     * @param designValueReqd
     */
    public void setDesignValueReqd(Boolean designValueReqd) {
        this.designValueReqd = designValueReqd;
    }

    /**
     * @return allow_range
     */
    public String getAllowRange() {
        return allowRange;
    }

    /**
     * @param allowRange
     */
    public void setAllowRange(String allowRange) {
        this.allowRange = allowRange == null ? null : allowRange.trim();
    }
}