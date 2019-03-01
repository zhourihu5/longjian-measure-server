package com.longfor.longjian.measure.app.appservice.paintareaservice;

import java.util.List;

public interface IRegionService {
    /**
     * 添加描画区域
     * @param projectId
     * @param regionList
     * @param id
     */
    void add(Integer projectId, String regionList, Integer id);

    /**
     * 编辑描画区域
     * @param projectId
     * @param regionInfoList
     */
    void edit(Integer projectId, String regionInfoList);

    /**
     * 删除描画区域
     * @param projectId
     * @param collect
     */
    void delete(Integer projectId, List<Integer> collect);
}
