package com.longfor.longjian.measure.app.req.paintareareq;

import com.longfor.longjian.common.consts.ReqParamCheckErrors;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * wangxs
 * 2018-11-22
 */
@Data
public class GetGroupMeasureRegionTagReq implements Serializable {


    @NotNull(message = ReqParamCheckErrors.PARAM_IS_NULL)
    private Integer group_id;
    private String page_level;
    private Integer project_id;
    private Integer team_id;
    private Integer proj_id;
}
