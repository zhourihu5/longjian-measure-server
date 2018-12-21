package com.longfor.longjian.measure.model.tree;

import com.longfor.longjian.measure.po.zhijian2.CategoryV3;
import lombok.Data;

import java.util.List;

@Data
public class CategoryPathTreeNode {
    private String key;
    private CategoryV3 item;
    private CheckItemPathTree checkItemTree;
    private CategoryPathTreeNode father;
    private List<CategoryPathTreeNode> subs;
    private Boolean isBlank;

    public CategoryPathTreeNode(){
    }

    public CategoryPathTreeNode(CategoryV3 categoryV3){
        this.item = categoryV3;
    }
}
