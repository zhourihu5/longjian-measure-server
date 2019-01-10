package com.longfor.longjian.measure.app.appService.paintAreaService.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.longfor.longjian.common.exception.LjBaseRuntimeException;
import com.longfor.longjian.measure.app.appService.areaService.ICoreAreaService;
import com.longfor.longjian.measure.app.appService.paintAreaService.IRegionService;
import com.longfor.longjian.measure.app.vo.feignVo.AreaRetrieveVo;
import com.longfor.longjian.measure.consts.Enum.MeasureErrorEnum;
import com.longfor.longjian.measure.po.zhijian2.Area;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class RegionServiceImpl implements IRegionService {

    @Resource
    private ICoreAreaService coreAreaService;

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
        List<AreaRetrieveVo> areaList = coreAreaService.searchByIdList(project_id,area_id_list);
        log.info("area_id_list: " + JSON.toJSONString(area_id_list));
        log.info("area_list: " + JSON.toJSONString(areaList));
        List<Area> area_list = JSONArray.parseArray(JSON.toJSONString(areaList),Area.class);
        Map<Integer,Area> area_map = area_list.stream().collect(Collectors.toMap(Area::getId,area -> area));
        log.info("area_map: " + JSON.toJSONString(area_map));

        Set<Integer> difference = new HashSet(area_id_list);
        difference.removeAll(area_map.keySet());
        if (difference.size() > 0){
            log.info("missing area_id: " + JSON.toJSONString(difference));
            throw new LjBaseRuntimeException(MeasureErrorEnum.AreaMissing.getId(),MeasureErrorEnum.AreaMissing.getValue());
        }

        //todo: 有复杂的选择语句如何处理? 直接在peewee外部加一个固定参数的功能? (python源码也是todo)
        //获取measure_region现有的最大的index



    }
}
