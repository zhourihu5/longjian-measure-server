package com.longfor.longjian.measure.consts.Enum;
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
/*
    public static void main(String[] args) {
        System.out.print(MeasureListCloseStatusEnum.Closed.getId());
    }*/
}
