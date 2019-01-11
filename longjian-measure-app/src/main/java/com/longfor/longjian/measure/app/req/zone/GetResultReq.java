package com.longfor.longjian.measure.app.req.zone;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * Created by Wang on 2019/1/8.
 */
@Data
public class GetResultReq {


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
     * 测区uuid
     */
    @NotBlank
    private String zone_uuid;
}
