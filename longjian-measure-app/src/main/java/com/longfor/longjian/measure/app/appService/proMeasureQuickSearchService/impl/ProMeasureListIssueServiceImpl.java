package com.longfor.longjian.measure.app.appService.proMeasureQuickSearchService.impl;

import com.longfor.longjian.measure.app.appService.proMeasureQuickSearchService.IProMeasureListIssueService;
import com.longfor.longjian.measure.app.commonEntity.MeasureListIssueHelper;
import com.longfor.longjian.measure.consts.Enum.MeasureListCloseStatusEnum;
import com.longfor.longjian.measure.consts.Enum.MeasureListIssueCheckStatusEnum;
import com.longfor.longjian.measure.consts.constant.MeasureListIssueType;
import com.longfor.longjian.measure.domain.externalService.IMeasureListIssueService;
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

    @Override
    public void measureListIssueDeleteByProjUuid(Integer project_id, String uuid) {
        measureListIssueService.deletedByUpdateDeletedAt(project_id,uuid);
    }

    @Override
    public boolean updateIssueApproveStatusByUuid(String uuid, Integer projectId, Integer senderId, Integer status, String desc, String attachmentMd5List) throws ParseException {
        long eLong = -1;
        int eInt = -1;
        String eStr = "";

        MeasureListIssue issue = measureListIssueService.GetIssueByProjectIdAndUuid(projectId,uuid);

        boolean isClosed = false;
        if (MeasureListCloseStatusEnum.Closed.getId().equals(issue.getCloseStatus())){
            return true;
        }

        MeasureListIssueHelper helper = new MeasureListIssueHelper();
        helper.init(projectId);

        if (MeasureListIssueCheckStatusEnum.CheckYes.getId().equals(status)){
            //审核通过
            helper.start().setNormalField(uuid,issue.getListId(),issue.getUuid(),senderId,eStr,eInt, MeasureListIssueType.CHECKYES,eStr,eStr,new Date().getTime()).
                    setDatailField(eStr, eLong, eLong, eInt, eInt, eInt, eStr, eInt, eInt, eInt, eInt, eLong,status).
                    done();
        }else if (MeasureListIssueCheckStatusEnum.CheckNo.getId().equals(status)){
            helper.start().setNormalField(uuid, issue.getListId(),issue.getUuid(),senderId,desc,eInt,MeasureListIssueType.ASSIGNNOREFORM,attachmentMd5List,eStr,new Date().getTime()).
                    setDatailField(eStr, eLong, eLong, eInt, eInt, eInt, eStr, eInt, eInt, eInt, eInt, eLong,status).
                    done();
        }else {
            return isClosed;
        }

        helper.execute();
        return isClosed;
    }

    @Override
    public void updateIssueCloseStatusByUuid(String uuid, Integer project_id, Integer senderId, Integer status) throws ParseException {
        long eLong = -1;
        int eInt = -1;
        String eStr = "";

        MeasureListIssue issue = measureListIssueService.GetIssueByProjectIdAndUuid(project_id,uuid);

        MeasureListIssueHelper helper = new MeasureListIssueHelper();
        helper.init(project_id);

        //审核通过
        helper.start().setNormalField(uuid,issue.getListId(),issue.getUuid(),senderId,eStr,eInt, eInt,eStr,eStr,new Date().getTime()).
                setDatailField(eStr, eLong, eLong, eInt, eInt, eInt, eStr, eInt, eInt, status, senderId, new Date().getTime(), eInt).
                done();

        helper.execute();
    }
}
