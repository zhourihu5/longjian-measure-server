package com.longfor.longjian.measure.app.commonentity;

import lombok.Data;

@Data
@SuppressWarnings("squid:S1068")
public class MeasureListIssueStruct {
    /**
     * 唯一编码
     */
    @SuppressWarnings("squid:S00116")
    private String uuid;
    /**
     * 清单ID
     */
    @SuppressWarnings("squid:S00116")
    private Integer list_id;
    /**
     * 所属issue_uuid
     */
    @SuppressWarnings("squid:S00116")
    private String issue_uuid;
    /**
     * 提交人user_id
     */
    @SuppressWarnings("squid:S00116")
    private Integer sender_id;
    /**
     * 问题描述
     */
    @SuppressWarnings("squid:S00116")
    private String desc;
    /**
     * 问题类型
     */
    @SuppressWarnings("squid:S00116")
    private Integer typ;
    /**
     * 问题类型
     */
    @SuppressWarnings("squid:S00116")
    private Integer status;
    /**
     *  产生Issue时的现场照片，多个用半角逗号“,”分隔
     */
    @SuppressWarnings("squid:S00116")
    private String attachment_md5_list;
    /**
     * 任务类型key
     */
    @SuppressWarnings("squid:S00116")
    private String category_key;
    /**
     * 客户端记录时间
     */
    @SuppressWarnings("squid:S00116")
    private Integer client_create_at;
    /**
     * 所属测区uuid
     */
    @SuppressWarnings("squid:S00116")
    private String zone_uuid;
    /**
     *计划完成时间
     */
    @SuppressWarnings("squid:S00116")
    private Integer plan_end_on;
    /**
     * 完成时间
     */
    @SuppressWarnings("squid:S00116")
    private Integer end_on;
    /**
     * 整改人user_id
     */
    @SuppressWarnings("squid:S00116")
    private Integer repairer_id;
    /**
     * 问题严重情况
     */
    @SuppressWarnings("squid:S00116")
    private Integer condition;
    /**
     * 区域Id
     */
    @SuppressWarnings("squid:S00116")
    private Integer area_id;
    /**
     * 图纸文件Md5
     */
    @SuppressWarnings("squid:S00116")
    private String drawing_md5;
    /**
     * 图纸上的位置X
     */
    @SuppressWarnings("squid:S00116")
    private Integer pos_x;
    /**
     * 图纸上的位置Y
     */
    @SuppressWarnings("squid:S00116")
    private Integer pos_y;
    /**
     * 关闭标志 1=打开 2=关闭
     */
    @SuppressWarnings("squid:S00116")
    private Integer close_status;
    /**
     * 关闭人
     */
    @SuppressWarnings("squid:S00116")
    private Integer close_user;
    /**
     * 关闭时间
     */
    @SuppressWarnings("squid:S00116")
    private Integer close_time;
    /**
     * 审核通过(消项)状态 1=通过 2=不通过
     */
    @SuppressWarnings("squid:S00116")
    private Integer check_status;
}
