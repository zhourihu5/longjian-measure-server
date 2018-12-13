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
    /**
     * 描画区域列表
     */
    private List<RegionListVo> region_list;
    /**
     * 本次获取的最后ID
     */
    private Integer last_id;
}
