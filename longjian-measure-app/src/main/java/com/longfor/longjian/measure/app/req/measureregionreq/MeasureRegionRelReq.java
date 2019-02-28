package com.longfor.longjian.measure.app.req.measureregionreq;

import com.longfor.longjian.common.consts.ReqParamCheckErrors;
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
    @NotNull(message = ReqParamCheckErrors.PARAM_IS_NULL)
    private Integer group_id;
    /**
     * 项目id
     */
    @NotNull(message = ReqParamCheckErrors.PARAM_IS_NULL)
    private Integer proj_id;
    /**
     * 描画区域uuid
     */
    @NotNull(message = ReqParamCheckErrors.PARAM_IS_NULL)
    private String region_uuid;
}
