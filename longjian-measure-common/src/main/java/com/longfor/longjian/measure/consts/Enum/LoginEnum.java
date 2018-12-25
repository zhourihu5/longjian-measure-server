package com.longfor.longjian.measure.consts.Enum;

public enum LoginEnum {
    NO_PERMISSION("没有权限",000011);
    private String name;
    private Integer value;

    LoginEnum(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Integer getValue() {
        return value;
    }
}
