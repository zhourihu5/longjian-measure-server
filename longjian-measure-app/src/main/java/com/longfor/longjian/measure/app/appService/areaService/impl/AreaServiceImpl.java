package com.longfor.longjian.measure.app.appService.areaService.impl;

import com.longfor.longjian.measure.app.appService.areaService.IAreaService;
import com.longfor.longjian.measure.po.zhijian2.Area;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AreaServiceImpl implements IAreaService {

    @Override
    public List<Area> searchByIdList(Integer project_id, List area_id_list) {

        return null;
    }
}
