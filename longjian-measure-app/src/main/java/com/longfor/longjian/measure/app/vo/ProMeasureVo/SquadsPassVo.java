package com.longfor.longjian.measure.app.vo.ProMeasureVo;

import lombok.Data;

/**
 * wangxs
 * 2018-11-17 15:45
 */
@Data
public class SquadsPassVo {
    private String average_percent;
    private Integer gte60_count;
    private Integer gte80_count;
    private Integer id;
    private Integer lt60_count;
    private String name;

    private Integer squad_id;
    private String squad_name;
    private String rate;
}
