package com.longfor.longjian.measure.app.appservice.measureregionrelsearchservice.impl;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.longfor.longjian.common.base.LjBaseResponse;
import com.longfor.longjian.measure.app.appservice.measureregionrelsearchservice.IMeasureRegionRelSearchService;
import com.longfor.longjian.measure.app.appservice.areaservice.ICoreAreaService;
import com.longfor.longjian.measure.app.req.measureregionreq.MeasureRegionRelReq;
import com.longfor.longjian.measure.app.vo.appmeasuresyncvo.AreaProtoVo;
import com.longfor.longjian.measure.app.vo.appmeasuresyncvo.MeasureRegionRelProtoVo;
import com.longfor.longjian.measure.app.vo.appmeasuresyncvo.MeasureRegionRelVo;
import com.longfor.longjian.measure.app.vo.feignvo.AreaRetrieveVo;
import com.longfor.longjian.measure.app.vo.propaintareamanagevo.PolygonVo;
import com.longfor.longjian.measure.domain.externalService.IMeasureRegionService;
import com.longfor.longjian.measure.po.zhijian2.MeasureRegion;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Jiazm 2019/01/09 11:03
 */
@Service
@Slf4j
public class MeasureRegionRelSearchImpl implements IMeasureRegionRelSearchService {
    @Autowired
    private IMeasureRegionService measureRegionService;
    @Autowired
    private ICoreAreaService coreAreaService;

    @Override
    public LjBaseResponse<MeasureRegionRelVo> searchByRegionUuid(MeasureRegionRelReq measureRegionRelReq) {
        LjBaseResponse<MeasureRegionRelVo> ljBaseResponse = new LjBaseResponse<>();
        MeasureRegionRelVo measureRegionRelVo = new MeasureRegionRelVo();
        List<MeasureRegionRelProtoVo> measureRegionRelProtoVos =Lists.newArrayList();
        MeasureRegion region = measureRegionService.searchByProjIdAndRegionUuid(measureRegionRelReq.getProj_id(), measureRegionRelReq.getRegion_uuid());
        //search regions by rel_id
        // 如果rel_id 为0, 则没有关联关系, 不参与接下来的搜索 直接返回
        if (region.getRelId() == 0) {
            measureRegionRelVo.setRegion_list(new ArrayList<>());
            ljBaseResponse.setResult(0);
            ljBaseResponse.setMessage("rel_id等于0，没有关联关系");
            return ljBaseResponse;
        }
        List<MeasureRegion> measureRegionList = measureRegionService.searchByProjIdAndRelId(measureRegionRelReq.getProj_id(), region.getRelId());
        Map<Integer, Object> areaMap = Maps.newHashMap();
        List<Integer> areaIds = Lists.newArrayList();
        measureRegionList.forEach(measureRegion -> {
            areaIds.add(measureRegion.getAreaId());
        });
        Set<Integer> areaIdPaths = Sets.newHashSet();
        List<AreaRetrieveVo> areaRetrieveVos = coreAreaService.searchByIdList(measureRegionRelReq.getProj_id(), areaIds);
        areaRetrieveVos.forEach(areaRetrieveVo -> {
            areaMap.put(areaRetrieveVo.getId(),areaRetrieveVo);
            String[] areaPathIds = StringUtils.split(areaRetrieveVo.getPath(), "/");
            for (String areaPathId : areaPathIds) {
                areaIdPaths.add(Integer.parseInt(areaPathId));
            }
        });
        List<AreaRetrieveVo> areaRetrieveVoList = coreAreaService.searchByIdList(measureRegionRelReq.getProj_id(), new ArrayList<Integer>(areaIdPaths));
        areaRetrieveVoList.forEach(areaRetrieveVo -> {
            areaMap.put(areaRetrieveVo.getId(),areaRetrieveVo);
        });
        /*areaRetrieveVoList.forEach(areaRetrieveVo -> {
            String[] areaPathIds = StringUtils.split(areaRetrieveVo.getPath(), "/");
            List<String> joinList =Lists.newArrayList();
            for (String areaPathId : areaPathIds) {
                if(areaMap.containsKey(Integer.parseInt(areaPathId))){
                    AreaRetrieveVo retrieveVo = (AreaRetrieveVo) areaMap.get(Integer.parseInt(areaPathId));
                    joinList.add(retrieveVo.getName());
                }else{
                    joinList.add("【已删除】");
                }
            }
            areaRetrieveVo.setFull_name(StringUtils.join(joinList,"/"));
        });*/
        for (MeasureRegion measureRegion : measureRegionList) {
            if (!areaMap.containsKey(measureRegion.getAreaId())) {
                continue;
            }
            MeasureRegionRelProtoVo measureRegionRelProtoVo =new MeasureRegionRelProtoVo();
            measureRegionRelProtoVo.setArea_id(measureRegion.getAreaId());
            measureRegionRelProtoVo.setId(measureRegion.getId());
            measureRegionRelProtoVo.setProj_id(measureRegion.getProjectId());
            measureRegionRelProtoVo.setArea_path_and_id(measureRegion.getAreaPathAndId());
            measureRegionRelProtoVo.setDrawing_md5(measureRegion.getDrawingMd5());
            measureRegionRelProtoVo.setRegion_index(measureRegion.getRegionIndex());
            measureRegionRelProtoVo.setRel_id(measureRegion.getRelId());
            measureRegionRelProtoVo.setSrc_type(measureRegion.getSrcType());
            measureRegionRelProtoVo.setUuid(measureRegion.getUuid());
            measureRegionRelProtoVo.setTag_id_list(measureRegion.getTagIdList());
            //JsonToMap
            Map map = JSON.parseObject(measureRegion.getPolygon(), Map.class);
            PolygonVo polygonVo =new PolygonVo();
            polygonVo.setX(Double.parseDouble(map.get("X").toString()));
            polygonVo.setY(Double.parseDouble(map.get("Y").toString()));
            measureRegionRelProtoVo.setPolygon(polygonVo);
            //Area
            AreaRetrieveVo areaRetrieveVo = (AreaRetrieveVo) areaMap.get(measureRegion.getAreaId());
            AreaProtoVo areaProtoVo =new AreaProtoVo();
            areaProtoVo.setId(areaRetrieveVo.getId());
            areaProtoVo.setDrawing_md5(areaRetrieveVo.getDrawing_md5());
            areaProtoVo.setFather_id(areaRetrieveVo.getFather_id());
            areaProtoVo.setFull_name(areaRetrieveVo.getFull_name());
            areaProtoVo.setLocation(areaRetrieveVo.getLocation());
            areaProtoVo.setName(areaRetrieveVo.getName());
            areaProtoVo.setPath(areaRetrieveVo.getPath());
            measureRegionRelProtoVo.setArea(areaProtoVo);
            measureRegionRelProtoVos.add(measureRegionRelProtoVo);
        }
        log.info("{}",JSON.toJSONString(measureRegionRelProtoVos));
        measureRegionRelVo.setRegion_list(measureRegionRelProtoVos);
        ljBaseResponse.setResult(0);
        ljBaseResponse.setData(measureRegionRelVo);
        return ljBaseResponse;
    }
}
