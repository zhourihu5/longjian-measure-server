package com.longfor.longjian.measure.app.appservice.promeasurequicksearchservice;

import com.longfor.longjian.common.base.LjBaseResponse;
import com.longfor.longjian.measure.app.req.promeasurequicksearchreq.*;
import com.longfor.longjian.measure.app.vo.promeasurequicksearchvo.MeasureListIssueDetailIssueInfoVo;
import com.longfor.longjian.measure.app.vo.promeasurequicksearchvo.MeasureListIssueDetailRepairListVo;
import com.longfor.longjian.measure.app.vo.promeasurequicksearchvo.MeasureListIssueDetailZoneInfoVo;
import com.longfor.longjian.measure.app.vo.promeasurequicksearchvo.MeasureListIssueGetIssueStatus;

public interface IMeasureListIssueDetailService {

    MeasureListIssueDetailIssueInfoVo IssueInfo(GetMeasureListIssueDetailReq req);

    MeasureListIssueDetailZoneInfoVo zoneInfo(GetMeasureListIssueDetailReq req);

    MeasureListIssueDetailRepairListVo repairList(GetMeasureListIssueDetailReq req);

    void updateRepairer(MeasureListDetailUpdateIssueRepairerReq req);

    LjBaseResponse updateIssueType(PostMeasureListDetailUpdateIssueTypeReq req);

    LjBaseResponse UpdatePlanEndOn(PostMeasureListDetailUpdateIssuePlanEndOnReq req);

    LjBaseResponse<MeasureListIssueGetIssueStatus> getIssueByProjectIdAndUuid(MeasureListIssueDetailReq req);
}
