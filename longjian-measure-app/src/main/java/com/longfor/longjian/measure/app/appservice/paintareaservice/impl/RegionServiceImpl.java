package com.longfor.longjian.measure.app.appservice.paintareaservice.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.longfor.longjian.common.exception.LjBaseRuntimeException;
import com.longfor.longjian.measure.app.appservice.areaservice.ICoreAreaService;
import com.longfor.longjian.measure.app.appservice.paintareaservice.IRegionService;
import com.longfor.longjian.measure.app.vo.feignvo.AreaRetrieveVo;
import com.longfor.longjian.measure.consts.enums.MeasureErrorEnum;
import com.longfor.longjian.measure.domain.externalservice.IMeasureRegionRelService;
import com.longfor.longjian.measure.domain.externalservice.IMeasureRegionService;
import com.longfor.longjian.measure.domain.externalservice.IMeasureZoneService;
import com.longfor.longjian.measure.po.zhijian2.Area;
import com.longfor.longjian.measure.po.zhijian2.MeasureRegion;
import com.longfor.longjian.measure.po.zhijian2.MeasureRegionRel;
import com.longfor.longjian.measure.po.zhijian2.MeasureZone;
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
    @Resource
    private IMeasureRegionService measureRegionService;
    @Resource
    private IMeasureRegionRelService measureRegionRelService;
    @Resource
    private IMeasureZoneService measureZoneService;
    private static final String POLYGON = "polygon";
    private static final String TAG_ID_LIST = "tag_id_list";
    private static final String PATHFLAG = "/";
    @Override
    @Transactional
    public void add(Integer projectId, String regionList, Integer srcType) {
        //将bulk_create 移植到peewee2能加速, 待读源码移植
        //找出所有area_id对应的area
        Set areaIdSet = new HashSet();
        List<HashMap> regionInfoList = JSONArray.parseArray(regionList, HashMap.class);
        regionInfoList.forEach(regionInfo ->
                areaIdSet.addAll((List) regionInfo.get("area_id_list"))
        );
        log.info(JSON.toJSONString(areaIdSet));
        List areaIdList = new ArrayList(areaIdSet);
        //查询area信息, 用id建立map关系
        List<AreaRetrieveVo> areaList = coreAreaService.searchByIdList(projectId, areaIdList);
        log.info("area_id_list: " + JSON.toJSONString(areaIdList));
        log.info("area_list: " + JSON.toJSONString(areaList));
        List<Area> areaLists = JSONArray.parseArray(JSON.toJSONString(areaList), Area.class);
        Map<Integer, Area> areaMap = areaLists.stream().collect(Collectors.toMap(Area::getId, area -> area));
        log.info("area_map: " + JSON.toJSONString(areaMap));
        Set<Integer> difference = new HashSet(areaIdList);
        difference.removeAll(areaMap.keySet());
        if (!difference.isEmpty()) {
            log.info("missing area_id: " + JSON.toJSONString(difference));
            throw new LjBaseRuntimeException(MeasureErrorEnum.AREAMISSING.getId(), MeasureErrorEnum.AREAMISSING.getValue());
        }
        //获取measure_region现有的最大的index
        List<Map<String, Object>> indexMap = measureRegionService.getMaxRegionIndexGroupByAreaIdNoDeleted(projectId, areaIdList);
        Map<String, Object> indexDict = new HashMap<>();
        indexMap.forEach(map ->
                indexDict.put(map.get("area_id").toString(), map.get("region_index"))
        );
        log.info("index_dict: " + JSON.toJSONString(indexDict));
        //补全没有出现过的测区index
        Map<String, Object> trueIndexDict = new HashMap<>();
        areaIdList.forEach(areaId ->
                trueIndexDict.put(areaId.toString(), indexDict.get(areaId.toString()) == null ? 0 : indexDict.get(areaId.toString()))
        );
        log.info("true_index_dict: " + JSON.toJSONString(trueIndexDict));
        //插入region
        List<MeasureRegion> modelList;
        List<MeasureRegion> modelSaveList = new ArrayList<>();
        regionInfoList.forEach(regionInfo -> {
            //region
            List<Integer> areaIdLists = JSONArray.parseArray(regionInfo.get("area_id_list").toString(), Integer.class);
            log.info("len : " + areaIdLists.size());
            log.info(JSON.toJSONString(regionInfoList));
            areaIdList.forEach(areaId -> {
                Area areaInfo = areaMap.get(areaId);
                String areaPathAndId = areaInfo.getPath() + areaId + PATHFLAG;
                String uuid = UUID.randomUUID().toString();
                String genUuid = uuid.replace("-", "");
                String polygon = regionInfo.get(POLYGON).toString();
                trueIndexDict.put(areaId.toString(), (int) trueIndexDict.get(areaId.toString()) + 1);
                Map<String, Object> regionDict = new HashMap<>();
                regionDict.put("project_id", projectId);
                regionDict.put("area_id", areaId);
                regionDict.put("area_path_and_id", areaPathAndId);
                regionDict.put("drawing_md5", areaInfo.getDrawingMd5());
                regionDict.put(POLYGON, polygon);
                regionDict.put("src_type", srcType);
                regionDict.put("uuid", genUuid);
                regionDict.put("region_index", trueIndexDict.get(areaId.toString()));
                regionDict.put("rel_id", 0);
                regionDict.put(TAG_ID_LIST, regionInfo.get(TAG_ID_LIST));
                log.info("region_dict : " + JSON.toJSONString(regionDict));
                log.info("true_index_dict : " + JSON.toJSONString(trueIndexDict));
                MeasureRegion model = JSONObject.toJavaObject((JSON) JSON.toJSON(regionDict), MeasureRegion.class);
                model.setCreateAt(new Date());
                model.setUpdateAt(new Date());
                modelSaveList.add(model);
            });
        });
        modelList = measureRegionService.saveList(modelSaveList);
        //数量大于一个的时候创立关联关系
        if (modelList.size() > 1) {
            String regionIds = String.join(",", modelList.stream().map(measureRegion ->
                    measureRegion.getId() + ""
            ).collect(Collectors.toList()).toArray(new String[modelList.size()]));
            Map<String, Object> relDict = new HashMap<>();
            relDict.put("desc", "");
            relDict.put("project_id", projectId);
            relDict.put("region_ids", regionIds);
            MeasureRegionRel model = JSONObject.toJavaObject((JSON) JSON.toJSON(relDict), MeasureRegionRel.class);
            model.setCreateAt(new Date());
            model.setUpdateAt(new Date());
            MeasureRegionRel relModel = measureRegionRelService.save(model);

            //关系写回measure_region
            modelList.forEach(mode -> {
                mode.setRelId(relModel.getId());
            });
            measureRegionService.updateList(modelList);
        }
    }

    @Override
    @Transactional
    public void edit(Integer projectId, String regionInfoList) {
        List<HashMap> regionInfolist = JSONArray.parseArray(regionInfoList, HashMap.class);
        log.info(JSON.toJSONString(regionInfolist));
        regionInfolist.forEach(regionInfo ->
            measureRegionService.updateByProjectIdAndIdInNoDeleted(projectId, (List) regionInfo.get("region_ids"), regionInfo.get(POLYGON).toString(), regionInfo.get(TAG_ID_LIST).toString())
        );
    }

    @Override
    @Transactional
    public void delete(Integer projectId, List<Integer> regionIdList) {
        //描区下不能有测区
        List<MeasureRegion> regionModelList = measureRegionService.selectByProjectIdAndIdNoDeleted(projectId, regionIdList);
        List<String> regionUuidList = regionModelList.stream().map(MeasureRegion::getUuid).collect(Collectors.toList());
        List<MeasureZone> zoneResult = measureZoneService.selectByProjectIdAndRegionUUIdIn(projectId, regionUuidList);
        if (zoneResult != null && !zoneResult.isEmpty()) {
            log.info(JSON.toJSONString(zoneResult));
            throw new LjBaseRuntimeException(MeasureErrorEnum.MEASUREZONEEXIST.getId(), MeasureErrorEnum.MEASUREZONEEXIST.getValue());
        }

        //MeasureRegionRel里面的rel_ids去除MeasureRegion.id
        //找出所有rel_id对应的model
        List<Integer> relIdList = new ArrayList<>();
        regionModelList.forEach(regoinModel -> {
            if (regoinModel.getRelId() != 0) {
                relIdList.add(regoinModel.getRelId());
            }
        });
        if (!relIdList.isEmpty()) {
            List<MeasureRegionRel> relModelList = measureRegionRelService.selectByProjectIdAndIdNoDeleted(projectId, relIdList);

            log.info("rel_list: " + JSON.toJSONString(relIdList));
            //建立关系映射字典
            Map<Integer, List<Integer>> relModelDict = new HashMap<>();
            relModelList.forEach(relModel ->
                relModelDict.put(relModel.getId(), Arrays.stream(relModel.getRegionIds().split(",")).map(Integer::parseInt).collect(Collectors.toList()))
            );

            //移除相关的id
            regionModelList.forEach(regionModel -> {
                log.info(regionModel.getRelId().toString());
                if (regionModel.getRelId() != 0) {
                    relModelDict.get(regionModel.getRelId()).remove(regionModel.getId());
                }
            });

            //更新相关的rel_model
            relModelList.forEach(relModel -> {
                String regionIds = String.join(",", relModelDict.get(relModel.getId()).stream().map(x -> x + "").collect(Collectors.toList()).toArray(new String[relModelDict.get(relModel.getId()).size()]));
                relModel.setRegionIds(regionIds);
                relModel.setUpdateAt(new Date());
                measureRegionRelService.update(relModel);
            });
        }

        //删除描区
        log.info("region_id_list : " + regionIdList);
        measureRegionService.delete(projectId, regionIdList);
    }
}
