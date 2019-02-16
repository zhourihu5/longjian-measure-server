package com.longfor.longjian.measure.vo;

import com.google.common.collect.Lists;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * Jiazm 2019/01/18 14:33
 */
@Data
@NoArgsConstructor
public class MeasuredDataGroupVo implements Comparable<MeasuredDataGroupVo>, Serializable {
    private Integer buildingOrder;
    private Integer floorOrder;
    private Integer houseOrder;
    private String categoryOrder;
    private Integer building_id;
    private Integer floor_id;
    private Integer house_id;
    private String region_uuid;
    private String category_key;
    private List<String> category_names = Lists.newArrayList();
    private Integer squad_id;
    private Integer recorder_id;
    private Integer update_at;
    private String texture;
    private List<MeasureDataPointVo> points = Lists.newArrayList();

    @Override
    public int compareTo(MeasuredDataGroupVo o) {
        return 0;
    }
}
