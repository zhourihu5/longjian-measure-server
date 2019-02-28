package com.longfor.longjian.measure.app.vo.appmeasuresyncvo;

import lombok.Data;

import java.util.List;

/**
 * wangxs
 * 2018-11-20 18:06
 */
@Data
public class TextResultVo {
    /**
     * 记录者Id
     */
    private Integer recorder_id;
    /**
     * 测点类型（材质）
     */
    private String texture;
    /**
     * 单名称测点测量结果
     */
    private List<SinglePointTestVo> data;
    /**
     * 得分（公式计算后得到）
     */
    private Double score;
    /**
     * 更新时间
     */
    private Integer update_at;
}
