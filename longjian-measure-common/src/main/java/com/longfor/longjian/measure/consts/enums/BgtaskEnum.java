package com.longfor.longjian.measure.consts.enums;

/**
 * '''后台任务类型'''
 */
public enum BgtaskEnum {

    MEASURE_LIST_CREATE("实测实量:后台创建任务","app_measure.measure.measure_list.create");
    private String name;
    private String value;
    BgtaskEnum(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }


    public String getValue() {
        return value;
    }

}
