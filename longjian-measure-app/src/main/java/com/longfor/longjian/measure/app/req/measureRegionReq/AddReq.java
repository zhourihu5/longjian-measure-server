package com.longfor.longjian.measure.app.req.measureRegionReq;

import lombok.Data;
import lombok.NonNull;
import org.hibernate.validator.constraints.NotBlank;

@Data
public class AddReq {
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
     * Json格式的描区信息\n'
     *                      'eg: [{"area_ids": [1, 2, 3, 4],'
     *                      '"polygon": {"x": 123, "y": 456}}'
     *                      '{"tag_id_list": [1, 2, 3, 4]}]'
     */
    @NotBlank
    private String region_list;

}
