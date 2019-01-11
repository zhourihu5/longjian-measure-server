package com.longfor.longjian.measure.app.req.measureRegionReq;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * Jiazm  2019/01/09  16:20
 */
@Data
@NoArgsConstructor
public class AddOnProjReq {
    /**
     * 集团id
     */
    @NotNull
    private Integer group_id;
    /**
     * 项目id
     */
    @NotNull
    private Integer project_id;
    /**
     * 标签名列表, 用逗号分隔的字符串
     */
    @NotNull
    private String name_list;
}
