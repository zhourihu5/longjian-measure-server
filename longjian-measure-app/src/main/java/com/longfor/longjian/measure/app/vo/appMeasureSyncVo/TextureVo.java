package com.longfor.longjian.measure.app.vo.appMeasureSyncVo;

import lombok.Data;

import java.util.List;

/**
 * wangxs
 * 2018-11-20
 */
@Data
public class TextureVo {
    /**
     * 记录者Id
     */
    private Integer recorder_id;
    /**
     * 记录时间
     */
    private Integer update_at;
    /**
     * 测点类型（材质）
     */
    private String texture;
    /**
     * 单名称测点测量结果
     */
    private List<SinglePointTestVo> data;
}
