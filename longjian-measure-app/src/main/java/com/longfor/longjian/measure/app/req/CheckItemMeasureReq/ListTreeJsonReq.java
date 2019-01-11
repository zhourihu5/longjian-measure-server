package com.longfor.longjian.measure.app.req.CheckItemMeasureReq;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Jiazm 2019/01/10 17:10
 */
@Data
@NoArgsConstructor
public class ListTreeJsonReq {
    /**
     * 语言 取值范围及含义：;1 - 简体中文; ;2 - English
     */
    private Integer lang;
    /**
     * category_v3表id
     * 任务类型ID
     */
    private Integer id;
}
