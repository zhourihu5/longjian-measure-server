package com.longfor.longjian.measure.app.controller.paintAreaController;

import com.longfor.longjian.common.base.LjBaseResponse;
import com.longfor.longjian.measure.app.appService.paintAreaService.IProPaintAreaManageService;
import com.longfor.longjian.measure.app.req.paintAreaReq.GetProjMeasureRegionReq;
import com.longfor.longjian.measure.app.req.paintAreaReq.GetGroupMeasureRegionTagReq;
import com.longfor.longjian.measure.app.req.paintAreaReq.GetProjMeasureRegionTagReq;
import com.longfor.longjian.measure.app.vo.proPaintAreaManageVo.AreaRegionTagVo;
import com.longfor.longjian.measure.app.vo.proPaintAreaManageVo.GroupRegionTagVo;
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
@RequestMapping("measure/v1/papi/")
@Slf4j
public class ProPaintAreaManageController {

    @Autowired
    private IProPaintAreaManageService proPaintAreaManageService;

    /**
     * 集团描画区域新增加描画区域请求标签
     * http://192.168.37.159:3000/project/8/interface/api/712
     * @param getGroupMeasureRegionTagReq
     * @return
     */
    @GetMapping(value = "measure_region_tag/search_by_group_id/" ,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
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
    @GetMapping(value = "measure_region_tag/search_by_proj_id/" ,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<GroupRegionTagVo> getGroupMeasureRegionTag(@Valid GetProjMeasureRegionTagReq getProjMeasureRegionTagReq) throws IntrospectionException, InstantiationException, IllegalAccessException, InvocationTargetException {
        LjBaseResponse<GroupRegionTagVo> ljBaseResponse = proPaintAreaManageService.getProjMeasureRegionTag(getProjMeasureRegionTagReq);
        return ljBaseResponse;
    }

    /**
     * 项目描画区域管理新增描画区域请求测区
     * http://192.168.37.159:3000/project/8/interface/api/726
     * @param getProjMeasureRegionReq
     * @return
     */
    @GetMapping(value = "measure_region/search_by_area_id/" ,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<AreaRegionTagVo> getAreaRegionList(@Valid GetProjMeasureRegionReq getProjMeasureRegionReq) throws IntrospectionException, InstantiationException, IllegalAccessException, InvocationTargetException {
        LjBaseResponse<AreaRegionTagVo> ljBaseResponse =  proPaintAreaManageService.getProjMeasureRegionByAreaId(getProjMeasureRegionReq);
        return ljBaseResponse;
    }

    /**
     * 项目实测任务列表 ??
     * http://192.168.37.159:3000/project/8/interface/api/772
     * 暂时未找到接口调用位置
     */

    /**
     * 项目实测任务列表请求检查项 ??
     * http://192.168.37.159:3000/project/8/interface/api/780
     * 暂时未找到接口调用位置
     */

    /**
     * 项目实测任务列表选择区域 ??
     * http://192.168.37.159:3000/project/8/interface/api/788
     * 暂时未找到接口调用位置
     */

}
