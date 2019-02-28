package com.longfor.longjian.measure.app.vo.promeasurevo;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Jiazm 2019/01/10 11:07
 */
@Data
@NoArgsConstructor
public class CheckItemUpdateJsonVo {
    /**
     *已强制写入
     */
    private Boolean done;
    /**
     *信息
     */
    private String msg;
    /**
     *因规则或公式不存在要删除实测实量记录数
     */
    private Integer rule_no_found_delete;
    /**
     *因规则或公式不兼容要删除实测实量记录数
     */
    private Integer rule_error_delete;
}
