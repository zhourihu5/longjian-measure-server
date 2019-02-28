package com.longfor.longjian.measure.app.req.apimeasurerulereq;

import com.longfor.longjian.common.consts.ReqParamCheckErrors;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Jiazm
 * 2018/12/14 16:44
 */
@Data
public class ApiMeasureIssueLogReq {
    /**
     * 清单Id
     */
    @NotNull(message = ReqParamCheckErrors.PARAM_IS_NULL)
    private Integer list_id;
    /**
     * 上次获取最后的Id
     */
    @NotNull(message = ReqParamCheckErrors.PARAM_IS_NULL)
    private Integer last_id;
    /**
     * 更新时间
     */
    @NotNull(message = ReqParamCheckErrors.PARAM_IS_NULL)
    private Long    timestamp;
}
