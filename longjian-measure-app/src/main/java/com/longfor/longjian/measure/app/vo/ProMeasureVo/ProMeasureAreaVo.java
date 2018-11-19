package com.longfor.longjian.measure.app.vo.ProMeasureVo;

import lombok.Data;

import java.util.List;

/**
 * wangxs
 * 2018-11-17 14:24
 */
@Data
public class ProMeasureAreaVo {
    private Integer areaClassId;
    private String createAt;
    private String deleteAt;
    private String drawingMd5;
    private Integer fatherId;
    private Integer id;
    private Integer isLock;
    private boolean isParent;
    private String location;
    private String name;
    private Integer orderBy;
    private String path;
    private List<String> pathNames;
    private Integer projectId;
    private Integer typ;
    private String updateAt;

}
