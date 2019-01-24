package com.longfor.longjian.measure.app.req.proMeasureQuickSearchReq;

import com.longfor.longjian.common.consts.ReqParamCheckErrors;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class GetAreaPOPReq {
    /**
     * 项目ID
     */
    @NotNull(message = ReqParamCheckErrors.PARAM_IS_NULL)
    private Integer project_id;
    /**
     * 任务ID，逗号隔开
     */
    @NotNull(message = ReqParamCheckErrors.PARAM_IS_NULL)
    private String list_ids;
    /**
     * 区域ID，逗号隔开
     */
    @NotNull(message = ReqParamCheckErrors.PARAM_IS_NULL)
    private String area_ids;
    /**
     * 父级检查项key
     */
    private String parent_category_key;
}
