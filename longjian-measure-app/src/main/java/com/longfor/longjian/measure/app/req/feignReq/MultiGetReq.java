package com.longfor.longjian.measure.app.req.feignReq;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
public class MultiGetReq implements Serializable {

    @NotNull
    private List<Integer> user_ids;//账号id列表

    @NotNull
    private Integer group_id;//集团id

}
