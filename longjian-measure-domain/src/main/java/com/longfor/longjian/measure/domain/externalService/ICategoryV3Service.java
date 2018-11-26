package com.longfor.longjian.measure.domain.externalService;

import com.longfor.longjian.measure.po.zhijian2.CategoryV3;

import java.util.List;
import java.util.Map;

public interface ICategoryV3Service {
    /**
     *通过key查对象
     * @param category_key
     * @return
     */
    CategoryV3 getCategoryByKey(String category_key);

    /**
     * 查子集
     * @param key
     * @return
     */
    List<Map<String,Object>> getCategoryByFatherKey(String key);

    /**
     *
     * @param measure
     * @param teamId
     * @return
     */
    List<Map<String,Object>> getRootCategoryByClsTeamId(Integer measure, Integer teamId);
}
