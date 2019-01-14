package com.longfor.longjian.measure.app.req.MeasureList;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Jiazm 2019/01/11 18:06
 */
@Data
@NoArgsConstructor
public class MeasureIssueDeleteReq {
    private Integer lang;// 语言 取值范围及含义：;1 - 简体中文; ;2 - English;
    private String uuid; // 唯一编号
    private Integer project_id;// 项目id
}
