package com.longfor.longjian.measure.app.req.CheckItemMeasureReq;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * Jiazm 2019/01/10 10:42
 */
@Data
@NoArgsConstructor
public class GetCheckItemReq {
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
