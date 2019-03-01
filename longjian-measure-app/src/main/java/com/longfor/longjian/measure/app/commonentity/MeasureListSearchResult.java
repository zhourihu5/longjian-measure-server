package com.longfor.longjian.measure.app.commonentity;

import com.longfor.longjian.measure.po.zhijian2.Area;
import com.longfor.longjian.measure.po.zhijian2.MeasureList;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@SuppressWarnings("squid:S1068")
public class MeasureListSearchResult {
    private MeasureList list;
    private String rootCategory;
    private List<Area> topAreas = new ArrayList<>();
    private String finishStatus;
    private String closeStatus;
}
