package com.longfor.longjian.measure.app.req.MeasureList;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * Jiazm 2019/01/18 11:13
 */
@Data
@NoArgsConstructor
public class MeasureDetailExportExcelReq {
    /**
     * 语言 取值范围及含义：;1 - 简体中文; ;2 - English;
     */
    private Integer lang;
    /**
     * 所属任务
     */
    @NotNull
    private Integer list_id;
}
