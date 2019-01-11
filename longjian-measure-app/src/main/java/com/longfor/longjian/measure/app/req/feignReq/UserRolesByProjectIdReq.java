package com.longfor.longjian.measure.app.req.feignReq;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class UserRolesByProjectIdReq implements Serializable {
    @NotNull
    private Integer project_id;//节点id
    @NotNull
    private Integer group_id;//集团id

}
