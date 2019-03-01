package com.longfor.longjian.measure.consts.enums;
/**
 * Jiazm
 * 2018/12/18 15:57
 */
public enum MeasureListCloseStatusEnum {
    UNCLOSE(1, "打开"),CLOSED(2, "关闭");

    private Integer id;
    private String value;

    public Integer getId() {
        return id;
    }


    public String getValue() {
        return value;
    }


    MeasureListCloseStatusEnum(Integer id, String value) {
        this.id = id;
        this.value = value;
    }
}
