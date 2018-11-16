package com.longfor.longjian.measure.po.zhijian2_stat;

import javax.persistence.*;

@Table(name = "measure_proj_pass_rate_stat")
public class MeasureProjPassRateStat {
    @Id
    private Integer id;

    /**
     * 时段类型
     */
    @Column(name = "tf_type")
    private Integer tfType;

    /**
     * 年份
     */
    @Column(name = "tf_year")
    private Integer tfYear;

    /**
     * 年内时段序号
     */
    @Column(name = "tf_idx")
    private Integer tfIdx;

    /**
     * 集团ID
     */
    @Column(name = "group_id")
    private Integer groupId;

    /**
     * 分公司ID
     */
    @Column(name = "team_id")
    private Integer teamId;

    /**
     * 项目ID
     */
    @Column(name = "proj_id")
    private Integer projId;

    /**
     * 任务类型
     */
    @Column(name = "root_category_key")
    private String rootCategoryKey;

    /**
     * 版本
     */
    private Integer version;

    /**
     * 测点合格数
     */
    @Column(name = "ok_total")
    private Integer okTotal;

    /**
     * 测点总数
     */
    private Integer total;

    /**
     * 测点合格率
     */
    @Column(name = "ok_rate")
    private Float okRate;

    /**
     * 结果合格数
     */
    @Column(name = "result_passed_count")
    private Integer resultPassedCount;

    /**
     * 结果总数
     */
    @Column(name = "result_count")
    private Integer resultCount;

    /**
     * 结果合格率
     */
    @Column(name = "result_passed_rate")
    private Float resultPassedRate;

    /**
     * 测点合格率排名
     */
    private Integer rank;

    /**
     * 集团内合格率排名
     */
    @Column(name = "rank_in_group")
    private Integer rankInGroup;

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
     * 获取时段类型
     *
     * @return tf_type - 时段类型
     */
    public Integer getTfType() {
        return tfType;
    }

    /**
     * 设置时段类型
     *
     * @param tfType 时段类型
     */
    public void setTfType(Integer tfType) {
        this.tfType = tfType;
    }

    /**
     * 获取年份
     *
     * @return tf_year - 年份
     */
    public Integer getTfYear() {
        return tfYear;
    }

    /**
     * 设置年份
     *
     * @param tfYear 年份
     */
    public void setTfYear(Integer tfYear) {
        this.tfYear = tfYear;
    }

    /**
     * 获取年内时段序号
     *
     * @return tf_idx - 年内时段序号
     */
    public Integer getTfIdx() {
        return tfIdx;
    }

    /**
     * 设置年内时段序号
     *
     * @param tfIdx 年内时段序号
     */
    public void setTfIdx(Integer tfIdx) {
        this.tfIdx = tfIdx;
    }

    /**
     * 获取集团ID
     *
     * @return group_id - 集团ID
     */
    public Integer getGroupId() {
        return groupId;
    }

    /**
     * 设置集团ID
     *
     * @param groupId 集团ID
     */
    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    /**
     * 获取分公司ID
     *
     * @return team_id - 分公司ID
     */
    public Integer getTeamId() {
        return teamId;
    }

    /**
     * 设置分公司ID
     *
     * @param teamId 分公司ID
     */
    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    /**
     * 获取项目ID
     *
     * @return proj_id - 项目ID
     */
    public Integer getProjId() {
        return projId;
    }

    /**
     * 设置项目ID
     *
     * @param projId 项目ID
     */
    public void setProjId(Integer projId) {
        this.projId = projId;
    }

    /**
     * 获取任务类型
     *
     * @return root_category_key - 任务类型
     */
    public String getRootCategoryKey() {
        return rootCategoryKey;
    }

    /**
     * 设置任务类型
     *
     * @param rootCategoryKey 任务类型
     */
    public void setRootCategoryKey(String rootCategoryKey) {
        this.rootCategoryKey = rootCategoryKey == null ? null : rootCategoryKey.trim();
    }

    /**
     * 获取版本
     *
     * @return version - 版本
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * 设置版本
     *
     * @param version 版本
     */
    public void setVersion(Integer version) {
        this.version = version;
    }

    /**
     * 获取测点合格数
     *
     * @return ok_total - 测点合格数
     */
    public Integer getOkTotal() {
        return okTotal;
    }

    /**
     * 设置测点合格数
     *
     * @param okTotal 测点合格数
     */
    public void setOkTotal(Integer okTotal) {
        this.okTotal = okTotal;
    }

    /**
     * 获取测点总数
     *
     * @return total - 测点总数
     */
    public Integer getTotal() {
        return total;
    }

    /**
     * 设置测点总数
     *
     * @param total 测点总数
     */
    public void setTotal(Integer total) {
        this.total = total;
    }

    /**
     * 获取测点合格率
     *
     * @return ok_rate - 测点合格率
     */
    public Float getOkRate() {
        return okRate;
    }

    /**
     * 设置测点合格率
     *
     * @param okRate 测点合格率
     */
    public void setOkRate(Float okRate) {
        this.okRate = okRate;
    }

    /**
     * 获取结果合格数
     *
     * @return result_passed_count - 结果合格数
     */
    public Integer getResultPassedCount() {
        return resultPassedCount;
    }

    /**
     * 设置结果合格数
     *
     * @param resultPassedCount 结果合格数
     */
    public void setResultPassedCount(Integer resultPassedCount) {
        this.resultPassedCount = resultPassedCount;
    }

    /**
     * 获取结果总数
     *
     * @return result_count - 结果总数
     */
    public Integer getResultCount() {
        return resultCount;
    }

    /**
     * 设置结果总数
     *
     * @param resultCount 结果总数
     */
    public void setResultCount(Integer resultCount) {
        this.resultCount = resultCount;
    }

    /**
     * 获取结果合格率
     *
     * @return result_passed_rate - 结果合格率
     */
    public Float getResultPassedRate() {
        return resultPassedRate;
    }

    /**
     * 设置结果合格率
     *
     * @param resultPassedRate 结果合格率
     */
    public void setResultPassedRate(Float resultPassedRate) {
        this.resultPassedRate = resultPassedRate;
    }

    /**
     * 获取测点合格率排名
     *
     * @return rank - 测点合格率排名
     */
    public Integer getRank() {
        return rank;
    }

    /**
     * 设置测点合格率排名
     *
     * @param rank 测点合格率排名
     */
    public void setRank(Integer rank) {
        this.rank = rank;
    }

    /**
     * 获取集团内合格率排名
     *
     * @return rank_in_group - 集团内合格率排名
     */
    public Integer getRankInGroup() {
        return rankInGroup;
    }

    /**
     * 设置集团内合格率排名
     *
     * @param rankInGroup 集团内合格率排名
     */
    public void setRankInGroup(Integer rankInGroup) {
        this.rankInGroup = rankInGroup;
    }
}