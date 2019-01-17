package com.longfor.longjian.measure.app.req.measureRegionReq;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * Jiazm 2019/01/09 16:45
 */
@Data
@NoArgsConstructor
public class EditByProjIdReq {
    /**
     * 集团id
     */
    @NotNull
    private Integer group_id;
    /**
     * 编辑的标签列表
     */
    @NotNull
    private String edit_tag_list;
    /**
     * 项目id
     */
    @NotNull
    private Integer project_id;

}
