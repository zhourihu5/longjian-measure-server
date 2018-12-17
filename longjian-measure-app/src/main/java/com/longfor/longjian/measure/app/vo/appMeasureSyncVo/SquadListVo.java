package com.longfor.longjian.measure.app.vo.appMeasureSyncVo;

import lombok.Data;

/**
 * wangxs
 * 2018-11-20 17:42
 */
@Data
public class SquadListVo {
    /**
     * Id
     */
   private Integer id;
    /**
     * 项目ID
     */
    private Integer project_id;
    /**
     * 清单ID
     */
    private Integer list_id;
    /**
     * 组名
     */
    private String name;
    /**
     * 计划检查率
     */
    private Integer plan_rate;
    /**
     * 实际检查率
     */
    private Integer rate;
    /**
     * 更新时间
     */
    private Integer update_at;
    /**
     * 删除时间(0表示未删除
     */
    private Integer delete_at;

}
