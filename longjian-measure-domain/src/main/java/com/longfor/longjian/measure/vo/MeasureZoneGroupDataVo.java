package com.longfor.longjian.measure.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * Jiazm 2019/01/18 17:37
 */
@Data
@NoArgsConstructor
public class MeasureZoneGroupDataVo {
    private Integer RecorderId;
    private Date UpdateAt;
    //测点类型（材质）
    private String Texture;
    //测点测量结果
    private List<MeasureZonePointDataVo> Data;
    //得分
    private Float Score;
}
