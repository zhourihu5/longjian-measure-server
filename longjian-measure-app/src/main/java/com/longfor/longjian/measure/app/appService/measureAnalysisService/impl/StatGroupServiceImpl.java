package com.longfor.longjian.measure.app.appService.measureAnalysisService.impl;

import com.longfor.longjian.common.base.LjBaseResponse;
import com.longfor.longjian.measure.app.appService.measureAnalysisService.IStatGroupService;
import com.longfor.longjian.measure.app.req.measureAnalysisReq.StatGroupReq;
import com.longfor.longjian.measure.app.vo.ItemsVo;
import com.longfor.longjian.measure.app.vo.ResultVo;
import com.longfor.longjian.measure.app.vo.measureAnalysisVo.RankingVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * wangxs
 * 2018-11-22
 */
@Service
public class StatGroupServiceImpl implements IStatGroupService {
    @Override
    public LjBaseResponse<ResultVo<ItemsVo<List<RankingVo>>>> getStatGroup(StatGroupReq statGroupReq) {
        LjBaseResponse<ResultVo<ItemsVo<List<RankingVo>>>> ljBaseResponse = new LjBaseResponse<>();
        return ljBaseResponse;
    }
}
