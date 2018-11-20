package com.longfor.longjian.measure.app.controller;

import com.longfor.gaia.gfs.web.mock.MockOperation;
import com.longfor.longjian.common.base.LjBaseResponse;
import com.longfor.longjian.measure.app.vo.areaVo.DrawAreaVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 项目描画区域管理
 * 请求区域
 * wangxs
 * 2018-11-19
 */
@RestController
@RequestMapping("area/v1/papi/")
@Slf4j
public class AreaController {

    /**
     * 项目描画区域新增加描画区域请求区域
     * http://192.168.37.159:3000/project/8/interface/api/716
     * 项目描画区域管理新增描画区域查询楼层
     * http://192.168.37.159:3000/project/8/interface/api/724
     * 项目创建实测实量任务请求区域
     * http://192.168.37.159:3000/project/8/interface/api/742
     * @return
     */
    @MockOperation
    @PostMapping(value = "proj_area/subs/",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<DrawAreaVo> getAreaList(RequestParam requestParam){
        return null;
    }


    /**
     * 项目描画区域新增加描画区域请求房间
     * http://192.168.37.159:3000/project/8/interface/api/728
     * @param requestParam
     * @return
     */
    @MockOperation
    @PostMapping(value = "proj_area/search_area_by_drawing_md5/" ,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<DrawAreaVo> getRoomRegionList(RequestParam requestParam){
        return null;
    }
}
