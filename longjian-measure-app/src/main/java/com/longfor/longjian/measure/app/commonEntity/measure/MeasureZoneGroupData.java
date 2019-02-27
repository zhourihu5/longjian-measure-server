package com.longfor.longjian.measure.app.commonEntity.measure;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;

@Data
@NoArgsConstructor
public class MeasureZoneGroupData implements Serializable {

    private String texture;
    private Map<String, MeasureZonePointData> data;
    private Float score;
}
