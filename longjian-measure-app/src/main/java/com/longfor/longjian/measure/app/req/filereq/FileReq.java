package com.longfor.longjian.measure.app.req.filereq;

import com.longfor.longjian.common.consts.ReqParamCheckErrors;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * Jiazm 2019/01/11 11:42
 */
@Data
@NoArgsConstructor
public class FileReq {
    @NotNull(message = ReqParamCheckErrors.PARAM_IS_NULL)
    private Integer id;

    private String _download;
}
