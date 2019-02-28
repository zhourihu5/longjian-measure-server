package com.longfor.longjian.measure.domain.externalservice.impl;

import com.longfor.longjian.measure.dao.zhijian2.CategoryV3Mapper;
import com.longfor.longjian.measure.domain.externalservice.ICheckItemService;
import com.longfor.longjian.measure.po.zhijian2.CategoryV3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CheckItemServiceImpl implements ICheckItemService {
    @Autowired
    private CategoryV3Mapper categoryV3Mapper;
    @Override
    public CategoryV3 getCategoryByKeyNoFoundErr(String category_key) {
        return categoryV3Mapper.getCategoryByKeyNoFoundErr(category_key);
    }
}
