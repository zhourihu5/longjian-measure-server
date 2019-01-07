package com.longfor.longjian.measure.app.appService.areaService;

import com.longfor.longjian.measure.po.zhijian2.Area;

import java.util.List;

public interface IAreaService {
    List<Area> searchByIdList(Integer project_id, List area_id_list);
}
