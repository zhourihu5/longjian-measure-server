package com.longfor.longjian.measure.consts.Enum;

/**
 * Jiazm
 * 2018/12/18 09:58
 */
public enum  ApiDropDataReasonEnum {
    NotFound("未找到资源", 0), EXISTS("服务器已经存在", 1), OTHER("其它错误", 2);

    private String name;
    private Integer value;

    ApiDropDataReasonEnum(String name, Integer value) {
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
