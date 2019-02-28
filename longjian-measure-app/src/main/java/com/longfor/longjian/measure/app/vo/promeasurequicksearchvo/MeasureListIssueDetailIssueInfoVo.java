package com.longfor.longjian.measure.app.vo.promeasurequicksearchvo;

import lombok.Data;

import java.util.List;

/**
 * @description:
 * @author: DDC
 * @create: 2019-01-09 17:23
 **/
@Data
public class MeasureListIssueDetailIssueInfoVo {
    /**
     *任务名称
     */
    private String task_name;

    /**
     *创建时间
     */
    private Integer create_at;

    /**
     *检查项名称列表
     */
    private List<String> category_path_names;

    /**
     *区域名称列表
     */
    private List<String> area_path_names;

    /**
     *问题类型
     */
    private Integer issue_type;

    /**
     *关闭状态
     */
    private Integer close_status;

    /**
     *整改人ID
     */
    private Integer repairer_id;

    /**
     *整改人名称
     */
    private String repairer_name;
    /**
     *预计整改完成时间
     */
    private Integer plan_end_on;
    /**
     *问题状态
     */
    private Integer status;
}


