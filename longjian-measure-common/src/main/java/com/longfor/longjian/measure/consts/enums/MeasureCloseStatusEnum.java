package com.longfor.longjian.measure.consts.enums;

public enum MeasureCloseStatusEnum {
    OPEN(1, "未关闭"),CLOSED(2, "已关闭");

    private Integer id;
    private String value;

    public Integer getId() {
        return id;
    }


    public String getValue() {
        return value;
    }


    MeasureCloseStatusEnum(Integer id, String value) {
        this.id = id;
        this.value = value;
    }
}
