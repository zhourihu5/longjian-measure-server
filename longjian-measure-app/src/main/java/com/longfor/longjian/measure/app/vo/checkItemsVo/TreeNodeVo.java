package com.longfor.longjian.measure.app.vo.checkItemsVo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Jiazm 2019/01/10 17:36
 */
@Data
@NoArgsConstructor
public class TreeNodeVo implements Serializable {
    /**
     * Key
     */
    private String key;
    /**
     * 上级Key
     */
    private String father_key;
    /**
     * 原始Key
     */
    private String orig_key;
    /**
     * 原始上级Key
     */
    private String orig_father_key;
    /**
     * 名称
     */
    private String name;
    /**
     * 是否任务类型
     */
    private boolean is_category;
    /**
     * 是否检查项
     */
    private boolean is_check_item;
}
