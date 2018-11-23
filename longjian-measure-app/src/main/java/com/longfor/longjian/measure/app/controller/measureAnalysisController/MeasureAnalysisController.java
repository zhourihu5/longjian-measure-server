package com.longfor.longjian.measure.app.controller.measureAnalysisController;

import com.longfor.gaia.gfs.web.mock.MockOperation;
import com.longfor.longjian.common.base.LjBaseResponse;
import com.longfor.longjian.measure.app.appService.measureAnalysisService.IStatGroupService;
import com.longfor.longjian.measure.app.req.measureAnalysisReq.StatGroupReq;
import com.longfor.longjian.measure.app.vo.ItemsVo;
import com.longfor.longjian.measure.app.vo.measureAnalysisVo.*;
import com.longfor.longjian.measure.app.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * zhijian_server_stat_measure 服务
 * 集团统计分析
 * 公司统计分析
 * 项目统计分析
 * @author wangxs
 * @date 2018-11-16 14:16
 */
@RestController
@RequestMapping("gapi/v3/stat_measure/")
@Slf4j
public class MeasureAnalysisController {

    @Autowired
    private IStatGroupService statGroupService;

    /**
     * 集团统计分析检查项
     * http://192.168.37.159:3000/project/8/interface/api/64
     * 集团合作伙伴检查项标准
     * 公司统计分析检查项
     * http://192.168.37.159:3000/mock/8/////gapi/v3/res/category?team_id=25&page_level=team&group_id=4&tip=categorySubs
     * 公司统计项目对比检查项
     * http://192.168.37.159:3000/mock/8//////gapi/v3/res/category?team_id=25&page_level=team&group_id=4&tip=categorySubs
     * 项目统计分析检查项
     * http://192.168.37.159:3000/project/8/interface/api/804
     * 项目统计分析实测与整改进度检查项
     * http://192.168.37.159:3000/project/8/interface/api/836
     * 项目统计分析问题分析问题分类
     * http://192.168.37.159:3000/project/8/interface/api/860
     * @param requestParam
     * @return
     */
//    @MockOperation
//    @PostMapping(value = "res/category", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    public LjBaseResponse<ResultVo<List<CheckItemVo>>> getAnalysisCheckItems(RequestParam requestParam){
//        return null;
//    }

    /**
     * 集团统计分析统计指标合格率 + 每天
     * http://192.168.37.159:3000/project/8/interface/api/68
     * http://192.168.37.159:3000/project/8/interface/api/70
     * 集团统计分析组织对比公司排名
     * 集团统计分析组织对项目对比
     * 集团统计分析组织对比总包单位对比，分包单位对比，监理单位对比
     * 集团统计分析组织对比分包单位对比公司排名
     * 集团合作伙伴检查项标准
     * 集团合作伙伴横向对比检查项
     * 集团合作伙伴分项详情
     * http://192.168.37.159:3000/mock/8////gapi/v3/stat_measure/stat/group?group_id=4&page_level=group&tip=partnersByRoleType Request Method: POST
     * @param statGroupReq
     * @return
     */
    @MockOperation
    @PostMapping(value = "stat/group", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<ResultVo<ItemsVo<List<RankingVo>>>> statGroup(@Valid@RequestParam StatGroupReq statGroupReq){
        LjBaseResponse<ResultVo<ItemsVo<List<RankingVo>>>> ljBaseResponse = statGroupService.getStatGroup(statGroupReq);
        return ljBaseResponse;
    }

    /**
     * 集团统计分析组织对比请求公司,获取所有公司
     * 集团使用功能统计横向请求公司
     * http://192.168.37.159:3000/mock/8///gapi/v3/res/org?group_id=4&tip=teamAll
     * 公司统计组织对比项目对比请求公司(项目)
     * http://192.168.37.159:3000/mock/8////gapi/v3/res/org?team_id=25&page_level=team&group_id=4&tip=projAll
     * 公司功能统计总揽横向对比请求公司
     * http://192.168.37.159:3000/mock/8/////gapi/v3/res/org?team_id=25&page_level=team&group_id=4&tip=teamAll
     * @param requestParam
     * @return
     */
//    @MockOperation
//    @PostMapping(value = "res/org", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    public LjBaseResponse<ResultVo<CompanyVo>> getCompanyList(RequestParam requestParam){
//        return null;
//    }

    /**
     * 集团统计合作伙伴选择组织 ResultVo<List<OrganizationInfoVo>>
     * http://192.168.37.159:3000/mock/8/gapi/v3/stat_measure/stat/partner_board?group_id=4&page_level=group&tip=allByUser
     * 集团合作伙伴项目对比对比项目 ResultVo<List<OrganizationInfoVo>>
     * http://192.168.37.159:3000/mock/8//gapi/v3/stat_measure/stat/partner_board?group_id=4&page_level=group&tip=projs
     * 集团合作伙伴统计分析横向查询
     * http://192.168.37.159:3000/mock/8///gapi/v3/stat_measure/stat/partner_board?group_id=4&page_level=group&tip=proj
     * @param requestParam
     * @return
     */
    @MockOperation
    @PostMapping(value = "stat/partner_board", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<ResultVo<OrganizationVo>> getOrganization(RequestParam requestParam){
        return null;
    }

    /**
     * 集团使用功能统计总揽查询
     * http://192.168.37.159:3000/mock/8/gapi/v3/stat_measure/issue_action?group_id=4&tip=groupSummary
     * 集团使用功能统计横向分析查询
     * http://192.168.37.159:3000/mock/8//gapi/v3/stat_measure/issue_action?group_id=4&tip=groupHorizontalComparison
     * 集团使用功能统计趋势查询
     * http://192.168.37.159:3000/mock/8///gapi/v3/stat_measure/issue_action?group_id=4&tip=groupTrendComparison
     * 公司功能统计总揽查询
     * http://192.168.37.159:3000/mock/8////gapi/v3/stat_measure/issue_action?team_id=25&page_level=team&group_id=4&tip=teamSummary
     * 公司功能统计横向查询
     * http://192.168.37.159:3000/mock/8/////gapi/v3/stat_measure/issue_action?team_id=25&page_level=team&group_id=4&tip=teamHorizontalComparison
     * 公司功能统计趋势查询
     * http://192.168.37.159:3000/mock/8//////gapi/v3/stat_measure/issue_action?team_id=25&page_level=team&group_id=4&tip=teamTrendComparison
     * @param requestParam
     * @return
     */
    @MockOperation
    @PostMapping(value = "issue_action", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<ResultVo<UsedFuncAnalysisVo>> getUsedFunc(RequestParam requestParam){
        return null;
    }


    /**
     * 公司统计分析总揽关键指标
     * http://192.168.37.159:3000/mock/8/.net/gapi/v3/stat_measure/stat/team?team_id=25&page_level=team&group_id=4&tip=summary
     * 公司统计总揽每天指标
     * http://192.168.37.159:3000/mock/8//gapi/v3/stat_measure/stat/team?team_id=25&page_level=team&group_id=4&tip=summary
     * 公司统计组织对比项目排名
     * http://192.168.37.159:3000/mock/8///gapi/v3/stat_measure/stat/team?team_id=25&page_level=team&group_id=4&tip=projRank
     * 公司统计组织对比查询结果
     * http://192.168.37.159:3000/mock/8////gapi/v3/stat_measure/stat/team?team_id=25&page_level=team&group_id=4&tip=proj
     * 公司统计分析总包对比合作伙伴
     * http://192.168.37.159:3000/mock/8/////gapi/v3/stat_measure/stat/team?team_id=25&page_level=team&group_id=4&tip=partnersByRoleType
     * 公司统计总包对比排名
     * http://192.168.37.159:3000/mock/8//////gapi/v3/stat_measure/stat/team?team_id=25&page_level=team&group_id=4&tip=partnerRank
     * @param requestParam
     * @return
     */
    @MockOperation
    @PostMapping(value = "stat/team", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<ResultVo<AnalysisVo>> getTeamPOPTotal(RequestParam requestParam){
        return null;
    }


    /**
     * 项目统计分析本月指标
     * http://192.168.37.159:3000/project/8/interface/api/820
     * 项目统计分析近7天指标
     * http://192.168.37.159:3000/project/8/interface/api/828
     * 项目统计分析实测也整改进度请求测区
     * http://192.168.37.159:3000/project/8/interface/api/852
     * 项目统计分析问题分析整改完成情况
     * http://192.168.37.159:3000/project/8/interface/api/876
     * 项目统计分析区域对比查询
     * http://192.168.37.159:3000/project/8/interface/api/892
     * 统计分析区域对比趋势对比
     * http://192.168.37.159:3000/project/8/interface/api/908
     * 项目统计分析人员对比
     * http://192.168.37.159:3000/project/8/interface/api/916
     * 项目对比人员对比横向对比查询
     * http://192.168.37.159:3000/project/8/interface/api/924
     * 项目统计分析人员对比工作量对比
     * http://192.168.37.159:3000/project/8/interface/api/940
     * 项目统计分析人员对比工作量对比查询
     * http://192.168.37.159:3000/project/8/interface/api/948
     * 项目使用功能统计总览查询
     * http://192.168.37.159:3000/project/8/interface/api/956
     * 项目使用功能统计趋势对比查询
     * http://192.168.37.159:3000/project/8/interface/api/964
     * @param requestParam
     * @return
     */
    @MockOperation
    @PostMapping(value = "stat/proj", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<ResultVo<AnalysisVo>> getProjPOPTotal(RequestParam requestParam){
        return null;
    }

    /**
     * 项目统计分析问题分析区域
     * http://192.168.37.159:3000/project/8/interface/api/868
     * @param requestParam
     * @return
     */
//    @MockOperation
//    @PostMapping(value = "res/area", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    public LjBaseResponse<ResultVo<List<AreaInfoVo>>> getAreaList(RequestParam requestParam){
//        return null;
//    }
}
