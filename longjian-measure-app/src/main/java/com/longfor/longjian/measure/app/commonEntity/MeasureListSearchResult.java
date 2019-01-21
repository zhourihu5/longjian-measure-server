package com.longfor.longjian.measure.app.commonEntity;

import com.longfor.longjian.measure.po.zhijian2.Area;
import com.longfor.longjian.measure.po.zhijian2.MeasureList;
import lombok.Data;

@Data
public class MeasureListSearchResult {
    private MeasureList list;
    private String rootCategory;
    private Area topAreas;
    private String finishStatus;
    private String closeStatus;
}
