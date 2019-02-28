package com.longfor.longjian.measure.app.req.measureregionreq;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

@Setter
@Getter
public class DeleteReq {
    /**
     * 集团id
     */
    @NonNull
    private Integer group_id;
    /**
     * 项目id
     */
    @NonNull
    private Integer project_id;
    /**
     * 描区id
     */
    @NotBlank
    private String region_id_list;
}
