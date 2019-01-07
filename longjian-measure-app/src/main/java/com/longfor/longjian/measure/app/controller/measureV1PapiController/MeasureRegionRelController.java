package com.longfor.longjian.measure.app.controller.measureV1PapiController;

import com.longfor.longjian.common.base.LjBaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * app_measure服务
 * 项目实测描画区域管理
 * 项目实测实量任务列表
 * wangxs
 * 2019-1-7
 */
@RestController
@RequestMapping("measure/v1/papi/measure_region_rel/")
@Slf4j
public class MeasureRegionRelController {
    /**
     *
     * @return
     */
    @GetMapping(value = "search_by_region_uuid/" ,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse searchByRegionUuid() {
        return null;
    }
}
