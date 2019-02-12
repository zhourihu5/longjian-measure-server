package com.longfor.longjian.measure.consts.Enum;

public enum MeasureFinishStatusEnum {
    Processing(1, "未完成"),Done(2, "已完成");

    private Integer id;
    private String value;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    MeasureFinishStatusEnum(Integer id, String value) {
        this.id = id;
        this.value = value;
    }
}
