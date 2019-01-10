package com.longfor.longjian.measure.app.appService.paintAreaService.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.longfor.longjian.common.exception.LjBaseRuntimeException;
import com.longfor.longjian.measure.app.appService.areaService.ICoreAreaService;
import com.longfor.longjian.measure.app.appService.paintAreaService.IRegionService;
import com.longfor.longjian.measure.app.commonEntity.MeasureRegionHelper;
import com.longfor.longjian.measure.app.vo.feignVo.AreaRetrieveVo;
import com.longfor.longjian.measure.consts.Enum.MeasureErrorEnum;
import com.longfor.longjian.measure.domain.externalService.IMeasureRegionRelService;
import com.longfor.longjian.measure.domain.externalService.IMeasureRegionService;
import com.longfor.longjian.measure.po.zhijian2.Area;
import com.longfor.longjian.measure.po.zhijian2.MeasureRegion;
import com.longfor.longjian.measure.po.zhijian2.MeasureRegionRel;
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
    private IMeasureRegionRelService measureRegionRel;

    @Override
    @Transactional
    public void add(Integer project_id, String region_list, Integer src_type) {
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
        List<Map<String,Object>> index_map  = measureRegionService.getMaxRegionIndexGroupByAreaIdNoDeleted(project_id,area_id_list);
        Map<String,Object> index_dict = new HashMap<>();
        index_map.forEach(map ->{
            index_dict.put(map.get("area_id").toString(),map.get("region_index"));
        });
        log.info("index_dict: " + JSON.toJSONString(index_map));

        //补全没有出现过的测区index
        Map<String,Object> true_index_dict = new HashMap<>();
        area_id_list.forEach(area_id -> {
            true_index_dict.put(area_id.toString(),index_dict.get(area_id) == null ? 0 : index_dict.get(area_id));
        });
        log.info("true_index_dict: " + JSON.toJSONString(true_index_dict));

        //插入region
        region_info_list.forEach(region_info -> {
            List<MeasureRegion> model_list = new ArrayList<>();
//            MeasureRegionHelper region_helper = new MeasureRegionHelper(project_id);

            //region
            List<Integer> areaIdList = JSONArray.parseArray(region_info.get("area_id_list").toString(),Integer.class);
            log.info("len : " + areaIdList.size());
            log.info(JSON.toJSONString(region_info_list));
            area_id_list.forEach(area_id -> {
                Area area_info = area_map.get(area_id);
                String area_path_and_id = area_info.getPath() + area_id + "/";
                String uuid = UUID.randomUUID().toString();
                String gen_uuid = uuid.replace("-","");
                String polygon = region_info.get("polygon").toString();
                true_index_dict.put(area_id.toString(),(int)true_index_dict.get(area_id) + 1);
                Map<String,Object> region_dict = new HashMap<>();
                region_dict.put("project_id",project_id);
                region_dict.put("area_id",area_id);
                region_dict.put("area_path_and_id",area_path_and_id);
                region_dict.put("drawing_md5",area_info.getDrawingMd5());
                region_dict.put("polygon",polygon);
                region_dict.put("src_type",src_type);
                region_dict.put("uuid",gen_uuid);
                region_dict.put("region_index",true_index_dict.get(area_id));
                region_dict.put("rel_id",0);
                region_dict.put("tag_id_list",region_info.get("tag_id_list"));
                log.info("region_dict : " + JSON.toJSONString(region_dict));
                log.info("true_index_dict : " + JSON.toJSONString(true_index_dict));
                MeasureRegion model = JSONObject.toJavaObject((JSON)JSON.toJSON(region_dict),MeasureRegion.class);
                MeasureRegion region_model = measureRegionService.save(model);
                model_list.add(region_model);
            });
            //数量大于一个的时候创立关联关系
            if (model_list.size() > 1){
                String region_ids = String.join(",",model_list.stream().map(MeasureRegion::getId).collect(Collectors.toList()).toArray(new String[model_list.size()]));
                Map<String,Object> rel_dict = new HashMap<>();
                rel_dict.put("desc","");
                rel_dict.put("project_id",project_id);
                rel_dict.put("region_ids",region_ids);
                MeasureRegionRel model = JSONObject.toJavaObject((JSON)JSON.toJSON(rel_dict),MeasureRegionRel.class);
                MeasureRegionRel rel_model = measureRegionRel.save(model);

                //关系写回measure_region
                model_list.forEach(mode ->{
                    mode.setRelId(rel_model.getId());
                    measureRegionService.update(mode);
                });
            }
        });
    }
}
