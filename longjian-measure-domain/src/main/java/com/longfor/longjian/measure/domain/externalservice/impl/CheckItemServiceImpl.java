package com.longfor.longjian.measure.domain.externalservice.impl;

import com.longfor.longjian.measure.dao.zhijian2.CategoryV3Mapper;
import com.longfor.longjian.measure.domain.externalservice.ICheckItemService;
import com.longfor.longjian.measure.po.zhijian2.CategoryV3;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CheckItemServiceImpl implements ICheckItemService {
    @Resource
    private CategoryV3Mapper categoryV3Mapper;
    @Override
    public CategoryV3 getCategoryByKeyNoFoundErr(String categoryKey) {
        return categoryV3Mapper.getCategoryByKeyNoFoundErr(categoryKey);
    }
}
