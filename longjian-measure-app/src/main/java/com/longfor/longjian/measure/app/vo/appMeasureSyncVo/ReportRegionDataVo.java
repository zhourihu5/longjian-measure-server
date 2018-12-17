package com.longfor.longjian.measure.app.vo.appMeasureSyncVo;

import lombok.Data;

/**
 * Jiazm
 * 2018/12/15 11:24
 */
@Data
public class ReportRegionDataVo {
    private String uuid;
    private Integer project_id;
    private Integer area_id;
    private String drawing_md5;
    private String polygon;
    /**
     * 来源类型：1，后台添加；2，客户端添加
     */
    private Integer src_type;

}
