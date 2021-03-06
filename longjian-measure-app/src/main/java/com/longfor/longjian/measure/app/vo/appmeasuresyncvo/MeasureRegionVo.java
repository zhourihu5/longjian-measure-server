package com.longfor.longjian.measure.app.vo.appmeasuresyncvo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.longfor.longjian.measure.app.vo.propaintareamanagevo.RelVo;
import lombok.Data;

import java.util.List;

/**
 * wangxs
 * 2018-11-20 16:27
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MeasureRegionVo {
    private List<MeasureRegionListVo> region_list;
    private List<RelVo> rel_list;
}
