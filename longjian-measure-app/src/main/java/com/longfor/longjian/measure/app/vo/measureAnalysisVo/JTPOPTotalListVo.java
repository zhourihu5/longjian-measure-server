package com.longfor.longjian.measure.app.vo.measureAnalysisVo;

import lombok.Data;

/**
 * wangxs
 * 2018-11-16 20:03
 */
@Data
public class JTPOPTotalListVo {
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
    String timeFrameType;
    private Integer year;
}
