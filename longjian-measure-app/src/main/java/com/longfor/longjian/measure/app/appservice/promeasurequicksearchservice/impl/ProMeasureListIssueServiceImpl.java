package com.longfor.longjian.measure.app.appservice.promeasurequicksearchservice.impl;

import com.longfor.longjian.measure.app.appservice.promeasurequicksearchservice.IProMeasureListIssueService;
import com.longfor.longjian.measure.app.commonentity.MeasureListIssueHelper;
import com.longfor.longjian.measure.consts.constant.MeasureListIssueType;
import com.longfor.longjian.measure.consts.enums.MeasureListCloseStatusEnum;
import com.longfor.longjian.measure.consts.enums.MeasureListIssueCheckStatusEnum;
import com.longfor.longjian.measure.domain.externalservice.IMeasureListIssueService;
import com.longfor.longjian.measure.po.zhijian2.MeasureListIssue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.Date;

@Service
@Slf4j
public class ProMeasureListIssueServiceImpl implements IProMeasureListIssueService {

    @Resource
    private IMeasureListIssueService measureListIssueService;
    @Resource
    private MeasureListIssueHelper helper;

    @Override
    public void measureListIssueDeleteByProjUuid(Integer projectId, String uuid) {
        measureListIssueService.deletedByUpdateDeletedAt(projectId,uuid);
    }

    @Override
    public boolean updateIssueApproveStatusByUuid(String uuid, Integer projectId, Integer senderId, Integer status, String desc, String attachmentMd5List) throws ParseException {
        long eLong = -1;
        int eInt = -1;
        String eStr = "";

        MeasureListIssue issue = measureListIssueService.getIssueByProjectIdAndUuid(projectId,uuid);

        boolean isClosed = false;
        if (MeasureListCloseStatusEnum.CLOSED.getId().equals(issue.getCloseStatus())){
            return true;
        }

        helper.init(projectId);

        if (MeasureListIssueCheckStatusEnum.CHECKYES.getId().equals(status)){
            //审核通过
            helper.start().setNormalField(uuid,issue.getListId(),issue.getUuid(),senderId,eStr,eInt, MeasureListIssueType.CHECKYES,eStr,issue.getCategoryKey(),new Date().getTime()).
                    setDatailField(eStr, eLong, eLong, eInt, eInt, eInt, eStr, eInt, eInt, eInt, eInt, eLong,status).
                    done();
        }else if (MeasureListIssueCheckStatusEnum.CHECKNO.getId().equals(status)){
            helper.start().setNormalField(uuid, issue.getListId(),issue.getUuid(),senderId,desc,eInt,MeasureListIssueType.ASSIGNNOREFORM,attachmentMd5List,issue.getCategoryKey(),new Date().getTime()).
                    setDatailField(eStr, eLong, eLong, eInt, eInt, eInt, eStr, eInt, eInt, eInt, eInt, eLong,status).
                    done();
        }else {
            return isClosed;
        }

        helper.execute();
        return isClosed;
    }

    @Override
    public void updateIssueCloseStatusByUuid(String uuid, Integer projectId, Integer senderId, Integer status) throws ParseException {
        long eLong = -1;
        int eInt = -1;
        String eStr = "";

        MeasureListIssue issue = measureListIssueService.getIssueByProjectIdAndUuid(projectId,uuid);

        helper.init(projectId);

        //审核通过
        helper.start().setNormalField(uuid,issue.getListId(),issue.getUuid(),senderId,eStr,eInt, eInt,eStr,issue.getCategoryKey(),new Date().getTime()).
                setDatailField(eStr, eLong, eLong, eInt, eInt, eInt, eStr, eInt, eInt, status, senderId, new Date().getTime(), eInt).
                done();

        helper.execute();
    }
}
