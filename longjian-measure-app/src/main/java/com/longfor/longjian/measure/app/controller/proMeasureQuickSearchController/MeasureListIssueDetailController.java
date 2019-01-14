package com.longfor.longjian.measure.app.controller.proMeasureQuickSearchController;

import com.longfor.longjian.common.base.LjBaseResponse;
import com.longfor.longjian.measure.app.appService.proMeasureQuickSearchService.IMeasureListIssueDetailService;
import com.longfor.longjian.measure.app.req.proMeasureQuickSearchReq.GetMeasureListIssueDetailReq;
import com.longfor.longjian.measure.app.req.proMeasureQuickSearchReq.MeasureListDetailUpdateIssueRepairerReq;
import com.longfor.longjian.measure.app.vo.proMeasureQuickSearchVo.MeasureListIssueDetailIssueInfoVo;
import com.longfor.longjian.measure.app.vo.proMeasureQuickSearchVo.MeasureListIssueDetailRepairerVo;
import com.longfor.longjian.measure.app.vo.proMeasureQuickSearchVo.MeasureListIssueDetailZoneInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * @description: go服务
 * @author: DDC
 * @create: 2019-01-08 10:26
 **/
@RestController
@RequestMapping("oapi/v3/measure/measure_list_issue_detail/")
@Slf4j
public class MeasureListIssueDetailController {

    @Autowired
    IMeasureListIssueDetailService measureListIssueDetailService;

    /**
     * @Description: go项目实测爆点详情
     * http://192.168.37.159:3000/project/8/interface/api/3008
     * @return com.longfor.longjian.common.base.LjBaseResponse<com.longfor.longjian.measure.app.vo.proMeasureQuickSearchVo.MeasureListIssueDetailIssueInfoVo>
     * @author DDC
     * @date 2019/1/14 14:31
     **/
    @GetMapping(value = "issue_info/",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<MeasureListIssueDetailIssueInfoVo> IssueInfo(@Valid GetMeasureListIssueDetailReq req){
        //todo 未添加鉴权
        return new LjBaseResponse<>(measureListIssueDetailService.IssueInfo(req));
    }

    /**
     * @Description: go项目实测爆点详情查看测区
     * http://192.168.37.159:3000/project/8/interface/api/2996
     * @return com.longfor.longjian.common.base.LjBaseResponse<com.longfor.longjian.measure.app.vo.proMeasureQuickSearchVo.MeasureListIssueDetailZoneInfoVo>
     * @author DDC
     * @date 2019/1/14 14:34
     **/
    @GetMapping(value ="zone_info/",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<MeasureListIssueDetailZoneInfoVo> zoneInfo(@Valid GetMeasureListIssueDetailReq req){
        //todo 未添加鉴权
        return new LjBaseResponse<>(measureListIssueDetailService.zoneInfo(req));
    }


    /**
     * @Description: go项目实测爆点整改测区详情
     * http://192.168.37.159:3000/project/8/interface/api/2992
     * @return com.longfor.longjian.common.base.LjBaseResponse<java.util.List<com.longfor.longjian.measure.app.vo.proMeasureQuickSearchVo.MeasureListIssueDetailRepairerVo>>
     * @author DDC
     * @date 2019/1/14 14:59
     **/
    @GetMapping(value = "repair_list/",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<List<MeasureListIssueDetailRepairerVo>> repairList(@Valid GetMeasureListIssueDetailReq req){
        //todo 未添加鉴权
        return new LjBaseResponse<>(measureListIssueDetailService.repairList(req));
    }

    /**
     * @Description: go项目实测爆点详情修给整改人
     * http://192.168.37.159:3000/project/8/interface/api/3012
     * @return com.longfor.longjian.common.base.LjBaseResponse
     * @author DDC
     * @date 2019/1/14 20:32
     **/
    @PostMapping(value = "update_repairer/",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse updateRepairer(MeasureListDetailUpdateIssueRepairerReq req){
        //todo 未添加鉴权
        return measureListIssueDetailService.updateRepairer(req);
    }
}


