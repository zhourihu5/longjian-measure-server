package com.longfor.longjian.measure.app.commonentity;

import com.longfor.longjian.measure.po.zhijian2.MeasureListIssue;
import lombok.Data;

import java.util.List;

/**
 * @description:
 * @author: DDC
 * @create: 2019-01-10 18:07
 **/
@Data
@SuppressWarnings("squid:S1068")
public class MeasureListIssueInfo {

    @SuppressWarnings("squid:S00116")
    private MeasureListIssue Issue;
    @SuppressWarnings("squid:S00116")
    private String TaskName;
    @SuppressWarnings("squid:S00116")
    private List<String> CategoryPathNames;
    @SuppressWarnings("squid:S00116")
    private List<String> AreaPathNames;
    @SuppressWarnings("squid:S00116")
    private String RepairerName;
}


