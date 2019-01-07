package com.longfor.longjian.measure.app.appService.paintAreaService;

public interface IRegionService {
    /**
     * 添加描画区域
     * @param project_id
     * @param region_list
     * @param id
     */
    void add(Integer project_id, String region_list, Integer id);
}
