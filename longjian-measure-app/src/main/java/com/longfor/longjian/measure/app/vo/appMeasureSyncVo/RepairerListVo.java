package com.longfor.longjian.measure.app.vo.appMeasureSyncVo;

import lombok.Data;

/**
 * wangxs
 * 2018-11-20 17:52
 */
@Data
public class RepairerListVo {
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
     * 角色类型  2=爆点跟进人 3=爆点整改人
     */
    private Integer role_type;
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
