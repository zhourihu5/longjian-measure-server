package com.longfor.longjian.measure.consts.enums;

public enum  MeasureListIssueActionLogTypeEnum {
    CREATE(1,"创建问题"),
    ASSIGN(2,"分派问题"),
    REPAIR(3,"整改问题"),
    APPROVE(4,"销项"),
    UNAPPROVE(5,"整改审核不通过"),
    CLOSE(6,"关闭问题"),
    OPEN(7,"打开问题"),
    CHANGETYPE(8,"修改问题类型"),
    ADDDESC(9,"补充描述文字"),
    ADDATTACHMENT(10,"新增描述图片");
    private Integer id;
    private String value;
    public Integer getId() {
        return id;
    }


    public String getValue() {
        return value;
    }


    MeasureListIssueActionLogTypeEnum(Integer id, String value) {
        this.id = id;
        this.value = value;
    }
}
