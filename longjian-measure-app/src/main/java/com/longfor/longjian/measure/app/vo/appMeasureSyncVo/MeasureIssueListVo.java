package com.longfor.longjian.measure.app.vo.appMeasureSyncVo;

import lombok.Data;

import java.util.List;

/**
 * Jiazm
 * 2018/12/14 15:33
 */
@Data
public class MeasureIssueListVo {
    /**
     * 本次获取的最后ID
     */
    private Integer last_id;
    /**
     * issue列表
     */
    private List<MeasureIssueVo> issue_list;
}
