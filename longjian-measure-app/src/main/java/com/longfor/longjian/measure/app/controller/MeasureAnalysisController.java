package com.longfor.longjian.measure.app.controller;

import com.longfor.gaia.gfs.web.mock.MockOperation;
import com.longfor.longjian.common.base.LjBaseResponse;
import com.longfor.longjian.measure.app.vo.ItemsVo;
import com.longfor.longjian.measure.app.vo.companyVo.CompanyVo;
import com.longfor.longjian.measure.app.vo.measureAnalysisVo.CheckItemVo;
import com.longfor.longjian.measure.app.vo.measureAnalysisVo.RankingVo;
import com.longfor.longjian.measure.app.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * @author wangxs
 * @date 2018-11-16 14:16
 */
@RestController
@RequestMapping("gapi/v3/")
@Slf4j
public class MeasureAnalysisController {

    /**
     * 集团统计分析检查项
     * @param requestParam
     * @return
     */
    @MockOperation
    @PostMapping(value = "res/category", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<ResultVo<List<CheckItemVo>>> getAnalysisCheckItems(RequestParam requestParam){
        return null;
    }

    /**
     * 集团统计分析统计指标合格率 + 每天
     * 集团统计分析组织对比公司排名
     * 集团统计分析组织对项目对比
     * 集团统计分析组织对比总包单位对比，分包单位对比，监理单位对比
     * @param requestParam
     * @return
     */
    @MockOperation
    @PostMapping(value = "stat_measure/stat/group", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<ResultVo<ItemsVo<List<RankingVo>>>> getJTPOPTotal(RequestParam requestParam){
        return null;
    }

    /**
     * 集团统计分析组织对比请求公司,获取所有公司
     * @param requestParam
     * @return
     */
    @MockOperation
    @PostMapping(value = "res/org", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<ResultVo<CompanyVo>> getCompanyList(RequestParam requestParam){
        return null;
    }
    
}
