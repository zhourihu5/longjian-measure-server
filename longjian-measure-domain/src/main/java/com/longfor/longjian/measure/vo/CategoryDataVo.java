package com.longfor.longjian.measure.vo;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.longfor.longjian.measure.po.zhijian2.MeasureRule;
import com.longfor.longjian.measure.po.zhijian2.MeasureSquad;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 检查项数据
 * Jiazm 2019/01/18 14:04
 */
@Data
@NoArgsConstructor
public class CategoryDataVo {
    /**
     * 检查项（任务类型）Key
     */
    private String key;
    /**
     * 检查项（任务类型）名称
     */
    private String name;
    /**
     *  参与测量的小组
     */
    private List<MeasureSquad> squads = Lists.newArrayList();
    private OkRateVo ok_rate = new OkRateVo();
    private Map<String,OkRateVo> squad_ok_rates = Maps.newHashMap();
    /**
     * 下级检查项
     */
    private List<CategoryDataVo> children = Lists.newArrayList();

    private MeasureRule rule = new MeasureRule();
    private List<PointDataVo> data =Lists.newArrayList();
    private Map<String,Integer> dataIndex = Maps.newHashMap();
}
