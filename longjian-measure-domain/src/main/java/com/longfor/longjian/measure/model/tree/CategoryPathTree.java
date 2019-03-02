package com.longfor.longjian.measure.model.tree;

import com.longfor.longjian.measure.constant.ErrorConstant;
import com.longfor.longjian.measure.po.zhijian2.CategoryV3;
import com.longfor.longjian.measure.util.StringUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Data
@Slf4j
public class CategoryPathTree {
    private CategoryV3 rootCategory;
    private CategoryPathTreeNode root;
    private List<CategoryPathTreeNode> nodes;

    public CategoryPathTree(CategoryV3 rootCategory,CategoryV3 root){
        this.rootCategory = rootCategory;
        this.root = new CategoryPathTreeNode(root);
        this.nodes = new ArrayList<>();
    }

    public void addNode(CategoryV3 item){
        String err;
        String keyPath = item.childPath();
        try{
            List<Object> list = this.findNode(keyPath);
            CategoryPathTreeNode father = (CategoryPathTreeNode)(list.get(0));
            CategoryPathTreeNode node = (CategoryPathTreeNode)(list.get(1));
            String keyPathLeft = (String)(list.get(2));
            if (node != null){
                if (!node.getIsBlank()){
                    err = ErrorConstant.CHECK_ITEM_ORDER_EXISTS;
                    log.warn(err);
                    return ;
                }
                node.setItem(item);
                node.setIsBlank(false);
                return ;
            }
            String[] keys = StringUtil.getPathSlice(keyPathLeft);
            CategoryPathTreeNode newNode = new CategoryPathTreeNode();
            for (String key:keys
                 ) {
                newNode = newBlankCategoryPathTreeNode(key,father);
                if (father.equals(this.root)){
                    this.nodes.add(newNode);
                }else {
                    father.getSubs().add(newNode);
                }
                father = newNode;
            }
            newNode.setItem(item);
            newNode.setIsBlank(false);
        }catch (Exception e){
            log.warn(e + "");
            throw e;
        }
    }

    private CategoryPathTreeNode newBlankCategoryPathTreeNode(String key, CategoryPathTreeNode father) {
        String fatherPath = "/";
        if (father != null){
            fatherPath = father.getItem().getPath();
        }
        CategoryPathTreeNode categoryPathTreeNode = new CategoryPathTreeNode();
        categoryPathTreeNode.setKey(key);
        categoryPathTreeNode.setItem(new CategoryV3(fatherPath));
        categoryPathTreeNode.setFather(father);
        categoryPathTreeNode.setSubs(new ArrayList<>());
        categoryPathTreeNode.setIsBlank(true);
        return categoryPathTreeNode;
    }

    private List<Object> findNode(String keyPath) {
        String keyPathLeft = "";
        List<Object> result = new ArrayList<>();
        String err = null;
        if (this.root != null){
            String prefix = this.root.getItem().childPath();
            if (keyPath.length() <= 0 || !prefix.equals(keyPath.substring(0,prefix.length()))){
                err = ErrorConstant.CHECK_ITEM_INVALID_ORDER_PRE;
                log.warn(err);
                return result;
            }
            keyPath = keyPath.substring(prefix.length() - 1);
            CategoryPathTreeNode father = this.root;
        }
        String [] keys = StringUtil.getPathSlice(keyPath);
        if (keys.length == 0 || err != null){
            err = ErrorConstant.CHECK_ITEM_INVALID_ORDER_FMT;
            log.warn(err);
            return result;
        }
        List<Object> list = this.fNode(keys,this.root,this.nodes);
        CategoryPathTreeNode father = (CategoryPathTreeNode)(list.get(0));
        CategoryPathTreeNode node = (CategoryPathTreeNode)(list.get(1));
        String[] keysLeft = (String[])(list.get(2));
        if (keysLeft.length > 0){
            keyPathLeft = StringUtil.keysToPath(keysLeft);
        }
        result.add(father);
        result.add(node);
        result.add(keyPathLeft);
        return result;
    }

    private List<Object> fNode(String[] keys, CategoryPathTreeNode father, List<CategoryPathTreeNode> nodes) {
        List<Object> result = new ArrayList<>();
        CategoryPathTreeNode reFather = father;
        CategoryPathTreeNode reNode = null;
        String[] keyLeft = keys;
        String key = keys[0];
        //数组复制
        String[] reKeys = new String[keys.length - 1];
        System.arraycopy(keys,1,reKeys,0,keys.length - 1);
        for (CategoryPathTreeNode node:nodes
             ) {
            if (node.getKey().equals(key)){
                if (reKeys.length > 0){
                    List<Object> list = this.fNode(reKeys,node,node.getSubs());
                    reFather = (CategoryPathTreeNode)(list.get(0));
                    reNode = (CategoryPathTreeNode)(list.get(1));
                    keyLeft = (String[])(list.get(2));
                }else {
                    reNode = node;
                    keyLeft = reKeys;
                }
                break;
            }
        }
        result.add(reFather);
        result.add(reNode);
        result.add(keyLeft);
        return result;
    }

    public CategoryPathTreeNode findTop(String childPath) {
        try {
            CategoryPathTreeNode top = (CategoryPathTreeNode) this.findNode(childPath).get(1);
            if (top == null){
                return null;
            }
            for (int i = 0; i < 20; i ++){
                if (top.getFather() == null || top.getFather().getItem().equals(this.rootCategory)){
                    break;
                }
                top = top.getFather();
            }
            return top;
        }catch (Exception e){
            log.warn(e + "");
            throw e;
        }
    }
}
