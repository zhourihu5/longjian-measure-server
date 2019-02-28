package com.longfor.longjian.measure.app.vo.appmeasuresyncvo;

import lombok.Data;

import java.util.List;

/**
 * wangxs
 * 2018-11-20 17:01
 */
@Data
public class MeasureZoneVo {
    private List<ZoneInfoVo> zone_list;
    /**
     * 本次获取的最后ID
     */
    private Integer last_id;
}
