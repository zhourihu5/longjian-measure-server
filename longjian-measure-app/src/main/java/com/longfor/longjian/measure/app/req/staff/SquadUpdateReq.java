package com.longfor.longjian.measure.app.req.staff;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * Created by Wang on 2019/1/7.
 */
@Data
public class SquadUpdateReq {

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
     * 小组id
     */
    @NotNull
    private Integer squad_id;

    /**
     * 组名
     */
    @NotBlank
    private String name;

    /**
     * 用户id以逗号分隔
     */
    @NotBlank
    private String user_id_list;

    /**
     * 检测点百分比
     */
    @NotNull
    private Integer plan_rate;
}
