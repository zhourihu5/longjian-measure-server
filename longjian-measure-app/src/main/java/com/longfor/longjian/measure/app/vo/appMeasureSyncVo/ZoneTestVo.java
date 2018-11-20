package com.longfor.longjian.measure.app.vo.appMeasureSyncVo;

import lombok.Data;

import java.util.List;

/**
 * wangxs
 * 2018-11-20 19:36
 */
@Data
public class ZoneTestVo {
    /**
     * 唯一编码
     */
    private String uuid;
    /**
     * 项目ID
     */
    private Integer project_id;
    /**
     * 清单ID
     */
    private Integer list_id;
    /**
     * 所属测区UUID
     */
    private String zone_uuid;
    /**
     * 用户所在小队Id
     */
    private Integer squad_id;
    /**
     * 计算时使用的规则Id
     */
    private Integer rule_id;
    /**
     * 测区测量数据
     */
    private List<TextureVo> data;
}
