package com.longfor.longjian.measure.app.appservice.promeasurequicksearchservice.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.longfor.longjian.measure.app.appservice.promeasurequicksearchservice.IProMeasureListIssueLogService;
import com.longfor.longjian.measure.app.commonentity.MeasureListIssueLogDetail;
import com.longfor.longjian.measure.app.vo.promeasurequicksearchvo.MeasureListIssueHistoryRepairLogItemVo;
import com.longfor.longjian.measure.app.vo.promeasurequicksearchvo.MeasureListIssueHistoryRepairLogVo;
import com.longfor.longjian.measure.consts.constant.MeasureListIssueType;
import com.longfor.longjian.measure.consts.enums.MeasureListCloseStatusEnum;
import com.longfor.longjian.measure.consts.enums.MeasureListIssueActionLogTypeEnum;
import com.longfor.longjian.measure.consts.enums.MeasureListIssueCheckStatusEnum;
import com.longfor.longjian.measure.domain.externalservice.IMeasureListIssueLogService;
import com.longfor.longjian.measure.domain.externalservice.IUserService;
import com.longfor.longjian.measure.po.zhijian2.MeasureListIssueLog;
import com.longfor.longjian.measure.po.zhijian2_apisvr.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProMeasureListIssueLogServiceImpl implements IProMeasureListIssueLogService {

    @Resource
    private IMeasureListIssueLogService measureListIssueLogService;
    @Resource
    private IUserService userService;

    @Override
    @SuppressWarnings("squid:S3776")
    public List<MeasureListIssueHistoryRepairLogVo> getIssueActionLogByIssueUuid(Integer projectId, String uuid) {
        List<MeasureListIssueHistoryRepairLogVo> items = new LinkedList<>();

        List<MeasureListIssueLog> logs = measureListIssueLogService.searchByIssueUuid(projectId, uuid);

        if (logs == null || logs.isEmpty()) {
            return new ArrayList<>();
        }

        List<Integer> uids = new ArrayList<>();
        logs.forEach(l -> {
            uids.add(l.getSenderId());
            MeasureListIssueLogDetail detail = JSONObject.parseObject(l.getDetail(), MeasureListIssueLogDetail.class);
            log.info(JSON.toJSONString(detail));
            if (detail.getRepairerId() != null && detail.getRepairerId() > 0) {
                uids.add(detail.getRepairerId());
            }
        });

        List<Integer> collect = uids.stream().distinct().collect(Collectors.toList());
        Map<Integer, User> userMap = userService.getUsersByIds(collect);
        //???????????????????????????log
        boolean hasCreateLog = false;

        for (MeasureListIssueLog measureListIssueLog : logs) {
            log.info(JSON.toJSONString(measureListIssueLog));
            MeasureListIssueLogDetail detail = JSONObject.parseObject(measureListIssueLog.getDetail(), MeasureListIssueLogDetail.class);
            MeasureListIssueHistoryRepairLogVo item = new MeasureListIssueHistoryRepairLogVo();
            item.setUser_id(measureListIssueLog.getSenderId());
            if (userMap.get(measureListIssueLog.getSenderId()) != null) {
                item.setUser_name(userMap.get(measureListIssueLog.getSenderId()).getRealName());
            }
            item.setCreate_at(Integer.parseInt((measureListIssueLog.getCreateAt().getTime()) / 1000 + ""));

            //??????????????????
            if (MeasureListCloseStatusEnum.CLOSED.getId().equals(detail.getCloseStatus())) {
                MeasureListIssueHistoryRepairLogItemVo l = new MeasureListIssueHistoryRepairLogItemVo();
                l.setLog_type(MeasureListIssueActionLogTypeEnum.CLOSE.getId());
                item.getItems().add(l);
            } else if (MeasureListCloseStatusEnum.UNCLOSE.getId().equals(detail.getCloseStatus())) {
                MeasureListIssueHistoryRepairLogItemVo l = new MeasureListIssueHistoryRepairLogItemVo();
                l.setLog_type(MeasureListIssueActionLogTypeEnum.OPEN.getId());
                item.getItems().add(l);
            }

            //??????????????????
            log.info(measureListIssueLog.getStatus().toString());
            if (measureListIssueLog.getStatus() == MeasureListIssueType.NOTENOASSIGN) {
                //????????????
                hasCreateLog = true;
                MeasureListIssueHistoryRepairLogItemVo l = new MeasureListIssueHistoryRepairLogItemVo();
                l.setLog_type(MeasureListIssueActionLogTypeEnum.CREATE.getId());
                item.getItems().add(0, l);
            } else if (measureListIssueLog.getStatus() == MeasureListIssueType.ASSIGNNOREFORM) {
                // ????????????????????????????????????????????????
                if (MeasureListIssueCheckStatusEnum.CHECKNO.getId().equals(detail.getCheckStatus())) {
                    //????????????
                    MeasureListIssueHistoryRepairLogItemVo l = new MeasureListIssueHistoryRepairLogItemVo();
                    l.setLog_type(MeasureListIssueActionLogTypeEnum.UNAPPROVE.getId());
                    l.setData(measureListIssueLog.getDesc());
                    item.getItems().add(l);
                } else {
                    MeasureListIssueHistoryRepairLogItemVo l = new MeasureListIssueHistoryRepairLogItemVo();
                    l.setLog_type(MeasureListIssueActionLogTypeEnum.ASSIGN.getId());
                    l.setTarget_user_id(detail.getRepairerId());
                    if (userMap.get(l.getTarget_user_id()) != null) {
                        l.setTarget_user_name(userMap.get(l.getTarget_user_id()).getRealName());
                    }
                    l.setData(detail.getPlanEndOn() != null && detail.getPlanEndOn() > 0 ? detail.getPlanEndOn() + "" : "-1");
                    item.getItems().add(l);
                }
            } else if (measureListIssueLog.getStatus() == MeasureListIssueType.REFORMNOCHECK) {
                //??????????????????
                MeasureListIssueHistoryRepairLogItemVo l = new MeasureListIssueHistoryRepairLogItemVo();
                l.setLog_type(MeasureListIssueActionLogTypeEnum.REPAIR.getId());
                item.getItems().add(l);
            } else if (measureListIssueLog.getStatus() == MeasureListIssueType.CHECKYES) {
                MeasureListIssueHistoryRepairLogItemVo l = new MeasureListIssueHistoryRepairLogItemVo();
                l.setLog_type(MeasureListIssueActionLogTypeEnum.APPROVE.getId());
                item.getItems().add(l);
            } else {
                if ((detail.getPlanEndOn() != null && detail.getPlanEndOn() != -1) || detail.getRepairerId() > 0) {
                    MeasureListIssueHistoryRepairLogItemVo l = new MeasureListIssueHistoryRepairLogItemVo();
                    l.setLog_type(MeasureListIssueActionLogTypeEnum.ASSIGN.getId());
                    l.setTarget_user_id(detail.getRepairerId());
                    if (userMap.get(l.getTarget_user_id()) != null) {
                        l.setTarget_user_name(userMap.get(l.getTarget_user_id()).getRealName());
                    }
                    l.setData(detail.getPlanEndOn() != null && detail.getPlanEndOn() > 0 ? detail.getPlanEndOn() + "" : "-1");
                    item.getItems().add(l);
                }
                if (StringUtils.isNotBlank(measureListIssueLog.getDesc())) {
                    MeasureListIssueHistoryRepairLogItemVo l = new MeasureListIssueHistoryRepairLogItemVo();
                    l.setLog_type(MeasureListIssueActionLogTypeEnum.ADDDESC.getId());
                    item.getItems().add(l);
                }
                if (StringUtils.isNotBlank(measureListIssueLog.getAttachmentMd5List())) {
                    MeasureListIssueHistoryRepairLogItemVo l = new MeasureListIssueHistoryRepairLogItemVo();
                    l.setLog_type(MeasureListIssueActionLogTypeEnum.ADDATTACHMENT.getId());
                    item.getItems().add(l);
                }
            }

            //????????????????????????
            if (MeasureListIssueType.REPAIRABLE == measureListIssueLog.getTyp() || MeasureListIssueType.NOREPAIRABLE == measureListIssueLog.getTyp()) {
                MeasureListIssueHistoryRepairLogItemVo l = new MeasureListIssueHistoryRepairLogItemVo();
                l.setLog_type(MeasureListIssueActionLogTypeEnum.CHANGETYPE.getId());
                l.setData(measureListIssueLog.getTyp() + "");
                item.getItems().add(l);
            }

            if (item.getItems().size() > 0) {
                items.add(item);
            }
        }

        if (!hasCreateLog) {
            //??????log?????????????????????????????????????????????????????????????????????log??????
            MeasureListIssueHistoryRepairLogVo item = new MeasureListIssueHistoryRepairLogVo();
            MeasureListIssueHistoryRepairLogVo m = items.get(0);
            item.setUser_id(m.getUser_id());
            item.setUser_name(m.getUser_name());
            item.setCreate_at(m.getCreate_at());
            MeasureListIssueHistoryRepairLogItemVo l = new MeasureListIssueHistoryRepairLogItemVo();
            l.setLog_type(MeasureListIssueActionLogTypeEnum.CREATE.getId());
            item.getItems().add(l);
            items.add(0, item);
        }
        return items;
    }

    @Override
    public List<MeasureListIssueLog> searchIssueLogByIssueUuidAndStatus(Integer projectId, String uuid, int reformnocheck) {
        return measureListIssueLogService.searchIssueLogByIssueUuidAndStatus(projectId, uuid, reformnocheck);
    }
}
