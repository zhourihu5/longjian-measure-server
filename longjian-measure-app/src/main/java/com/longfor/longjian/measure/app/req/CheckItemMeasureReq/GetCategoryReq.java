package com.longfor.longjian.measure.app.req.CheckItemMeasureReq;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * Jiazm 2019/01/09 18:23
 */
@Data
@NoArgsConstructor
public class GetCategoryReq {
    /**
     * 语言 取值范围及含义：;1 - 简体中文; ;2 - English
     */
    private Integer lang;
    /**
     * Key
     */
    @NotNull
    private String key;
}
