package com.longfor.longjian.measure.app.appService.paintAreaService;

import java.util.List;

public interface IRegionService {
    /**
     * 添加描画区域
     * @param project_id
     * @param region_list
     * @param id
     */
    void add(Integer project_id, String region_list, Integer id);

    /**
     * 编辑描画区域
     * @param project_id
     * @param region_info_list
     */
    void edit(Integer project_id, String region_info_list);

    /**
     * 删除描画区域
     * @param project_id
     * @param collect
     */
    void delete(Integer project_id, List<Integer> collect);
}
