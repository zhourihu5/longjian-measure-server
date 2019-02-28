package com.longfor.longjian.measure.app.req.checkitemmeasurereq;

import com.longfor.longjian.common.consts.ReqParamCheckErrors;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * Jiazm 2019/01/10 10:42
 */
@Data
@NoArgsConstructor
public class UpdateReqMeasureReq {
    /**
     *检查项Id
     */
    @NotNull(message = ReqParamCheckErrors.PARAM_IS_NULL)
    private Integer category_id;
    /**
     * 检查项类型
     */
    private Integer cls = 102;
    /**
     *文件Md5
     */
    @NotNull(message = ReqParamCheckErrors.PARAM_IS_NULL)
    private String file_md5;
    /**
     *强制写入
     */
    private Boolean force;
}
