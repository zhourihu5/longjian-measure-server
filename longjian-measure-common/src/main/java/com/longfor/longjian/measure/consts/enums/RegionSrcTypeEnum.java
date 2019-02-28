package com.longfor.longjian.measure.consts.enums;

/**
 * 描画区域来源
 */
public enum  RegionSrcTypeEnum {

    BACKEND(1, "后台添加"),
    CLIENT(2, "客户端添加");

    private Integer id;
    private String value;

    public Integer getId() {
        return id;
    }


    public String getValue() {
        return value;
    }


    RegionSrcTypeEnum(Integer id, String value) {
        this.id = id;
        this.value = value;
    }
}
