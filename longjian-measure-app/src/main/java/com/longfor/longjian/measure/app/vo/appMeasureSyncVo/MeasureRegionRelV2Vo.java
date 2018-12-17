package com.longfor.longjian.measure.app.vo.appMeasureSyncVo;

import com.longfor.longjian.measure.app.vo.proPaintAreaManageVo.RegionListVo;
import com.longfor.longjian.measure.app.vo.proPaintAreaManageVo.RelVo;
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
