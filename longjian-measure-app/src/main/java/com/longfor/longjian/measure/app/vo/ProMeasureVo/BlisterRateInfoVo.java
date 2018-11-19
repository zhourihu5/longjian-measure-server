package com.longfor.longjian.measure.app.vo.ProMeasureVo;

import lombok.Data;

/**
 * wangxs
 * 2018-11-19 11:08
 */
@Data
public class BlisterRateInfoVo {
    private Integer assign_no_reform;
    private Integer check_yes;
    private Integer note_no_assign;
    private Integer reform_no_check;
    private Integer repairable_count;
    private Integer unrepairable_count;
    private Integer zone_count;
    private String zone_percentage;

}
