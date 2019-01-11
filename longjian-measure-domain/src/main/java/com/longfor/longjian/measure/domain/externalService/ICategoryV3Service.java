package com.longfor.longjian.measure.domain.externalService;

import com.longfor.longjian.measure.model.tree.CategoryPathTree;
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

    /**
     *
     * @param categoryKeys
     * @return
     */
    List<CategoryV3> getCategoryByKeys(List<String> categoryKeys);

    /**
     *
     * @param id
     * @return
     */
    List<CategoryV3> searchByRootCategoryId(Integer id);

    /**
     *
     * @param rootCategory
     * @return
     */
    CategoryPathTree getPathTreeByRootCategory(CategoryV3 rootCategory);

    /**
     * 取出所有的category子项
     * @param parent_category_key
     * @return
     */
    List<CategoryV3> searchSubCategoryByFatherKey(String parent_category_key);

    /**
     * 查询子集个数
     * @param key
     * @return
     */
    Integer countCategoryByFatherKey(String key);

    /**
     *
     * @param currentCategoryKey
     * @return
     */
    CategoryV3 getCategoryByKeyNoFoundErr(String currentCategoryKey);

    /**
     * @Description:
     * @param keys
     * @return java.util.List<com.longfor.longjian.measure.po.zhijian2.CategoryV3>
     * @author DDC
     * @date 2019/1/10 20:55
     **/
    List<CategoryV3> SearchCategoryByKeyIn(List keys);
}
