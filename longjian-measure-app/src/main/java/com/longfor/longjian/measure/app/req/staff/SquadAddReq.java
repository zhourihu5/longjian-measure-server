package com.longfor.longjian.measure.app.req.staff;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Created by Wang on 2019/1/7.
 */
@Data
public class SquadAddReq {

    @NotNull
    private Integer group_id;

    @NotNull
    private Integer project_id;

    /**
     * 任务id
     */
    @NotNull
    private Integer list_id;

    /**
     * 小组名称
     */
    @NotNull
    private String name;

    @NotNull
    private String user_id_list;

    /**
     * 检测点百分比
     */
    @NotNull
    private Integer plan_rate;
}
