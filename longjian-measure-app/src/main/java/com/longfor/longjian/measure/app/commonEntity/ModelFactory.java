package com.longfor.longjian.measure.app.commonEntity;

import lombok.Data;

/**
 * 这个工厂类用来查询数据库使用的
 */
@Data
public class ModelFactory {
    private Integer proj_id;
    private Integer group_id;
    private Object model;
    private String condi;
    private String fix_condi;
    private Boolean zi_strict;



}
