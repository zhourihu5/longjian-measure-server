package com.longfor.longjian.measure.app.vo.proMeasureQuickSearchVo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class MeasureListIssueHistoryRepairLogVo {
    /**
     * 用户ID
     */
    private Integer user_id;
    /**
     * 用户名称
     */
    private String user_name;
    /**
     * 创建时间
     */
    private Integer create_at;
    /**
     * 历史日志
     */
    private List<MeasureListIssueHistoryRepairLogItemVo> items = new ArrayList<>();
}
