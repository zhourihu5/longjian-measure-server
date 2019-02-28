package com.longfor.longjian.measure.app.req.promeasurequicksearchreq;

import com.longfor.longjian.common.consts.ReqParamCheckErrors;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @description:
 * @author: DDC
 * @create: 2019-01-08 10:44
 **/
@Data
public class GetMeasureStatisticTaskReq {

    /**
     * 项目ID
     */
    @NotNull(message = ReqParamCheckErrors.PARAM_IS_NULL)
    private Integer project_id;

    /**
     * 任务ID
     */
    @NotNull(message = ReqParamCheckErrors.PARAM_IS_NULL)
    private Integer measure_list_id;
}


