package com.longfor.longjian.measure.app.req.paintareareq;

import com.longfor.longjian.common.consts.ReqParamCheckErrors;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * wangxs
 * 2018-11-22
 */
@Data
public class GetProjMeasureRegionReq {
    @NotNull(message = ReqParamCheckErrors.PARAM_IS_NULL)
    private Integer group_id;
    private String page_level;
    @NotNull(message = ReqParamCheckErrors.PARAM_IS_NULL)
    private Integer project_id;
    private Integer team_id;
    private Integer proj_id;
    @NotNull(message = ReqParamCheckErrors.PARAM_IS_NULL)
    private Integer area_id;

}
