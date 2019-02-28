package com.longfor.longjian.measure.app.vo.checkitemsvo;

import lombok.Data;

/**
 * wangxs
 * 2018-11-17 13:05
 */
@Data
public class CheckItemsPlanTypeVo {
    private String father_key;
    private boolean is_category;
    private boolean is_check_item;
    private String key;
    private String name;
    private String orig_father_key;
    private String orig_key;
}
