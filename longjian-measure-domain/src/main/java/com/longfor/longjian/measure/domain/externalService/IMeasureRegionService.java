package com.longfor.longjian.measure.domain.externalService;

import java.util.List;
import java.util.Map;

public interface IMeasureRegionService {
    /**
     * 项目描画区域管理新增描画区域请求测区
     * @param project_id
     * @param area_id
     * @return
     */
    List<Map<String,Object>> getProjMeasureRegionByAreaId(Integer project_id, Integer area_id);
}
