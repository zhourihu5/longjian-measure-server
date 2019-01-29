package com.longfor.longjian.measure.app.appService.proMeasureQuickSearchService;

import com.longfor.longjian.common.base.LjBaseResponse;
import com.longfor.longjian.measure.app.req.proMeasureQuickSearchReq.GetMeasureListIssueDetailReq;
import com.longfor.longjian.measure.app.req.proMeasureQuickSearchReq.MeasureListDetailUpdateIssueRepairerReq;
import com.longfor.longjian.measure.app.req.proMeasureQuickSearchReq.PostMeasureListDetailUpdateIssuePlanEndOnReq;
import com.longfor.longjian.measure.app.req.proMeasureQuickSearchReq.PostMeasureListDetailUpdateIssueTypeReq;
import com.longfor.longjian.measure.app.vo.proMeasureQuickSearchVo.MeasureListIssueDetailIssueInfoVo;
import com.longfor.longjian.measure.app.vo.proMeasureQuickSearchVo.MeasureListIssueDetailRepairListVo;
import com.longfor.longjian.measure.app.vo.proMeasureQuickSearchVo.MeasureListIssueDetailRepairerVo;
import com.longfor.longjian.measure.app.vo.proMeasureQuickSearchVo.MeasureListIssueDetailZoneInfoVo;

import java.util.List;

public interface IMeasureListIssueDetailService {

    MeasureListIssueDetailIssueInfoVo IssueInfo(GetMeasureListIssueDetailReq req);

    MeasureListIssueDetailZoneInfoVo zoneInfo(GetMeasureListIssueDetailReq req);

    MeasureListIssueDetailRepairListVo repairList(GetMeasureListIssueDetailReq req);

    void updateRepairer(MeasureListDetailUpdateIssueRepairerReq req);

    LjBaseResponse updateIssueType(PostMeasureListDetailUpdateIssueTypeReq req);

    LjBaseResponse UpdatePlanEndOn(PostMeasureListDetailUpdateIssuePlanEndOnReq req);
}
