package com.longfor.longjian.measure.vo;

import com.google.common.collect.Maps;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 测区数据
 * Jizm 2019/01/18 14:19
 */
@Data
@NoArgsConstructor
public class MeasureZoneDataVo {
    //描区UUID
    private String region_uuid;
    //排序依据
    private Integer order;
    //区域名称
    private String area_name;
    //key: 测量小组id
    //List<MeasureZonePointDataVo> listMap = Lists.newArrayList();
    private Map<String, List<MeasureZonePointDataVo>> data = new HashMap<>();
    private OkRateVo ok_rate = new OkRateVo();
    private Map<String, OkRateVo> squad_ok_rates = Maps.newHashMap();
}
