package com.longfor.longjian.measure.app.req.measureRegionReq;

import com.longfor.longjian.common.consts.ReqParamCheckErrors;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * Jiazm 2019/01/09 16:45
 */
@Data
@NoArgsConstructor
public class EditByProjIdReq {
    /**
     * 集团id
     */
    @NotNull(message = ReqParamCheckErrors.PARAM_IS_NULL)
    private Integer group_id;
    /**
     * 编辑的标签列表
     */
    @NotNull(message = ReqParamCheckErrors.PARAM_IS_NULL)
    private String edit_tag_list;
    /**
     * 项目id
     */
    @NotNull(message = ReqParamCheckErrors.PARAM_IS_NULL)
    private Integer project_id;

}
