package com.longfor.longjian.measure.app.req.CheckItemMeasureReq;

import javafx.beans.DefaultProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.bytebuddy.implementation.bind.annotation.Default;

import javax.validation.constraints.NotNull;

/**
 * Jiazm 2019/01/10 10:42
 */
@Data
@NoArgsConstructor
public class UpdateReqMeasureReq {
    /**
     * 语言 取值范围及含义：;1 - 简体中文; ;2 - English;
     */
    private Integer lang;
    /**
     *检查项Id
     */
    @NotNull
    private Integer category_id;
    /**
     * 检查项类型
     */
    private Integer cls = 102;
    /**
     *文件Md5
     */
    @NotNull
    private String file_md5;
    /**
     *强制写入
     */
    @NotNull
    private Boolean force;
}
