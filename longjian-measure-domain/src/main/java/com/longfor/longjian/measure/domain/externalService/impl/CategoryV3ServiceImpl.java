package com.longfor.longjian.measure.domain.externalService.impl;

import com.longfor.longjian.measure.dao.zhijian2.CategoryV3Mapper;
import com.longfor.longjian.measure.domain.externalService.ICategoryV3Service;
import com.longfor.longjian.measure.po.zhijian2.CategoryV3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CategoryV3ServiceImpl implements ICategoryV3Service {

    @Autowired
    private CategoryV3Mapper categoryV3Mapper;

    @Override
    public CategoryV3 getCategoryByKey(String category_key) {
        CategoryV3 categoryV3 = new CategoryV3();
        categoryV3.setKey(category_key);
        categoryV3.setDeleteAt(null);
        return categoryV3Mapper.selectOne(categoryV3);
    }

    @Override
    public List<Map<String, Object>> getCategoryByFatherKey(String key) {
        return categoryV3Mapper.getCategoryByFatherKey(key);
    }

    @Override
    public List<Map<String, Object>> getRootCategoryByClsTeamId(Integer measure, Integer teamId) {
        return categoryV3Mapper.getRootCategoryByClsTeamId(measure,teamId);
    }
}
