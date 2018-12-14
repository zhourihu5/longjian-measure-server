package com.longfor.longjian.measure.app.vo.appMeasureSyncVo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

/**
 * wangxs
 * 2018-11-20 17:57
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MeasureZoneResultVo {
    /**
     * 返回结果列表
     */
    private List<ResultListVo> result_list;
    /**
     * 本次获取的最后ID
     */
    private Integer last_id;

}
