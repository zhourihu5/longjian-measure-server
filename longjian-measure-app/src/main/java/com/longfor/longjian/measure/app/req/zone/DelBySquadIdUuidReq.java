package com.longfor.longjian.measure.app.req.zone;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * Created by Wang on 2019/1/8.
 */
@Data
public class DelBySquadIdUuidReq {


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
     * 小队id列表
     */
    @NotNull
    private Integer squad_id;

    /**
     * zone_uuid列表
     */
    @NotBlank
    private  String zone_uuid_list;
}
