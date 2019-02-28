package com.longfor.longjian.measure.consts.enums;

/**
 * Jiazm
 * 2018/12/18 15:54
 */
public enum  MeasureListFinishStatusEnum {
    UNFINISH(1, "进行中"),FINISHED(2, "已完成");

    private Integer id;
    private String value;

    public Integer getId() {
        return id;
    }


    public String getValue() {
        return value;
    }


    MeasureListFinishStatusEnum(Integer id, String value) {
        this.id = id;
        this.value = value;
    }
}
