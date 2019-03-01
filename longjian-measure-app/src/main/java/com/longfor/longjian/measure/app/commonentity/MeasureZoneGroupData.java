package com.longfor.longjian.measure.app.commonentity;

import lombok.Data;

import java.util.List;

@Data
@SuppressWarnings("squid:S1068")
public class MeasureZoneGroupData {
    private Integer RecorderId;
    private Data UpdateAt;
    /**
     * 测点类型（材质）
     */
    private String Texture;
    /**
     * 测点测量结果
     */
    private List<MeasureZonePointData> Data;
    /**
     * 得分
     */
    private Double Score;
}
