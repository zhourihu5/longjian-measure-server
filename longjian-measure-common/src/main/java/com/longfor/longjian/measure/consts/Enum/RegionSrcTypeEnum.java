package com.longfor.longjian.measure.consts.Enum;

/**
 * 描画区域来源
 */
public enum  RegionSrcTypeEnum {

    BackEnd(1, "后台添加"),
    Client(2, "客户端添加");

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
