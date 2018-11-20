package com.longfor.longjian.measure.app.vo.appMeasureSyncVo;

import com.longfor.longjian.measure.app.vo.proPaintAreaManageVo.RegionListVo;
import lombok.Data;

import java.util.List;

/**
 * wangxs
 * 2018-11-20 16:38
 */
@Data
public class MeasureRegionV2Vo {
    private List<RegionListVo> region_list;
    private Integer last_id;
}
