package com.longfor.longjian.measure.app.appService.proMeasureQuickSearchService;

import com.longfor.longjian.common.base.LjBaseResponse;
import com.longfor.longjian.measure.app.req.proMeasureQuickSearchReq.*;
import com.longfor.longjian.measure.app.vo.proMeasureQuickSearchVo.MeasureListIssueDetailIssueInfoVo;
import com.longfor.longjian.measure.app.vo.proMeasureQuickSearchVo.MeasureListIssueDetailRepairListVo;
import com.longfor.longjian.measure.app.vo.proMeasureQuickSearchVo.MeasureListIssueDetailZoneInfoVo;
import com.longfor.longjian.measure.app.vo.proMeasureQuickSearchVo.MeasureListIssueGetIssueStatus;

public interface IMeasureListIssueDetailService {

    MeasureListIssueDetailIssueInfoVo IssueInfo(GetMeasureListIssueDetailReq req);

    MeasureListIssueDetailZoneInfoVo zoneInfo(GetMeasureListIssueDetailReq req);

    MeasureListIssueDetailRepairListVo repairList(GetMeasureListIssueDetailReq req);

    void updateRepairer(MeasureListDetailUpdateIssueRepairerReq req);

    LjBaseResponse updateIssueType(PostMeasureListDetailUpdateIssueTypeReq req);

    LjBaseResponse UpdatePlanEndOn(PostMeasureListDetailUpdateIssuePlanEndOnReq req);

    LjBaseResponse<MeasureListIssueGetIssueStatus> getIssueByProjectIdAndUuid(MeasureListIssueDetailReq req);
}
