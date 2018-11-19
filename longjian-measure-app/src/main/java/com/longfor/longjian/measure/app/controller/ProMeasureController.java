package com.longfor.longjian.measure.app.controller;

import com.longfor.gaia.gfs.web.mock.MockOperation;
import com.longfor.longjian.common.base.LjBaseResponse;
import com.longfor.longjian.measure.app.vo.ItemsVo;
import com.longfor.longjian.measure.app.vo.ProMeasureVo.CheckerVo;
import com.longfor.longjian.measure.app.vo.ProMeasureVo.ProMeasureAreaVo;
import com.longfor.longjian.measure.app.vo.ProMeasureVo.ProMeasureCheckIteamVo;
import com.longfor.longjian.measure.app.vo.ProMeasureVo.ProMeasurePlanVo;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.cert.ocsp.Req;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *
 * @author wangxs
 * @date 2018-11-17 14:07
 */
@RestController
@RequestMapping("v3/")
@Slf4j
public class ProMeasureController {

    /**
     * go项目实测任务列表
     * @param requestParam
     * @return
     */
    @MockOperation
    @GetMapping(value = "measure/measure_list/search_json/" ,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<ItemsVo<List<ProMeasurePlanVo>>> getProMeasurePlanList(RequestParam requestParam){
        return null;
    }

    /**
     * go项目实测任务列表请求检查项
     * @param requestParam
     * @return
     */
    @MockOperation
    @GetMapping(value = "measure/measure_list/sub_categorys/" ,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<ItemsVo<List<ProMeasureCheckIteamVo>>> getProMeasureCheckItems(RequestParam requestParam){
        return null;
    }

    /**
     * go项目实测任务列表区域
     * go项目实测任务列表区域详情楼层
     * @param requestParam
     * @return
     */
    @MockOperation
    @GetMapping(value = "area/area/subs/" ,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<ItemsVo<List<ProMeasureAreaVo>>> getProMeasureAreaList(RequestParam requestParam){
        return null;
    }

    /**
     * go项目实测任务列表请求检查人员
     * @param requestParam
     * @return
     */
    @MockOperation
    @GetMapping(value = "measure/ajax_json/user_list/" ,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<ItemsVo<List<CheckerVo>>> getCheckerList(RequestParam requestParam){
        return null;
    }


}
