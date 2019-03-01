package com.longfor.longjian.measure.consts.constant;

public class ApiDropDataReasonConstant {
    private ApiDropDataReasonConstant() {
    }

    public static final String NOTFOUND = "name:\"未找到资源\" value:\"1\"";
    public static final String EXISTS = "name:\"资源已存在\" value:\"2\"";
    public static final String MEASYREZONENOTFOUND = "name:\"测区不存在\" value:\"11\"";
    public static final String MEASUREZONEISCLOSE = "name:\"测区已关闭\" value:\"12\"";
    public static final String MEASUREZONEEXISTS = "name:\"测区已存在\" value:\"13\"";
    public static final String MEASUREZONEUUIDEXISTS = "name:\"测区已存在\" value:\"14\"";

    public static final String MEASUREZONERESULTEXISTS = "name:\"该测区已有测量数据\" value:\"21\"";
    public static final String MEASUREZONERESULTUUIDEXISTS = "name:\"该测量数据已上传\" value:\"22\"";

    public static final String MEASUREREGIONNOTFOUND = "name:\"描画区域不存在\" value:\"31\"";

    public static final String CATEGORYNOTFOUND = "name:\"类型不存在\" value:\"41\"";

    public static final String MEASSUREZONEHASISSUEALREADY = "name:\"测区已存在爆点整改信息\" value:\"51\"";
    public static final String MEASSUREISSUEWASDELETED = "name:\"爆点信息已被删除\" value:\"52\"";
    public static final String MEASUREUSERNOTINSQUAD = "name:\"人员不存在于测量小组中\" value:\"53\"";

    public static final String MEASURERULENOFOUND = "const:\"61,规则不存在\"";
    public static final String MEASURERULEERROR = "const:\"62,测量结果计算失败\"";

    public static final String HOUSEQMISSUELOGUUIDEXISTS = "const:\"101,日志信息已存在\"";
    public static final String HOUSEQMTASKREMOVED = "const:\"102,工程任务已删除\"";

    public static final String OTHER = "name:\"未知错误\" value:\"999\"";
}
