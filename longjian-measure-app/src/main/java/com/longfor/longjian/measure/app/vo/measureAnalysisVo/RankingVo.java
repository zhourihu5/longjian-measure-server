package com.longfor.longjian.measure.app.vo.measureAnalysisVo;

import lombok.Data;

/**
 * wangxs
 * 2018-11-16 20:03
 */
@Data
public class RankingVo {

    /**
     * 集团统计分析统计指标合格率 + 每天
     */
    private Integer groupId;
    private String groupName;
    private Integer issueCount;
    private Integer issueIntimeChecked;
    private Integer issueIntimeToAssign;
    private Integer issueIntimeToCheck;
    private Integer issueIntimeToReform;
    private Integer issueNotsetChecked;
    private Integer issueNotsetToAssign;
    private Integer issueNotsetToCheck;
    private Integer issueNotsetToReform;
    private Integer issueOverdueChecked;
    private Integer issueOverdueToAssign;
    private Integer issueOverdueToCheck;
    private Integer issueOverdueToReform;
    private Integer pointOkTotal;
    private Integer pointTotal;
    private Integer timeFrameIdx;
    private String timeFrameType;
    private Integer year;


    /**
     * 集团统计分析组织对比公司排名
     */
    private Integer lastRank;
    private Double lastRate;
    private Integer rank;
    private Double rate;
    private Integer teamId;
    private String teamName;

    /**
     * 集团统计分析组织对项目对比
     */
    private Integer projId;
    private String projName;

    /**
     * 总包单位对比，分包单位对比，监理单位对比
     */
    private Integer partnerId;
    private String partnerName;
    private String roleType;


    /**
     *集团使用功能统计横向分析查询
     * 集团使用功能统计趋势查询
     */
    private Integer actionCount;
    private Integer actionCountPerUser;
    private Integer activeUserRate;
    private Integer activeUserTotal;
    private Integer checkedCount;
    private Integer issueCheckedCount;
    private Integer issueCreatedCount;
    private Integer issueReformedCount;
    private Double projArea;
    private Integer userTotal;


    /**
     * 项目实测实量统计分析
     */
    private Integer finishedZoneCount;
    private Integer issueZoneCount;
    private Integer totalZoneCount;

    private Integer areaId;
    private String areaName;

    private String categoryKey;
    private String categoryName;



    /**
     * 对比人员横向查询
     */
    private Integer partnerUserId;
    private Integer partnerUserName;

    /**
     * 项目统计分析人员对比工作量查询
     */
    private Integer userId;
    private String userName;



}