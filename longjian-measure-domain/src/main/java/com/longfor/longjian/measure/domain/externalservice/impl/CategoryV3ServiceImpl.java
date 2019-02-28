package com.longfor.longjian.measure.domain.externalservice.impl;

import com.longfor.longjian.measure.dao.zhijian2.CategoryV3Mapper;
import com.longfor.longjian.measure.domain.externalservice.ICategoryV3Service;
import com.longfor.longjian.measure.model.tree.CategoryPathTree;
import com.longfor.longjian.measure.po.zhijian2.CategoryV3;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
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

    @Override
    public List<CategoryV3> getCategoryByKeys(List<String> categoryKeys) {
        return categoryV3Mapper.getCategoryByKeys(categoryKeys);
    }

    @Override
    public List<CategoryV3> searchByRootCategoryId(Integer id) {
        return categoryV3Mapper.searchByRootCategoryId(id);
    }

    @Override
    public CategoryPathTree getPathTreeByRootCategory(CategoryV3 rootCategory) {
        CategoryPathTree tree = null;
        try {
            List<CategoryV3> categoryV3s = searchByRootCategoryId(rootCategory.getId());
            tree = getPathTree(rootCategory, rootCategory, categoryV3s);
        }catch (Exception e){
            log.warn(e.getMessage());
        }
        return tree;
    }

    @Override
    public List<CategoryV3> searchSubCategoryByFatherKey(String parent_category_key) {
        Example example = new Example(CategoryV3.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("fatherKey",parent_category_key);
        criteria.andIsNull("deleteAt");
        return categoryV3Mapper.selectByExample(example);
    }

    @Override
    public Integer countCategoryByFatherKey(String key) {
        return categoryV3Mapper.countCategoryByFatherKey(key);
    }

    @Override
    public CategoryV3 getCategoryByKeyNoFoundErr(String currentCategoryKey) {
        return categoryV3Mapper.getCategoryByKeyNoFoundErr(currentCategoryKey);
    }

    @Override
    public List<CategoryV3> SearchCategoryByKeyIn(List keys) {
        return categoryV3Mapper.SearchCategoryByKeyIn(keys);
    }

    private CategoryPathTree getPathTree(CategoryV3 rootCategory, CategoryV3 root, List<CategoryV3> items) {

        CategoryPathTree tree = new CategoryPathTree(rootCategory,root);
        items.forEach(item -> {
            tree.addNode(item);
        });
        return tree;
    }
}
