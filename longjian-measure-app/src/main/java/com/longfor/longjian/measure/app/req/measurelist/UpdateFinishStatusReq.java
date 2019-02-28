package com.longfor.longjian.measure.app.req.measurelist;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * Created by Wang on 2019/1/7.
 */
@Data
public class UpdateFinishStatusReq {

    /**
     * 集团id
     */
    @NotNull
    private Integer group_id;

    /**
     * 项目id
     */
    @NotNull
    private Integer project_id;

    /**
     * 任务列表id用逗号分隔
     */
    @NotBlank
    private String list_ids;

    /**
     * 任务开关状态
     */
    @NotNull
    private Integer finish_status;
}
