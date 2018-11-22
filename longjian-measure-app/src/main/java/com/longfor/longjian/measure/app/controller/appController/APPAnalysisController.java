package com.longfor.longjian.measure.app.controller.appController;

import com.longfor.gaia.gfs.web.mock.MockOperation;
import com.longfor.longjian.common.base.LjBaseResponse;
import com.longfor.longjian.measure.app.vo.appAnalysisVo.ApiAreaVo;
import com.longfor.longjian.measure.app.vo.appMeasureSyncVo.RuleListVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * app 端实测实量统计分析
 * wangxs
 * 2018-11-20
 */
@RestController
@RequestMapping("gapi/v3")
@Slf4j
public class APPAnalysisController {
    /**
     * 统计-获取整改追踪问题列表
     * http://192.168.37.159:3000/project/8/interface/api/450
     * @param requestParam
     * @return
     */
    @MockOperation
    @GetMapping(value = "stat_measure/api_area/",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<ApiAreaVo> apiArea(RequestParam requestParam){
        return null;
    }
}
