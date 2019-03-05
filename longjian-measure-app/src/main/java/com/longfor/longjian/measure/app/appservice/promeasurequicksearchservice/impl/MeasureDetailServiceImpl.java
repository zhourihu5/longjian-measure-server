package com.longfor.longjian.measure.app.appservice.promeasurequicksearchservice.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.longfor.longjian.common.consts.ExportFileRecordType;
import com.longfor.longjian.common.exception.LjBaseRuntimeException;
import com.longfor.longjian.common.util.DateUtil;
import com.longfor.longjian.measure.app.appservice.promeasurequicksearchservice.IExportFileRecordService;
import com.longfor.longjian.measure.app.appservice.promeasurequicksearchservice.IMeasureDetailService;
import com.longfor.longjian.measure.consts.constant.DateConstant;
import com.longfor.longjian.measure.consts.enums.AreaTypeEnum;
import com.longfor.longjian.measure.consts.enums.MeasureRegionSrcType;
import com.longfor.longjian.measure.domain.externalservice.*;
import com.longfor.longjian.measure.po.zhijian2.*;
import com.longfor.longjian.measure.po.zhijian2_apisvr.User;
import com.longfor.longjian.measure.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.util.StringUtil;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Jiazm 2019/01/18 11:33
 */
@Service
@Slf4j
public class MeasureDetailServiceImpl implements IMeasureDetailService {
    @Resource
    private IProjectService projectService;
    @Resource
    private IMeasureZoneResultService measureZoneResultService;
    @Resource
    private ICheckItemV3Service checkItemV3Service;
    @Resource
    private IMeasureRuleService measureRuleService;
    @Resource
    private IMeasureZoneService measureZoneService;
    @Resource
    private IMeasureRegionService measureRegionService;
    @Resource
    private IAreaService areaService;
    @Resource
    private IMeasureListService measureListService;
    @Resource
    private IUserService userService;
    @Resource
    private IMeasureRegionService regionSerVice;
    @Resource
    private IExportFileRecordService exportFileRecordService;
    private static final String ROOTCATEGORYDATAS = "rootCategoryDatas";
    private static final String MEASUREDDATA = "measuredData";

    @Override
    public void exportExcelAsync(Integer curUserId, Integer projId, Integer listId) {
        try {
            Project project = projectService.getByIdNoFoundErr(projId);
            MeasureList measureList = projectService.getByProjIdAndIdNoFoundErr(projId, listId);
            if (measureList == null) {
                measureList = new MeasureList();
            }
            Map<String, Object> map = this.search4ExportExcel(projId, listId);
            InputVo input = new InputVo();
            input.setData(map.get(ROOTCATEGORYDATAS) == null ? new ArrayList<>() : (List<CategoryDataVo>) map.get(ROOTCATEGORYDATAS));
            input.setMeasured_data(map.get(MEASUREDDATA) == null ? new MeasuredDataVo() : (MeasuredDataVo) map.get(MEASUREDDATA));
            Date date = new Date();
            String newTime = new SimpleDateFormat("yyyyMMddHHmmss").format(date);
            exportFileRecordService.create(curUserId, project.getTeamId(), projId, ExportFileRecordType.MeasureDetail.getValue(), input, String.format("%s-统计.%s.xlsx", measureList.getName() == null ? "" : measureList.getName(), newTime), date);
        } catch (Exception e) {
            log.error("error:" + e);
            throw new LjBaseRuntimeException(-9999, e.getMessage());
        }
    }

    private Map<String, Object> search4ExportExcel(Integer projId, Integer listId) {
        Map<String, Object> map = Maps.newHashMap();
        try {
            List<MeasureZoneResult> measureZoneResults = measureZoneResultService.searchByListId(projId, listId);
            Map<String, Boolean> categoryKeyMap = Maps.newHashMap();
            Map<Integer, Boolean> ruleIdMap = Maps.newHashMap();
            Map<String, Boolean> zoneUuidMap = Maps.newHashMap();
            Map<Integer, Boolean> squadIdMap = Maps.newHashMap();
            for (MeasureZoneResult obj : measureZoneResults) {
                List<String> categoryPathAndKeyList = com.longfor.longjian.measure.util.StringUtil.removeStartAndEndStrAndSplit(obj.getCategoryPathAndKey(), "/", "/");
                for (String key : categoryPathAndKeyList) {
                    categoryKeyMap.put(key, true);
                }
                ruleIdMap.put(obj.getRuleId(), true);
                zoneUuidMap.put(obj.getZoneUuid(), true);
                squadIdMap.put(obj.getSquadId(), true);
            }
            //Category
            Map<String, CategoryV3> categoryMap = Maps.newHashMap();
            if (!categoryKeyMap.keySet().isEmpty()) {
                List<CategoryV3> categorys = checkItemV3Service.searchCategoryByKeyIn(categoryKeyMap.keySet());
                categorys.forEach(categoryV3 ->
                        categoryMap.put(categoryV3.getKey(), categoryV3)
                );
            }
            // Rule
            Map<Integer, MeasureRule> ruleMap = Maps.newHashMap();
            if (!ruleIdMap.keySet().isEmpty()) {
                List<MeasureRule> rules = measureRuleService.searchUnscopedByIds(ruleIdMap.keySet());
                rules.forEach(measureRule ->
                        ruleMap.put(measureRule.getId(), measureRule)
                );
            }
            // RegionUuid
            List<MeasureZone> measureZones = measureZoneService.searchByListId(projId, listId);
            Map<String, String> regionUuidMap = Maps.newHashMap();
            measureZones.forEach(measureZone ->
                    regionUuidMap.put(measureZone.getUuid(), measureZone.getRegionUuid())
            );
            // Area

            List<MeasureRegion> regions = measureRegionService.searchUnscopedByProjIdUpdateAtGt2(projId, DateConstant.TIME_FMT);
            Map<String, Integer> regionMap = Maps.newHashMap();
            regions.forEach(measureRegion ->
                    regionMap.put(measureRegion.getUuid(), measureRegion.getAreaId())
            );
            Map<Integer, Boolean> areaIdMap = Maps.newHashMap();
            Collection<String> values = regionUuidMap.values();
            values.forEach(value -> {
                if (regionMap.get(value) > 0) {
                    areaIdMap.put(regionMap.get(value), true);
                }
            });
            Map<String, AreaInfoWithExtendVo> regionUuidToAreaMap = Maps.newHashMap();
            if (!areaIdMap.keySet().isEmpty()) {
                List<Area> areas = areaService.selectByIds(areaIdMap.keySet());
                List<AreaInfoWithExtendVo> areaExs = areaService.formatAreaInfoWithExtend(areas);
                Map<Integer, AreaInfoWithExtendVo> areaMap = Maps.newHashMap();
                areaExs.forEach(areaInfoWithExtendVo ->
                        areaMap.put(areaInfoWithExtendVo.getArea().getId(), areaInfoWithExtendVo)
                );
                measureZones.forEach(measureZone -> {
                    Integer areaId = regionMap.get(measureZone.getRegionUuid());
                    AreaInfoWithExtendVo areaInfoWithExtendVo = areaMap.get(areaId);
                    if (areaInfoWithExtendVo == null) {
                        AreaInfoWithExtendVo areaInfoWithExtendVo1 = new AreaInfoWithExtendVo();
                        areaMap.put(areaId, areaInfoWithExtendVo1);
                    }
                    regionUuidToAreaMap.put(measureZone.getRegionUuid(), areaInfoWithExtendVo);
                });
            }
            // Squad
            List<MeasureSquad> squads = measureListService.searchOnlyMeasureSquadByProjIdAndListId(projId, listId);
            Map<Integer, MeasureSquad> squadMap = Maps.newHashMap();
            squads.forEach(measureSquad ->
                    squadMap.put(measureSquad.getId(), measureSquad)
            );
            Map<String, CategoryDataVo> categoryDataMap = Maps.newHashMap();
            List<CategoryDataVo> rootCategoryDatas = Lists.newArrayList();
            for (MeasureZoneResult obj : measureZoneResults) {
                List<String> categoryPathAndKeyList = com.longfor.longjian.measure.util.StringUtil.removeStartAndEndStrAndSplit(obj.getCategoryPathAndKey(), "/", "/");
                CategoryDataVo tail = null;
                for (String k : categoryPathAndKeyList) {
                    CategoryDataVo d = categoryDataMap.get(k);
                    if (d == null) {
                        CategoryV3 categoryV3 = categoryMap.get(k);
                        if (categoryV3 == null) {
                            categoryV3 = new CategoryV3();
                            categoryV3.setKey(k);
                            categoryV3.setName("");
                        }
                        d = newCategoryData(categoryV3.getKey(), categoryV3.getName());
                        categoryDataMap.put(k, d);
                        if (tail == null) {
                            rootCategoryDatas.add(d);
                        } else {
                            tail.getChildren().add(d);
                        }

                    }
                    tail = d;
                }
                if (tail.getRule() == null && ruleMap.get(obj.getRuleId()) != null) {
                    tail.setRule(ruleMap.get(obj.getRuleId()));
                }
                MeasureSquad squad = squadMap.get(obj.getSquadId());
                if (squad == null) {
                    squad = new MeasureSquad();
                    squadMap.put(obj.getSquadId(), squad);
                }
                String regionUuid = regionUuidMap.get(obj.getZoneUuid());
                if (StringUtil.isEmpty(regionUuid)) {
                    regionUuid = "";
                    regionUuidMap.put(obj.getZoneUuid(), regionUuid);
                }
                AreaInfoWithExtendVo areaInfoWithExtendVo = regionUuidToAreaMap.get(regionUuid);
                if (areaInfoWithExtendVo == null) {
                    areaInfoWithExtendVo = new AreaInfoWithExtendVo();
                    areaInfoWithExtendVo.setArea(new Area());
                    regionUuidToAreaMap.put(regionUuid, areaInfoWithExtendVo);
                }
                try {
                    this.addResult(obj, squad, regionUuid, areaInfoWithExtendVo, tail);
                } catch (ParseException e) {
                    log.error("error:", e);
                }
            }
            rootCategoryDatas.forEach(categoryDataVo ->
                    reCalc(categoryDataVo)
            );
            MeasuredDataVo measuredDataVo = this.getMeasuredDataByListId(projId, listId);
            map.put(MEASUREDDATA, measuredDataVo);
            map.put(ROOTCATEGORYDATAS, rootCategoryDatas);
        } catch (Exception e) {
            throw new LjBaseRuntimeException(-9999, e.getMessage());
        }
        return map;
    }

    private MeasuredDataVo getMeasuredDataByListId(Integer projId, Integer listId) {
        try {
            MeasuredDataVo md = new MeasuredDataVo();
            MeasureList list = measureListService.getNoFoundErr(projId, listId);
            Project proj = new Project();
            if (list != null) {
                md.setList_name(list.getName());
                md.setProj_id(list.getProjectId());
                proj = projectService.getByIdNoFoundErr(md.getProj_id());
                md.setProj_name(proj.getName());
            }
            List<MeasureZoneResult> results = measureZoneResultService.searchByProjIdListId(projId, listId);
            Map<String, MeasureZone> zoneMap = Maps.newHashMap();
            Map<Integer, Boolean> squadMap = Maps.newHashMap();
            Map<Integer, MeasureRule> ruleMap = Maps.newHashMap();
            Map<String, Boolean> categoryMap = Maps.newHashMap();
            Map<Integer, Boolean> userMap = Maps.newHashMap();
            Map<String, MeasureRegion> regionMap = Maps.newHashMap();
            Map<Integer, Area> areaMap = Maps.newHashMap();
            for (MeasureZoneResult measureZoneResult : results) {
                zoneMap.put(measureZoneResult.getZoneUuid(), null);
                squadMap.put(measureZoneResult.getSquadId(), true);
                ruleMap.put(measureZoneResult.getRuleId(), null);
                for (String categoryKey : StringUtils.split(measureZoneResult.getCategoryPathAndKey(), "/")) {
                    if (categoryKey.equals("")) {
                        continue;
                    }
                    categoryMap.put(categoryKey, true);
                }
                List<String> dataList = Arrays.asList(measureZoneResult.getData());
                for (String groupData : dataList) {
                    List<MeasureZoneGroupDataVo> measureZoneGroupDataVos = JSON.parseArray(groupData, MeasureZoneGroupDataVo.class);
                    for (MeasureZoneGroupDataVo measureZoneGroupDataVo : measureZoneGroupDataVos) {
                        userMap.put(measureZoneGroupDataVo.getRecorderId(), true);
                    }
                }
            }
            if (!zoneMap.keySet().isEmpty() && (proj.getId() != null || proj.getId().equals(0))) {
                List<MeasureZone> zones = measureListService.searchZoneByProjUuids(proj.getId(), zoneMap.keySet());
                zones.forEach(measureZone -> {
                    zoneMap.put(measureZone.getUuid(), measureZone);
                    regionMap.put(measureZone.getRegionUuid(), null);
                });
            }
            if (!squadMap.keySet().isEmpty() && (proj.getId() != null || proj.getId().equals(0))) {
                List<MeasureSquad> squads = measureListService.searchByProjIdIdIn(proj.getId(), squadMap.keySet());
                squads.forEach(measureSquad -> md.getSquad_map().put(measureSquad.getId(), measureSquad.getName()));
            }
            Map<Integer, MeasureRule> ruleMap2 = Maps.newHashMap();
            if (!ruleMap.keySet().isEmpty()) {
                List<MeasureRule> measureRules = measureRuleService.searchByIds(ruleMap.keySet());
                measureRules.forEach(measureRule ->
                        ruleMap2.put(measureRule.getId(), measureRule)
                );
            }
            ruleMap.putAll(ruleMap2);
            if (!categoryMap.keySet().isEmpty()) {
                List<CategoryV3> categorys = checkItemV3Service.searchCategoryByKeyIn(categoryMap.keySet());
                categorys.forEach(categoryV3 -> {
                    MeasuredDataCategoryVo measuredDataCategoryVo = new MeasuredDataCategoryVo();
                    measuredDataCategoryVo.setKey(categoryV3.getKey());
                    measuredDataCategoryVo.setName(categoryV3.getName());
                    measuredDataCategoryVo.setOrder(categoryV3.getOrder());
                    md.getCategory_map().put(categoryV3.getKey(), measuredDataCategoryVo);
                });
            }
            if (!userMap.keySet().isEmpty()) {
                Map<Integer, User> users = userService.getUsersByIds(new ArrayList<>(userMap.keySet()));
                users.forEach((id, user) -> md.getUser_map().put(user.getUserId(), user.getRealName())
                );
            }
            if (!regionMap.keySet().isEmpty()) {
                List<MeasureRegion> regions = regionSerVice.searchByProjUuids(projId, new ArrayList<>(regionMap.keySet()));
                regions.forEach(region -> {
                    regionMap.put(region.getUuid(), region);
                    String srcType = null;
                    if (region.getSrcType().equals(MeasureRegionSrcType.BACKEND.getId())) {
                        srcType = "A";
                    } else if (region.getSrcType().equals(MeasureRegionSrcType.APP.getId())) {
                        srcType = "B";
                    }
                    md.getRegion_map().put(region.getUuid(), String.format("%s%d", srcType, region.getRegionIndex()));
                    String[] areaIds = StringUtils.split(region.getAreaPathAndId(), "/");
                    Integer[] areaIdsInt = (Integer[]) ConvertUtils.convert(areaIds, Integer.class);
                    for (Integer areaId : areaIdsInt) {
                        areaMap.put(areaId, null);
                    }
                });
            }
            if (!areaMap.keySet().isEmpty()) {
                List<Area> areas = areaService.selectByIds(areaMap.keySet());
                areas.forEach(area -> {
                    areaMap.put(area.getId(), area);
                    md.getArea_name_map().put(area.getId(), area.getName());
                });
            }
            for (MeasureZoneResult measureZoneResult : results) {
                Map<String, MeasurePointRule> pointMap = Maps.newHashMap();
                if (ruleMap.get(measureZoneResult.getRuleId()) != null) {
                    String points = ruleMap.get(measureZoneResult.getRuleId()).getPoints();
                    List<MeasurePointRule> pointRules = JSONArray.parseArray(points, MeasurePointRule.class);
                    pointRules.forEach(measurePointRule ->
                            pointMap.put(measurePointRule.getKey(), measurePointRule)
                    );
                }
                String regionUuid = null;
                MeasureZone zone = zoneMap.get(measureZoneResult.getZoneUuid());
                if (zone != null) {
                    regionUuid = zone.getRegionUuid();
                }
                Integer buildingId = null;
                Integer floorId = null;
                Integer houseId = null;
                Integer buildingOrder = null;
                Integer floorOrder = null;
                Integer houseOrder = null;
                MeasureRegion region = regionMap.get(regionUuid);
                if (region != null) {
                    String[] areaIds = StringUtils.split(region.getAreaPathAndId(), "/");
                    Integer[] areaIdsInt = (Integer[]) ConvertUtils.convert(areaIds, Integer.class);
                    for (Integer areaId : areaIdsInt) {
                        if (areaMap.get(areaId) != null) {
                            Area area = areaMap.get(areaId);
                            Integer order = area.getOrderBy();
                            if (order.equals(0)) {
                                order = area.getId();
                            }
                            if (area.getType().equals(AreaTypeEnum.BUILDING.getId())) {
                                buildingId = areaId;
                                buildingOrder = order;
                            } else if (area.getType().equals(AreaTypeEnum.FLOOR.getId())) {
                                floorId = areaId;
                                floorOrder = order;
                            } else if (area.getType().equals(AreaTypeEnum.HOUSE.getId())) {
                                houseId = areaId;
                                houseOrder = order;
                            }
                        }
                    }
                }
                List<String> categoryNames = Lists.newArrayList();
                for (String key : StringUtils.split(measureZoneResult.getCategoryPathAndKey(), "/")) {
                    if (md.getCategory_map().get(key) != null && !md.getCategory_map().get(key).getName().equals("")) {
                        categoryNames.add(md.getCategory_map().get(key).getName());
                    }
                }
                String categoryOrder = null;
                MeasuredDataCategoryVo measuredDataCategoryVo = md.getCategory_map().get(measureZoneResult.getCategoryKey());
                if (measuredDataCategoryVo != null) {
                    categoryOrder = measuredDataCategoryVo.getOrder();
                }
                String data = measureZoneResult.getData();
                List<MeasureZoneGroupDataVo> measureZoneGroupDataVos = JSONArray.parseArray(data, MeasureZoneGroupDataVo.class);
                for (MeasureZoneGroupDataVo groupData : measureZoneGroupDataVos) {
                    MeasuredDataGroupVo gd = new MeasuredDataGroupVo();
                    gd.setBuildingOrder(buildingOrder);
                    gd.setFloorOrder(floorOrder);
                    gd.setHouseOrder(houseOrder);
                    gd.setCategoryOrder(categoryOrder);
                    gd.setBuilding_id(buildingId);
                    gd.setFloor_id(floorId);
                    gd.setHouse_id(houseId);
                    gd.setRegion_uuid(regionUuid);
                    gd.setCategory_key(measureZoneResult.getCategoryKey());
                    gd.setCategory_names(categoryNames);
                    gd.setSquad_id(measureZoneResult.getSquadId());
                    gd.setRecorder_id(groupData.getRecorderId());
                    gd.setUpdate_at(DateUtil.dateToTimestamp(groupData.getUpdateAt()));
                    gd.setTexture(groupData.getTexture());
                    gd.setPoints(new ArrayList<>());
                    for (MeasureZonePointDataVo pointData : groupData.getData()) {
                        MeasureDataPointVo d = new MeasureDataPointVo();
                        d.setKey(pointData.getKey());
                        d.setData_type(pointData.getDataType());
                        d.setData(pointData.getData());
                        d.setDesign_value_reqd(pointData.getDesignValueReqd());
                        d.setDesign_value(pointData.getDesignValue());
                        d.setSeq(pointData.getSeq());
                        d.setDeviation(pointData.getDeviation());
                        if (pointMap.get(pointData.getKey()) != null) {
                            d.setName(pointMap.get(pointData.getKey()).getName());
                            d.setAllow_range(pointMap.get(pointData.getKey()).getAllowRange());
                        }
                        List<MeasureDataPointVo> points = Lists.newArrayList();
                        points.add(d);
                        gd.setPoints(points);
                    }
                    List<MeasuredDataGroupVo> details = Lists.newArrayList();
                    details.add(gd);
                    md.setDetails(details);
                }

            }
            Collections.sort(md.getDetails(), new Comparator<MeasuredDataGroupVo>() {
                @Override
                public int compare(MeasuredDataGroupVo a, MeasuredDataGroupVo b) {
                    if (a.getSquad_id().equals(b.getSquad_id())) {
                        return a.getSquad_id() - b.getSquad_id();
                    } else if (!a.getBuilding_id().equals(b.getBuilding_id())) {
                        if (a.getBuildingOrder().equals(b.getBuildingOrder())) {
                            return a.getBuildingOrder() - b.getBuildingOrder();
                        }
                        return a.getBuilding_id() - b.getBuilding_id();
                    } else if (!a.getFloor_id().equals(b.getFloor_id())) {
                        if (a.getFloorOrder().equals(b.getFloorOrder())) {
                            return a.getFloorOrder() - b.getFloorOrder();
                        }
                        return a.getFloor_id() - b.getFloor_id();
                    } else if (!a.getHouse_id().equals(b.getHouse_id())) {
                        if (a.getHouseOrder().equals(b.getHouseOrder())) {
                            return a.getHouseOrder() - b.getHouseOrder();
                        }
                        return a.getHouse_id() - b.getHouse_id();
                    }
                    return com.longfor.longjian.measure.util.StringUtil.compareIdList(a.getCategoryOrder(), b.getCategoryOrder(), "\\.");
                }
            });
            return md;

        } catch (Exception e) {
            log.info("异常:{}", e.getMessage());
            return null;
        }
    }

    private void reCalc(CategoryDataVo d) {
        if (d.getChildren().size() == 0) {
            return;
        }
        OkRateVo okRate = new OkRateVo();
        Map<String, OkRateVo> squadOkRates = Maps.newHashMap();
        Map<Integer, Boolean> squadMap = Maps.newHashMap();
        List<MeasureSquad> squads = Lists.newArrayList();
        for (CategoryDataVo child : d.getChildren()) {
            reCalc(child);
            okRate.setOk_total((okRate.getOk_total() == null ? 0 : okRate.getOk_total()) + (child.getOk_rate().getOk_total() == null ? 0 : child.getOk_rate().getOk_total()));
            okRate.setTotal((okRate.getTotal() == null ? 0 : okRate.getTotal()) + (child.getOk_rate().getTotal() == null ? 0 : child.getOk_rate().getTotal()));
            child.getSquad_ok_rates().forEach((squadId, squadOkRate) -> {
                OkRateVo okRateVo = squadOkRates.get(squadId);
                if (okRateVo == null) {
                    okRateVo = new OkRateVo();
                    squadOkRates.put(squadId, okRateVo);
                }
                okRateVo.setOk_total((okRateVo.getOk_total() == null ? 0 : okRateVo.getOk_total()) + (squadOkRate.getOk_total() == null ? 0 : squadOkRate.getOk_total()));
                okRateVo.setTotal((okRateVo.getTotal() == null ? 0 : okRateVo.getTotal()) + (squadOkRate.getTotal() == null ? 0 : squadOkRate.getTotal()));
            });
            child.getSquads().forEach(measureSquad -> {
                if (squadMap.get(measureSquad.getId()) == null) {
                    squadMap.put(measureSquad.getId(), true);
                    squads.add(measureSquad);
                }
            });

        }
        d.setSquads(squads);
        d.setOk_rate(okRate);
        d.setSquad_ok_rates(squadOkRates);
    }

    private void addResult(MeasureZoneResult result, MeasureSquad squad, String regionUuid, AreaInfoWithExtendVo areaInfoWithExtendVo, CategoryDataVo d) throws ParseException {
        List<String> dataList = Arrays.asList(result.getData());
        for (String groupData : dataList) {
            List<MeasureZoneGroupDataVo> measureZoneGroupDataVos = JSON.parseArray(groupData, MeasureZoneGroupDataVo.class);
            for (MeasureZoneGroupDataVo zoneGroupDataVo : measureZoneGroupDataVos) {
                for (MeasureZonePointDataVo zoneData : zoneGroupDataVo.getData()) {
                    String key = String.format("%s:%s", zoneData.getKey(), zoneGroupDataVo.getTexture());
                    Integer dataIndex = d.getDataIndex().get(key);
                    if (dataIndex == null || dataIndex == 0) {
                        dataIndex = d.getData().size();
                        d.getData().add(newPointData(zoneData.getKey(), zoneGroupDataVo.getTexture()));
                        d.getDataIndex().put(key, dataIndex);
                    }
                    addZoneData(zoneData, squad, regionUuid, areaInfoWithExtendVo, d.getData().get(dataIndex));
                }
            }
        }
        OkRateVo okRateVo = new OkRateVo();
        okRateVo.setOk_total((d.getOk_rate().getOk_total() == null ? 0 : d.getOk_rate().getOk_total()) + (result.getOkTotal() == null ? 0 : result.getOkTotal()));
        okRateVo.setTotal((result.getTotal() == null ? 0 : result.getTotal()));
        d.setOk_rate(okRateVo);
        String squadId = String.format("%s", squad.getId());
        OkRateVo squadRate = d.getSquad_ok_rates().get(squadId);
        if (squadRate == null) {
            squadRate = new OkRateVo();
            d.getSquad_ok_rates().put(squadId, squadRate);
        }
        squadRate.setOk_total((squadRate.getOk_total() == null ? 0 : squadRate.getOk_total()) + (result.getOkTotal() == null ? 0 : result.getOkTotal()));
        squadRate.setTotal((squadRate.getTotal() == null ? 0 : squadRate.getTotal()) + (result.getTotal() == null ? 0 : result.getTotal()));
    }

    private void addZoneData(MeasureZonePointDataVo zoneData, MeasureSquad squad, String regionUuid, AreaInfoWithExtendVo areaInfoWithExtendVo, PointDataVo d) {
        String squadId = String.format("%s", squad.getId());
        OkRateVo squadRate = d.getSquad_ok_rates().get(squadId);
        if (squadRate == null) {
            squadRate = new OkRateVo();
            d.getSquad_ok_rates().put(squadId, squadRate);
            d.getSquads().add(squad);
        }
        Integer dataIndex = d.getDataIndex().get(regionUuid);
        if (dataIndex == null) {
            dataIndex = d.getData().size();
            d.getDataIndex().put(regionUuid, dataIndex);
            d.getData().add(newZoneData(regionUuid, areaInfoWithExtendVo));
        }
        addZoneData2(zoneData, squadId, d.getData().get(dataIndex));
        OkRateVo okRateVo = new OkRateVo();
        okRateVo.setOk_total((d.getOk_rate().getOk_total() == null ? 0 : d.getOk_rate().getOk_total()) + (zoneData.getOkTotal() == null ? 0 : zoneData.getOkTotal()));
        okRateVo.setTotal((d.getOk_rate().getTotal() == null ? 0 : d.getOk_rate().getTotal()) + (zoneData.getTotal() == null ? 0 : zoneData.getTotal()));
        d.setOk_rate(okRateVo);
        squadRate.setOk_total((squadRate.getOk_total() == null ? 0 : squadRate.getOk_total()) + (zoneData.getOkTotal() == null ? 0 : zoneData.getOkTotal()));
        squadRate.setTotal((squadRate.getTotal() == null ? 0 : squadRate.getTotal()) + (zoneData.getTotal() == null ? 0 : zoneData.getTotal()));
    }

    private void addZoneData2(MeasureZonePointDataVo zoneData, String squadId, MeasureZoneDataVo d) {
        List<MeasureZonePointDataVo> data = d.getData().get(squadId);
        if (data == null || data.isEmpty()) {
            d.getSquad_ok_rates().put(squadId, new OkRateVo());
        }
        Map<String, List<MeasureZonePointDataVo>> datamap = d.getData();
        List<MeasureZonePointDataVo> dataVos = new ArrayList<>();
        dataVos.add(zoneData);
        datamap.put(squadId, dataVos);
        OkRateVo okRateVo = new OkRateVo();
        okRateVo.setOk_total((d.getOk_rate().getOk_total() == null ? 0 : d.getOk_rate().getOk_total()) + (zoneData.getOkTotal() == null ? 0 : zoneData.getOkTotal()));
        okRateVo.setTotal((d.getOk_rate().getTotal() == null ? 0 : d.getOk_rate().getTotal()) + (zoneData.getTotal() == null ? 0 : zoneData.getTotal()));
        d.setOk_rate(okRateVo);
        OkRateVo squadRate = d.getSquad_ok_rates().get(squadId);
        squadRate.setOk_total((squadRate.getOk_total() == null ? 0 : squadRate.getOk_total()) + (zoneData.getOkTotal() == null ? 0 : zoneData.getOkTotal()));
        squadRate.setTotal((squadRate.getTotal() == null ? 0 : squadRate.getTotal()) + (zoneData.getTotal() == null ? 0 : zoneData.getTotal()));
    }

    private MeasureZoneDataVo newZoneData(String regionUuid, AreaInfoWithExtendVo area) {
        MeasureZoneDataVo measureZoneDataVo = new MeasureZoneDataVo();
        measureZoneDataVo.setRegion_uuid(regionUuid);
        measureZoneDataVo.setOrder(area.getArea().getId());
        measureZoneDataVo.setArea_name(StringUtils.join(area.getPathNames(), ">"));
        measureZoneDataVo.setData(new HashMap<String, List<MeasureZonePointDataVo>>());
        measureZoneDataVo.setSquad_ok_rates(new HashMap<String, OkRateVo>());
        return measureZoneDataVo;
    }

    private PointDataVo newPointData(String key, String texture) {
        PointDataVo pointDataVo = new PointDataVo();
        pointDataVo.setKey(key);
        pointDataVo.setTexture(texture);
        pointDataVo.setSquads(new ArrayList<MeasureSquad>());
        pointDataVo.setData(new ArrayList<MeasureZoneDataVo>());
        pointDataVo.setSquad_ok_rates(new HashMap<String, OkRateVo>());
        pointDataVo.setDataIndex(new HashMap<String, Integer>());
        return pointDataVo;
    }

    private CategoryDataVo newCategoryData(String key, String name) {
        CategoryDataVo categoryDataVo = new CategoryDataVo();
        categoryDataVo.setKey(key);
        categoryDataVo.setName(name);
        categoryDataVo.setSquad_ok_rates(new HashMap<String, OkRateVo>());
        categoryDataVo.setChildren(new ArrayList<CategoryDataVo>());
        categoryDataVo.setData(new ArrayList<PointDataVo>());
        categoryDataVo.setDataIndex(new HashMap<String, Integer>());
        return categoryDataVo;
    }
}
