package com.longfor.longjian.measure.consts.Enum;

/**
 * Created by lipeishuai on 2018/11/11.
 */
public enum CompanyLevelEnum {

    Group(1, "集团"),Team(2, "公司"),SubTeam(3, "子公司");

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

    CompanyLevelEnum(Integer id, String value) {
        this.id = id;
        this.value = value;
    }


}
