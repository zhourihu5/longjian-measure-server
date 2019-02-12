package com.longfor.longjian.measure.app.appService.proMeasureQuickSearchService;

import com.longfor.longjian.common.base.LjBaseResponse;
import com.longfor.longjian.measure.app.req.proMeasureQuickSearchReq.*;
import com.longfor.longjian.measure.app.vo.proMeasureQuickSearchVo.*;

import java.util.List;

public interface IMeasureListIssueDetailService {

    MeasureListIssueDetailIssueInfoVo IssueInfo(GetMeasureListIssueDetailReq req);

    MeasureListIssueDetailZoneInfoVo zoneInfo(GetMeasureListIssueDetailReq req);

    MeasureListIssueDetailRepairListVo repairList(GetMeasureListIssueDetailReq req);

    void updateRepairer(MeasureListDetailUpdateIssueRepairerReq req);

    LjBaseResponse updateIssueType(PostMeasureListDetailUpdateIssueTypeReq req);

    LjBaseResponse UpdatePlanEndOn(PostMeasureListDetailUpdateIssuePlanEndOnReq req);

    LjBaseResponse<MeasureListIssueGetIssueStatus> getIssueByProjectIdAndUuid(MeasureListIssueDetailReq req);
}
