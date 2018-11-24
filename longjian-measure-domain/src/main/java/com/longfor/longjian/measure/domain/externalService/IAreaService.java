package com.longfor.longjian.measure.domain.externalService;

import com.longfor.longjian.measure.po.zhijian2.Area;

public interface IAreaService {
    /**
     *
     * @param project_id
     * @param area_id
     * @return
     */
    Area getAreaByProjIdAndAreaId(Integer project_id, Integer area_id);
}
