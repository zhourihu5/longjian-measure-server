package com.longfor.longjian.measure.app.req.measurelist;

import com.longfor.longjian.common.consts.ReqParamCheckErrors;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * Jiazm 2019/01/26 16:08
 */
@Data
@NoArgsConstructor
public class ConditionSearchReq {
    /**
     * 集团id
     */
    @NotNull(message = ReqParamCheckErrors.PARAM_IS_NULL)
    private Integer group_id;
    /**
     * 项目id
     */
    @NotNull(message = ReqParamCheckErrors.PARAM_IS_NULL)
    private Integer project_id;
    /**
     * 任务列表id用逗号分隔
     */
    private String area_id;
    /**
     * 人员id列表
     */
    private String user_id_list;
    /**
     * 任务开关状态
     */
    private Integer finish_status;
    /**
     * 任务名称
     */
    private String name;
    /**
     * 页面数
     */
    @NotNull(message = ReqParamCheckErrors.PARAM_IS_NULL)
    private Integer page;
    /**
     * 每页条目数
     */
    @NotNull(message = ReqParamCheckErrors.PARAM_IS_NULL)
    private Integer page_size;
    /**
     * 检查项key
     */
    private String category_key;

}
