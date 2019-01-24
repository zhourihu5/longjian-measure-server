package com.longfor.longjian.measure.app.req.zone;

import com.longfor.longjian.common.consts.ReqParamCheckErrors;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * Created by Wang on 2019/1/8.
 */
@Data
public class DelBySquadIdUuidReq {


    /**
     * 集团id
     */
    @NotNull(message = ReqParamCheckErrors.PARAM_IS_NULL)
    private  Integer group_id;

    /**
     * 项目id
     */
    @NotNull(message = ReqParamCheckErrors.PARAM_IS_NULL)
    private  Integer project_id;

    /**
     * 小队id列表
     */
    @NotNull(message = ReqParamCheckErrors.PARAM_IS_NULL)
    private Integer squad_id;

    /**
     * zone_uuid列表
     */
    @NotBlank(message = ReqParamCheckErrors.PARAM_IS_NULL)
    private  String zone_uuid_list;
}
