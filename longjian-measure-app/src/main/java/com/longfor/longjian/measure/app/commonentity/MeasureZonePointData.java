package com.longfor.longjian.measure.app.commonentity;

import lombok.Data;

import java.util.List;

@Data
@SuppressWarnings("squid:S1068")
public class MeasureZonePointData {
    /**
     * MeasurePointRule.Key
     */
    private String Key;
    /**
     * 数据类型[MeasureRuleDataType]：1，boolean；2，整数(int)；14，浮点数（float64）
     */
    private Integer DataType;
    /**
     * 数据列表
     */
    private List<Double> Data;
    /**
     * 是否需要录入设计值
     */
    private Boolean DesignValueReqd;
    /**
     * 设计值
     */
    private Double DesignValue;
    /**
     * 合格数
     */
    private Integer OkTotal;
    /**
     * 总数
     */
    private Integer Total;
    /**
     * 合格情况序列：0，不合格；1，合格。例："0110001"，合格数=3，总数=7
     */
    private String Seq;
    /**
     * 偏差值
     */
    private List<Double> Deviation;
}
