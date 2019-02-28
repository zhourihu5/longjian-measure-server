package com.longfor.longjian.measure.app.vo.promeasurequicksearchvo;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Date 2019/2/12 10:56
 * Created by Jiazhongmin
 */
@Data
@NoArgsConstructor
public class MeasureListIssueGetIssueStatus {
    private Integer status;//问题状态
    private Integer close_status;//关闭状态
}
