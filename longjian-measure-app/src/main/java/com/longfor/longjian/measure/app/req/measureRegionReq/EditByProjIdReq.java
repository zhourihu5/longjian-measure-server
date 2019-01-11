package com.longfor.longjian.measure.app.req.measureRegionReq;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Jiazm 2019/01/09 16:45
 */
@Data
@NoArgsConstructor
public class EditByProjIdReq {
    /**
     * 集团id
     */
    private Integer group_id;
    /**
     * 编辑的标签列表
     */
    private String edit_tag_list;
    /**
     * 项目id
     */
    private Integer project_id;

}
