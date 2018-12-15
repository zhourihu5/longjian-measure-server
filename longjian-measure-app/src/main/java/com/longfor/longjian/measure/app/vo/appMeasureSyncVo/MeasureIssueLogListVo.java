package com.longfor.longjian.measure.app.vo.appMeasureSyncVo;

import lombok.Data;

import java.util.List;

/**
 * Jiazm
 * 2018/12/14 17:15
 */
@Data
public class MeasureIssueLogListVo {
    private List<MeasureIssueLogVo> issueLog_list;
    private Integer last_id;
}
