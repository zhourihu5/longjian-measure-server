package com.longfor.longjian.measure.app.commonentity;

import lombok.Data;

/**
 * 这个工厂类用来查询数据库使用的
 */
@Data
@SuppressWarnings("squid:S1068")
public class ModelFactory {
    @SuppressWarnings("squid:S00116")
    private Integer proj_id;
    @SuppressWarnings("squid:S00116")
    private Integer group_id;
    @SuppressWarnings("squid:S00116")
    private Object model;
    @SuppressWarnings("squid:S00116")
    private String condi;
    @SuppressWarnings("squid:S00116")
    private String fix_condi;
    @SuppressWarnings("squid:S00116")
    private Boolean zi_strict;



}
