package com.longfor.longjian.measure.app.vo.appMeasureSyncVo;

import lombok.Data;

/**
 * wangxs
 * 2018-11-20
 */
@Data
public class IssueLogListVo {
    /**
     * ID
     */
    private Integer id;
    /**
     * 唯一编码
     */
    private String uuid;
    /**
     * 清单ID
     */
    private Integer list_id;
    /**
     * 所属测区uuid
     */
    private String zone_uuid;
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
     * 任务类型key
     */
    private String category_key;
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
     * 区域Id
     */
    private Integer area_id;
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
    /**
     * 客户端记录时间
     */
    private Integer client_create_at;
    /**
     * 更新时间
     */
    private Integer update_at;
    /**
     * 删除时间(`0`表示未删除)
     */
    private Integer delete_at;
}
