package com.longfor.longjian.measure.app.vo.proMeasureVo;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Jiazm 2019/01/09 19:51
 */
@Data
@NoArgsConstructor
public class CategoryVo {
    /**
     * ID
     */
    private Integer id;
    /**
     * Key
     */
    private String key;
    /**
     * FatherKey
     */
    private String father_key;
    /**
     * 排序编号
     */
    private String order;
    /**
     * 用户自定义识别码
     */
    private String custom_key;
    /**
     * 类型名称
     */
    private String name;
    /**
     * 说明
     */
    private String desc;
}
