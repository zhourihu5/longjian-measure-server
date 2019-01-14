package com.longfor.longjian.measure.app.vo.proMeasureQuickSearchVo;

import lombok.Data;

import java.util.List;

/**
 * @description:
 * @author: DDC
 * @create: 2019-01-11 17:30
 **/
@Data
public class MeasureListIssueDetailSquadVo {
    /**
     * 小组ID
     */
    private Integer id;

    /**
     * 小组名称
     */
    private String name;

    /**
     * 用户名称列表
     */
    private List<String> users;
}


