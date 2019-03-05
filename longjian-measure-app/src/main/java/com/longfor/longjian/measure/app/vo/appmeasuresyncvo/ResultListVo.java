package com.longfor.longjian.measure.app.vo.appmeasuresyncvo;

import lombok.Data;

import java.util.List;

/**
 * wangxs
 * 2018-11-20 17:59
 */
@Data
public class ResultListVo {
    /**
     * ID
     */
    private Integer id;
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
     * 区域Id
     */
    private Integer area_id;
    /**
     * 所属测区uuid
     */
    private String zone_uuid;
    /**
     * 小队Id
     */
    private Integer squad_id;
    /**
     * 规则Id
     */
    private Integer rule_id;
    /**
     * 组测量结果
     */
    private List<TextResultVo> data;
    /**
     * 合格数
     */
    private Integer ok_total;
    /**
     * 总数
     */
    private Integer total;
    /**
     * 得分
     */
    private Float score;
    /**
     * 更新时间
     */
    private Integer update_at;
    /**
     * 删除时间(0表示未删除)
     */
    private Integer delete_at;
    /**
     * 描区uuid
     */
    private String region_uuid;
    /**
     * 区域路径及ID
     */
    private String area_path_and_id;
    /**
     * 任务类型KEY
     */
    private String category_key;
    /**
     * 任务路径及KEY
     */
    private String category_path_and_key;
    /**
     *
     */
    private Integer closeStatus;
}
