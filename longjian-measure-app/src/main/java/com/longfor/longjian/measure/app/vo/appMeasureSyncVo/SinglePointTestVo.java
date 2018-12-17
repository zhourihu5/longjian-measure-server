package com.longfor.longjian.measure.app.vo.appMeasureSyncVo;

import lombok.Data;

/**
 * wangxs
 * 2018-11-20 18:08
 */
@Data
public class SinglePointTestVo {
    /**
     *测量点识别名 MeasurePointRule.Key
     */
    private String key;
    /**
     * 数据类型：1，boolean；2，整数(int)；14，浮点数（double）
     */
    private Integer data_type;
    /**
     *数据列表，多个请用半角逗号“,”分隔
     */
    private String data;
    /**
     * 是否需要录入设计值
     */
    private boolean design_value_reqd;
    /**
     * 设计值
     */
    private Double design_value;
    /**
     * 合格数（公式计算后得到）
     */
    private Integer ok_total;
    /**
     * 总数（公式计算后得到）
     */
    private Integer total;
    /**
     * 合格情况序列：0，不合格；1，合格。例："0110001"，合格数=3，总数=7（公式计算后得到）
     */
    private String seq;
    /**
     * 偏差值，多个请用半角逗号“,”分隔
     */
    private String deviation;


}
