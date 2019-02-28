package com.longfor.longjian.measure.app.vo.appmeasuresyncvo;

import lombok.Data;

/**
 * wangxs
 * 2018-11-20
 */
@Data
public class TaskInfoVo {
    /**
     * ID
     */
    private Integer id;
    /**
     * project_id
     */
    private Integer project_id;
    /**
     *  清单名称
     */
    private String name;
    /**
     * 完成状态：1、未完成；2、已完成
     */
    private Integer finish_status;
    /**
     * 关闭状态：1、未关闭；2、已关闭
     */
    private Integer close_status;
    /**
     * 计划开始时间
     */
    private Integer plan_begin_on;
    /**
     * 计划完成时间
     */
    private Integer plan_end_on;
    /**
     * 清单涉及区域，半角逗号分隔
     */
    private String area_ids;
    /**
     * 根任务类型
     */
    private String root_category_key;
    /**
     * 更新时间
     */
    private Integer update_at;
    /**
     * 删除时间
     */
    private Integer delete_at;


}
