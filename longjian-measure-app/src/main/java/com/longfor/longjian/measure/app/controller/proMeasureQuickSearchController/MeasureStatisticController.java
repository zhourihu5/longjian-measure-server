package com.longfor.longjian.measure.app.controller.proMeasureQuickSearchController;

import com.longfor.longjian.common.base.LjBaseResponse;
import com.longfor.longjian.common.exception.LjBaseRuntimeException;
import com.longfor.longjian.measure.app.appService.proMeasureQuickSearchService.IMeasureStatisticService;
import com.longfor.longjian.measure.app.req.proMeasureQuickSearchReq.GetMeasureStatisticTaskReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @description: go服务
 * @author: DDC
 * @create: 2019-01-08 10:15
 **/
@RestController
@RequestMapping("oapi/v3/")
@Slf4j
public class MeasureStatisticController {

    @Autowired
    private IMeasureStatisticService measureStatisticService;

    /**
     * @Description:
     * @param
     * @return void
     * @author DDC
     * @date 2019/1/8 11:19
     **/
    @GetMapping(value = "measure/measure_statistic/squad_measure_stats_json/" , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse SquadMeasureStatsJson(@Valid GetMeasureStatisticTaskReq getMeasureStatisticTaskReq, Errors errors){
        if (errors.hasErrors()) {
            throw new LjBaseRuntimeException(-1,"");
        }
        //todo 未添加鉴权
        return new LjBaseResponse(measureStatisticService.SquadMeasureStatsJson(getMeasureStatisticTaskReq));
    }
}


