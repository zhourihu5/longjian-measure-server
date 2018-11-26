package com.longfor.longjian.measure.dao.zhijian2;

import com.longfor.gaia.gfs.data.mybatis.LFMySQLMapper;
import com.longfor.longjian.measure.po.zhijian2.CategoryV3;
import org.apache.ibatis.annotations.Param;

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
}