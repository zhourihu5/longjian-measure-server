package com.longfor.longjian.measure.app.vo.proMeasureVo;

import lombok.Data;

/**
 * wangxs
 * 2018-11-19 11:08
 */
@Data
public class BlisterRateInfoVo {
    private Integer zone_count;
    private String zone_percentage;
    private Integer repairable_count;
    private Integer unrepairable_count;
    private Integer note_no_assign;
    private Integer assign_no_reform;
    private Integer reform_no_check;
    private Integer check_yes;


}
