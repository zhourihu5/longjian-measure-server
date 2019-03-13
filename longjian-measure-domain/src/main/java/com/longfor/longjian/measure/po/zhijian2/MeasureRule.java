package com.longfor.longjian.measure.po.zhijian2;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "measure_rule")
public class MeasureRule {
    @Id
    private Integer id;

    /**
     * 所属任务类型Key category_v3.key
     */
    @Column(name = "category_key")
    private String categoryKey;

    /**
     * 规则说明
     */
    @Column(name = "`desc`")
    private String desc;

    /**
     * 公式
     */
    private String formula;

    /**
     * 自定义公式
     */
    @Column(name = "formula_default")
    private Integer formulaDefault;

    /**
     * 初始组数
     */
    @Column(name = "group_count_init")
    private Integer groupCountInit;

    /**
     * 最大组数
     */
    @Column(name = "group_count_max")
    private Integer groupCountMax;

    /**
     * 最小组数
     */
    @Column(name = "group_count_min")
    private Integer groupCountMin;

    /**
     * 必填测点名数
     */
    @Column(name = "point_need")
    private Integer pointNeed;

    /**
     * 测点规则集。调用公式时，按照本字段顺序传递参
     */
    private String points;

    /**
     * 规则类型
     */
    @Column(name = "rule_type")
    private Integer ruleType;

    /**
     * 所属集团Id zhijian2_apisvr.team.id
     */
    @Column(name = "team_id")
    private Integer teamId;

    /**
     * 测点可选类型(材质),半角逗号分
     */
    private String textures;

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
     * 获取所属任务类型Key category_v3.key
     *
     * @return category_key - 所属任务类型Key category_v3.key
     */
    public String getCategoryKey() {
        return categoryKey;
    }

    /**
     * 设置所属任务类型Key category_v3.key
     *
     * @param categoryKey 所属任务类型Key category_v3.key
     */
    public void setCategoryKey(String categoryKey) {
        this.categoryKey = categoryKey == null ? null : categoryKey.trim();
    }

    /**
     * 获取规则说明
     *
     * @return desc - 规则说明
     */
    public String getDesc() {
        return desc;
    }

    /**
     * 设置规则说明
     *
     * @param desc 规则说明
     */
    public void setDesc(String desc) {
        this.desc = desc == null ? null : desc.trim();
    }

    /**
     * 获取公式
     *
     * @return formula - 公式
     */
    public String getFormula() {
        return formula;
    }

    /**
     * 设置公式
     *
     * @param formula 公式
     */
    public void setFormula(String formula) {
        this.formula = formula == null ? null : formula.trim();
    }

    /**
     * 获取自定义公式
     *
     * @return formula_default - 自定义公式
     */
    public Integer getFormulaDefault() {
        return formulaDefault;
    }

    /**
     * 设置自定义公式
     *
     * @param formulaDefault 自定义公式
     */
    public void setFormulaDefault(Integer formulaDefault) {
        this.formulaDefault = formulaDefault;
    }

    /**
     * 获取初始组数
     *
     * @return group_count_init - 初始组数
     */
    public Integer getGroupCountInit() {
        return groupCountInit;
    }

    /**
     * 设置初始组数
     *
     * @param groupCountInit 初始组数
     */
    public void setGroupCountInit(Integer groupCountInit) {
        this.groupCountInit = groupCountInit;
    }

    /**
     * 获取最大组数
     *
     * @return group_count_max - 最大组数
     */
    public Integer getGroupCountMax() {
        return groupCountMax;
    }

    /**
     * 设置最大组数
     *
     * @param groupCountMax 最大组数
     */
    public void setGroupCountMax(Integer groupCountMax) {
        this.groupCountMax = groupCountMax;
    }

    /**
     * 获取最小组数
     *
     * @return group_count_min - 最小组数
     */
    public Integer getGroupCountMin() {
        return groupCountMin;
    }

    /**
     * 设置最小组数
     *
     * @param groupCountMin 最小组数
     */
    public void setGroupCountMin(Integer groupCountMin) {
        this.groupCountMin = groupCountMin;
    }

    /**
     * 获取必填测点名数
     *
     * @return point_need - 必填测点名数
     */
    public Integer getPointNeed() {
        return pointNeed;
    }

    /**
     * 设置必填测点名数
     *
     * @param pointNeed 必填测点名数
     */
    public void setPointNeed(Integer pointNeed) {
        this.pointNeed = pointNeed;
    }

    /**
     * 获取测点规则集。调用公式时，按照本字段顺序传递参
     *
     * @return points - 测点规则集。调用公式时，按照本字段顺序传递参
     */
    public String getPoints() {
        return points;
    }

    /**
     * 设置测点规则集。调用公式时，按照本字段顺序传递参
     *
     * @param points 测点规则集。调用公式时，按照本字段顺序传递参
     */
    public void setPoints(String points) {
        this.points = points == null ? null : points.trim();
    }

    /**
     * 获取规则类型
     *
     * @return rule_type - 规则类型
     */
    public Integer getRuleType() {
        return ruleType;
    }

    /**
     * 设置规则类型
     *
     * @param ruleType 规则类型
     */
    public void setRuleType(Integer ruleType) {
        this.ruleType = ruleType;
    }

    /**
     * 获取所属集团Id zhijian2_apisvr.team.id
     *
     * @return team_id - 所属集团Id zhijian2_apisvr.team.id
     */
    public Integer getTeamId() {
        return teamId;
    }

    /**
     * 设置所属集团Id zhijian2_apisvr.team.id
     *
     * @param teamId 所属集团Id zhijian2_apisvr.team.id
     */
    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    /**
     * 获取测点可选类型(材质),半角逗号分
     *
     * @return textures - 测点可选类型(材质),半角逗号分
     */
    public String getTextures() {
        return textures;
    }

    /**
     * 设置测点可选类型(材质),半角逗号分
     *
     * @param textures 测点可选类型(材质),半角逗号分
     */
    public void setTextures(String textures) {
        this.textures = textures == null ? null : textures.trim();
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