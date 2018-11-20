package com.longfor.longjian.measure.app.vo.appMeasureSyncVo;

import com.longfor.longjian.measure.app.vo.proPaintAreaManageVo.RegionListVo;
import com.longfor.longjian.measure.app.vo.proPaintAreaManageVo.RelVo;
import lombok.Data;

import java.util.List;

/**
 * wangxs
 * 2018-11-20 16:27
 */
@Data
public class MeasureRegionVo {
    private List<RegionListVo> region_list;
    private List<RelVo> rel_list;
}
