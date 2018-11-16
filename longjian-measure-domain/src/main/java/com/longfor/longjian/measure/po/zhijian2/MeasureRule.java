package com.longfor.longjian.measure.po.zhijian2;

import java.util.Date;
import javax.persistence.*;

@Table(name = "measure_rule")
public class MeasureRule {
    @Id
    private Integer id;

    @Column(name = "team_id")
    private Integer teamId;

    @Column(name = "rule_type")
    private Integer ruleType;

    @Column(name = "category_key")
    private String categoryKey;

    private String desc;

    @Column(name = "formula_default")
    private Short formulaDefault;

    @Column(name = "group_count_min")
    private Integer groupCountMin;

    @Column(name = "group_count_max")
    private Integer groupCountMax;

    @Column(name = "group_count_init")
    private Integer groupCountInit;

    private String textures;

    @Column(name = "point_need")
    private Integer pointNeed;

    @Column(name = "create_at")
    private Date createAt;

    @Column(name = "update_at")
    private Date updateAt;

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
     * @return team_id
     */
    public Integer getTeamId() {
        return teamId;
    }

    /**
     * @param teamId
     */
    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    /**
     * @return rule_type
     */
    public Integer getRuleType() {
        return ruleType;
    }

    /**
     * @param ruleType
     */
    public void setRuleType(Integer ruleType) {
        this.ruleType = ruleType;
    }

    /**
     * @return category_key
     */
    public String getCategoryKey() {
        return categoryKey;
    }

    /**
     * @param categoryKey
     */
    public void setCategoryKey(String categoryKey) {
        this.categoryKey = categoryKey == null ? null : categoryKey.trim();
    }

    /**
     * @return desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * @param desc
     */
    public void setDesc(String desc) {
        this.desc = desc == null ? null : desc.trim();
    }

    /**
     * @return formula_default
     */
    public Short getFormulaDefault() {
        return formulaDefault;
    }

    /**
     * @param formulaDefault
     */
    public void setFormulaDefault(Short formulaDefault) {
        this.formulaDefault = formulaDefault;
    }

    /**
     * @return group_count_min
     */
    public Integer getGroupCountMin() {
        return groupCountMin;
    }

    /**
     * @param groupCountMin
     */
    public void setGroupCountMin(Integer groupCountMin) {
        this.groupCountMin = groupCountMin;
    }

    /**
     * @return group_count_max
     */
    public Integer getGroupCountMax() {
        return groupCountMax;
    }

    /**
     * @param groupCountMax
     */
    public void setGroupCountMax(Integer groupCountMax) {
        this.groupCountMax = groupCountMax;
    }

    /**
     * @return group_count_init
     */
    public Integer getGroupCountInit() {
        return groupCountInit;
    }

    /**
     * @param groupCountInit
     */
    public void setGroupCountInit(Integer groupCountInit) {
        this.groupCountInit = groupCountInit;
    }

    /**
     * @return textures
     */
    public String getTextures() {
        return textures;
    }

    /**
     * @param textures
     */
    public void setTextures(String textures) {
        this.textures = textures == null ? null : textures.trim();
    }

    /**
     * @return point_need
     */
    public Integer getPointNeed() {
        return pointNeed;
    }

    /**
     * @param pointNeed
     */
    public void setPointNeed(Integer pointNeed) {
        this.pointNeed = pointNeed;
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
}