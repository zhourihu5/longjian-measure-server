package com.longfor.longjian.measure.app.appService.areaService.impl;

import com.alibaba.fastjson.JSON;
import com.longfor.longjian.common.base.LjBaseResponse;
import com.longfor.longjian.common.exception.LjBaseRuntimeException;
import com.longfor.longjian.measure.app.appService.areaService.ICoreAreaService;
import com.longfor.longjian.measure.app.commonEntity.AreasMap;
import com.longfor.longjian.measure.app.feignClient.ICoreAreaFeignService;
import com.longfor.longjian.measure.app.req.feignReq.SearchByIdListReq;
import com.longfor.longjian.measure.app.vo.feignVo.AreaRetrieveVo;
import com.longfor.longjian.measure.app.vo.feignVo.ProjAreaSearchByIdListVo;
import com.longfor.longjian.measure.domain.externalService.IAreaService;
import com.longfor.longjian.measure.po.zhijian2.Area;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
@Slf4j
public class CoreAreaServiceImpl implements ICoreAreaService {

    @Resource
    ICoreAreaFeignService coreAreaFeignService;

    @Resource
    IAreaService areaService;

    @Override
    public List<AreaRetrieveVo> searchByIdList(Integer project_id, List area_id_list) {
        SearchByIdListReq searchByIdListReq = new SearchByIdListReq();
        searchByIdListReq.setArea_id_list(area_id_list);
        searchByIdListReq.setProject_id(project_id);
        try {
            LjBaseResponse<ProjAreaSearchByIdListVo> ljBaseResponse = coreAreaFeignService.searchByIdList(searchByIdListReq);
            return ljBaseResponse.getData().getArea_list();
        }catch (Exception e){
            throw new LjBaseRuntimeException(500,"core_svr 调用失败");
        }
    }

    @Override
    public AreasMap createAreasMapByLeaveIds(List<Integer> ids) {
        List<Area> areas = SelectAllByLeaveIds(ids);
        return createAreasMapByAreaList(areas);
    }

    public AreasMap createAreasMapByAreaList(List<Area> areas) {
        AreasMap aMap = new AreasMap();
        Map<Integer,Area> map = new HashMap<>();
        for (Area area : areas) {
            map.put(area.getId(),area);
        }
        aMap.setAreas(map);
        aMap.setList(areas);
        return aMap;
    }

    List<Area> SelectAllByLeaveIds(List<Integer> ids){
        List<Area> areas = areaService.getAreaByIds(ids);
        List<Integer> totalIds = new ArrayList<>();
        for (Area area : areas) {
            totalIds.add(area.getId());
            List<Integer> sids = splitToIds(area.getPath(), "/");
            totalIds.addAll(sids);
        }
        List<Integer> distinctIds = distinctIntList(totalIds);
        return areaService.getAreaByIds(distinctIds);
    }

    public List<Integer> splitToIds(String idsStr,String sep){
        List<Integer> ids = new ArrayList<>();
        for (String idStr : idsStr.split("/")) {
            if (!"".equals(idStr)) {
                int id = Integer.parseInt(idStr);
                ids.add(id);
            }
        }
        return ids;
    }

    public List<Integer> distinctIntList(List<Integer> list){
        List<Integer> newList = new ArrayList<>();
        HashSet<Integer> set = new HashSet<>();
        set.addAll(list);
        newList.addAll(set);
        return newList;
    }
}
