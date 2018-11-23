package com.longfor.longjian.measure.app.appService.measureAnalysisService;

import com.longfor.longjian.common.base.LjBaseResponse;
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
public interface IStatGroupService {
    /**
     *
     * @param statGroupReq
     * @return
     */
    LjBaseResponse<ResultVo<ItemsVo<List<RankingVo>>>> getStatGroup(StatGroupReq statGroupReq);
}
