package com.longfor.longjian.measure.app.req.measureRegionReq;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * Jiazm 2019/01/09 10:57
 */
@Data
@NoArgsConstructor
public class MeasureRegionRelReq {
    /**
     * 集团id
     */
    @NotNull(message = "group_id不能为空")
    private Integer group_id;
    /**
     * 项目id
     */
    @NotNull(message = "proj_id不能为空")
    private Integer proj_id;
    /**
     * 描画区域uuid
     */
    @NotNull(message = "region_uuid不能为空")
    private String region_uuid;
}
