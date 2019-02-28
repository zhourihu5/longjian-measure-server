package com.longfor.longjian.measure.consts.Enum;

public enum MeasureCloseStatusEnum {
    Open(1, "未关闭"),Closed(2, "已关闭");

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
