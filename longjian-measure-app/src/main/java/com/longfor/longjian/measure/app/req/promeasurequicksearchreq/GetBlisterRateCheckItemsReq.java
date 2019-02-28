package com.longfor.longjian.measure.app.req.promeasurequicksearchreq;

import com.longfor.longjian.common.consts.ReqParamCheckErrors;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class GetBlisterRateCheckItemsReq {
    /**
     * 项目id
     */
    @NotNull(message = ReqParamCheckErrors.PARAM_IS_NULL)
    private Integer project_id;
    /**
     * 实测清单ID
     */
    @NotNull(message = ReqParamCheckErrors.PARAM_IS_NULL)
    private Integer measure_list_id;
    /**
     * 传入key取出其下的所有子项目的数据,空返回顶级检查项
     */
    private String category_key;
}
