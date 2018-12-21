package com.longfor.longjian.measure.model.tree;

import com.longfor.longjian.measure.po.zhijian2.CategoryV3;
import lombok.Data;

import java.util.List;

@Data
public class CheckItemPathTree {
    private CategoryV3 rootCategory;
    private CategoryV3 category;
    private CheckItemPathTreeNode root;
    private List<CheckItemPathTreeNode> nodes;
}
