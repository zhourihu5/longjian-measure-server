package com.longfor.longjian.measure.domain.externalService.impl;

import com.google.common.collect.Lists;
import com.longfor.gaia.gfs.data.mybatis.datasource.LFAssignDataSource;
import com.longfor.longjian.measure.dao.zhijian2.CategoryV3Mapper;
import com.longfor.longjian.measure.dao.zhijian2.CheckItemV3Mapper;
import com.longfor.longjian.measure.domain.externalService.ICheckItemV3Service;
import com.longfor.longjian.measure.po.zhijian2.CategoryV3;
import com.longfor.longjian.measure.po.zhijian2.CheckItemV3;
import com.longfor.longjian.measure.vo.StoreUrlVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * Jiazm 2019/01/09 20:35
 */
@Service
@Slf4j
public class CheckItemV3ServiceImpl implements ICheckItemV3Service {
    @Resource
    private CheckItemV3Mapper checkItemV3Mapper;
    @Resource
    private CategoryV3Mapper categoryV3Mapper;
    @Override
    @LFAssignDataSource("zhijian2")
    public CheckItemV3 getCheckItemByKeyNoFoundErr(String subKey) {
        Example example = new Example(CheckItemV3.class);
        example.createCriteria().andEqualTo("key", subKey);
        return checkItemV3Mapper.selectOneByExample(example);
    }

    @Override
    @LFAssignDataSource("zhijian2")
    public CategoryV3 getCategoryByKeyNoFoundErr(String subKey) {
        Example example = new Example(CategoryV3.class);
        example.createCriteria().andEqualTo("key", subKey);
        return categoryV3Mapper.selectOneByExample(example);
    }
    @Override
    @LFAssignDataSource("zhijian2")
    public CategoryV3 getRootCategoryNoFoundErr(Integer rootCategoryId) {
        Example example =new Example(CategoryV3.class);
        example.createCriteria().andEqualTo("id",rootCategoryId);
        return categoryV3Mapper.selectOneByExample(example);
    }
}
