package com.longfor.longjian.measure.app.req.measureRegionReq;

import com.longfor.longjian.common.consts.ReqParamCheckErrors;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * Jiazm  2019/01/09  16:20
 */
@Data
@NoArgsConstructor
public class AddOnProjReq {
    /**
     * 集团id
     */
    @NotNull(message = ReqParamCheckErrors.PARAM_IS_NULL)
    private Integer group_id;
    /**
     * 项目id
     */
    @NotNull(message = ReqParamCheckErrors.PARAM_IS_NULL)
    private Integer project_id;
    /**
     * 标签名列表, 用逗号分隔的字符串
     */
    @NotNull(message = ReqParamCheckErrors.PARAM_IS_NULL)
    private String name_list;
}
