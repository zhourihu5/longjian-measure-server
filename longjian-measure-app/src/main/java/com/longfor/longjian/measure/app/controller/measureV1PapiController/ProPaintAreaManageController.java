package com.longfor.longjian.measure.app.controller.measureV1PapiController;

import com.longfor.longjian.common.base.LjBaseResponse;
import com.longfor.longjian.measure.app.appService.paintAreaService.IProPaintAreaManageService;
import com.longfor.longjian.measure.app.appService.paintAreaService.IRegionService;
import com.longfor.longjian.measure.app.req.measureRegionReq.AddReq;
import com.longfor.longjian.measure.app.req.paintAreaReq.GetProjMeasureRegionReq;
import com.longfor.longjian.measure.app.vo.proPaintAreaManageVo.AreaRegionTagVo;
import com.longfor.longjian.measure.consts.Enum.RegionSrcTypeEnum;
import com.longfor.longjian.measure.domain.externalService.IMeasureRegionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;

/**
 * app_measure服务
 * 项目实测描画区域管理
 * 项目实测实量任务列表
 * wangxs
 * 2018-11-19
 */
@RestController
@RequestMapping("measure/v1/papi/measure_region/")
@Slf4j
public class ProPaintAreaManageController {

    @Autowired
    private IProPaintAreaManageService proPaintAreaManageService;
    @Autowired
    private IRegionService regionService;

    /**
     * 项目描画区域管理新增描画区域请求测区
     * http://192.168.37.159:3000/project/8/interface/api/726
     * @param getProjMeasureRegionReq
     * @return
     */
    @GetMapping(value = "search_by_area_id/" ,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<AreaRegionTagVo> getAreaRegionList(@Valid GetProjMeasureRegionReq getProjMeasureRegionReq) throws IntrospectionException, InstantiationException, IllegalAccessException, InvocationTargetException {
        LjBaseResponse<AreaRegionTagVo> ljBaseResponse =  proPaintAreaManageService.getProjMeasureRegionByAreaId(getProjMeasureRegionReq);
        return ljBaseResponse;
    }


    /**
     *添加描画区域
     * @return
     */
    @PostMapping(value = "add" ,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse add(@RequestBody @Valid AddReq addReq) {
        regionService.add(addReq.getProject_id(),addReq.getRegion_list(), RegionSrcTypeEnum.BackEnd.getId());
        return new LjBaseResponse();
    }


    /**
     *
     * @return
     */
    @PostMapping(value = "delete/" ,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse delete() {
        return null;
    }


    /**
     *
     * @return
     */
    @PostMapping(value = "edit/" ,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse edit() {
        return null;
    }


}
