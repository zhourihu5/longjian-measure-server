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
    @NotNull
    private Integer group_id;
    /**
     * 项目id
     */
    @NotNull
    private Integer proj_id;
    /**
     * 描画区域uuid
     */
    @NotNull
    private String region_uuid;
}
