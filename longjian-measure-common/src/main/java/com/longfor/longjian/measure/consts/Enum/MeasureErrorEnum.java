package com.longfor.longjian.measure.consts.Enum;

/**
 * 实测错误
 * 错误码范围 1200000 ~ 1299999
 *
 *core层 数据操作错误 1200100 ~ 1200199
 */
public enum MeasureErrorEnum {
    AreaMissing(1200301, "区域缺失"),
    MeasureZoneExist(1200201,"测区存在无法删除描区");
    private Integer id;
    private String value;
    public Integer getId() {
        return id;
    }


    public String getValue() {
        return value;
    }


    MeasureErrorEnum(Integer id, String value) {
        this.id = id;
        this.value = value;
    }
}
