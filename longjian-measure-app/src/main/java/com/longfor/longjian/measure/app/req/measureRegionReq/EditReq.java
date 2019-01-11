package com.longfor.longjian.measure.app.req.measureRegionReq;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

@Getter
@Setter
public class EditReq {
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
     *                      'eg:[{"region_ids": [1686548, 1686545, 1686546],'
     *                      '"polygon": {"X": 550.0848896434635,'
     *                      '"Y": 350.4244482173175}}]'
     */
    @NotBlank
    private String region_info_list;

}
