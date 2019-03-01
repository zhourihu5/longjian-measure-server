package com.longfor.longjian.measure.app.commonentity;

import lombok.Data;

@Data
@SuppressWarnings("squid:S1068")
public class MeasureListIssueLogDetail {
    /**
     * 测区Uuid
     */
    private String ZoneUuid;
    /**
     * 计划完成时间
     */
    private Long PlanEndOn;
    /**
     * 实际完成时间
     */
    private Long EndOn;
    /**
     * 修复人ID
     */
    private Integer RepairerId;
    /**
     * 严重程度
     */
    private Integer Condition;
    /**
     * 区域ID
     */
    private Integer AreaId;
    /**
     * 图纸文件Md5
     */
    private String DrawingMd5;
    /**
     * 在图纸上的位置X
     */
    private Integer PosX;
    /**
     * 在图纸上的位置Y
     */
    private Integer PosY;
    /**
     *  开启 2 关闭
     */
    private Integer CloseStatus;
    /**
     * 关闭人
     */
    private Integer CloseUser;
    /**
     * 关闭时间
     */
    private  Long CloseTime;
    /**
     * 审核状态
     */
    private Integer CheckStatus;
}
