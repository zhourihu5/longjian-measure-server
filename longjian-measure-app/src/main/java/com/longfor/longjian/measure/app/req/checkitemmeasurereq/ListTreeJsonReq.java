package com.longfor.longjian.measure.app.req.checkitemmeasurereq;

import com.longfor.longjian.common.consts.ReqParamCheckErrors;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * Jiazm 2019/01/10 17:10
 */
@Data
@NoArgsConstructor
public class ListTreeJsonReq {
    /**
     * category_v3表id
     * 任务类型ID
     */
    @NotNull(message = ReqParamCheckErrors.PARAM_IS_NULL)
    private Integer id;
}
