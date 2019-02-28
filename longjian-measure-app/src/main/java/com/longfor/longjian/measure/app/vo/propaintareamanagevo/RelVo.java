package com.longfor.longjian.measure.app.vo.propaintareamanagevo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * wangxs
 * 2018-11-20 10:02
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RelVo {
    /**
     * '关系id'
     */
    private Integer id;
    /**
     * '描述'
     */
    private String desc;
    /**
     * '描区id列表'
     */
    private String region_ids;
    /**
     * 项目ID
     */
    private Integer project_id;
    /**
     * 更新时间
     */
    private Integer update_at;
    /**
     * 删除时间
     */
    private Integer delete_at;
}
