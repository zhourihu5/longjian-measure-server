package com.longfor.longjian.measure.consts.Enum;

public enum  MeasureListIssueCheckStatusEnum {
    CheckYes(1,"通过"),
    CheckNo(2,"不通过");
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

    MeasureListIssueCheckStatusEnum(Integer id, String value) {
        this.id = id;
        this.value = value;
    }
}
