package com.longfor.longjian.measure.app.vo.appmeasuresyncvo;

import lombok.Data;

/**
 * wangxs
 * 2018-11-20 19:27
 */
@Data
public class DroppedVo {
    /**
     * UUID
     */
    private String uuid;
    /**
     * 抛弃原因类型
     */
    private Integer reason_type;
    /**
     * 抛弃原因说明
     */
    private String reason;
}
