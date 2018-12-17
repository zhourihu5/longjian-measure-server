package com.longfor.longjian.measure.app.vo.appMeasureSyncVo;

import lombok.Data;

import java.util.List;
@Data
public class MeasureZoneListVo {
    private List<MeasureZoneZoneVo> zone_list;
    /**
     * 本次获取的最后ID
     */
    private Integer last_id;
}
