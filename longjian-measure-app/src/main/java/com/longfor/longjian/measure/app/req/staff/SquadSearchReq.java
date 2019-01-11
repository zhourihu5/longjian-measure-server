package com.longfor.longjian.measure.app.req.staff;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Created by Wang on 2019/1/7.
 */
@Data
public class SquadSearchReq {

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
     * 页面数
     */
    @NotNull
    private Integer page=1;

    /**
     * 每页条目数
     */
    @NotNull
    private Integer page_size=20;
}
