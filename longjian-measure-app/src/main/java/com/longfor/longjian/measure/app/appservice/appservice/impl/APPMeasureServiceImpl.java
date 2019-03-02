package com.longfor.longjian.measure.app.appservice.appservice.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.longfor.longjian.common.base.LjBaseResponse;
import com.longfor.longjian.common.exception.CommonRuntimeException;
import com.longfor.longjian.common.exception.LjBaseRuntimeException;
import com.longfor.longjian.common.kafka.KafkaProducer;
import com.longfor.longjian.common.util.DateUtil;
import com.longfor.longjian.common.util.SessionInfo;
import com.longfor.longjian.common.util.StringUtil;
import com.longfor.longjian.measure.app.appservice.appservice.IAPPMeasureService;
import com.longfor.longjian.measure.app.appservice.appservice.IKeyProcedureTaskAppService;
import com.longfor.longjian.measure.app.commonentity.MeasureListIssueHelper;
import com.longfor.longjian.measure.app.commonentity.MeasureListIssueStruct;
import com.longfor.longjian.measure.app.commonentity.MeasureZoneResultCreateMsg;
import com.longfor.longjian.measure.app.commonentity.measure.MeasureZoneGroupData;
import com.longfor.longjian.measure.app.commonentity.measure.MeasureZonePointData;
import com.longfor.longjian.measure.app.req.appreq.*;
import com.longfor.longjian.measure.app.vo.MsgAppendVo;
import com.longfor.longjian.measure.app.vo.appmeasuresyncvo.*;
import com.longfor.longjian.measure.app.vo.propaintareamanagevo.RelVo;
import com.longfor.longjian.measure.consts.enums.ApiDropDataReasonEnum;
import com.longfor.longjian.measure.consts.enums.EventQueueEnum;
import com.longfor.longjian.measure.consts.constant.KeyProcedureTaskConstant;
import com.longfor.longjian.measure.consts.constant.MeasureListConstant;
import com.longfor.longjian.measure.domain.externalservice.*;
import com.longfor.longjian.measure.po.zhijian2.*;
import com.longfor.longjian.measure.util.DateTool;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class APPMeasureServiceImpl implements IAPPMeasureService {

    private static final Integer MEASUREAPIGETPERTIME = 5000;

    @Autowired
    private IKeyProcedureTaskAppService keyProcedureTaskAppService;
    @Autowired
    private IMeasureRegionService measureRegionService;
    @Autowired
    private IMeasureRegionRelService measureRegionRelService;
    @Autowired
    private IMeasureListService measureListService;
    @Autowired
    private IMeasureSquadService measureSquadService;
    @Autowired
    private IMeasureSquadUserService measureSquadUserService;
    @Autowired
    private IMeasureRepairerUserService measureRepairerUserService;
    @Autowired
    private IMeasureZoneResultService measureZoneResultService;
    @Autowired
    private IMeasureRuleService measureRuleService;
    @Autowired
    private IMeasureZoneService measureZoneService;
    @Autowired
    private ICategoryV3Service categoryV3Service;
    @Autowired
    private KafkaProducer kafkaProducer;
    @Resource
    private MeasureListIssueHelper helper;
    @Resource
    private SessionInfo sessionInfo;
    private static final String SEARCHUNSCOPEDBYPROJIDLASTIDUPDATEATGT = "SearchUnscopedByProjIdLastIdUpdateAtGt";
    private static final String ERROR = "],error:";
    private static final String UPDATEAT = "UpdateAt";
    private static final String TEXTURE = "Texture";
    private static final String DATATYPE = "DataType";
    private static final String DESIGNVALUE = "DesignValue";
    private static final String DESIGNVALUEREQD = "DesignValueReqd";

    @Override
    public LjBaseResponse<DroppedInfoVo> reportIssue(ApiMeasureReportIssueReq apiMeasureReportIssueReq, HttpServletRequest request) throws LjBaseRuntimeException, ParseException {
        LjBaseResponse<DroppedInfoVo> ljBaseResponse = new LjBaseResponse<>();
        DroppedInfoVo droppedInfoVo = new DroppedInfoVo();
        // 检查uuid，没有uuid也可以执行以下代码以保存请求内容
        String reportUuidStatus = KeyProcedureTaskConstant.ERROR;
        Integer uid = null;
        try {
            uid = (Integer) sessionInfo.getBaseInfo("userId");
        } catch (Exception e) {
            throw new LjBaseRuntimeException(-9999, e.getMessage());
        }
        try {
            keyProcedureTaskAppService.startReport(apiMeasureReportIssueReq.getReport_uuid(), uid, request);
        } catch (Exception e) {
            keyProcedureTaskAppService.updateReportStatus(apiMeasureReportIssueReq.getReport_uuid(), reportUuidStatus);
            throw new LjBaseRuntimeException(-9999, e + "");
        }
        List<MeasureListIssueStruct> datas = null;
        log.debug(apiMeasureReportIssueReq.getData());
        datas = JSONArray.parseArray(apiMeasureReportIssueReq.getData(), MeasureListIssueStruct.class);
        helper.init(apiMeasureReportIssueReq.getProject_id());
        for (MeasureListIssueStruct v : datas
        ) {
            helper.start().
                    setNormalField(v.getUuid(), v.getList_id(), v.getIssue_uuid(), v.getSender_id(), v.getDesc(),
                            v.getTyp(), v.getStatus(), v.getAttachment_md5_list(), v.getCategory_key(), v.getClient_create_at().longValue()).
                    setDatailField(v.getZone_uuid(), Long.parseLong(v.getPlan_end_on().toString()), Long.parseLong(v.getEnd_on().toString()), v.getRepairer_id(),
                            v.getCondition(), v.getArea_id(), v.getDrawing_md5(), v.getPos_x(), v.getPos_y(),
                            v.getClose_status(), v.getClose_user(), v.getClose_time() == null ? 0 : Long.parseLong(v.getClose_time().toString()), v.getCheck_status())
                    .done();
        }
        try {
            helper.execute();
        } catch (Exception e) {
            throw new LjBaseRuntimeException(-9999, e + "");
        }
        droppedInfoVo.setDropped(helper.getDroppedIssueLog());
        ljBaseResponse.setData(droppedInfoVo);
        return ljBaseResponse;
    }

    @Override
    public LjBaseResponse<DroppedInfoVo> reportZoneResult(ApiMeasureReportZoneResultReq apiMeasureReportZoneResultReq, HttpServletRequest request) throws ParseException, LjBaseRuntimeException {
        LjBaseResponse<DroppedInfoVo> ljBaseResponse = new LjBaseResponse<>();
        DroppedInfoVo droppedInfoVo = new DroppedInfoVo();
        // 检查uuid，没有uuid也可以执行以下代码以保存请求内容
        String reportUuidStatus = KeyProcedureTaskConstant.ERROR;
        Integer uid = null;
        try {
            uid = (Integer) sessionInfo.getBaseInfo("userId");
        } catch (Exception e) {
            throw new LjBaseRuntimeException(-9999, e.getMessage());
        }
        try {
            keyProcedureTaskAppService.startReport(apiMeasureReportZoneResultReq.getReport_uuid(), uid, request);
        } catch (Exception e) {
            keyProcedureTaskAppService.updateReportStatus(apiMeasureReportZoneResultReq.getReport_uuid(), reportUuidStatus);
            throw new LjBaseRuntimeException(-9999, e + "");
        }
        List<ResultListVo> zoneResults = JSONArray.parseArray(apiMeasureReportZoneResultReq.getData(), ResultListVo.class);
        List<DroppedVo> dropped = createZoneResultsNoProj(zoneResults);
        droppedInfoVo.setDropped(dropped);
        ljBaseResponse.setData(droppedInfoVo);
        return ljBaseResponse;
    }

    /**
     * @param zoneResults
     * @return
     */
    private List<DroppedVo> createZoneResultsNoProj(List<ResultListVo> zoneResults) throws ParseException {
        Map<Integer, List<ResultListVo>> projData = new HashMap<>();
        List<DroppedVo> dropData = new ArrayList<>();
        zoneResults.forEach(resultListVo -> {
            List<ResultListVo> projD = projData.get(resultListVo.getProject_id());
            if (projD == null) {
                projD = new ArrayList<>();
            }
            projD.add(resultListVo);
            projData.put(resultListVo.getProject_id(), projD);
        });
        for (Map.Entry<Integer, List<ResultListVo>> entry : projData.entrySet()) {
            List<DroppedVo> droppedVo = createZoneResults(entry.getKey(), entry.getValue());
            dropData.addAll(droppedVo);
        }
        return dropData;
    }

    private List<DroppedVo> createZoneResults(Integer projId, List<ResultListVo> data) throws ParseException {
        Map<String, MeasureZone> zoneUuidMap = new HashMap<>();
        Map<String, MeasureRule> ruleMap = new HashMap<>();
        Map<String, CategoryV3> categoryMap = new HashMap<>();
        zoneUuidMap = data.stream().collect(Collectors.toMap(ResultListVo::getZone_uuid, vo -> new MeasureZone()));
        List<MeasureZone> measureZones = measureZoneService.searchZoneByUuid(projId, zoneUuidMap.keySet());
        Map<String, Boolean> regionUuidMap = new HashMap<>();
        for (MeasureZone measureZone : measureZones
        ) {
            zoneUuidMap.put(measureZone.getUuid(), measureZone);
            regionUuidMap.put(measureZone.getRegionUuid(), true);
        }
        List<MeasureRegion> regions = measureRegionService.searchByUuids(projId, regionUuidMap.keySet());
        Map<String, MeasureRegion> regionMap = regions.stream().collect(Collectors.toMap(MeasureRegion::getUuid, measureRegion -> measureRegion));
        List<DroppedVo> droppedVos = new ArrayList<>();
        MeasureZoneResultCreateMsg msgPkg = new MeasureZoneResultCreateMsg();
        for (ResultListVo resultListVo : data
        ) {
            //0、根据zone_uuid看是否有上传过
            //如果有上传过就不让写入（在未有开关之前统一拒收）
            try {
                List<MeasureZoneResult> measureZoneResults = measureZoneResultService.getByProjIdListIdZoneUuidSquadId(resultListVo.getProject_id(), resultListVo.getList_id(), resultListVo.getZone_uuid(), resultListVo.getSquad_id());
                boolean has = measureZoneResults != null && !measureZoneResults.isEmpty();
                if (has) {
                    log.warn("zone result already uploaded, zone_uuid:" + resultListVo.getZone_uuid());
                    ApiDropDataReasonEnum reason = ApiDropDataReasonEnum.MEASUREZONERESULTEXISTS;
                    try {
                        MeasureZoneResult measureZoneResult = measureZoneResultService.getByUuid(projId, resultListVo.getUuid());
                        has = measureZoneResult != null;
                        if (has) {
                            reason = ApiDropDataReasonEnum.MEASUREZONERESULTUUIDEXISTS;
                        }
                        //已经存在，舍弃掉此信息
                        DroppedVo droppedVo = new DroppedVo();
                        droppedVo.setUuid(resultListVo.getUuid());
                        droppedVo.setReason_type(Integer.parseInt(reason.getValue()));
                        droppedVo.setReason(reason.getName());
                        droppedVos.add(droppedVo);
                        continue;
                    } catch (Exception e) {
                        log.warn("zoneResultDao.GetByUuid:" + resultListVo.getUuid());
                    }
                }
                //1、根据zone_uuid获取对应测区的数据，包括检查项，计算公式等
                //zone info
                MeasureZone zoneInfo = zoneUuidMap.get(resultListVo.getZone_uuid());
                has = zoneInfo != null;
                if (!has) {
                    log.warn("zone info not found, zone_uuid:" + resultListVo.getZone_uuid());
                    //找不到测区就舍弃此条记录
                    DroppedVo droppedVo = new DroppedVo();
                    droppedVo.setUuid(resultListVo.getUuid());
                    droppedVo.setReason_type(Integer.parseInt(ApiDropDataReasonEnum.MEASYREZONENOTFOUND.getValue()));
                    droppedVo.setReason(ApiDropDataReasonEnum.MEASYREZONENOTFOUND.getName());
                    droppedVos.add(droppedVo);
                    continue;
                } else if (zoneInfo.getCloseStatus() == Integer.parseInt(MeasureListConstant.CLOSEDCODE)) {
                    //判断测区是否打开，如果没打开，就要舍弃数据
                    log.warn("zone is close, zone_uuid:" + resultListVo.getZone_uuid());
                    //找不到测区就舍弃此条记录
                    DroppedVo droppedVo = new DroppedVo();
                    droppedVo.setUuid(resultListVo.getUuid());
                    droppedVo.setReason_type(Integer.parseInt(ApiDropDataReasonEnum.MEASUREZONEISCLOSE.getValue()));
                    droppedVo.setReason(ApiDropDataReasonEnum.MEASUREZONEISCLOSE.getName());
                    droppedVos.add(droppedVo);
                    continue;
                }
                String currentCategoryKey = zoneInfo.getCategoryKey();
                CategoryV3 category = categoryMap.get(currentCategoryKey);
                if (category == null) {
                    category = categoryV3Service.getCategoryByKeyNoFoundErr(currentCategoryKey);
                    categoryMap.put(currentCategoryKey, category);
                }
                Integer currentRuleId = 0;
                //rule info
                MeasureRule ruleInfo = ruleMap.get(currentCategoryKey);
                has = ruleInfo != null;
                if (!has) {
                    ruleInfo = measureRuleService.getByCategoryKey(currentCategoryKey);
                    has = ruleInfo != null;
                    if (!has) {
                        log.warn("ruleInfo not found, zone_uuid:" + resultListVo.getZone_uuid());
                        DroppedVo droppedVo = new DroppedVo();
                        droppedVo.setUuid(resultListVo.getUuid());
                        droppedVo.setReason_type(Integer.parseInt(ApiDropDataReasonEnum.MEASURERULENOFOUND.getValue()));
                        droppedVo.setReason(ApiDropDataReasonEnum.MEASURERULENOFOUND.getName());
                        droppedVos.add(droppedVo);
                        continue;
                    } else {
                        ruleMap.put(currentCategoryKey, ruleInfo);
                        currentRuleId = ruleInfo.getId();
                    }
                } else {
                    currentRuleId = ruleInfo.getId();
                }
                //2、根据计算公式，得出结果
                //因暂时没有计算公式，所以结果先随便写
                MeasureZoneResult zoneResult = new MeasureZoneResult();
                zoneResult.setUuid(resultListVo.getUuid());
                zoneResult.setProjectId(resultListVo.getProject_id());
                zoneResult.setListId(resultListVo.getList_id());
                zoneResult.setZoneUuid(resultListVo.getZone_uuid());
                zoneResult.setSquadId(resultListVo.getSquad_id());
                zoneResult.setRuleId(currentRuleId);
                zoneResult.setRegionUuid(zoneInfo.getRegionUuid());
                zoneResult.setCategoryKey(currentCategoryKey);
                if (category != null) {
                    zoneResult.setCategoryPathAndKey(category.getPath() + category.getKey() + "/");
                }
                MeasureRegion region = regionMap.get(zoneInfo.getRegionUuid());
                if (region != null) {
                    zoneResult.setAreaId(region.getAreaId());
                    zoneResult.setAreaPathAndId(region.getAreaPathAndId());
                }
                List<Map> zoneResultData = new ArrayList<>();
                resultListVo.getData().forEach(textResultVo -> {
                    Map<String, Object> groupData = Maps.newHashMap();
                    groupData.put("RecorderId", textResultVo.getRecorder_id());
                    groupData.put(UPDATEAT, textResultVo.getUpdate_at());
                    groupData.put(TEXTURE, textResultVo.getTexture());
                    List<Map> textResultData = new ArrayList<>();
                    textResultVo.getData().forEach(singlePointTestVo -> {
                        Map<String, Object> pointData = Maps.newHashMap();
                        pointData.put("Key", singlePointTestVo.getKey());
                        List<Double> tempData = splitToFloatsComma(singlePointTestVo.getData(), true);
                        pointData.put("Data", tempData);
                        pointData.put(DATATYPE, singlePointTestVo.getData_type());
                        pointData.put(DESIGNVALUE, singlePointTestVo.getDesign_value());
                        pointData.put(DESIGNVALUEREQD, singlePointTestVo.getDesign_value_reqd());
                        textResultData.add(pointData);
                    });
                    groupData.put("Data", textResultData);
                    zoneResultData.add(groupData);
                });
                log.info(JSON.toJSONString(zoneResultData));
                zoneResult.setData(JSON.toJSONString(zoneResultData));
                zoneResult.setCreateAt(new Date());
                zoneResult.setUpdateAt(new Date());
                zoneResult.setCloseStatus(0);
                //计算结果是否合格
                try {
                    calcResult(ruleInfo.getFormula(), zoneResult);
                } catch (Exception e) {
                    log.warn("calc result error:" + e);
                    DroppedVo droppedVo = new DroppedVo();
                    droppedVo.setUuid(resultListVo.getUuid());
                    droppedVo.setReason_type(Integer.parseInt(ApiDropDataReasonEnum.MEASURERULEERROR.getValue()));
                    droppedVo.setReason(ApiDropDataReasonEnum.MEASURERULEERROR.getName());
                    droppedVos.add(droppedVo);
                    continue;
                }
                try {
                    measureZoneResultService.insertObjectNoAffectedErr(zoneResult);
                } catch (Exception e) {
                    log.warn("insert zone result error:%s", e.getMessage());
                    DroppedVo droppedVo = new DroppedVo();
                    droppedVo.setUuid(resultListVo.getUuid());
                    droppedVo.setReason_type(Integer.parseInt(ApiDropDataReasonEnum.OTHER.getValue()));
                    droppedVo.setReason(ApiDropDataReasonEnum.OTHER.getName());
                    droppedVos.add(droppedVo);
                    continue;
                }
                Integer senderId = 0;
                String clientCreateAt = "0001-01-01 00:00:00";
                int i = 0;
                for (TextResultVo t : resultListVo.getData()
                ) {
                    if (i == 0) {
                        senderId = t.getRecorder_id();
                    }
                    if (t.getUpdate_at() != null && t.getUpdate_at() > DateTool.getLongFromString(clientCreateAt)) {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Date date = new Date();
                        log.info("时间{}", DateUtil.dateToString(date));
                        try {
                            Date parse = sdf.parse(sdf.format(new Date((t.getUpdate_at() * 1000))));
                            clientCreateAt = sdf.format(parse);
                        } catch (NumberFormatException e) {
                            log.error("error:", e);
                        } catch (ParseException e) {
                            log.error("error:", e);
                            throw new ParseException("error :" + e, -9999);
                        }
                    }
                    i++;
                }
                MsgAppendVo msgAppendVo = new MsgAppendVo();
                msgAppendVo.setUuid(zoneResult.getUuid());
                msgAppendVo.setZoneUuid(zoneResult.getZoneUuid());
                msgAppendVo.setProjId(zoneResult.getProjectId());
                msgAppendVo.setListId(zoneResult.getListId());
                msgAppendVo.setSenderId(senderId);
                msgAppendVo.setAreaId(zoneResult.getAreaId());
                msgAppendVo.setAreaPathAndId(zoneResult.getAreaPathAndId());
                msgAppendVo.setCategoryKey(zoneResult.getCategoryKey());
                msgAppendVo.setCategoryPathAndKey(zoneResult.getCategoryPathAndKey());
                msgAppendVo.setOkTotal(zoneResult.getOkTotal());
                msgAppendVo.setTotal(zoneResult.getTotal());
                msgAppendVo.setTimeAt(DateTool.getLongFromString(clientCreateAt));
                msgPkg.append(msgAppendVo);
            } catch (Exception e) {
                log.warn("zoneResultDao.GetByZoneUuid:" + resultListVo.getZone_uuid());
                throw e;
            } finally {
                //执行推送
                kafkaProducer.produce(EventQueueEnum.PKG_MEASURE_RESULT_CREATED.getValue(), msgPkg);
            }
        }

        return droppedVos;
    }

    private List<Double> splitToFloatsComma(String idsStr, boolean ignoreBlank) {
        return splitToFloats(idsStr, ",", ignoreBlank);
    }

    private List<Double> splitToFloats(String idsStr, String sep, boolean ignoreBlank) {
        List<Double> ids = new ArrayList<>();
        for (String idStr : idsStr.split(sep)
        ) {
            idStr = idStr.trim();
            if ("".equals(idStr)) {
                continue;
            }
            try {
                double id = Double.parseDouble(idStr);
                ids.add(id);
            } catch (Exception e) {
                if (ignoreBlank) {
                    continue;
                }
                log.error("String switch double exception");
                return new ArrayList<>();
            }
        }
        return ids;
    }

    /**
     * 计算结果是否合格
     *
     * @param formula
     */
    private void calcResult(String formula, MeasureZoneResult result) throws LjBaseRuntimeException{
        List<MeasureZoneGroupData> resultData = Lists.newArrayList();
        List<Map> dataZone = JSONArray.parseArray(result.getData(), Map.class);
        for (Map d : dataZone) {
            MeasureZoneGroupData r = new MeasureZoneGroupData();
            r.setTexture(MapUtils.getString(d, TEXTURE, ""));
            r.setData(Maps.newHashMap());
            r.setScore(0F);
            List<Map> dataPoint = JSONArray.parseArray(MapUtils.getString(d, "Data", null), Map.class);
            for (Map pd : dataPoint) {
                MeasureZonePointData npd = new MeasureZonePointData();
                npd.setKey(MapUtils.getString(pd, "Key", null));
                npd.setDataType(MapUtils.getInteger(pd, DATATYPE, null));
                npd.setData(JSONArray.parseArray(MapUtils.getString(pd, "Data", null), Long.class));
                npd.setDesignValueReqd(MapUtils.getBoolean(pd, DESIGNVALUEREQD, null));
                npd.setDesignValue(MapUtils.getLong(pd, DESIGNVALUE, null));
                r.getData().put(MapUtils.getString(pd, "Key", null), npd);
            }
            resultData.add(r);
        }
        ScriptEngineManager sem = new ScriptEngineManager();
        ScriptEngine se = sem.getEngineByName("js");
        for (MeasureZoneGroupData r : resultData) {
            Map<String, Object> args = Maps.newHashMap();
            args.put("texture", r.getTexture());
            for (Map.Entry<String, MeasureZonePointData> entry : r.getData().entrySet()) {
                args.put(entry.getKey(), entry.getValue().toMap());
            }
            String res = "";
            try {
                se.eval(formula);
                Invocable inv2 = (Invocable) se;
                res = JSON.toJSONString(inv2.invokeFunction("calc", args));
                log.info("JS执行结果res:{}", res);
            } catch (Exception e) {
                log.error("执行JavaScript错误", e);
                throw new LjBaseRuntimeException(-9999,e+"");
            }
            Map resultValue = JSONObject.parseObject(res, Map.class);
            for (Map.Entry<String, MeasureZonePointData> entry : r.getData().entrySet()) {
                String key = entry.getKey();
                MeasureZonePointData pd = entry.getValue();
                boolean ok = resultValue.containsKey(key);
                if (!ok) {
                    CommonRuntimeException e = new CommonRuntimeException(String.format("'%s' unfound", key));
                    log.warn(e.getMessage());
                    throw e;
                }
                Map vpd = JSONObject.parseObject(MapUtils.getString(resultValue, key, null), Map.class);
                pd.setTotal(MapUtils.getInteger(vpd, "total", null));
                pd.setOkTotal(MapUtils.getInteger(vpd, "ok_total", null));
                pd.setSeq(MapUtils.getString(vpd, "seq", null));
                pd.setDeviation(MapUtils.getString(vpd, "deviation", null));
            }
            boolean ok = resultValue.containsKey("score");
            if (!ok) {
                CommonRuntimeException e = new CommonRuntimeException("'score' unfound");
                log.warn(e.getMessage());
                throw e;
            }
            r.setScore(MapUtils.getFloat(resultValue, "score", 0F));
        }
        result.setTotal(0);
        result.setOkTotal(0);
        result.setScore(0f);

        for (int i = 0; i < resultData.size(); i++) {
            MeasureZoneGroupData r = resultData.get(i);
            Map data = dataZone.get(i);
            data.put("Score", r.getScore());
            result.setScore(result.getScore() + r.getScore());
            for (Map v : (List<Map>) data.get("Data")) {
                MeasureZonePointData d = r.getData().get(v.get("Key"));
                v.put("Total", d.getTotal());
                result.setTotal(result.getTotal() + d.getTotal());
                v.put("OkTotal", d.getOkTotal());
                result.setOkTotal(result.getOkTotal() + d.getOkTotal());
                v.put("Seq", d.getSeq());
                v.put("Deviation", StringUtil.strToFloats(d.getDeviation(), ","));
            }
        }
        result.setData(JSON.toJSONString(dataZone));
        if (result.getTotal() > 0) {
            result.setScore((float) (result.getOkTotal() * 100 / result.getTotal()));
        }
    }

    @Override
    public LjBaseResponse<MeasureRegionVo> getMeasureRegion(ApiMeasureRegionReq apiMeasureRegionReq) throws LjBaseRuntimeException {
        LjBaseResponse<MeasureRegionVo> ljBaseResponse = new LjBaseResponse<>();
        MeasureRegionVo measureRegionVo = new MeasureRegionVo();
        List<MeasureRegionListVo> regionLists = new ArrayList<>();
        List<RelVo> relList = new ArrayList<>();
        String[] projectIds = apiMeasureRegionReq.getProject_ids().split(",");
        if (projectIds.length == 0) {
            throw new LjBaseRuntimeException(-9999, "project ids is empty.");
        }
        String updateAtGte = "0001-01-01 00:00:00";
        List<MeasureRegion> regionList = new ArrayList<>();
        for (String projectId : projectIds
        ) {
            List<MeasureRegion> regions = measureRegionService.searchUnscopedByProjIdUpdateAtGt(projectId, updateAtGte);
            regionList.addAll(regions);
        }
        //类转换,手动
        regionList.forEach(region -> {
            MeasureRegionListVo regionListVo = converMeasureRegionToMeasureRegionListVo(region);
            regionLists.add(regionListVo);
        });
        List<MeasureRegionRel> regionRelList = new ArrayList<>();
        for (String projectId : projectIds
        ) {
            List<MeasureRegionRel> regionRels = measureRegionRelService.searchRelUnscopedByProjIdUpdateAtGt(projectId, updateAtGte);
            regionRelList.addAll(regionRels);
        }
        //类转换,手动
        regionRelList.forEach(regionRel -> {
            RelVo relVo = converMeasureRegionRelToRegionRelListVo(regionRel);
            relList.add(relVo);
        });
        measureRegionVo.setRel_list(relList);
        measureRegionVo.setRegion_list(regionLists);
        ljBaseResponse.setData(measureRegionVo);
        return ljBaseResponse;
    }

    @Override
    public LjBaseResponse<MeasureRegionV2Vo> getMeasureRegionV2(ApiMeasureRegionReqV2 apiMeasureRegionReqV2) throws LjBaseRuntimeException {
        LjBaseResponse<MeasureRegionV2Vo> ljBaseResponse = new LjBaseResponse<>();
        MeasureRegionV2Vo measureRegionV2Vo = new MeasureRegionV2Vo();
        List<MeasureRegionListVo> regionList = new ArrayList<>();
        Integer start = 0;
        try {
            List<MeasureRegion> items = measureRegionService.searchUnscopedByProjIdLastIdUpdateAtGt(apiMeasureRegionReqV2.getProject_id(), apiMeasureRegionReqV2.getLast_id(), apiMeasureRegionReqV2.getTimestamp(), MEASUREAPIGETPERTIME, start);
            Integer newLastId = 0;
            if (!items.isEmpty()) {
                newLastId = items.get(items.size() - 1).getId();
            }
            measureRegionV2Vo.setLast_id(newLastId);
            items.forEach(measureRegion -> {
                MeasureRegionListVo regionListVo = converMeasureRegionToMeasureRegionListVo(measureRegion);
                regionList.add(regionListVo);
            });
            measureRegionV2Vo.setRegion_list(regionList);
            ljBaseResponse.setData(measureRegionV2Vo);
        } catch (Exception e) {
            log.error(SEARCHUNSCOPEDBYPROJIDLASTIDUPDATEATGT + "[" + apiMeasureRegionReqV2.getProject_id() + ERROR + e);
            throw new LjBaseRuntimeException(-9999, "读取数据失败，code:region");
        }
        return ljBaseResponse;
    }

    @Override
    public LjBaseResponse<TotalVo> getMeasureRegionV2Total(ApiMeasureRegionTotalReqV2 apiMeasureRegionTotalReqV2) throws LjBaseRuntimeException {
        LjBaseResponse<TotalVo> ljBaseResponse = new LjBaseResponse<>();
        TotalVo totalVo = new TotalVo();
        try {
            Integer total = measureRegionService.getCountUnscopedByProjIdUpdateAtGt(apiMeasureRegionTotalReqV2.getProject_id(), apiMeasureRegionTotalReqV2.getTimestamp());
            totalVo.setTotal(total);
            ljBaseResponse.setData(totalVo);
        } catch (Exception e) {
            log.error("GetCountUnscopedByProjIdUpdateAtGt" + "[" + apiMeasureRegionTotalReqV2.getProject_id() + ERROR + e);
            throw new LjBaseRuntimeException(-9999, "读取数据失败，code:region_total");
        }
        return ljBaseResponse;
    }

    @Override
    public LjBaseResponse<MeasureRegionRelV2Vo> getMeasureRegionRelV2(ApiMeasureRegionRelReqV2 apiMeasureRegionRelReqV2) throws LjBaseRuntimeException {
        LjBaseResponse<MeasureRegionRelV2Vo> ljBaseResponse = new LjBaseResponse<>();
        MeasureRegionRelV2Vo measureRegionRelV2Vo = new MeasureRegionRelV2Vo();
        List<RelVo> relList = new ArrayList<>();
        Integer start = 0;
        try {
            List<MeasureRegionRel> items = measureRegionRelService.searchRelUnscopedByProjIdLastIdUpdateAtGt(apiMeasureRegionRelReqV2.getProject_id(), apiMeasureRegionRelReqV2.getLast_id(), apiMeasureRegionRelReqV2.getTimestamp(), MEASUREAPIGETPERTIME, start);
            Integer newLastId = 0;
            if (!items.isEmpty()) {
                newLastId = items.get(items.size() - 1).getId();
            }
            measureRegionRelV2Vo.setLast_id(newLastId);
            items.forEach(measureRegionRel -> {
                RelVo relVo = converMeasureRegionRelToRegionRelListVo(measureRegionRel);
                relList.add(relVo);
            });
            measureRegionRelV2Vo.setRel_list(relList);
            ljBaseResponse.setData(measureRegionRelV2Vo);
        } catch (Exception e) {
            log.error("SearchRelUnscopedByProjIdLastIdUpdateAtGt" + "[" + apiMeasureRegionRelReqV2.getProject_id() + ERROR + e);
            throw new LjBaseRuntimeException(-9999, "读取数据失败，code:region_rel");
        }
        return ljBaseResponse;
    }

    @Override
    public LjBaseResponse<MeasureSquadAndRepairerVo> measureSquadAndRepairer(ApiMeasureSquadAndRepairerReq apiMeasureSquadAndRepairerReq) throws LjBaseRuntimeException {
        LjBaseResponse<MeasureSquadAndRepairerVo> ljBaseResponse = new LjBaseResponse<>();
        MeasureSquadAndRepairerVo measureSquadAndRepairerVo = new MeasureSquadAndRepairerVo();
        List<SquadListVo> squadList = new ArrayList<>();
        List<SquadUserListVo> squadUserList = new ArrayList<>();
        List<RepairerListVo> repairerList = new ArrayList<>();
        MeasureList measureList = null;
        try {
            measureList = measureListService.getNoProjNoFoundErr(apiMeasureSquadAndRepairerReq.getList_id().toString());
            String updateAtGt = "";
            if (apiMeasureSquadAndRepairerReq.getTimestamp() != null && apiMeasureSquadAndRepairerReq.getTimestamp() > 0) {
                updateAtGt = DateTool.getDateStringByLong(apiMeasureSquadAndRepairerReq.getTimestamp());
            }
            List<MeasureSquad> squads = measureSquadService.searchMeasureSquadByListIdTimestampGt(measureList.getProjectId(), apiMeasureSquadAndRepairerReq.getList_id(), updateAtGt);
            squads.forEach(measureSquad -> {
                SquadListVo squadListVo = converMeasureSquadToSquadListVo(measureSquad);
                squadList.add(squadListVo);
            });
            measureSquadAndRepairerVo.setSquad_list(squadList);
            List<MeasureSquadUser> squadsUsers = measureSquadUserService.searchMeasureSquadUserByListIdTimestampGt(measureList.getProjectId(), apiMeasureSquadAndRepairerReq.getList_id(), updateAtGt);
            squadsUsers.forEach(measureSquadUser -> {
                SquadUserListVo squadUserListVo = converMeasureSquadUserToSquadUserListVo(measureSquadUser);
                squadUserList.add(squadUserListVo);
            });
            measureSquadAndRepairerVo.setSquad_user_list(squadUserList);
            List<MeasureRepairerUser> repairerUsers = measureRepairerUserService.searchMeasureReparierUserByListIdTimestampGt(measureList.getProjectId(), apiMeasureSquadAndRepairerReq.getList_id(), updateAtGt);
            repairerUsers.forEach(measureRepairerUser -> {
                RepairerListVo repairerListVo = converMeasureRepairerUserToRepairerListVo(measureRepairerUser);
                repairerList.add(repairerListVo);
            });
            measureSquadAndRepairerVo.setRepairer_list(repairerList);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new LjBaseRuntimeException(-9999, e + "");
        }
        ljBaseResponse.setData(measureSquadAndRepairerVo);
        return ljBaseResponse;
    }

    @Override
    public LjBaseResponse<MeasureZoneResultVo> measureZoneResult(ApiMeasureZoneResultReq apiMeasureZoneResultReq) throws LjBaseRuntimeException {
        LjBaseResponse<MeasureZoneResultVo> ljBaseResponse = new LjBaseResponse<>();
        MeasureZoneResultVo measureZoneResultVo = new MeasureZoneResultVo();
        List<ResultListVo> resultList = new ArrayList<>();
        if (apiMeasureZoneResultReq.getList_ids() == null || apiMeasureZoneResultReq.getList_ids().split(",").length == 0) {
            throw new LjBaseRuntimeException(-9999, "list is empty.");
        }
        String[] listIds = apiMeasureZoneResultReq.getList_ids().split(",");
        Integer lastId = 0;
        Integer limit = 0;
        Integer start = 0;
        Integer timestamp = 0;
        try {
            for (String listId : listIds
            ) {
                MeasureList measureList = measureListService.getNoProjNoFoundErr(listId);
                List<MeasureZoneResult> items = measureZoneResultService.searchResultUnscopedByListIdLastIdUpdateAtGt(measureList.getProjectId(), listId, lastId, timestamp, limit, start);
                items.forEach(measureZoneResult -> {
                    ResultListVo resultListVo = converMeasureZoneResultToResultListVo(measureZoneResult);
                    resultList.add(resultListVo);
                });
            }
            measureZoneResultVo.setResult_list(resultList);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
        ljBaseResponse.setData(measureZoneResultVo);
        return ljBaseResponse;
    }

    @Override
    public LjBaseResponse<MeasureZoneResultVo> measureZoneResultV2(ApiMeasureZoneResultReqV2 apiMeasureZoneResultReqV2) throws LjBaseRuntimeException {
        LjBaseResponse<MeasureZoneResultVo> ljBaseResponse = new LjBaseResponse<>();
        MeasureZoneResultVo measureZoneResultVo = new MeasureZoneResultVo();
        List<ResultListVo> resultList = new ArrayList<>();
        try {
            MeasureList measureList = measureListService.getNoProjNoFoundErr(apiMeasureZoneResultReqV2.getList_id().toString());
            Integer start = 0;
            List<MeasureZoneResult> items = measureZoneResultService.searchResultUnscopedByListIdLastIdUpdateAtGt(measureList.getProjectId(), apiMeasureZoneResultReqV2.getList_id().toString(), apiMeasureZoneResultReqV2.getLast_id(), apiMeasureZoneResultReqV2.getTimestamp(), MEASUREAPIGETPERTIME, start);
            Integer newLastId = 0;
            if (!items.isEmpty()) {
                newLastId = items.get(items.size() - 1).getId();
            }
            measureZoneResultVo.setLast_id(newLastId);
            items.forEach(measureZoneResult -> {
                ResultListVo resultListVo = converMeasureZoneResultToResultListVo(measureZoneResult);
                resultList.add(resultListVo);
            });
            measureZoneResultVo.setResult_list(resultList);
        } catch (Exception e) {
            log.error(SEARCHUNSCOPEDBYPROJIDLASTIDUPDATEATGT + "[" + apiMeasureZoneResultReqV2.getList_id() + ERROR + e.getMessage());
            throw new LjBaseRuntimeException(-9999, "读取数据失败，code:zone");
        }
        ljBaseResponse.setData(measureZoneResultVo);
        return ljBaseResponse;
    }

    @Override
    public LjBaseResponse<TotalVo> measureZoneResultV2Total(ApiMeasureZoneResultTotalReqV2 apiMeasureZoneResultTotalReqV2) throws LjBaseRuntimeException {
        LjBaseResponse<TotalVo> ljBaseResponse = new LjBaseResponse<>();
        TotalVo totalVo = new TotalVo();
        try {
            MeasureList measureList = measureListService.getNoProjNoFoundErr(apiMeasureZoneResultTotalReqV2.getList_id().toString());
            Integer total = measureZoneResultService.getCountResultUnscopedByListIdLastIdUpdateAtGt(measureList.getProjectId(), apiMeasureZoneResultTotalReqV2.getList_id(), apiMeasureZoneResultTotalReqV2.getTimestamp());
            totalVo.setTotal(total);
        } catch (Exception e) {
            log.error(SEARCHUNSCOPEDBYPROJIDLASTIDUPDATEATGT + "[" + apiMeasureZoneResultTotalReqV2.getList_id() + ERROR + e.getMessage());
            throw new LjBaseRuntimeException(-9999, "读取数据失败，code:zone_total");
        }
        ljBaseResponse.setData(totalVo);
        return ljBaseResponse;
    }

    /**
     * @param measureZoneResult
     * @return
     */
    private ResultListVo converMeasureZoneResultToResultListVo(MeasureZoneResult measureZoneResult) {
        ResultListVo resultListVo = new ResultListVo();
        resultListVo.setArea_id(measureZoneResult.getAreaId());
        resultListVo.setArea_path_and_id(measureZoneResult.getAreaPathAndId());
        resultListVo.setCategory_key(measureZoneResult.getCategoryKey());
        resultListVo.setDelete_at(measureZoneResult.getDeleteAt() == null ? 0 : DateUtil.dateToTimestamp(measureZoneResult.getDeleteAt()));
        resultListVo.setId(measureZoneResult.getId());
        resultListVo.setList_id(measureZoneResult.getListId());
        resultListVo.setCategory_path_and_key(measureZoneResult.getCategoryPathAndKey());
        resultListVo.setOk_total(measureZoneResult.getOkTotal());
        resultListVo.setProject_id(measureZoneResult.getProjectId());
        resultListVo.setRegion_uuid(measureZoneResult.getRegionUuid());
        resultListVo.setZone_uuid(measureZoneResult.getZoneUuid());
        resultListVo.setUpdate_at(measureZoneResult.getUpdateAt() == null ? 0 : DateUtil.dateToTimestamp(measureZoneResult.getUpdateAt()));
        resultListVo.setTotal(measureZoneResult.getTotal());
        resultListVo.setUuid(measureZoneResult.getUuid());
        resultListVo.setSquad_id(measureZoneResult.getSquadId());
        resultListVo.setScore(measureZoneResult.getScore());
        resultListVo.setRule_id(measureZoneResult.getRuleId());
        List<TextResultVo> d = new ArrayList<>();
        String data = measureZoneResult.getData();
        if (StringUtils.isNotBlank(data)) {
            JSONArray jsonArray = JSONArray.parseArray(data);
            jsonArray.forEach(jsa -> {
                JSONObject textResult = (JSONObject) jsa;
                TextResultVo textResultVo = converDataToTextResultVo(textResult);
                d.add(textResultVo);
            });
        }
        resultListVo.setData(d);
        return resultListVo;
    }

    /**
     * @param textResult
     * @return
     */
    private TextResultVo converDataToTextResultVo(JSONObject textResult) {
        TextResultVo textResultVo = new TextResultVo();
        textResultVo.setRecorder_id(textResult.getInteger("RecorderId"));
        textResultVo.setScore(textResult.getDouble("Score"));
        textResultVo.setTexture(textResult.getString(TEXTURE));
        textResultVo.setUpdate_at(textResult.getDate(UPDATEAT) == null ? 0 : DateUtil.dateToTimestamp(textResult.getDate(UPDATEAT)));
        List<SinglePointTestVo> d = new ArrayList<>();
        String data = textResult.getString("Data");
        if (StringUtils.isNotBlank(data)) {
            JSONArray jsonArray = JSONArray.parseArray(data);
            jsonArray.forEach(jsa -> {
                JSONObject singlePointTest = (JSONObject) jsa;
                SinglePointTestVo singlePointTestVo = converDataToSinglePointTestVo(singlePointTest);
                d.add(singlePointTestVo);
            });
        }
        textResultVo.setData(d);
        return textResultVo;
    }

    /**
     * @param singlePointTest
     * @return
     */
    private SinglePointTestVo converDataToSinglePointTestVo(JSONObject singlePointTest) {
        SinglePointTestVo singlePointTestVo = new SinglePointTestVo();
        singlePointTestVo.setData_type(singlePointTest.getInteger(DATATYPE));
        singlePointTestVo.setDesign_value(singlePointTest.getDouble(DESIGNVALUE));
        singlePointTestVo.setDesign_value_reqd(singlePointTest.getBoolean(DESIGNVALUEREQD));
        singlePointTestVo.setKey(singlePointTest.getString("Key"));
        singlePointTestVo.setSeq(singlePointTest.getString("Seq"));
        singlePointTestVo.setOk_total(singlePointTest.getInteger("OkTotal"));
        singlePointTestVo.setTotal(singlePointTest.getInteger("Total"));
        List<Object> data = (List<Object>) singlePointTest.get("Data");
        if (data != null) {
            data.forEach(d ->
                    singlePointTestVo.setData(singlePointTestVo.getData() == null ? "" + "," + d : singlePointTestVo.getData() + "," + d)
            );
        }
        if (!",".equals(singlePointTestVo.getData()) && StringUtils.isNotBlank(singlePointTestVo.getData())) {
            singlePointTestVo.setData(singlePointTestVo.getData().substring(1));
        } else {
            singlePointTestVo.setData("");
        }
        List<Object> deviation = (List<Object>) singlePointTest.get("Deviation");
        if (deviation != null) {
            deviation.forEach(d ->
                    singlePointTestVo.setDeviation(singlePointTestVo.getDeviation() == null ? "" + "," + d : singlePointTestVo.getDeviation() + "," + d)
            );
        }
        if (!",".equals(singlePointTestVo.getDeviation()) && StringUtils.isNotBlank(singlePointTestVo.getDeviation())) {
            singlePointTestVo.setDeviation(singlePointTestVo.getDeviation().substring(1));
        } else {
            singlePointTestVo.setDeviation("");
        }
        return singlePointTestVo;
    }

    /**
     * @param measureRepairerUser
     * @return
     */
    private RepairerListVo converMeasureRepairerUserToRepairerListVo(MeasureRepairerUser measureRepairerUser) {
        RepairerListVo repairerListVo = new RepairerListVo();
        repairerListVo.setDelete_at(measureRepairerUser.getDeleteAt() == null ? 0 : measureRepairerUser.getDeleteAt().getTime());
        repairerListVo.setId(measureRepairerUser.getId());
        repairerListVo.setList_id(measureRepairerUser.getListId());
        repairerListVo.setProject_id(measureRepairerUser.getProjectId());
        repairerListVo.setRole_type(measureRepairerUser.getRoleType());
        repairerListVo.setUpdate_at(measureRepairerUser.getUpdateAt() == null ? 0 : measureRepairerUser.getUpdateAt().getTime());
        repairerListVo.setUser_id(measureRepairerUser.getUserId());
        return repairerListVo;
    }

    /**
     * @param measureSquadUser
     * @return
     */
    private SquadUserListVo converMeasureSquadUserToSquadUserListVo(MeasureSquadUser measureSquadUser) {
        SquadUserListVo squadUserListVo = new SquadUserListVo();
        squadUserListVo.setDelete_at(measureSquadUser.getDeleteAt() == null ? 0 : measureSquadUser.getDeleteAt().getTime());
        squadUserListVo.setId(measureSquadUser.getId());
        squadUserListVo.setList_id(measureSquadUser.getListId());
        squadUserListVo.setProject_id(measureSquadUser.getProjectId());
        squadUserListVo.setSquad_id(measureSquadUser.getSquadId());
        squadUserListVo.setUpdate_at(measureSquadUser.getUpdateAt() == null ? 0 : measureSquadUser.getUpdateAt().getTime());
        squadUserListVo.setUser_id(measureSquadUser.getUserId());
        return squadUserListVo;
    }

    /**
     * @param measureSquad
     * @return
     */
    private SquadListVo converMeasureSquadToSquadListVo(MeasureSquad measureSquad) {
        SquadListVo squadListVo = new SquadListVo();
        squadListVo.setDelete_at(measureSquad.getDeleteAt() == null ? 0 : measureSquad.getDeleteAt().getTime());
        squadListVo.setId(measureSquad.getId());
        squadListVo.setList_id(measureSquad.getListId());
        squadListVo.setName(measureSquad.getName());
        squadListVo.setPlan_rate(measureSquad.getPlanRate());
        squadListVo.setProject_id(measureSquad.getProjectId());
        squadListVo.setRate(measureSquad.getRate());
        squadListVo.setUpdate_at(measureSquad.getUpdateAt() == null ? 0 : measureSquad.getUpdateAt().getTime());
        return squadListVo;
    }

    /**
     * @param regionRel
     * @return
     */
    private RelVo converMeasureRegionRelToRegionRelListVo(MeasureRegionRel regionRel) {
        RelVo relVo = new RelVo();
        relVo.setDelete_at(regionRel.getDeleteAt() == null ? 0 : (int) (regionRel.getDeleteAt().getTime() / 1000));
        if (StringUtils.isNotBlank(regionRel.getDesc())) {
            relVo.setDesc(regionRel.getDesc());
        }
        relVo.setId(regionRel.getId());
        relVo.setProject_id(regionRel.getProjectId());
        relVo.setRegion_ids(regionRel.getRegionIds());
        relVo.setUpdate_at(regionRel.getUpdateAt() == null ? 0 : (int) (regionRel.getUpdateAt().getTime() / 1000));
        return relVo;
    }

    /**
     * 手动转换
     *
     * @param region
     * @return
     */
    private MeasureRegionListVo converMeasureRegionToMeasureRegionListVo(MeasureRegion region) {
        MeasureRegionListVo regionListVo = new MeasureRegionListVo();
        regionListVo.setArea_id(region.getAreaId());
        regionListVo.setArea_path_and_id(region.getAreaPathAndId());
        regionListVo.setDelete_at(region.getDeleteAt() == null ? 0 : (int) (region.getDeleteAt().getTime() / 1000));
        regionListVo.setDrawing_md5(region.getDrawingMd5());
        regionListVo.setId(region.getId());
        regionListVo.setProject_id(region.getProjectId());
        regionListVo.setRegion_index(region.getRegionIndex());
        regionListVo.setRel_id(region.getRelId());
        regionListVo.setSrc_type(region.getSrcType());
        if (StringUtils.isNotBlank(region.getTagIdList())) {
            regionListVo.setTag_id_list(region.getTagIdList());
        }
        regionListVo.setUpdate_at(region.getUpdateAt() == null ? 0 : (int) (region.getUpdateAt().getTime() / 1000));
        regionListVo.setUuid(region.getUuid());
        JSONObject polygon = JSON.parseObject(region.getPolygon());
        regionListVo.setPolygon(Double.parseDouble(polygon.get("X") + "") + "," + Double.parseDouble(polygon.get("Y") + ""));
        return regionListVo;
    }
}
