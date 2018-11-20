package com.longfor.longjian.measure.app.vo.appMeasureSyncVo;

import lombok.Data;

import java.util.List;

/**
 * wangxs
 * 2018-11-20 19:19
 */
@Data
public class IssueLogVo {

    /**
     * 本次获取的最后ID
     */
    private Integer last_id;
    /**
     * issue列表
     */
    private List<IssueLogListVo> issue_log_list;
}
