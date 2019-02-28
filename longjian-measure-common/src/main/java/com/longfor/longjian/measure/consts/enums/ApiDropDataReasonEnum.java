package com.longfor.longjian.measure.consts.enums;


/**
 * Jiazm
 * 2018/12/18 09:58
 */
public enum ApiDropDataReasonEnum {
    NOTFOUND("未找到资源", "0"),
    EXISTS("资源已存在", "2"),
    MEASYREZONENOTFOUND("测区不存在", "11"),
    MEASUREZONEISCLOSE("测区已关闭", "12"),
    MEASUREZONEEXISTS("测区已存在", "13"),
    MEASUREZONEUUIDEXISTS("测区已存在", "14"),
    MEASUREZONERESULTEXISTS("该测区已有测量数据", "21"),
    MEASUREZONERESULTUUIDEXISTS("该测量数据已上传", "22"),
    MEASUREREGIONNOTFOUND("描画区域不存在", "31"),
    CATEGORYNOTFOUND("类型不存在", "41"),
    MEASSUREZONEHASISSUEALREADY("测区已存在爆点整改信息", "51"),
    MEASSUREISSUEWASDELETED("爆点信息已被删除", "52"),
    MEASUREUSERNOTINSQUAD("人员不存在于测量小组中", "53"),
    OTHER("未知错误", "999"),
    MEASURERULENOFOUND("规则不存在","61");
    private String name;
    private String value;

    ApiDropDataReasonEnum(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }


    public String getValue() {
        return value;
    }

}
