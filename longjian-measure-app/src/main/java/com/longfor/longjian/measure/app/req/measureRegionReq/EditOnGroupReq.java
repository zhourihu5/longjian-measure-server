package com.longfor.longjian.measure.app.req.measureRegionReq;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Jiazm 2019/01/08 17:56
 */
@Data
@NoArgsConstructor
public class EditOnGroupReq implements Serializable {
    /**
     * 集团id
     */
    private Integer group_id;
    /**
     * 编辑的标签列表
     */
    private String edit_tag_list;
    /**
     * 分页
     */
    private String page_level;
}
