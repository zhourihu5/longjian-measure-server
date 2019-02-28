package com.longfor.longjian.measure.consts.enums;

public enum ReportStatusEnum {
    NO_REPORT("未上报", 0), PADDING("正在处理上报的数据", 1), ERROR("上报出错", 2), SUCCEED("已上报成功", 3);

    private String name;
    private Integer value;

    ReportStatusEnum(String name, Integer value) {
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
