package com.longfor.longjian.measure.domain.externalService;

import com.longfor.longjian.measure.po.zhijian2.Area;

import java.util.List;
import java.util.Map;

public interface IAreaService {
    /**
     *
     * @param project_id
     * @param area_id
     * @return
     */
    Area getAreaByProjIdAndAreaId(Integer project_id, Integer area_id);

    /**
     *
     * @param project_id
     * @param area_id
     * @return
     */
    List<Map<String,Object>> getProMeasureAreaListByFatherId(String project_id, String area_id);

    /**
     *
     * @param areaId
     * @return
     */
    Area getAreaById(String areaId);

    /**
     *
     * @param areaIds
     * @return
     */
    List<Area> getAreaByIds(List<Integer> areaIds);
}
