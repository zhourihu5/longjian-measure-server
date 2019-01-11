package com.longfor.longjian.measure.app.req.staff;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * Created by Wang on 2019/1/7.
 */
@Data
public class RepairerListUpdateReq {

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
     * 用户id以逗号分隔
     */
    @NotBlank
    private String user_id_list;

    /**
     * 整改小组人员类型
     */
    @NotNull
    private Integer role_type;
}
