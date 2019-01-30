package com.longfor.longjian.measure.app.controller.proMeasureQuickSearchController;

import com.longfor.longjian.common.base.LjBaseResponse;
import com.longfor.longjian.common.exception.LjBaseRuntimeException;
import com.longfor.longjian.common.util.CtrlTool;
import com.longfor.longjian.measure.app.appService.proMeasureQuickSearchService.IMeasureStatisticService;
import com.longfor.longjian.measure.app.req.proMeasureQuickSearchReq.GetMeasureStatisticTaskReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @description: go服务
 * @author: DDC
 * @create: 2019-01-08 10:15
 **/
@RestController
@RequestMapping("oapi/v3/measure/measure_statistic/")
@Slf4j
public class MeasureStatisticController {

    @Autowired
    private IMeasureStatisticService measureStatisticService;

    @Autowired
    private CtrlTool ctrlTool;

    /**
     * @Description: go项目实测快速查新任务概览组间对比
     * http://192.168.37.159:3000/project/8/interface/api/2976
     * @return
     * @author DDC
     * @date 2019/1/8 11:19
     **/
    @RequestMapping(value = "squad_measure_stats_json/" , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse SquadMeasureStatsJson(@Valid GetMeasureStatisticTaskReq getMeasureStatisticTaskReq,HttpServletRequest request) throws Exception {
        try {
            ctrlTool.projPerm(request,"项目.实测实量.统计.查看");
        }catch (Exception e){
            throw new LjBaseRuntimeException(-9999,e.getMessage());
        }
        return new LjBaseResponse<>(measureStatisticService.SquadMeasureStatsJson(getMeasureStatisticTaskReq));
    }
}


