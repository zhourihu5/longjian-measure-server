package com.longfor.longjian.measure.app.controller;

import com.longfor.gaia.gfs.web.mock.MockOperation;
import com.longfor.longjian.common.base.LjBaseResponse;
import com.longfor.longjian.measure.app.vo.proPaintAreaManageVo.AreaRegionTagVo;
import com.longfor.longjian.measure.app.vo.proPaintAreaManageVo.GroupRegionTagVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * 项目实测描画区域管理
 * 项目实测实量任务列表
 * wangxs
 * 2018-11-19
 */
@RestController
@RequestMapping("measure/v1/papi/")
@Slf4j
public class ProPaintAreaManageController {

    /**
     * 项目描画区域新增加描画区域请求标签
     * http://192.168.37.159:3000/project/8/interface/api/712
     * @param requestParam
     * @return
     */
    @MockOperation
    @GetMapping(value = "measure_region_tag/search_by_group_id/" ,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<GroupRegionTagVo> getGroupMeasureRegionTag(RequestParam requestParam){
        return null;
    }


    /**
     * 项目描画区域管理新增描画区域请求测区
     * http://192.168.37.159:3000/project/8/interface/api/726
     * @param requestParam
     * @return
     */
    @MockOperation
    @GetMapping(value = "measure_region/search_by_area_id/" ,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<AreaRegionTagVo> getAreaRegionList(RequestParam requestParam){
        return null;
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
