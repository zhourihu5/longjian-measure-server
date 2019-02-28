package com.longfor.longjian.measure.app.vo.measureanalysisvo;

import lombok.Data;

/**
 * Created by wangxs on 2018/11/16.
 */
@Data
public class CheckItemVo {
    private String fatherKey;
    private boolean isLeaf;
    private String key;
    private String label;
    private String name;
    private String value;

    private Integer id;
}
