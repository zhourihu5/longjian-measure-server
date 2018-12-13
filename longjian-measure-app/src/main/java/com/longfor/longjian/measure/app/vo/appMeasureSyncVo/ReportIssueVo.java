package com.longfor.longjian.measure.app.vo.appMeasureSyncVo;

import lombok.Data;

/**
 * wangxs
 * 2018-11-20
 */
@Data
public class ReportIssueVo {
    /**
     * ID
     */
    private Integer id;
    /**
     * 唯一编码
     */
    private String uuid;
    /**
     * 项目ID
     */
    private Integer project_id;
    /**
     * 任务类型key
     */
    private String category_key;
    /**
     * 任务类型路径
     */
    private String category_path_and_key;
    /**
     * 清单ID
     */
    private Integer list_id;
    /**
     * 描画区域UUID
     */
    private String region_uuid;
    /**
     * 区域Id
     */
    private Integer area_id;
    /**
     * 区域路径及ID
     */
    private String area_path_and_id;
    /**
     * 更新时间
     */
    private Integer update_at;
    /**
     * 删除时间(`0`表示未删除)
     */
    private Integer delete_at;
    /**
     * 所属测区uuid
     */
    private String zone_uuid;
    /**
     * 小队Id
     */
    private Integer squad_id;
    private String drawing_md5;
    /**
     * 提交人user_id
     */
    private Integer sender_id;
    /**
     * 整改人user_id
     */
    private Integer repairer_id;
    /**
     * 问题类型
     */
    private Integer typ;
    /**
     * 问题类型
     */
    private Integer status;
    /**
     *计划完成时间
     */
    private Integer plan_end_on;
    /**
     * 完成时间
     */
    private Integer end_on;
    /**
     * 图纸上的位置X
     */
    private Integer pos_x;
    /**
     * 图纸上的位置Y
     */
    private Integer pos_y;
    /**
     * 问题描述
     */
    private String desc;
    /**
     * 问题严重情况
     */
    private Integer condition;
    /**
     *  产生Issue时的现场照片，多个用半角逗号“,”分隔
     */
    private String attachment_md5_list;
    /**
     * 最后指派人
     */
    private Integer last_assigner;
    /**
     * 最后指派时间
     */
    private Integer last_assigner_at;
    /**
     * 消项人
     */
    private Integer destroy_user;
    /**
     * 消项时间
     */
    private Integer destroy_at;
    /**
     * 删除人
     */
    private Integer delete_user;
    /**
     * 删除时间
     */
    private Integer delete_time;
    /**
     * 关闭人
     */
    private Integer close_user;
    /**
     * 关闭时间
     */
    private Integer close_time;
    /**
     * 客户端记录时间
     */
    private Integer client_create_at;
    /**
     * 创建时间
     */
    private Integer create_at;
    /**
     * 所属issue_uuid
     */
    private String issue_uuid;
    /**
     * 关闭标志 1=打开 2=关闭
     */
    private Integer close_status;
    /**
     * 审核通过(消项)状态 1=通过 2=不通过
     */
    private Integer check_status;
}
