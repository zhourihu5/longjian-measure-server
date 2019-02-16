package com.longfor.longjian.measure.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 合格率
 * jiazm 2019/01/18 14:10
 */
@Data
@NoArgsConstructor
public class OkRateVo implements Serializable {
    private Integer ok_total;
    private Integer total;
}
