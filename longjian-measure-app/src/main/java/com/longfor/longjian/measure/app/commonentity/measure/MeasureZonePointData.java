package com.longfor.longjian.measure.app.commonentity.measure;

import com.google.common.collect.Maps;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
public class MeasureZonePointData implements Serializable {

    private String key;// MeasurePointRule.Key
    private Integer dataType;  // 数据类型：1，boolean；2，整数(int)；14，浮点数（float64）
    private List<Long> data; // 数据列表
    private Boolean designValueReqd; // 是否需要录入设计值
    private Long designValue;// 设计值
    private Integer okTotal;  // 合格数
    private Integer total;  // 总数
    private String seq;// 合格情况序列：0，不合格；1，合格。例："0110001"，合格数=3，总数=7
    private String deviation;// 偏差值

    public Map<String, Object> toMap() {
        Map<String, Object> map = Maps.newHashMap();
        map.put("key", key);
        map.put("data_type", dataType);
        map.put("data", StringUtils.join(data, ","));
        map.put("design_value_reqd", designValueReqd);
        map.put("design_value", designValue);
        map.put("ok_total", okTotal);
        map.put("total", total);
        map.put("seq", seq);
        return map;
    }
}
