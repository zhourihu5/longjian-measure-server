package com.longfor.longjian.measure.app.req.promeasurequicksearchreq;

import com.longfor.longjian.common.consts.ReqParamCheckErrors;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @description:
 * @author: DDC
 * @create: 2019-01-09 17:14
 **/
@Data
public class GetMeasureListIssueDetailReq {
    /**
     * 唯一编号
     */
    @NotNull(message = ReqParamCheckErrors.PARAM_IS_NULL)
    private String uuid;
    /**
     * 项目ID
     */
    @NotNull(message = ReqParamCheckErrors.PARAM_IS_NULL)
    private Integer project_id;
}


