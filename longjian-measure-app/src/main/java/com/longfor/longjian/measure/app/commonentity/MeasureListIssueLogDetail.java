package com.longfor.longjian.measure.app.commonentity;

import lombok.Data;

@Data
@SuppressWarnings("squid:S1068")
public class MeasureListIssueLogDetail {
    /**
     * 测区Uuid
     */
    @SuppressWarnings("squid:S00116")
    private String ZoneUuid;
    /**
     * 计划完成时间
     */
    @SuppressWarnings("squid:S00116")
    private Long PlanEndOn;
    /**
     * 实际完成时间
     */
    @SuppressWarnings("squid:S00116")
    private Long EndOn;
    /**
     * 修复人ID
     */
    @SuppressWarnings("squid:S00116")
    private Integer RepairerId;
    /**
     * 严重程度
     */
    @SuppressWarnings("squid:S00116")
    private Integer Condition;
    /**
     * 区域ID
     */
    @SuppressWarnings("squid:S00116")
    private Integer AreaId;
    /**
     * 图纸文件Md5
     */
    @SuppressWarnings("squid:S00116")
    private String DrawingMd5;
    /**
     * 在图纸上的位置X
     */
    @SuppressWarnings("squid:S00116")
    private Integer PosX;
    /**
     * 在图纸上的位置Y
     */
    @SuppressWarnings("squid:S00116")
    private Integer PosY;
    /**
     *  开启 2 关闭
     */
    @SuppressWarnings("squid:S00116")
    private Integer CloseStatus;
    /**
     * 关闭人
     */
    @SuppressWarnings("squid:S00116")
    private Integer CloseUser;
    /**
     * 关闭时间
     */
    @SuppressWarnings("squid:S00116")
    private  Long CloseTime;
    /**
     * 审核状态
     */
    @SuppressWarnings("squid:S00116")
    private Integer CheckStatus;
}
