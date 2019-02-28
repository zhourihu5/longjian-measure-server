package com.longfor.longjian.measure.app.req.measureList;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * Created by Wang on 2019/1/7.
 */
@Data
public class UpdateNameReq {

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
     * 任务id
     */
    @NotNull
    private Integer list_id;

    /**
     * 修改任务名
     */
    @NotBlank
    private String  name;

}
