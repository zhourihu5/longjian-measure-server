package com.longfor.longjian.measure.app.vo.appMeasureSyncVo;

import lombok.Data;

/**
 * wangxs
 * 2018-11-20 17:47
 */
@Data
public class SquadUserListVo {

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
     * 测量组ID
     */
    private Integer squad_id;
    /**
     * 用户ID
     */
    private Integer user_id;
    /**
     * 更新时间
     */
    private Long update_at;
    /**
     * 删除时间(0表示未删除)
     */
    private Long delete_at;

}
