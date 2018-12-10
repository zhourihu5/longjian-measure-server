package com.longfor.longjian.measure.app.vo.proMeasureVo;

import lombok.Data;

/**
 * wangxs
 * 2018-11-19 11:28
 */
@Data
public class BlisterCategoryDetailsVo {
    /**
     * 检查项ID
     */
    private String category_key;
    /**
     * 检查项名称
     */
    private String category_name;
    /**
     * 是否叶子节点，是表示没有下级菜单
     */
    private boolean is_leaf;
    /**
     * 问题数量
     */
    private Integer issue_count;
    /**
     * 待指派
     */
    private Integer note_no_assign;
    /**
     * 待修复
     */
    private Integer assign_no_reform;
    /**
     * 待销项
     */
    private Integer reform_no_check;
    /**
     * 已销项
     */
    private Integer check_yes;
}
