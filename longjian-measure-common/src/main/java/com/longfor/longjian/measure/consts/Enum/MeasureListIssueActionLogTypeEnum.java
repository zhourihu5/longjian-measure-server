package com.longfor.longjian.measure.consts.Enum;

public enum  MeasureListIssueActionLogTypeEnum {
    Create(1,"创建问题"),
    Assign(2,"分派问题"),
    Repair(3,"整改问题"),
    Approve(4,"销项"),
    Unapprove(5,"整改审核不通过"),
    Close(6,"关闭问题"),
    Open(7,"打开问题"),
    ChangeType(8,"修改问题类型"),
    AddDesc(9,"补充描述文字"),
    AddAttachment(10,"新增描述图片");
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
