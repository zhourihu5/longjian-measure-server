package com.longfor.longjian.measure.vo;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * Jiazm 2019/01/18 14:28
 */
@Data
@NoArgsConstructor
public class MeasuredDataVo {
    private Integer list_id;
    private String list_name;
    private Integer proj_id;
    private String proj_name;
    private List<MeasuredDataGroupVo> details = Lists.newArrayList();
    private Map<Integer,String> area_name_map = Maps.newHashMap();
    private Map<String,String> region_map = Maps.newHashMap();
    private Map<String,MeasuredDataCategoryVo> category_map = Maps.newHashMap();
    private Map<Integer,String> squad_map = Maps.newHashMap();
    private Map<Integer,String> user_map = Maps.newHashMap();
}
