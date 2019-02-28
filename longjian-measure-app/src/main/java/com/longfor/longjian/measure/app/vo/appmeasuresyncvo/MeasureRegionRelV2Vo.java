package com.longfor.longjian.measure.app.vo.appmeasuresyncvo;

import com.longfor.longjian.measure.app.vo.propaintareamanagevo.RelVo;
import lombok.Data;

import java.util.List;

@Data
public class MeasureRegionRelV2Vo {
    /**
     * 描画区域列表
     */
    private List<RelVo> rel_list;
    /**
     * 本次获取的最后ID
     */
    private Integer last_id;
}
