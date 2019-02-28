package com.longfor.longjian.measure.app.commonEntity;

import com.longfor.longjian.measure.po.zhijian2.MeasureListIssue;
import lombok.Data;

import java.util.List;

/**
 * @description:
 * @author: DDC
 * @create: 2019-01-10 18:07
 **/
@Data
public class MeasureListIssueInfo {

    private MeasureListIssue Issue;
    private String TaskName;
    private List<String> CategoryPathNames;
    private List<String> AreaPathNames;
    private String RepairerName;
}


