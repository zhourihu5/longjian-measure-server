package com.longfor.longjian.measure.po.zhijian2;

import javax.persistence.*;

@Table(name = "measure_point_rule")
public class MeasurePointRule {
    @Id
    private Integer id;

    /**
     * key
     */
    private String key;

    /**
     * 允许范围
     */
    @Column(name = "allow_range")
    private String allowRange;

    /**
     * 初始测点数
     */
    @Column(name = "count_init")
    private Integer countInit;

    /**
     * 最大测点数
     */
    @Column(name = "count_max")
    private Integer countMax;

    /**
     * 最小测点数
     */
    @Column(name = "count_min")
    private Integer countMin;

    /**
     * 要求数据类型：1，boolean；2，整数(int)；14，浮点数（float64）
     */
    @Column(name = "data_type")
    private Integer dataType;

    /**
     * 是否需要录入设计
     */
    @Column(name = "design_value_reqd")
    private Integer designValueReqd;

    /**
     * 测点名称
     */
    private String name;

    /**
     * 测点类型：1，选择值；2，输入值
     */
    @Column(name = "point_type")
    private Integer pointType;

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
     * 获取key
     *
     * @return key - key
     */
    public String getKey() {
        return key;
    }

    /**
     * 设置key
     *
     * @param key key
     */
    public void setKey(String key) {
        this.key = key == null ? null : key.trim();
    }

    /**
     * 获取允许范围
     *
     * @return allow_range - 允许范围
     */
    public String getAllowRange() {
        return allowRange;
    }

    /**
     * 设置允许范围
     *
     * @param allowRange 允许范围
     */
    public void setAllowRange(String allowRange) {
        this.allowRange = allowRange == null ? null : allowRange.trim();
    }

    /**
     * 获取初始测点数
     *
     * @return count_init - 初始测点数
     */
    public Integer getCountInit() {
        return countInit;
    }

    /**
     * 设置初始测点数
     *
     * @param countInit 初始测点数
     */
    public void setCountInit(Integer countInit) {
        this.countInit = countInit;
    }

    /**
     * 获取最大测点数
     *
     * @return count_max - 最大测点数
     */
    public Integer getCountMax() {
        return countMax;
    }

    /**
     * 设置最大测点数
     *
     * @param countMax 最大测点数
     */
    public void setCountMax(Integer countMax) {
        this.countMax = countMax;
    }

    /**
     * 获取最小测点数
     *
     * @return count_min - 最小测点数
     */
    public Integer getCountMin() {
        return countMin;
    }

    /**
     * 设置最小测点数
     *
     * @param countMin 最小测点数
     */
    public void setCountMin(Integer countMin) {
        this.countMin = countMin;
    }

    /**
     * 获取要求数据类型：1，boolean；2，整数(int)；14，浮点数（float64）
     *
     * @return data_type - 要求数据类型：1，boolean；2，整数(int)；14，浮点数（float64）
     */
    public Integer getDataType() {
        return dataType;
    }

    /**
     * 设置要求数据类型：1，boolean；2，整数(int)；14，浮点数（float64）
     *
     * @param dataType 要求数据类型：1，boolean；2，整数(int)；14，浮点数（float64）
     */
    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }

    /**
     * 获取是否需要录入设计
     *
     * @return design_value_reqd - 是否需要录入设计
     */
    public Integer getDesignValueReqd() {
        return designValueReqd;
    }

    /**
     * 设置是否需要录入设计
     *
     * @param designValueReqd 是否需要录入设计
     */
    public void setDesignValueReqd(Integer designValueReqd) {
        this.designValueReqd = designValueReqd;
    }

    /**
     * 获取测点名称
     *
     * @return name - 测点名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置测点名称
     *
     * @param name 测点名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取测点类型：1，选择值；2，输入值
     *
     * @return point_type - 测点类型：1，选择值；2，输入值
     */
    public Integer getPointType() {
        return pointType;
    }

    /**
     * 设置测点类型：1，选择值；2，输入值
     *
     * @param pointType 测点类型：1，选择值；2，输入值
     */
    public void setPointType(Integer pointType) {
        this.pointType = pointType;
    }
}