package com.longfor.longjian.measure.app.vo.promeasurevo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * wangxs
 * 2018-11-17 15:45
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SquadsPassVo {
    private String average_percent;
    private Integer gte60_count;
    private Integer gte80_count;
    private Integer id;
    private Integer lt60_count;
    private String name;

    /**
     * 分组ID
     */
    private Integer squad_id;
    /**
     * 分组名称
     */
    private String squad_name;
    /**
     * 检查率
     */
    private String rate;
}
