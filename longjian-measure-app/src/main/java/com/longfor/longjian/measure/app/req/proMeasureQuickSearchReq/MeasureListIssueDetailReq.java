package com.longfor.longjian.measure.app.req.proMeasureQuickSearchReq;

import com.longfor.longjian.common.consts.ReqParamCheckErrors;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @Date 2019/2/12 10:39
 * Created by Jiazhongmin
 */
@Data
@NoArgsConstructor
public class MeasureListIssueDetailReq {
    private Integer lang;
    @NotNull(message = ReqParamCheckErrors.PARAM_IS_NULL)
    private String uuid;
    @NotNull(message = ReqParamCheckErrors.PARAM_IS_NULL)
    private Integer project_id;
}
