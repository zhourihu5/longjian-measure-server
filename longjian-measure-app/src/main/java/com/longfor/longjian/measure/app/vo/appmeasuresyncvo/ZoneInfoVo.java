package com.longfor.longjian.measure.app.vo.appmeasuresyncvo;

import lombok.Data;

/**
 * wangxs
 * 2018-11-30 17:03
 */
@Data
public class ZoneInfoVo {
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
     * 描画区域UUID
     */
    private String region_uuid;
    /**
     * 区域Id
     */
    private Integer area_id;

    /**
     * 区域路径及ID
     */
    private String area_path_and_id;

    /**
     * 任务类型key
     */
    private String category_key;
    /**
     * 任务类型路径
     */
    private String category_path_and_key;
    /**
     * 来源类型 1=后台添加 2=客户端添加
     */
    private Integer src_type;
    /**
     * 完成状态：1、未完成；2、已完成
     */
    private Integer finish_status;
    /**
     * 关闭状态：1、未关闭；2、已关闭
     */
    private Integer close_status;
    /**
     * 创建时间
     */
    private Integer create_at;
    /**
     * 更新时间
     */
    private Integer update_at;
    /**
     * 删除时间(`0`表示未删除)
     */
    private Integer delete_at;

}
