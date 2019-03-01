package com.longfor.longjian.measure.app.appservice.paintareaservice.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.longfor.longjian.common.base.LjBaseResponse;
import com.longfor.longjian.common.exception.LjBaseRuntimeException;
import com.longfor.longjian.measure.app.appservice.paintareaservice.IProPaintAreaManageService;
import com.longfor.longjian.measure.app.req.measureregionreq.AddOnGroupReq;
import com.longfor.longjian.measure.app.req.measureregionreq.AddOnProjReq;
import com.longfor.longjian.measure.app.req.measureregionreq.EditByProjIdReq;
import com.longfor.longjian.measure.app.req.measureregionreq.EditOnGroupReq;
import com.longfor.longjian.measure.app.req.paintareareq.GetGroupMeasureRegionTagReq;
import com.longfor.longjian.measure.app.req.paintareareq.GetProjMeasureRegionReq;
import com.longfor.longjian.measure.app.req.paintareareq.GetProjMeasureRegionTagReq;
import com.longfor.longjian.measure.app.vo.propaintareamanagevo.*;
import com.longfor.longjian.measure.consts.constant.MeasureTagConstant;
import com.longfor.longjian.measure.domain.externalservice.IMeasureRegionService;
import com.longfor.longjian.measure.domain.externalservice.IMeasureTagService;
import com.longfor.longjian.measure.util.ConvertUtil;
import com.longfor.longjian.measure.vo.EditTagProtoVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
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

    private static final String RELID = "relId";

    @Override
    public LjBaseResponse<GroupRegionTagVo> getGroupMeasureRegionTag(GetGroupMeasureRegionTagReq getGroupMeasureRegionTagReq) throws LjBaseRuntimeException {
        LjBaseResponse<GroupRegionTagVo> ljBaseResponse = new LjBaseResponse<>();
        GroupRegionTagVo groupRegionTagVo = new GroupRegionTagVo();
        List<TagListVo> listVos = new ArrayList<>();
        //查询tag
        List<Map<String, Object>> tagList = measureTagService.searchByGroupIdAndProjId(getGroupMeasureRegionTagReq.getGroup_id(), 0, MeasureTagConstant.GROUP);
        for (Map<String, Object> map : tagList
        ) {
            //map转换成vo
            TagListVo tagListVo = null;
            try {
                tagListVo = (TagListVo) ConvertUtil.convertMap(TagListVo.class, map);
            } catch (Exception e) {
                throw new LjBaseRuntimeException(-9999, e + "");
            }
            listVos.add(tagListVo);
        }
        groupRegionTagVo.setTag_list(listVos);
        ljBaseResponse.setData(groupRegionTagVo);
        return ljBaseResponse;
    }

    @Override
    public LjBaseResponse<GroupRegionTagVo> getProjMeasureRegionTag(GetProjMeasureRegionTagReq getProjMeasureRegionTagReq) throws LjBaseRuntimeException {
        LjBaseResponse<GroupRegionTagVo> ljBaseResponse = new LjBaseResponse<>();
        GroupRegionTagVo groupRegionTagVo = new GroupRegionTagVo();
        List<TagListVo> listVos = new ArrayList<>();
        //查询tag
        List<Map<String, Object>> tagList = measureTagService.searchByGroupIdAndProjId(getProjMeasureRegionTagReq.getGroup_id(), getProjMeasureRegionTagReq.getProject_id(), MeasureTagConstant.PROJECT);
        for (Map<String, Object> map : tagList
        ) {
            //map转换成vo
            TagListVo tagListVo = null;
            try {
                tagListVo = (TagListVo) ConvertUtil.convertMap(TagListVo.class, map);
            } catch (Exception e) {
                throw new LjBaseRuntimeException(-9999, e + "");
            }
            listVos.add(tagListVo);
        }
        groupRegionTagVo.setTag_list(listVos);
        ljBaseResponse.setData(groupRegionTagVo);
        return ljBaseResponse;
    }

    @Override
    public LjBaseResponse<AreaRegionTagVo> getProjMeasureRegionByAreaId(GetProjMeasureRegionReq getProjMeasureRegionReq) throws LjBaseRuntimeException {
        LjBaseResponse<AreaRegionTagVo> ljBaseResponse = new LjBaseResponse<>();
        AreaRegionTagVo areaRegionTagVo = new AreaRegionTagVo();
        List<RegionListVo> listVos = new ArrayList<>();
        //查询 measure_Region and rel 信息
        List<Map<String, Object>> regionList = measureRegionService.getProjMeasureRegionByAreaId(getProjMeasureRegionReq.getProject_id(), getProjMeasureRegionReq.getArea_id());
        for (Map<String, Object> map : regionList
        ) {
            //map转换成vo
            RegionListVo regionListVo = null;
            try {
                regionListVo = (RegionListVo) ConvertUtil.convertMap(RegionListVo.class, map);
            } catch (Exception e) {
                throw new LjBaseRuntimeException(-9999, e + "");
            }
            PolygonVo polygonVo = new PolygonVo();
            //转换jsonObject
            JSONObject jsonObject = JSON.parseObject(map.get("Polygon").toString());
            polygonVo.setX(Double.parseDouble(jsonObject.get("X").toString()));
            polygonVo.setY(Double.parseDouble(jsonObject.get("Y").toString()));
            regionListVo.setPolygon(polygonVo);
            if (map.get(RELID) != null && StringUtils.isNotBlank(map.get(RELID).toString())) {
                RelVo relVo = new RelVo();
                relVo.setId(Integer.parseInt(map.get(RELID).toString()));
                relVo.setDesc(map.get("desc").toString());
                relVo.setRegion_ids(map.get("region_ids").toString());
                regionListVo.setRel(relVo);
            } else {
                regionListVo.setRel(new RelVo());
            }
            listVos.add(regionListVo);
        }
        areaRegionTagVo.setRegion_list(listVos);
        ljBaseResponse.setData(areaRegionTagVo);
        return ljBaseResponse;
    }

    @Override
    public LjBaseResponse<Object> editOnGroup(EditOnGroupReq editOnGroupReq) {
        LjBaseResponse<Object> ljBaseResponse = new LjBaseResponse<>();
        List<EditTagProtoVo> editTagProtoVos = JSONArray.parseArray(editOnGroupReq.getEdit_tag_list(), EditTagProtoVo.class);
        Integer affCount = measureTagService.editOnGroup(editOnGroupReq.getGroup_id(), editTagProtoVos, MeasureTagConstant.GROUP);
        ljBaseResponse.setMessage(String.format("总共修改了 %s 条数据", affCount));
        ljBaseResponse.setResult(0);
        return ljBaseResponse;
    }

    @Override
    public LjBaseResponse<Object> addOnGroup(AddOnGroupReq addOnGroupReq) {
        LjBaseResponse<Object> ljBaseResponse = new LjBaseResponse<>();
        if (addOnGroupReq.getProj_id() == null) {
            addOnGroupReq.setProj_id(0);
        }
        if (addOnGroupReq.getName_list().length() > 0 && !addOnGroupReq.getName_list().equals("")) {
            String[] nameArr = addOnGroupReq.getName_list().split(",");
            List<String> nameList = Arrays.asList(nameArr);
            Integer affCount = measureTagService.addOnGroup(addOnGroupReq.getGroup_id(), nameList, MeasureTagConstant.GROUP, addOnGroupReq.getProj_id());
            ljBaseResponse.setMessage(String.format("总共添加了 %s 条数据", affCount));
            ljBaseResponse.setResult(0);
        }
        return ljBaseResponse;
    }

    @Override
    public LjBaseResponse<Object> addOnProj(AddOnProjReq addOnProjReq) {
        LjBaseResponse<Object> ljBaseResponse = new LjBaseResponse<>();
        if (addOnProjReq.getName_list().length() > 0 && !addOnProjReq.getName_list().equals("")) {
            String[] nameArr = addOnProjReq.getName_list().split(",");
            List<String> nameList = Arrays.asList(nameArr);
            Integer affCount = measureTagService.addOnProj(addOnProjReq.getGroup_id(), addOnProjReq.getProject_id(), nameList, MeasureTagConstant.PROJECT);
            ljBaseResponse.setMessage(String.format("总共添加了 %s 条数据", affCount));
            ljBaseResponse.setResult(0);
        }
        return ljBaseResponse;
    }

    @Override
    public LjBaseResponse<Object> editByProjId(EditByProjIdReq editByProjId) {
        LjBaseResponse<Object> ljBaseResponse = new LjBaseResponse<>();
        List<EditTagProtoVo> editTagProtoVos = JSONArray.parseArray(editByProjId.getEdit_tag_list(), EditTagProtoVo.class);
        Integer affCount = measureTagService.editOnProjId(editByProjId.getGroup_id(), editByProjId.getProject_id(), editTagProtoVos, MeasureTagConstant.PROJECT);
        ljBaseResponse.setMessage(String.format("总共修改了 %s 条数据", affCount));
        ljBaseResponse.setResult(0);
        return ljBaseResponse;
    }
}
