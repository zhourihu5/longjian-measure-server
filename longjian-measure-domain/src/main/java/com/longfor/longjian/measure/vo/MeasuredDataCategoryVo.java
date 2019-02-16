package com.longfor.longjian.measure.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Jiazm 2019/01/18 14:32
 */
@Data
@NoArgsConstructor
public class MeasuredDataCategoryVo implements Serializable {
    private String key;
    private String order;
    private String name;
}
