package com.longfor.longjian.measure.app.vo.proMeasureQuickSearchVo;

import lombok.Data;

@Data
public class MeasureListIssueHistoryRepairLogItemVo {
    /**
     * 目标用户ID目标用户ID
     */
    private Integer target_user_id;
    /**
     * 目标用户名称
     */
    private String target_user_name;
    /**
     * 日志类型
     */
    private Integer log_type;
    /**
     * 内容内容
     */
    private String data;
}
