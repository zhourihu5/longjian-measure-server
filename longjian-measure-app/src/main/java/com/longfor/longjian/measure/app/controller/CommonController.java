package com.longfor.longjian.measure.app.controller;

import com.longfor.gaia.gfs.web.mock.MockOperation;
import com.longfor.longjian.common.base.LjBaseResponse;
import com.longfor.longjian.measure.app.vo.ItemsVo;
import com.longfor.longjian.measure.app.vo.commonVo.TaskVo;
import com.longfor.longjian.measure.app.vo.measureAnalysisVo.CheckItemVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * 类似公共服务
 * wangxs
 * 2018-11-19
 */
@RestController
@Slf4j
public class CommonController {

    /**
     * 项目实测实量任务记录导出pdf
     * http://192.168.37.159:3000/project/8/interface/api/744
     * 项目创建实测实量任务生成任务
     * http://192.168.37.159:3000/project/8/interface/api/756
     * @param requestParam
     * @return
     */
    @MockOperation
    @GetMapping(value = "papi/v3/bgtask/bgtask/my_tasks/",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<ItemsVo<List<TaskVo>>> getTasks(RequestParam requestParam){
        return null;
    }

    /**
     * 项目创建实测实量任务请求检查项
     * http://192.168.37.159:3000/project/8/interface/api/746
     * @return
     */
    @MockOperation
    @GetMapping(value = "check_item/v1/papi/category/get_root_category/",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<ItemsVo<List<CheckItemVo>>> getCheckItems(RequestParam requestParam){
        return null;
    }


}
