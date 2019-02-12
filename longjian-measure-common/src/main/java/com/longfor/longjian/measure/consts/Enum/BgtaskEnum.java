package com.longfor.longjian.measure.consts.Enum;

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

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
