package com.longfor.longjian.measure.app.req.feignreq;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class SearchByIdListReq {
    /**
     * 区域id列表
     */
    @NotNull
    private List<Integer> area_id_list;
    /**
     * 项目id
     */
    @NotNull
    private Integer project_id;
}
