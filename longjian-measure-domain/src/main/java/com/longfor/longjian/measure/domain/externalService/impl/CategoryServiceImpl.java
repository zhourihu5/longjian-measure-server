package com.longfor.longjian.measure.domain.externalService.impl;

import com.longfor.longjian.measure.dao.zhijian2.CategoryV3Mapper;
import com.longfor.longjian.measure.domain.externalService.ICategoryService;
import com.longfor.longjian.measure.po.zhijian2.CategoryV3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private CategoryV3Mapper categoryV3Mapper;

    @Override
    public CategoryV3 getCategoryByKey(String category_key) {
        CategoryV3 categoryV3 = new CategoryV3();
        categoryV3.setKey(category_key);
        categoryV3.setDeleteAt(null);
        return categoryV3Mapper.selectOne(categoryV3);
    }
}
