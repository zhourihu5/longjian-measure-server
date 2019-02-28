package com.longfor.longjian.measure.app.req.measurelist;

import com.longfor.longjian.common.consts.ReqParamCheckErrors;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * Jiazm 2019/01/18 11:13
 */
@Data
@NoArgsConstructor
public class MeasureDetailExportExcelReq {
    /**
     * 所属任务
     */
    @NotNull(message = ReqParamCheckErrors.PARAM_IS_NULL)
    private Integer list_id;
}
