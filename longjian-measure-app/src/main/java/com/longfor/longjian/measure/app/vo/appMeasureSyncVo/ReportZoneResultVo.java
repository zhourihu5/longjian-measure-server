package com.longfor.longjian.measure.app.vo.appMeasureSyncVo;

import lombok.Data;

import java.util.List;

/**
 * wangxs
 * 2018-11-20 19:34
 */
@Data
public class ReportZoneResultVo {
    /**
     * 本次上传的唯一字符串，用于查询是否上传成功
     */
    private String report_uuid;
    /**
     * 要上传的数据
     */
    private List<ZoneTestVo> data;
}
