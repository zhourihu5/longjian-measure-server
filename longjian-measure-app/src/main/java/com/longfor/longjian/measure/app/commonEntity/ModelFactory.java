package com.longfor.longjian.measure.app.commonEntity;

import com.longfor.longjian.measure.po.zhijian2.MeasureList;
import lombok.Data;

import javax.sql.rowset.spi.SyncResolver;

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
