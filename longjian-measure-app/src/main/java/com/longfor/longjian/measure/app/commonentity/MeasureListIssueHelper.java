package com.longfor.longjian.measure.app.commonentity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.longfor.gaia.gfs.data.mybatis.toolkit.LFDataSourceContextHolder;
import com.longfor.longjian.common.exception.LjBaseRuntimeException;
import com.longfor.longjian.common.kafka.KafkaProducer;
import com.longfor.longjian.measure.app.vo.AppendVo;
import com.longfor.longjian.measure.app.vo.appmeasuresyncvo.DroppedVo;
import com.longfor.longjian.measure.consts.constant.MeasureListConstant;
import com.longfor.longjian.measure.consts.constant.MeasureListIssueType;
import com.longfor.longjian.measure.consts.enums.ApiDropDataReasonEnum;
import com.longfor.longjian.measure.consts.enums.EventQueueEnum;
import com.longfor.longjian.measure.domain.externalservice.*;
import com.longfor.longjian.measure.po.zhijian2.*;
import com.longfor.longjian.measure.util.DateTool;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.text.ParseException;
import java.util.*;

@Component
@Data
@Slf4j
@Transactional
public class MeasureListIssueHelper {
    @Autowired
    private IMeasureListIssueService measureListIssueService;
    @Autowired
    private IMeasureListService measureListService;
    @Autowired
    private IMeasureListIssueLogService measureListIssueLogService;
    @Autowired
    private IAreaService areaService;
    @Autowired
    private ICheckItemService checkItemService;
    @Autowired
    private ICategoryV3Service categoryV3Service;
    @Autowired
    private IMeasureZoneService measureZoneService;
    @Autowired
    private PlatformTransactionManager txManager;
    @Autowired
    private KafkaProducer kafkaProducer;

    private Map<String, MeasureListIssue> oldIssueMap;
    private Map<String, MeasureListIssue> needInsertIssueMap;
    private Map<String, MeasureListIssue> needUpdateIssueMap;
    private List<MeasureListIssueLog> issueLogs;
    private List<MeasureListIssueLog> needInsertIssueLog;
    private MeasureListIssueLog currentLog;
    private Integer currentProjectId;
    private Map<String, CategoryV3> categoryMap;
    private Map<Integer, Area> areaMap;
    private Map<String, MeasureZone> zoneMap;
    private Map<String, Integer> listIdAndUserIdToSquadIdMap;
    private List<DroppedVo> droppedIssueLog;
    private List<DroppedVo> droppedIssue;
    private static final String ERROR = "error:";
    /**
     * ?????????
     *
     * @param projId
     */
    public MeasureListIssueHelper init(Integer projId) {
        this.needInsertIssueMap = new HashMap<>();
        this.needUpdateIssueMap = new HashMap<>();
        this.oldIssueMap = new HashMap<>();
        this.issueLogs = new ArrayList<>();
        this.needInsertIssueLog = new ArrayList<>();

        this.categoryMap = new HashMap<>();

        this.droppedIssueLog = new ArrayList<>();
        this.droppedIssue = new ArrayList<>();
        this.start();

        this.currentProjectId = projId;
        return this;
    }

    /**
     * ???????????????????????????issue_log
     */
    public MeasureListIssueHelper start() {
        this.currentLog = new MeasureListIssueLog();
        return this;
    }

    /**
     * ??????????????????
     *
     * @param uuid
     * @param listId
     * @param issueUuid
     * @param senderId
     * @param desc
     * @param typ
     * @param status
     * @param attachmentMd5List
     * @param categoryKey
     * @param clientCreateAt
     * @return
     * @throws ParseException
     */
    public MeasureListIssueHelper setNormalField(String uuid, Integer listId, String issueUuid,
                                                 Integer senderId, String desc, Integer typ, Integer status, String attachmentMd5List,
                                                 String categoryKey, Long clientCreateAt) throws ParseException {
        this.currentLog.setUuid(uuid);
        this.currentLog.setProjectId(this.currentProjectId);
        this.currentLog.setListId(listId);
        this.currentLog.setIssueUuid(issueUuid);
        this.currentLog.setSenderId(senderId);
        this.currentLog.setDesc(desc);
        this.currentLog.setTyp(typ);
        this.currentLog.setStatus(status);
        this.currentLog.setAttachmentMd5List(attachmentMd5List);
        this.currentLog.setCategoryKey(categoryKey);
        this.currentLog.setClientCreateAt(DateTool.getDateByLong(clientCreateAt * 1000));
        return this;
    }


    /**
     * ??????detail??????
     *
     * @param zoneUuid
     * @param planEndOn
     * @param endOn
     * @param repairerId
     * @param condition
     * @param areaId
     * @param drawingMd5
     * @param posX
     * @param posY
     * @param closeStatus
     * @param closeUser
     * @param closeTime
     * @param checkStatus
     * @return
     */
    public MeasureListIssueHelper setDatailField(String zoneUuid, Long planEndOn, Long endOn, Integer repairerId,
                                                 Integer condition, Integer areaId, String drawingMd5, Integer posX, Integer posY, Integer closeStatus, Integer closeUser,
                                                 Long closeTime, Integer checkStatus) {
        MeasureListIssueLogDetail measureListIssueLogDetail = new MeasureListIssueLogDetail();
        measureListIssueLogDetail.setZoneUuid(zoneUuid);
        measureListIssueLogDetail.setPlanEndOn(planEndOn);
        measureListIssueLogDetail.setEndOn(endOn);
        measureListIssueLogDetail.setRepairerId(repairerId);
        measureListIssueLogDetail.setCondition(condition);
        measureListIssueLogDetail.setAreaId(areaId);
        measureListIssueLogDetail.setDrawingMd5(drawingMd5);
        measureListIssueLogDetail.setPosX(posX);
        measureListIssueLogDetail.setPosY(posY);
        measureListIssueLogDetail.setCloseStatus(closeStatus);
        measureListIssueLogDetail.setCloseUser(closeUser);
        measureListIssueLogDetail.setCloseTime(closeTime);
        measureListIssueLogDetail.setCheckStatus(checkStatus);
        String detail = JSONObject.toJSONString(measureListIssueLogDetail);
        this.currentLog.setDetail(detail);
        return this;
    }

    /**
     * ???????????????issue_log????????????
     *
     * @return
     */
    @SuppressWarnings("squid:S3776")
    public MeasureListIssueHelper done() {
        String issueUuid = this.currentLog.getIssueUuid();
        MeasureListIssue issue;
        boolean inNew = false;
        boolean inOld = false;
        boolean inUpdate = false;
        boolean needCreate = false;

        issue = this.needInsertIssueMap.get(issueUuid);
        inNew = issue != null;
        if (!inNew) {
            // ???????????????????????????????????????
            // ????????????????????????????????????issue?????????
            issue = this.needUpdateIssueMap.get(issueUuid);
            inUpdate = issue != null;
            if (!inUpdate) {
                // ?????????????????????????????????issue?????????
                //???????????????issueUuid???????????????issue?????????
                issue = this.oldIssueMap.get(issueUuid);
                inOld = issue != null;
                if (!inOld) {
                    boolean exists;
                    try {
                        issue = this.getIssueFromDb(issueUuid);
                        exists = issue != null;
                    } catch (Exception e) {
                        this.settingDroppedIssue(issueUuid,
                                Integer.parseInt(ApiDropDataReasonEnum.OTHER.getValue()),
                                ApiDropDataReasonEnum.OTHER.getName());
                        return this;
                    }

                    if (!exists) {
                        //??????????????????????????????????????????????????????
                        needCreate = true;
                    } else if (issue.getDeleteAt() != null) {
                        //??????????????????
                        this.settingDroppedIssue(issueUuid,
                                Integer.parseInt(ApiDropDataReasonEnum.MEASSUREISSUEWASDELETED.getValue()),
                                ApiDropDataReasonEnum.MEASSUREISSUEWASDELETED.getName());
                        return this;
                    } else {
                        //?????????????????????
                        //???????????????OldInssueMap???
                        inOld = true;
                        this.oldIssueMap.put(issueUuid, issue);
                    }
                } else {
                    //??????????????????????????????
                    //do nothing
                }
            } else {
                //?????????????????????????????????
                //do nothing
            }
        } else {
            // ?????????????????????????????????
            // do nothing
        }

        //??????????????????
        if (needCreate) {
            issue = new MeasureListIssue();
            issue.setUuid(this.currentLog.getIssueUuid());
            issue.setProjectId(this.currentProjectId);
            issue.setCategoryKey(this.currentLog.getCategoryKey());
            // ????????????
            // issue.CategoryPathAndKey = helper.getCategoryPathAndKey(helper.currentLog.CategoryKey)
            issue.setListId(this.currentLog.getListId());
            MeasureListIssueLogDetail detail = JSONObject.parseObject(this.currentLog.getDetail(), MeasureListIssueLogDetail.class);
            issue.setZoneUuid(detail.getZoneUuid());
            issue.setSenderId(this.currentLog.getSenderId());
            if (detail.getRepairerId() != -1) {
                issue.setRepairerId(detail.getRepairerId());
            }
            issue.setAreaId(detail.getAreaId());
            // ????????????
            // issue.AreaPathAndId = helper.getAreaPathAndId(helper.currentLog.Detail.AreaId)
            issue.setTyp(this.currentLog.getTyp());
            issue.setStatus(this.currentLog.getStatus());
            if (detail.getPlanEndOn() != -1) {
                issue.setPlanEndOn(Integer.parseInt(detail.getPlanEndOn() + ""));
            }else{
                issue.setPlanEndOn(0);
            }
            if (detail.getEndOn() != -1) {
                issue.setEndOn(Integer.parseInt(detail.getEndOn() + ""));
            }else{
                issue.setEndOn(0);
            }
            issue.setDrawingMd5(detail.getDrawingMd5());
            issue.setPosX(detail.getPosX());
            issue.setPosY(detail.getPosY());
            issue.setDesc(this.currentLog.getDesc());
            if (detail.getCondition() != -1) {
                issue.setCondition(detail.getCondition());
            }else{
                issue.setCondition(0);
            }
            issue.setAttachmentMd5List(this.currentLog.getAttachmentMd5List());
            issue.setLastAssigner(0);
            issue.setLastAssignerAt(0);
            issue.setDestroyUser(0);
            issue.setDestroyAt(0);
            issue.setCloseStatus(Integer.parseInt(MeasureListConstant.UNCLOSECODE));
            issue.setCloseUser(0);
            issue.setCloseTime(0);
            if (detail.getCloseStatus() != -1) {
                issue.setCloseStatus(detail.getCloseStatus());
            }
            if (detail.getCloseUser() != null && detail.getCloseUser() != -1) {
                issue.setCloseUser(detail.getCloseUser());
            }
            if (detail.getCloseTime() != null && detail.getCloseTime() > 0) {
                issue.setCloseTime(Integer.parseInt(detail.getCloseTime() + ""));
            }
            issue.setClientCreateAt(this.currentLog.getClientCreateAt());
            log.info("currentLog.getClientCreateAt:",this.currentLog.getClientCreateAt());
            //????????????????????????issue?????????
            this.needInsertIssueMap.put(issueUuid, issue);
        } else if (inOld) {
            //???????????????????????? && ???????????????????????????issue?????????
            // ???????????????
            // ??????issue?????????????????????????????????????????????????????????????????????issue??????????????????????????????????????????????????????
            // tempIssue := new(m.MeasureListIssue)

            // ?????????????????????
            // ????????????issue???????????????????????????????????????????????????????????????
            MeasureListIssue tempIssue = this.oldIssueMap.get(issueUuid);
            tempIssue = (MeasureListIssue) this.modifyIssue(tempIssue).get(0);
            //???????????????????????????????????????????????????issue?????????
            this.needUpdateIssueMap.put(issueUuid, tempIssue);
        } else if (inUpdate) {
            //????????????????????????????????????
            boolean changed;
            issue = this.needUpdateIssueMap.get(issueUuid);
            List<Object> oj = this.modifyIssue(issue);
            issue = (MeasureListIssue)(oj.get(0));
            changed = (boolean)(oj.get(1));
            if (changed) {
                this.needUpdateIssueMap.put(issueUuid, issue);
            }
        }else if (inNew){
            //????????????????????????????????????
            boolean changed;
            issue = this.needInsertIssueMap.get(issueUuid);
            List<Object> oj = this.modifyIssue(issue);
            issue = (MeasureListIssue)(oj.get(0));
            changed = (boolean)(oj.get(1));
            if (changed) {
                this.needInsertIssueMap.put(issueUuid, issue);
            }
        }

        //???log?????????insert?????????
        this.issueLogs.add(this.currentLog);
        return this;
    }

    /**
     * ?????????????????????issue
     *
     * @param issueUuid
     * @return
     */
    public MeasureListIssue getIssueFromDb(String issueUuid) {
        return measureListIssueService.getByUuidUnscoped(issueUuid);
    }

    /**
     * //???????????????uuid?????????
     *
     * @param uuid
     * @param reasonType
     * @param reason
     * @return
     */
    public MeasureListIssueHelper settingDroppedIssue(String uuid, Integer reasonType, String reason) {
        DroppedVo droppedVo = new DroppedVo();
        droppedVo.setUuid(uuid);
        droppedVo.setReason(reason);
        droppedVo.setReason_type(reasonType);
        this.getDroppedIssue().add(droppedVo);
        return this;
    }

    @SuppressWarnings("squid:S3776")
    public List<Object> modifyIssue(MeasureListIssue issue) {
        //?????????????????????????????????
        boolean changed = false;

        //????????????
        if (this.currentLog.getTyp() != null && this.currentLog.getTyp() != -1) {
            changed = true;
            issue.setTyp(this.currentLog.getTyp());
        }

        //????????????
        if (this.currentLog.getStatus() != -1) {
            changed = true;
            issue.setStatus(this.currentLog.getStatus());

            //??????issuelog???????????????????????????
            //??????issue???endon
            switch (this.currentLog.getStatus()) {
                case MeasureListIssueType.REFORMNOCHECK:
                    issue.setEndOn((int) (this.currentLog.getClientCreateAt().getTime()/1000));
                    break;
                case MeasureListIssueType.CHECKYES:
                    // ??????
                    break;
                default:
                    issue.setEndOn(0);
            }
        }
        //?????????
        MeasureListIssueLogDetail detail = JSONObject.parseObject(this.currentLog.getDetail(), MeasureListIssueLogDetail.class);
        if (detail.getRepairerId() > 0) {
            changed = true;
            issue.setRepairerId(detail.getRepairerId());
            issue.setLastAssigner(this.currentLog.getSenderId());
            issue.setLastAssignerAt((int) (this.currentLog.getClientCreateAt().getTime())/1000);
        } else if (detail.getRepairerId() == 0) {
            changed = true;
            issue.setRepairerId(0);
        }

        //??????????????????
        if (detail.getPlanEndOn() != -1) {
            changed = true;
            issue.setPlanEndOn(Integer.parseInt(detail.getPlanEndOn() + ""));
        }

        //????????????
        if (detail.getEndOn() != -1) {
            changed = true;
            issue.setEndOn(Integer.parseInt(detail.getEndOn() + ""));
        }

        //????????????
        if (detail.getCondition() != -1) {
            changed = true;
            issue.setCondition(detail.getCondition());
        }

        //??????issue
        if (detail.getCloseStatus() != -1) {
            changed = true;
            issue.setCloseStatus(detail.getCloseStatus());
            if (detail.getCloseStatus() == Integer.parseInt(MeasureListConstant.CLOSEDCODE)) {
                // ??????
                issue.setCloseUser(this.currentLog.getSenderId());
                issue.setCloseTime((int) (this.currentLog.getClientCreateAt().getTime()/1000));
            } else if (detail.getCloseStatus() == Integer.parseInt(MeasureListConstant.UNCLOSECODE)) {
                //??????
                issue.setCloseUser(0);
                issue.setCloseTime(0);
            }
        }

        //????????????
        if (detail.getCheckStatus() != -1) {
            changed = true;
            if (detail.getCheckStatus() == MeasureListConstant.CHECKYES) {
                // ???????????? status -> ??????
                issue.setStatus(MeasureListIssueType.CHECKYES);
            } else if (detail.getCheckStatus() == MeasureListConstant.CHECKNO) {
                // ??????????????? ?????? -> ?????????
                issue.setStatus(MeasureListIssueType.ASSIGNNOREFORM);
            }
        }

        issue.setUpdateAt(this.currentLog.getClientCreateAt());
        List<Object> result = new ArrayList<>();
        result.add(issue);
        result.add(changed);
        return result;
    }

    /**
     * ????????????
     */
    @SuppressWarnings("squid:S3776")
    public void execute() {
        //??????1????????????????????????????????????ZoneUuid???????????????issue???
        Set<Integer> listIds = new HashSet<>();
        for (Map.Entry<String, MeasureListIssue> entry : this.needInsertIssueMap.entrySet()) {
            listIds.add(entry.getValue().getListId());
        }
        //?????????????????? utils.DistinctIntSlice(&listIds)

        //?????????Squad_id map
        this.initSquadId(listIds);

        //?????????issue???zoneUuid
        log.debug(JSON.toJSONString(listIds));
        if (!listIds.isEmpty()) {
            //?????????????????????????????????????????????????????????
            try {
                List<String> needDropIssueUuidForUser = this.checkUserInSquad(this.needInsertIssueMap);
                log.debug(JSON.toJSONString(needDropIssueUuidForUser));
                //???????????????????????????zoneUuid?????????issue?????????
                if (!needDropIssueUuidForUser.isEmpty()) {
                    for (String tempIssueUuid : needDropIssueUuidForUser
                    ) {
                        //???????????????IssueUuid
                        this.settingDroppedIssue(
                                tempIssueUuid,
                                Integer.parseInt(ApiDropDataReasonEnum.MEASUREUSERNOTINSQUAD.getValue()),
                                ApiDropDataReasonEnum.MEASUREUSERNOTINSQUAD.getName()
                        );
                        this.needInsertIssueMap.remove(tempIssueUuid);
                    }
                }

                //??????????????????issue???ZoneUuid
                List<String> needDropIssueUuidForZone = this.checkZoneHasIssue(this.needInsertIssueMap);
                log.debug(JSON.toJSONString(needDropIssueUuidForZone));

                //???????????????????????????zoneUuid?????????issue?????????
                if (needDropIssueUuidForZone != null && !needDropIssueUuidForZone.isEmpty()) {
                    needDropIssueUuidForZone.forEach(tempIssueUuid -> {
                        //???????????????IssueUuid
                        this.settingDroppedIssue(
                                tempIssueUuid,
                                Integer.parseInt(ApiDropDataReasonEnum.MEASSUREZONEHASISSUEALREADY.getValue()),
                                ApiDropDataReasonEnum.MEASSUREZONEHASISSUEALREADY.getName()
                        );
                        this.needInsertIssueMap.remove(tempIssueUuid);
                    });
                }
            } catch (Exception e) {
                log.error(ERROR,e);
                throw e;
            }
        }

        //??????2 ??????insert???????????????area???category???Path
        List<Integer> existsAreaIds = new ArrayList<>();
        List<String> existsCategoryKeys = new ArrayList<>();
        List<String> existsZoneUuid = new ArrayList<>();
        for (Map.Entry<String, MeasureListIssue> entry : this.needInsertIssueMap.entrySet()) {
            existsAreaIds.add(entry.getValue().getAreaId());
            existsCategoryKeys.add(entry.getValue().getCategoryKey());
            existsZoneUuid.add(entry.getValue().getZoneUuid());
        }

        //???????????????????????????
        this.initArea(existsAreaIds).
                initCategory(existsCategoryKeys).
                initZone(existsZoneUuid);

        for (Map.Entry<String, MeasureListIssue> entry : this.needInsertIssueMap.entrySet()) {
            entry.getValue().setCategoryPathAndKey(this.getCategoryPathAndKey(entry.getValue().getCategoryKey()));
            entry.getValue().setAreaPathAndId(this.getAreaPathAndId(entry.getValue().getAreaId()));
            entry.getValue().setSquadId(this.getSquadId(entry.getValue().getListId(), entry.getValue().getSenderId()));
            entry.getValue().setRegionUuid(this.gettingRegionUuid(entry.getValue().getZoneUuid()));
        }

        //??????3 ??????????????????issue?????????log?????????????????????????????????
        Map<String, DroppedVo> droppedIssueUuidMap = new HashMap<>();
        this.droppedIssue.forEach(droppedVo -> droppedIssueUuidMap.put(droppedVo.getUuid(), droppedVo));

        this.needInsertIssueLog = new ArrayList<>();
        this.issueLogs.forEach(issueLog -> {
            DroppedVo droppedVo = droppedIssueUuidMap.get(issueLog.getIssueUuid());
            if (droppedVo != null) {
                //???issue???????????????????????????issue_log???
                this.settingDroppedIssueLog(
                        issueLog.getUuid(),
                        droppedVo.getReason_type(),
                        droppedVo.getReason());
                return;
            } else {
                this.needInsertIssueLog.add(issueLog);
            }
        });


        //????????????
        MeasureIssueReportMsg msgPkg = new MeasureIssueReportMsg();
        // ???????????????
        LFDataSourceContextHolder.switchTo("zhijian2");// ??????????????????????????????????????????
        DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
        TransactionStatus status = txManager.getTransaction(definition);
        try {
            for (Map.Entry<String, MeasureListIssue> entry : this.needInsertIssueMap.entrySet()) {
                MeasureListIssue issue = entry.getValue();
                measureListIssueService.insertMeasureListIssueObject(issue);
                AppendVo appendCreatedVo = new AppendVo();
                appendCreatedVo.setUuid(issue.getUuid());
                appendCreatedVo.setProjId(issue.getProjectId());
                appendCreatedVo.setListId(issue.getListId());
                appendCreatedVo.setCheckerId(issue.getSenderId());
                appendCreatedVo.setRepairerId(0);
                appendCreatedVo.setAreaId(issue.getAreaId());
                appendCreatedVo.setAreaPathAndId(issue.getAreaPathAndId());
                appendCreatedVo.setCategoryKey(issue.getCategoryKey());
                appendCreatedVo.setCategoryPathAndKey(issue.getCategoryPathAndKey());
                appendCreatedVo.setSenderId(issue.getSenderId());
                appendCreatedVo.setTimeAt(issue.getClientCreateAt());
                msgPkg.appendCreated(appendCreatedVo);
                if (issue.getStatus() == MeasureListIssueType.ASSIGNNOREFORM) {
                    AppendVo appendAssignedVo = new AppendVo();
                    appendAssignedVo.setUuid(issue.getUuid());
                    appendAssignedVo.setProjId(issue.getProjectId());
                    appendAssignedVo.setListId(issue.getListId());
                    appendAssignedVo.setCheckerId(issue.getSenderId());
                    appendAssignedVo.setRepairerId(issue.getRepairerId());
                    appendAssignedVo.setAreaId(issue.getAreaId());
                    appendAssignedVo.setAreaPathAndId(issue.getAreaPathAndId());
                    appendAssignedVo.setCategoryKey(issue.getCategoryKey());
                    appendAssignedVo.setCategoryPathAndKey(issue.getCategoryPathAndKey());
                    appendAssignedVo.setSenderId(issue.getSenderId());
                    appendAssignedVo.setTimeAt(issue.getClientCreateAt());
                    msgPkg.appendAssigned(appendAssignedVo);
                }
            }
            //?????????issue
            for (Map.Entry<String, MeasureListIssue> entry : this.needUpdateIssueMap.entrySet()) {
                MeasureListIssue issue = entry.getValue();
                measureListIssueService.updateFullNoAffectedErr(issue);
                switch (issue.getStatus()) {
                    case MeasureListIssueType.ASSIGNNOREFORM:
                        AppendVo appendAssignedVo = new AppendVo();
                        appendAssignedVo.setUuid(issue.getUuid());
                        appendAssignedVo.setProjId(issue.getProjectId());
                        appendAssignedVo.setListId(issue.getListId());
                        appendAssignedVo.setCheckerId(issue.getSenderId());
                        appendAssignedVo.setRepairerId(issue.getRepairerId());
                        appendAssignedVo.setAreaId(issue.getAreaId());
                        appendAssignedVo.setAreaPathAndId(issue.getAreaPathAndId());
                        appendAssignedVo.setCategoryKey(issue.getCategoryKey());
                        appendAssignedVo.setCategoryPathAndKey(issue.getCategoryPathAndKey());
                        appendAssignedVo.setSenderId(issue.getSenderId());
                        appendAssignedVo.setTimeAt(issue.getClientCreateAt());
                        msgPkg.appendAssigned(appendAssignedVo);
                        break;
                    case MeasureListIssueType.REFORMNOCHECK:
                        AppendVo appendReformedVo = new AppendVo();
                        appendReformedVo.setUuid(issue.getUuid());
                        appendReformedVo.setProjId(issue.getProjectId());
                        appendReformedVo.setListId(issue.getListId());
                        appendReformedVo.setCheckerId(issue.getSenderId());
                        appendReformedVo.setRepairerId(issue.getRepairerId());
                        appendReformedVo.setAreaId(issue.getAreaId());
                        appendReformedVo.setAreaPathAndId(issue.getAreaPathAndId());
                        appendReformedVo.setCategoryKey(issue.getCategoryKey());
                        appendReformedVo.setCategoryPathAndKey(issue.getCategoryPathAndKey());
                        appendReformedVo.setSenderId(issue.getRepairerId());
                        appendReformedVo.setTimeAt(issue.getClientCreateAt());
                        msgPkg.appendReformed(appendReformedVo);
                        break;
                    case MeasureListIssueType.CHECKYES:
                        AppendVo appendCheckedVo = new AppendVo();
                        appendCheckedVo.setUuid(issue.getUuid());
                        appendCheckedVo.setProjId(issue.getProjectId());
                        appendCheckedVo.setListId(issue.getListId());
                        appendCheckedVo.setCheckerId(issue.getSenderId());
                        appendCheckedVo.setRepairerId(issue.getRepairerId());
                        appendCheckedVo.setAreaId(issue.getAreaId());
                        appendCheckedVo.setAreaPathAndId(issue.getAreaPathAndId());
                        appendCheckedVo.setCategoryKey(issue.getCategoryKey());
                        appendCheckedVo.setCategoryPathAndKey(issue.getCategoryPathAndKey());
                        appendCheckedVo.setSenderId(issue.getDestroyUser());
                        appendCheckedVo.setTimeAt(issue.getClientCreateAt());
                        msgPkg.appendChecked(appendCheckedVo);
                        break;
                    default:
                        log.info("???????????? :{}",issue.getStatus());
                }
            }
            //issue log
            int affected;
            if (!needInsertIssueLog.isEmpty()) {
                affected = measureListIssueLogService.insertObjects(this.needInsertIssueLog);
                if (this.needInsertIssueLog.size() != affected) {
                    String msg = "insert affected not match, len[" + this.needInsertIssueLog.size() + "] affected[" + affected + "]";
                    log.warn(msg);
                    throw new LjBaseRuntimeException(-9999,msg);
                }
            }
            txManager.commit(status);
        } catch (Exception e) {
            txManager.rollback(status);
            log.warn("????????????", e.getMessage());
            log.error(ERROR,e);
        } finally {
            LFDataSourceContextHolder.clear();
        }

        //????????????
        kafkaProducer.produce(EventQueueEnum.PKG_MEASURE_ISSUE_REPORTED.getValue(), msgPkg);

    }

    /**
     * ???????????????issue_log uuid?????????
     *
     * @param uuid
     * @param reasonType
     * @param reason
     * @return
     */
    public MeasureListIssueHelper settingDroppedIssueLog(String uuid, Integer reasonType, String reason) {
        DroppedVo droppedVo = new DroppedVo();
        droppedVo.setUuid(uuid);
        droppedVo.setReason_type(reasonType);
        droppedVo.setReason(reason);
        this.droppedIssueLog.add(droppedVo);
        return this;
    }

    public String gettingRegionUuid(String zoneUuid) {
        MeasureZone zone = this.zoneMap.get(zoneUuid);
        if (zone == null) {
            return "";
        }
        return zone.getRegionUuid();
    }

    private String getAreaPathAndId(Integer areaId) {
        Area area = this.areaMap.get(areaId);
        if (area == null) {
            return "";
        }
        return area.getPath() + area.getId() + "/";
    }

    private String getCategoryPathAndKey(String categoryKey) {
        CategoryV3 category = this.categoryMap.get(categoryKey);
        if (category == null) {
            return "";
        }
        return category.getPath() + category.getKey() + "/";
    }

    /**
     * ?????????????????????squad_id??????
     *
     * @param listIds
     * @return
     */
    public MeasureListIssueHelper initSquadId(Set<Integer> listIds) {
        if (listIds.isEmpty()) {
            return this;
        }
        this.listIdAndUserIdToSquadIdMap = new HashMap<>();
        try {
            List<MeasureSquadUser> squadUsers = this.getSquadUsers(listIds);
            squadUsers.forEach(measureSquadUser -> {
                String key = measureSquadUser.getListId() + "-" + measureSquadUser.getUserId();
                this.listIdAndUserIdToSquadIdMap.put(key, measureSquadUser.getSquadId());
            });
        } catch (Exception e) {
            log.warn("helper init squadId error: " + e.getMessage());
            return this;
        }
        return this;
    }

    /**
     * ????????????ListIds?????????????????????
     *
     * @param listIds
     * @return
     */
    public List<MeasureSquadUser> getSquadUsers(Set<Integer> listIds) {
        return measureListService.searchMeasureSquadUserByListIds(this.currentProjectId, listIds);
    }

    /**
     * //?????????????????????????????????????????????????????????
     *
     * @param needInsertIssueMap
     * @return
     */
    public List<String> checkUserInSquad(Map<String, MeasureListIssue> needInsertIssueMap) {
        List<String> needDropIssueUuid = new ArrayList<>();
        for (Map.Entry<String, MeasureListIssue> entry : needInsertIssueMap.entrySet()) {
            int squadId = this.getSquadId(entry.getValue().getListId(), entry.getValue().getSenderId());
            if (squadId <= 0) {
                log.debug(squadId + "");
                needDropIssueUuid.add(entry.getValue().getUuid());
            }
        }
        return needDropIssueUuid;
    }

    /**
     * @param listId
     * @param userId
     * @return
     */
    public int getSquadId(int listId, int userId) {
        String key = listId + "-" + userId;
        Integer squadId = this.listIdAndUserIdToSquadIdMap.get(key);
        if (squadId == null || squadId == 0) {
            return 0;
        }
        return squadId;
    }


    /**
     * ??????????????????issue???ZoneUuid
     * ??????zoneUuid?????????squad_id???????????????issue
     *
     * @return
     */
    public List<String> checkZoneHasIssue(Map<String, MeasureListIssue> needInsertIssueMap) {
        List<String> needDropIssueUuid = new ArrayList<>();
        //?????????????????????????????????????????????
        //????????????????????????issue
        Set<String> zoneUuids = new HashSet<>();
        for (Map.Entry<String, MeasureListIssue> entry : needInsertIssueMap.entrySet()) {
            zoneUuids.add(entry.getValue().getZoneUuid());
        }
        if (zoneUuids.isEmpty()) {
            return new ArrayList<>();
        }
        //????????????????????? utils.DistinctStringSlice(&zoneUuids)
        try {
            List<MeasureListIssue> issuesInDb = measureListIssueService.searchMeasueListIssueInZoneUuids(zoneUuids);
            if (issuesInDb.isEmpty()) {
                return new ArrayList<>();
            }

            Map<String, Boolean> existsZoneUuidSquaIdMap = new HashMap<>();
            issuesInDb.forEach(tempIssue -> {
                String key = tempIssue.getZoneUuid() + "-" + tempIssue.getSquadId();
                existsZoneUuidSquaIdMap.put(key, true);
            });

            for (Map.Entry<String, MeasureListIssue> entry : needInsertIssueMap.entrySet()) {
                int squadId = this.getSquadId(entry.getValue().getListId(), entry.getValue().getSenderId());
                if (squadId <= 0) {
                    //??????????????????????????????
                    continue;
                }
                String key = entry.getValue().getZoneUuid() + "-" + entry.getValue().getSquadId();
                if (existsZoneUuidSquaIdMap.get(key) != null) {
                    needDropIssueUuid.add(entry.getValue().getUuid());
                }
            }
        } catch (Exception e) {
            log.error(ERROR,e);
            throw e;
        }
        return needDropIssueUuid;
    }

    /**
     * ?????????????????????area??????
     *
     * @param areaIds
     * @return
     */
    public MeasureListIssueHelper initArea(List<Integer> areaIds) {
        if (areaIds.isEmpty()) {
            return this;
        }
        this.areaMap = new HashMap<>();
        try {
            List<Area> areas = areaService.getAreaByIds(areaIds);
            areas.forEach(area -> this.areaMap.put(area.getId(), area));
        } catch (Exception e) {
            log.warn("helper init area error: " + e.getMessage());
            return this;
        }
        return this;
    }

    /**
     * ?????????????????????category??????
     *
     * @param categoryKeys
     * @return
     */
    public MeasureListIssueHelper initCategory(List<String> categoryKeys) {
        if (categoryKeys.isEmpty()) {
            return this;
        }
        this.categoryMap = new HashMap<>();
        try {
            List<CategoryV3> categorys = categoryV3Service.getCategoryByKeys(categoryKeys);
            categorys.forEach(categoryV3 -> this.categoryMap.put(categoryV3.getKey(), categoryV3));
        } catch (Exception e) {
            log.warn("helper init category error: " + e.getMessage());
            return this;
        }
        return this;
    }

    /**
     * ?????????????????????Region??????
     *
     * @param zoneUuids
     * @return
     */
    public MeasureListIssueHelper initZone(List<String> zoneUuids) {
        if (zoneUuids.isEmpty()) {
            return this;
        }
        this.zoneMap = new HashMap<>();
        try {
            List<MeasureZone> zones = measureZoneService.searchZoneByUuid(this.currentProjectId, new HashSet<>(zoneUuids));
            zones.forEach(zone -> this.zoneMap.put(zone.getUuid(), zone));
        } catch (Exception e) {
            log.warn("helper init Zone error: " + e.getMessage());
            return this;
        }
        return this;
    }

}
