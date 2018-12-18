package com.longfor.longjian.measure.consts.Enum;
/**
 * Jiazm
 * 2018/12/17 16:53
 */
public enum MeasureRegionSrcType {
    Backend(1,"后台添加"),App(2,"客户端添加");

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

    MeasureRegionSrcType(Integer id, String value) {
        this.id = id;
        this.value = value;
    }
}
