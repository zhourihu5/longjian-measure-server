package com.longfor.longjian.measure.app.controller.measurev1papicontroller;

import com.longfor.longjian.common.base.LjBaseResponse;
import com.longfor.longjian.measure.app.appservice.measureregionreisearchservice.IMeasureRegionRelSearchService;
import com.longfor.longjian.measure.app.req.measureregionreq.MeasureRegionRelReq;
import com.longfor.longjian.measure.app.vo.appmeasuresyncvo.MeasureRegionRelVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

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
    @Resource
    private IMeasureRegionRelSearchService measureRegionRelSearchService;

    /**
     * 实测描画区域管理删除描画区域
     * /longjian.longhu.net/measure/v1/papi/measure_region_rel/search_by_region_uuid/?_ct=json&project_id=930&page_level=project&group_id=4&team_id=25&proj_id=930&region_uuid=9e37788426104c4bb6838ba55c
     * http://192.168.37.159:3000/mock/8/longjian.longhu.net/measure/v1/papi/measure_region_rel/search_by_region_uuid/?_ct=json&project_id=930&page_level=project&group_id=4&team_id=25&proj_id=930&region_uuid=9e37788426104c4bb6838ba55c38df4
     *
     * @return
     */
    @RequestMapping(value = "search_by_region_uuid/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<MeasureRegionRelVo> searchByRegionUuid(@Valid MeasureRegionRelReq measureRegionRelReq) {
        LjBaseResponse<MeasureRegionRelVo> ljBaseResponse = measureRegionRelSearchService.searchByRegionUuid(measureRegionRelReq);
        return ljBaseResponse;
    }
}
