package com.longfor.longjian.measure.app.req.zone;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * Created by Wang on 2019/1/8.
 */
@Data
public class UpdateStatusReq {


    /**
     * 集团id
     */
    @NotNull
    private  Integer group_id;

    /**
     * 项目id
     */
    @NotNull
    private  Integer project_id;

    /**
     * 测区id列表以逗号分隔
     */
    @NotBlank
    private String zone_id_list;

    /**
     *测区开关状态
     */
    @NotNull
    private Integer close_status;
}
