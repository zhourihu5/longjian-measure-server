package com.longfor.longjian.measure.consts.Enum;

public enum MeasureFinishStatusEnum {
    PROCESSING(1, "未完成"),DONE(2, "已完成");

    private Integer id;
    private String value;

    public Integer getId() {
        return id;
    }


    public String getValue() {
        return value;
    }


    MeasureFinishStatusEnum(Integer id, String value) {
        this.id = id;
        this.value = value;
    }
}
