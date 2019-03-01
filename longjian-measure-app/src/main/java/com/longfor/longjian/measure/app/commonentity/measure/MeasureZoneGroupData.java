package com.longfor.longjian.measure.app.commonentity.measure;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;

@Data
@NoArgsConstructor
@SuppressWarnings("squid:S1068")
public class MeasureZoneGroupData implements Serializable {

    private String texture;
    private Map<String, MeasureZonePointData> data;
    private Float score;
}
