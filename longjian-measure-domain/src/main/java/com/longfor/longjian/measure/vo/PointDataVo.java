package com.longfor.longjian.measure.vo;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.longfor.longjian.measure.po.zhijian2.MeasureSquad;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * 单名称数据
 * Jizm 2019/01/18 14:14
 */
@Data
@NoArgsConstructor
public class PointDataVo {
    private String key;
    //材质
    private String texture;
    /**
     * 参与测量的小组
     */
    private List<MeasureSquad> squads = Lists.newArrayList();
    /**
     * 测区数据列表
     */
    private List<MeasureZoneDataVo> data = Lists.newArrayList();

    private OkRateVo ok_rate = new OkRateVo();

    private Map<String,OkRateVo> squad_ok_rates = Maps.newHashMap();
    private Map<String,Integer> dataIndex = Maps.newHashMap();
}
