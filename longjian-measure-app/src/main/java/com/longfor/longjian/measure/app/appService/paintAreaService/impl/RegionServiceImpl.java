package com.longfor.longjian.measure.app.appService.paintAreaService.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.longfor.longjian.measure.app.appService.areaService.IAreaService;
import com.longfor.longjian.measure.app.appService.paintAreaService.IRegionService;
import com.longfor.longjian.measure.po.zhijian2.Area;
import com.longfor.longjian.measure.po.zhijian2.MeasureList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

@Slf4j
@Service
public class RegionServiceImpl implements IRegionService {

    @Resource
    private IAreaService areaService;

    @Override
    @Transactional
    public void add(Integer project_id, String region_list, Integer id) {
        //todo: (python源码也是todo)
        //将bulk_create 移植到peewee2能加速, 待读源码移植

        //找出所有area_id对应的area
        Set area_id_set = new HashSet();
        List<HashMap> region_info_list = JSONArray.parseArray(region_list, HashMap.class);
        region_info_list.forEach(region_info -> {
            area_id_set.addAll((List)region_info.get("area_id_list"));
        });
        log.info(JSON.toJSONString(area_id_set));
        //area_id_list = area_id_set 不理解为啥set转换list
        List area_id_list = new ArrayList(area_id_set);
        //查询area信息, 用id建立map关系
        List<Area> area_list = areaService.searchByIdList(project_id,area_id_list);

    }
}
