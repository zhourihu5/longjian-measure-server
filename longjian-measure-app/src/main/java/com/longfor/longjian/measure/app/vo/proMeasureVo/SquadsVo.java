package com.longfor.longjian.measure.app.vo.proMeasureVo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * wangxs
 * 2018-11-17 15:45
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SquadsVo {
    private String complete_percent;
    private Integer id;
    private String name;
    private String require_percent;

    private Integer squad_id;
    private String checked_percent;
    private String pass_percent;
}
