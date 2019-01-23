package com.longfor.longjian.measure.app.req.measureRegionReq;

import com.longfor.longjian.common.consts.ReqParamCheckErrors;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Jiazm 2019/01/08 17:56
 */
@Data
@NoArgsConstructor
public class EditOnGroupReq implements Serializable {
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
     * 分页
     */
    private String page_level;
}
