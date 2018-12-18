package com.longfor.longjian.measure.app.commonEntity;

import lombok.Data;

@Data
public class MeasureListIssueStruct {
    /**
     * 唯一编码
     */
    private String uuid;
    /**
     * 清单ID
     */
    private Integer list_id;
    /**
     * 所属issue_uuid
     */
    private String issue_uuid;
    /**
     * 提交人user_id
     */
    private Integer sender_id;
    /**
     * 问题描述
     */
    private String desc;
    /**
     * 问题类型
     */
    private Integer typ;
    /**
     * 问题类型
     */
    private Integer status;
    /**
     *  产生Issue时的现场照片，多个用半角逗号“,”分隔
     */
    private String attachment_md5_list;
    /**
     * 任务类型key
     */
    private String category_key;
    /**
     * 客户端记录时间
     */
    private Integer client_create_at;
    /**
     * 所属测区uuid
     */
    private String zone_uuid;
    /**
     *计划完成时间
     */
    private Integer plan_end_on;
    /**
     * 完成时间
     */
    private Integer end_on;
    /**
     * 整改人user_id
     */
    private Integer repairer_id;
    /**
     * 问题严重情况
     */
    private Integer condition;
    /**
     * 区域Id
     */
    private Integer area_id;
    /**
     * 图纸文件Md5
     */
    private String drawing_md5;
    /**
     * 图纸上的位置X
     */
    private Integer pos_x;
    /**
     * 图纸上的位置Y
     */
    private Integer pos_y;
    /**
     * 关闭标志 1=打开 2=关闭
     */
    private Integer close_status;
    /**
     * 关闭人
     */
    private Integer close_user;
    /**
     * 关闭时间
     */
    private Integer close_time;
    /**
     * 审核通过(消项)状态 1=通过 2=不通过
     */
    private Integer check_status;
}
