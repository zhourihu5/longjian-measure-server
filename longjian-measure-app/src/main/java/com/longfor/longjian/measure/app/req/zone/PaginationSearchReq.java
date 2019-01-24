package com.longfor.longjian.measure.app.req.zone;

import com.longfor.longjian.common.consts.ReqParamCheckErrors;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * Created by Wang on 2019/1/8.
 */
@Data
public class PaginationSearchReq {

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
     * 实测任务id
     */
    @NotNull(message = ReqParamCheckErrors.PARAM_IS_NULL)
    private  Integer  list_id;

    /**
     * 页面数
     */
    @NotNull(message = ReqParamCheckErrors.PARAM_IS_NULL)
    private  Integer page=1;

    /**
     * 每页的条目数
     */
    @NotNull(message = ReqParamCheckErrors.PARAM_IS_NULL)
    private  Integer page_size=20;

    /**
     * 区域id列表
     */
    @NotBlank(message = ReqParamCheckErrors.PARAM_IS_NULL)
    private  String area_id_list;

    /**
     * 检查项key列表
     */
    @NotBlank(message = ReqParamCheckErrors.PARAM_IS_NULL)
    private  String category_key_list;


}
