package com.longfor.longjian.measure.app.commonentity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.longfor.gaia.gfs.data.mybatis.toolkit.LFDataSourceContextHolder;
import com.longfor.longjian.common.exception.LjBaseRuntimeException;
import com.longfor.longjian.common.kafka.KafkaProducer;
import com.longfor.longjian.measure.app.vo.AppendVo;
import com.longfor.longjian.measure.app.vo.appmeasuresyncvo.DroppedVo;
import com.longfor.longjian.measure.consts.enums.ApiDropDataReasonEnum;
import com.longfor.longjian.measure.consts.enums.EventQueueEnum;
import com.longfor.longjian.measure.consts.constant.MeasureListConstant;
import com.longfor.longjian.measure.consts.constant.MeasureListIssueType;
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
    /**
     * 初始化
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
     * 重新开始录入一个新issue_log
     */
    public MeasureListIssueHelper start() {
        this.currentLog = new MeasureListIssueLog();
        return this;
    }

    /**
     * 设置普通字段
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
     * 设置detail字段
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
     * 录入完一个issue_log后的处理
     *
     * @return
     */
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
            // 如果不存在于新上报的列表中
            // 看是否已存在于需要更新的issue列表中
            issue = this.needUpdateIssueMap.get(issueUuid);
            inUpdate = issue != null;
            if (!inUpdate) {
                // 如果不存在于需要更新的issue列表中
                //尝试将根据issueUuid在数据库将issue读出来
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
                        //如果也不存在于数据库中，则要创建新的
                        needCreate = true;
                    } else if (issue.getDeleteAt() != null) {
                        //已经被删除了
                        this.settingDroppedIssue(issueUuid,
                                Integer.parseInt(ApiDropDataReasonEnum.MEASSUREISSUEWASDELETED.getValue()),
                                ApiDropDataReasonEnum.MEASSUREISSUEWASDELETED.getName());
                        return this;
                    } else {
                        //存在于数据库中
                        //则加它加到OldInssueMap中
                        inOld = true;
                        this.oldIssueMap.put(issueUuid, issue);
                    }
                } else {
                    //已经从数据库中读取过
                    //do nothing
                }
            } else {
                //存在于需要更新的列表中
                //do nothing
            }
        } else {
            // 存在于需要创建的列表中
            // do nothing
        }

        //需要新创建的
        if (needCreate) {
            issue = new MeasureListIssue();
            issue.setUuid(this.currentLog.getIssueUuid());
            issue.setProjectId(this.currentProjectId);
            issue.setCategoryKey(this.currentLog.getCategoryKey());
            // 路径后补
            // issue.CategoryPathAndKey = helper.getCategoryPathAndKey(helper.currentLog.CategoryKey)
            issue.setListId(this.currentLog.getListId());
            MeasureListIssueLogDetail detail = JSONObject.parseObject(this.currentLog.getDetail(), MeasureListIssueLogDetail.class);
            issue.setZoneUuid(detail.getZoneUuid());
            issue.setSenderId(this.currentLog.getSenderId());
            if (detail.getRepairerId() != -1) {
                issue.setRepairerId(detail.getRepairerId());
            }
            issue.setAreaId(detail.getAreaId());
            // 路径后补
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
            //添加到需要创建的issue列表中
            this.needInsertIssueMap.put(issueUuid, issue);
        } else if (inOld) {
            //已存在于数据库中 && 未存在于需要更新的issue列表中
            // 完备方案：
            // 新建issue，可以只更新有变动字段，但需要记录具体的每一个issue变动了哪一些字段，工作量大，暂时舍弃
            // tempIssue := new(m.MeasureListIssue)

            // 此为拆中方案：
            // 直接使用issue中读取的数据，在高并发情况下，可能会有问题
            MeasureListIssue tempIssue = this.oldIssueMap.get(issueUuid);
            tempIssue = (MeasureListIssue) this.modifyIssue(tempIssue).get(0);
            //不判断是否被修改，必须要需要更新的issue列表中
            this.needUpdateIssueMap.put(issueUuid, tempIssue);
        } else if (inUpdate) {
            //已存在于需要更新的列表中
            boolean changed;
            issue = this.needUpdateIssueMap.get(issueUuid);
            List<Object> oj = this.modifyIssue(issue);
            issue = (MeasureListIssue)(oj.get(0));
            changed = (boolean)(oj.get(1));
            if (changed) {
                this.needUpdateIssueMap.put(issueUuid, issue);
            }
        }else if (inNew){
            //已存在于需要新增的列表中
            boolean changed;
            issue = this.needInsertIssueMap.get(issueUuid);
            List<Object> oj = this.modifyIssue(issue);
            issue = (MeasureListIssue)(oj.get(0));
            changed = (boolean)(oj.get(1));
            if (changed) {
                this.needInsertIssueMap.put(issueUuid, issue);
            }
        }

        //将log加入到insert列表中
        this.issueLogs.add(this.currentLog);
        return this;
    }

    /**
     * 从数据库中读出issue
     *
     * @param issueUuid
     * @return
     */
    public MeasureListIssue getIssueFromDb(String issueUuid) {
        MeasureListIssue issue = measureListIssueService.getByUuidUnscoped(issueUuid);
        return issue;
    }

    /**
     * //设置舍弃的uuid及原因
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

    public List<Object> modifyIssue(MeasureListIssue issue) {
        //仅修改允许被修改的字段
        boolean changed = false;

        //问题类型
        if (this.currentLog.getTyp() != null && this.currentLog.getTyp() != -1) {
            changed = true;
            issue.setTyp(this.currentLog.getTyp());
        }

        //问题状态
        if (this.currentLog.getStatus() != -1) {
            changed = true;
            issue.setStatus(this.currentLog.getStatus());

            //如果issuelog的状态为整改已完成
            //修改issue的endon
            switch (this.currentLog.getStatus()) {
                case MeasureListIssueType.REFORMNOCHECK:
                    issue.setEndOn((int) (this.currentLog.getClientCreateAt().getTime()/1000));
                    break;
                case MeasureListIssueType.CHECKYES:
                    // 不变
                    break;
                default:
                    issue.setEndOn(0);
            }
        }
        //整改人
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

        //计划结束时间
        if (detail.getPlanEndOn() != -1) {
            changed = true;
            issue.setPlanEndOn(Integer.parseInt(detail.getPlanEndOn() + ""));
        }

        //结束时间
        if (detail.getEndOn() != -1) {
            changed = true;
            issue.setEndOn(Integer.parseInt(detail.getEndOn() + ""));
        }

        //严重程度
        if (detail.getCondition() != -1) {
            changed = true;
            issue.setCondition(detail.getCondition());
        }

        //关闭issue
        if (detail.getCloseStatus() != -1) {
            changed = true;
            issue.setCloseStatus(detail.getCloseStatus());
            if (detail.getCloseStatus() == Integer.parseInt(MeasureListConstant.CLOSEDCODE)) {
                // 关闭
                issue.setCloseUser(this.currentLog.getSenderId());
                issue.setCloseTime((int) (this.currentLog.getClientCreateAt().getTime()/1000));
            } else if (detail.getCloseStatus() == Integer.parseInt(MeasureListConstant.UNCLOSECODE)) {
                //打开
                issue.setCloseUser(0);
                issue.setCloseTime(0);
            }
        }

        //审核状态
        if (detail.getCheckStatus() != -1) {
            changed = true;
            if (detail.getCheckStatus() == MeasureListConstant.CHECKYES) {
                // 审核通过 status -> 消项
                issue.setStatus(MeasureListIssueType.CHECKYES);
            } else if (detail.getCheckStatus() == MeasureListConstant.CHECKNO) {
                // 审核不通过 状态 -> 未整改
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
     * 执行操作
     */
    public void execute() {
        //处理1：看新建列表里面，有没有ZoneUuid已经是存在issue的
        Set<Integer> listIds = new HashSet<>();
        for (Map.Entry<String, MeasureListIssue> entry : this.needInsertIssueMap.entrySet()) {
            listIds.add(entry.getValue().getListId());
        }
        //不需要去重了 utils.DistinctIntSlice(&listIds)

        //初始化Squad_id map
        this.initSquadId(listIds);

        //已经有issue的zoneUuid
        log.debug(JSON.toJSONString(listIds));
        if (!listIds.isEmpty()) {
            //检查提交的人员是否合法（在测量小组中）
            try {
                List<String> needDropIssueUuidForUser = this.checkUserInSquad(this.needInsertIssueMap);
                log.debug(JSON.toJSONString(needDropIssueUuidForUser));
                //剔除新建列表中，其zoneUuid已经有issue的数据
                if (!needDropIssueUuidForUser.isEmpty()) {
                    for (String tempIssueUuid : needDropIssueUuidForUser
                    ) {
                        //记录丢弃的IssueUuid
                        this.settingDroppedIssue(
                                tempIssueUuid,
                                Integer.parseInt(ApiDropDataReasonEnum.MEASUREUSERNOTINSQUAD.getValue()),
                                ApiDropDataReasonEnum.MEASUREUSERNOTINSQUAD.getName()
                        );
                        this.needInsertIssueMap.remove(tempIssueUuid);
                    }
                }

                //判断已经存在issue的ZoneUuid
                List<String> needDropIssueUuidForZone = this.checkZoneHasIssue(this.needInsertIssueMap);
                log.debug(JSON.toJSONString(needDropIssueUuidForZone));

                //剔除新建列表中，其zoneUuid已经有issue的数据
                if (needDropIssueUuidForZone != null && !needDropIssueUuidForZone.isEmpty()) {
                    needDropIssueUuidForZone.forEach(tempIssueUuid -> {
                        //记录丢弃的IssueUuid
                        this.settingDroppedIssue(
                                tempIssueUuid,
                                Integer.parseInt(ApiDropDataReasonEnum.MEASSUREZONEHASISSUEALREADY.getValue()),
                                ApiDropDataReasonEnum.MEASSUREZONEHASISSUEALREADY.getName()
                        );
                        this.needInsertIssueMap.remove(tempIssueUuid);
                    });
                }
            } catch (Exception e) {
                log.error("error:",e);
                throw e;
            }
        }

        //处理2 为新insert的列表补全area和category的Path
        List<Integer> existsAreaIds = new ArrayList<>();
        List<String> existsCategoryKeys = new ArrayList<>();
        List<String> existsZoneUuid = new ArrayList<>();
        for (Map.Entry<String, MeasureListIssue> entry : this.needInsertIssueMap.entrySet()) {
            existsAreaIds.add(entry.getValue().getAreaId());
            existsCategoryKeys.add(entry.getValue().getCategoryKey());
            existsZoneUuid.add(entry.getValue().getZoneUuid());
        }

        //初始化好所有的数据
        this.initArea(existsAreaIds).
                initCategory(existsCategoryKeys).
                initZone(existsZoneUuid);

        for (Map.Entry<String, MeasureListIssue> entry : this.needInsertIssueMap.entrySet()) {
            entry.getValue().setCategoryPathAndKey(this.getCategoryPathAndKey(entry.getValue().getCategoryKey()));
            entry.getValue().setAreaPathAndId(this.getAreaPathAndId(entry.getValue().getAreaId()));
            entry.getValue().setSquadId(this.getSquadId(entry.getValue().getListId(), entry.getValue().getSenderId()));
            entry.getValue().setRegionUuid(this.gettingRegionUuid(entry.getValue().getZoneUuid()));
        }

        //处理3 找到被舍弃的issue对应的log，舍弃并返回告知客户端
        Map<String, DroppedVo> droppedIssueUuidMap = new HashMap<>();
        this.droppedIssue.forEach(droppedVo -> droppedIssueUuidMap.put(droppedVo.getUuid(), droppedVo));

        this.needInsertIssueLog = new ArrayList<>();
        this.issueLogs.forEach(issueLog -> {
            DroppedVo droppedVo = droppedIssueUuidMap.get(issueLog.getIssueUuid());
            if (droppedVo != null) {
                //将issue中抛弃的原因，放到issue_log中
                this.settingDroppedIssueLog(
                        issueLog.getUuid(),
                        droppedVo.getReason_type(),
                        droppedVo.getReason());
                return;
            } else {
                this.needInsertIssueLog.add(issueLog);
            }
        });


        //执行入库
        MeasureIssueReportMsg msgPkg = new MeasureIssueReportMsg();
        // 自定義事務
        LFDataSourceContextHolder.switchTo("zhijian2");// 需先切换数据源再生成事物属性
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
            //更新的issue
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
                        appendReformedVo.setSenderId(issue.getSenderId());
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
                        appendCheckedVo.setSenderId(issue.getSenderId());
                        appendCheckedVo.setTimeAt(issue.getClientCreateAt());
                        msgPkg.appendChecked(appendCheckedVo);
                        break;
                    default:
                        log.info("当前状态 :{}",issue.getStatus());
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
            log.warn("執行失敗", e.getMessage());
            log.error("error:",e);
        } finally {
            LFDataSourceContextHolder.clear();
        }

        //执行推送
        kafkaProducer.produce(EventQueueEnum.PKG_MEASURE_ISSUE_REPORTED.getValue(), msgPkg);

    }

    /**
     * 设置舍弃的issue_log uuid及原因
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
     * 初始化所需要的squad_id信息
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
     * 读出指定ListIds的检查小组信息
     *
     * @param listIds
     * @return
     */
    public List<MeasureSquadUser> getSquadUsers(Set<Integer> listIds) {
        List<MeasureSquadUser> squadUsers = measureListService.searchMeasureSquadUserByListIds(this.currentProjectId, listIds);
        return squadUsers;
    }

    /**
     * //检查提交的人员是否合法（在测量小组中）
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
     * 判断已经存在issue的ZoneUuid
     * 一个zoneUuid，一个squad_id只能有一个issue
     *
     * @return
     */
    public List<String> checkZoneHasIssue(Map<String, MeasureListIssue> needInsertIssueMap) {
        List<String> needDropIssueUuid = new ArrayList<>();
        //判断人员是否在组中，不在就舍弃
        //判断是否已经存在issue
        Set<String> zoneUuids = new HashSet<>();
        for (Map.Entry<String, MeasureListIssue> entry : needInsertIssueMap.entrySet()) {
            zoneUuids.add(entry.getValue().getZoneUuid());
        }
        if (zoneUuids.isEmpty()) {
            return new ArrayList<>();
        }
        //去重，不用做了 utils.DistinctStringSlice(&zoneUuids)
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
                    //前一步上面已经处理了
                    continue;
                }
                String key = entry.getValue().getZoneUuid() + "-" + entry.getValue().getSquadId();
                if (existsZoneUuidSquaIdMap.get(key) != null) {
                    needDropIssueUuid.add(entry.getValue().getUuid());
                }
            }
        } catch (Exception e) {
            log.error("error:",e);
            throw e;
        }
        return needDropIssueUuid;
    }

    /**
     * 初始化所需要的area信息
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
     * 初始化所需要的category信息
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
     * 初始化所需要的Region信息
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
