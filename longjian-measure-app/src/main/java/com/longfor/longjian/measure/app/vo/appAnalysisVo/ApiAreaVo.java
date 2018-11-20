package com.longfor.longjian.measure.app.vo.appAnalysisVo;

import com.longfor.longjian.measure.app.vo.appMeasureSyncVo.IssueListVo;
import lombok.Data;

import java.util.List;

/**
 * wangxs
 * 2018-11-20 20:03
 */
@Data
public class ApiAreaVo {
    /**
     * issue列表
     */
    private List<ApiAreaInfoVo> issue_list;
}
