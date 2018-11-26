package com.longfor.longjian.measure.app.appService.paintAreaService.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.longfor.longjian.common.base.LjBaseResponse;
import com.longfor.longjian.measure.app.appService.paintAreaService.IProPaintAreaManageService;
import com.longfor.longjian.measure.app.req.paintAreaReq.GetProjMeasureRegionReq;
import com.longfor.longjian.measure.app.req.paintAreaReq.GetGroupMeasureRegionTagReq;
import com.longfor.longjian.measure.app.req.paintAreaReq.GetProjMeasureRegionTagReq;
import com.longfor.longjian.measure.app.vo.proPaintAreaManageVo.*;
import com.longfor.longjian.measure.consts.constant.MeasureTagConstant;
import com.longfor.longjian.measure.consts.util.ConvertUtil;
import com.longfor.longjian.measure.domain.externalService.IMeasureRegionRelService;
import com.longfor.longjian.measure.domain.externalService.IMeasureRegionService;
import com.longfor.longjian.measure.domain.externalService.IMeasureTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * wangxs
 * 2018-11-22
 */
@Service
public class ProPaintAreaManageServiceImpl implements IProPaintAreaManageService {

    @Autowired
    private IMeasureTagService measureTagService;
    @Autowired
    private IMeasureRegionService measureRegionService;
    @Autowired
    private IMeasureRegionRelService measureRegionRelService;


    @Override
    public LjBaseResponse<GroupRegionTagVo> getGroupMeasureRegionTag(GetGroupMeasureRegionTagReq getGroupMeasureRegionTagReq) throws InvocationTargetException, IntrospectionException, InstantiationException, IllegalAccessException {
        LjBaseResponse<GroupRegionTagVo> ljBaseResponse = new LjBaseResponse<>();
        GroupRegionTagVo groupRegionTagVo = new GroupRegionTagVo();
        List<TagListVo> listVos = new ArrayList<>();
        //查询tag
        List<Map<String,Object>> tagList = measureTagService.searchByGroupId(getGroupMeasureRegionTagReq.getGroup_id(),MeasureTagConstant.GROUP);
        for (Map<String,Object> map: tagList
             ) {
            //map转换成vo
            TagListVo tagListVo = (TagListVo) ConvertUtil.convertMap(TagListVo.class,map);
            listVos.add(tagListVo);
        }
        groupRegionTagVo.setTag_list(listVos);
        ljBaseResponse.setData(groupRegionTagVo);
        return ljBaseResponse;
    }

    @Override
    public LjBaseResponse<GroupRegionTagVo> getProjMeasureRegionTag(GetProjMeasureRegionTagReq getProjMeasureRegionTagReq) throws InvocationTargetException, IntrospectionException, InstantiationException, IllegalAccessException {
        LjBaseResponse<GroupRegionTagVo> ljBaseResponse = new LjBaseResponse<>();
        GroupRegionTagVo groupRegionTagVo = new GroupRegionTagVo();
        List<TagListVo> listVos = new ArrayList<>();
        //查询tag
        List<Map<String,Object>> tagList = measureTagService.searchByGroupIdAndProjId(getProjMeasureRegionTagReq.getGroup_id(),getProjMeasureRegionTagReq.getProject_id(),MeasureTagConstant.PROJECT);
        for (Map<String,Object> map: tagList
                ) {
            //map转换成vo
            TagListVo tagListVo = (TagListVo) ConvertUtil.convertMap(TagListVo.class,map);
            listVos.add(tagListVo);
        }
        groupRegionTagVo.setTag_list(listVos);
        ljBaseResponse.setData(groupRegionTagVo);
        return ljBaseResponse;
    }

    @Override
    public LjBaseResponse<AreaRegionTagVo> getProjMeasureRegionByAreaId(GetProjMeasureRegionReq getProjMeasureRegionReq) throws InvocationTargetException, IntrospectionException, InstantiationException, IllegalAccessException {
        LjBaseResponse<AreaRegionTagVo> ljBaseResponse = new LjBaseResponse<>();
        AreaRegionTagVo areaRegionTagVo = new AreaRegionTagVo();
        List<RegionListVo> listVos = new ArrayList<>();
        //查询 measure_Region and rel 信息
        List<Map<String,Object>> regionList = measureRegionService.getProjMeasureRegionByAreaId(getProjMeasureRegionReq.getProject_id(),getProjMeasureRegionReq.getArea_id());
        for (Map<String,Object> map: regionList
                ) {
            //map转换成vo
            RegionListVo regionListVo = (RegionListVo) ConvertUtil.convertMap(RegionListVo.class,map);
            PolygonVo polygonVo = new PolygonVo();
            //转换jsonObject
            JSONObject jsonObject = JSON.parseObject(map.get("Polygon").toString());
            polygonVo.setX( Double.parseDouble(jsonObject.get("X").toString()));
            polygonVo.setY( Double.parseDouble(jsonObject.get("Y").toString()));
            regionListVo.setPolygon(polygonVo);
            RelVo relVo = new RelVo();
            relVo.setId(Integer.parseInt(map.get("relId").toString()));
            relVo.setDesc(map.get("desc").toString());
            relVo.setRegion_ids(map.get("region_ids").toString());
            regionListVo.setRel(relVo);
            listVos.add(regionListVo);
        }
        areaRegionTagVo.setRegion_list(listVos);
        ljBaseResponse.setData(areaRegionTagVo);
        return ljBaseResponse;
    }
}
