package com.longfor.longjian.measure.app.vo.proMeasureVo;

import lombok.Data;

/**
 * wangxs
 * 2018-11-19 11:28
 */
@Data
public class BlisterCategoryDetailsVo {
    private String category_key;
    private String category_name;
    private boolean is_leaf;
    private Integer issue_count;
    private Integer note_no_assign;
    private Integer assign_no_reform;
    private Integer reform_no_check;
    private Integer check_yes;
}
