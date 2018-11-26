package com.longfor.longjian.measure.app.vo.proMeasureVo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;

/**
 * wangxs
 * 2018-11-17 14:19
 */
@Data
public class ProMeasureCheckIteamVo{
    /**
     * id
     */
    private Integer id;
    /**
     * 是否父级
     */
    private Boolean isParent;
    /**
     * key
     */
    private String key;
    /**
     * 名称
     */
    private String name;
}
