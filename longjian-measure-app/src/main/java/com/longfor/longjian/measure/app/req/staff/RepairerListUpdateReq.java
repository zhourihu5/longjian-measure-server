package com.longfor.longjian.measure.app.req.staff;

import com.longfor.longjian.common.consts.ReqParamCheckErrors;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * Created by Wang on 2019/1/7.
 */
@Data
public class RepairerListUpdateReq {

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
     * 用户id以逗号分隔
     */
    @NotBlank(message = ReqParamCheckErrors.PARAM_IS_NULL)
    private String user_id_list;

    /**
     * 整改小组人员类型
     */
    @NotNull(message = ReqParamCheckErrors.PARAM_IS_NULL)
    private Integer role_type;
}
