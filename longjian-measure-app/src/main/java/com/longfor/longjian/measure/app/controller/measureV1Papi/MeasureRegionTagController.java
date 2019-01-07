package com.longfor.longjian.measure.app.controller.measureV1Papi;

import com.longfor.longjian.common.base.LjBaseResponse;
import com.longfor.longjian.measure.app.appService.paintAreaService.IProPaintAreaManageService;
import com.longfor.longjian.measure.app.req.paintAreaReq.GetGroupMeasureRegionTagReq;
import com.longfor.longjian.measure.app.req.paintAreaReq.GetProjMeasureRegionTagReq;
import com.longfor.longjian.measure.app.vo.proPaintAreaManageVo.GroupRegionTagVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;

/**
 * app_measure服务
 * 项目实测描画区域管理
 * 项目实测实量任务列表
 * wangxs
 * 2019-1-7
 */
@RestController
@RequestMapping("measure/v1/papi/measure_region_tag/")
@Slf4j
public class MeasureRegionTagController {

    @Autowired
    private IProPaintAreaManageService proPaintAreaManageService;

    /**
     * 集团描画区域新增加描画区域请求标签
     * http://192.168.37.159:3000/project/8/interface/api/712
     * @param getGroupMeasureRegionTagReq
     * @return
     */
    @GetMapping(value = "search_by_group_id/" ,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<GroupRegionTagVo> getGroupMeasureRegionTag(@Valid GetGroupMeasureRegionTagReq getGroupMeasureRegionTagReq) throws IntrospectionException, InstantiationException, IllegalAccessException, InvocationTargetException {
        LjBaseResponse<GroupRegionTagVo> ljBaseResponse = proPaintAreaManageService.getGroupMeasureRegionTag(getGroupMeasureRegionTagReq);
        return ljBaseResponse;
    }

    /**
     * 项目描画区域新增加描画区域请求标签
     * http://192.168.37.159:3000/project/8/interface/api/712
     * @param getProjMeasureRegionTagReq
     * @return
     */
    @GetMapping(value = "search_by_proj_id/" ,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<GroupRegionTagVo> getGroupMeasureRegionTag(@Valid GetProjMeasureRegionTagReq getProjMeasureRegionTagReq) throws IntrospectionException, InstantiationException, IllegalAccessException, InvocationTargetException {
        LjBaseResponse<GroupRegionTagVo> ljBaseResponse = proPaintAreaManageService.getProjMeasureRegionTag(getProjMeasureRegionTagReq);
        return ljBaseResponse;
    }

    /**
     *
     * @return
     */
    @PostMapping(value = "edit_by_group_id/" ,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse editByGroupId() {
        return null;
    }


    /**
     *
     * @return
     */
    @PostMapping(value = "add_on_group/" ,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse addOnGroup() {
        return null;
    }

    /**
     *
     * @return
     */
    @PostMapping(value = "add_on_proj/" ,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse addOnProj() {
        return null;
    }


    /**
     *
     * @return
     */
    @PostMapping(value = "edit_by_proj_id/" ,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse editByProjId() {
        return null;
    }





}
