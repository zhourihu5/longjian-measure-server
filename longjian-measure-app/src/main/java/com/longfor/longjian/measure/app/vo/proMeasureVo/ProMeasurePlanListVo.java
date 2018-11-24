package com.longfor.longjian.measure.app.vo.proMeasureVo;

import lombok.Data;

import java.util.List;

@Data
public class ProMeasurePlanListVo {
    private List<ProMeasurePlanVo> items;
    private Integer total;
}
