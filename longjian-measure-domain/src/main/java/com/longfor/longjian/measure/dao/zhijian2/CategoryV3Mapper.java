package com.longfor.longjian.measure.dao.zhijian2;

import com.longfor.gaia.gfs.data.mybatis.LFMySQLMapper;
import com.longfor.longjian.measure.po.zhijian2.CategoryV3;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PatchMapping;

import java.util.List;
import java.util.Map;

public interface CategoryV3Mapper extends LFMySQLMapper<CategoryV3> {
    /**
     * 查子集
     * @param key
     * @return
     */
    List<Map<String,Object>> getCategoryByFatherKey(@Param("fatherKey") String key);

    /**
     *
     * @param measure
     * @param teamId
     * @return
     */
    List<Map<String,Object>> getRootCategoryByClsTeamId(@Param("cls") Integer measure, @Param("teamId") Integer teamId);

    /**
     *
     * @param categoryKeys
     * @return
     */
    List<CategoryV3> getCategoryByKeys(@Param("categoryKeys") List<String> categoryKeys);

    /**
     *
     * @param id
     * @return
     */
    List<CategoryV3> searchByRootCategoryId(@Param("id") Integer id);

    /**
     * 查询子集个数
     * @param key
     * @return
     */
    Integer countCategoryByFatherKey(@Param("fatherKey") String key);

    /**
     *
     * @param currentCategoryKey
     * @return
     */
    CategoryV3 getCategoryByKeyNoFoundErr(@Param("key") String currentCategoryKey);

    List<CategoryV3> SearchCategoryByKeyIn(@Param("keyList") List keys);
}