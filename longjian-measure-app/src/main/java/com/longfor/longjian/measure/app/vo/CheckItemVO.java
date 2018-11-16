package com.longfor.longjian.measure.app.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by wangxs on 2018/11/16.
 */
@Data
public class CheckItemVO {
    private String fatherKey;
    private boolean isLeaf;
    private Integer key;
    private String label;
    private String name;
    private Integer value;
}
