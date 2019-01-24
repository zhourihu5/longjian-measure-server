package com.longfor.longjian.measure.app.req.staff;

import com.longfor.longjian.common.consts.ReqParamCheckErrors;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Created by Wang on 2019/1/7.
 */
@Data
public class SquadSearchReq {

    @NotNull(message = ReqParamCheckErrors.PARAM_IS_NULL)
    private Integer group_id;

    @NotNull(message = ReqParamCheckErrors.PARAM_IS_NULL)
    private Integer project_id;

    /**
     * 任务id
     */
    @NotNull(message = ReqParamCheckErrors.PARAM_IS_NULL)
    private Integer list_id;

    /**
     * 页面数
     */
    private Integer page=1;

    /**
     * 每页条目数
     */
    private Integer page_size=20;
}
