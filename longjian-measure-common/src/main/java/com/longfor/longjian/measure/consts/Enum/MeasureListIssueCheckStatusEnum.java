package com.longfor.longjian.measure.consts.Enum;

public enum  MeasureListIssueCheckStatusEnum {
    CheckYes(1,"通过"),
    CheckNo(2,"不通过");
    private Integer id;
    private String value;
    public Integer getId() {
        return id;
    }


    public String getValue() {
        return value;
    }


    MeasureListIssueCheckStatusEnum(Integer id, String value) {
        this.id = id;
        this.value = value;
    }
}
