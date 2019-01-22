package com.longfor.longjian.measure.app.vo.appMeasureSyncVo;

import lombok.Data;

import java.util.List;

/**
 * wangxs
 * 2018-11-20 18:58
 */
@Data
public class IssueVo {
    /**
     * issue列表
     */
    private List<IssueListVo> issue_list;
    /**
     * 本次获取的最后ID
     */
    private Integer last_id;
}
