package com.longfor.longjian.measure.model.tree;

import com.longfor.longjian.measure.po.zhijian2.CheckItemV3;
import lombok.Data;

import java.util.List;

@Data
@SuppressWarnings("squid:S1068")
public class CheckItemPathTreeNode {
    private String key;
    private CheckItemV3 item;
    private List<CheckItemPathTreeNode> subs;
    private Boolean isBlank;
}
