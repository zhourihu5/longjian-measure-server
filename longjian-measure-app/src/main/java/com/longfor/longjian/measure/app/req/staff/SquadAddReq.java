package com.longfor.longjian.measure.app.req.staff;

import com.longfor.longjian.common.consts.ReqParamCheckErrors;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Created by Wang on 2019/1/7.
 */
@Data
public class SquadAddReq {

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
     * 小组名称
     */
    @NotNull(message = ReqParamCheckErrors.PARAM_IS_NULL)
    private String name;

    @NotNull(message = ReqParamCheckErrors.PARAM_IS_NULL)
    private String user_id_list;

    /**
     * 检测点百分比
     */
    @NotNull(message = ReqParamCheckErrors.PARAM_IS_NULL)
    private Integer plan_rate;
}
