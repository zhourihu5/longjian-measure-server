package com.longfor.longjian.measure.app.controller.proMeasureManagerController;

import com.longfor.gaia.gfs.web.mock.MockOperation;
import com.longfor.longjian.common.base.LjBaseResponse;
import com.longfor.longjian.measure.app.appService.proMeasureManagerService.IProMeasureService;
import com.longfor.longjian.measure.app.req.proMeasureManagerReq.GetCheckerListReq;
import com.longfor.longjian.measure.app.req.proMeasureManagerReq.GetProMeasureAreaListReq;
import com.longfor.longjian.measure.app.req.proMeasureManagerReq.GetProMeasureCheckItemsReq;
import com.longfor.longjian.measure.app.req.proMeasureManagerReq.GetProMeasurePlanListReq;
import com.longfor.longjian.measure.app.vo.ItemsVo;
import com.longfor.longjian.measure.app.vo.proMeasureVo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.List;

/**
 * go服务
 * 项目实测实量
 * @author wangxs
 * @date 2018-11-17 14:07
 */
@RestController
@RequestMapping("v3/")
@Slf4j
public class ProMeasureController {

    @Autowired
    private IProMeasureService proMeasureService;

    /**
     * go项目实测任务列表
     * http://192.168.37.159:3000/project/8/interface/api/140
     * @param getProMeasurePlanListReq
     * @return
     */
    @GetMapping(value = "measure/measure_list/search_json/" ,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<ProMeasurePlanListVo> getProMeasurePlanList(@Valid GetProMeasurePlanListReq getProMeasurePlanListReq, HttpServletRequest request) throws Exception {
        LjBaseResponse<ProMeasurePlanListVo> ljBaseResponse = proMeasureService.getProMeasurePlanList(getProMeasurePlanListReq,request);
        return ljBaseResponse;
    }

    /**
     * go项目实测任务列表请求检查项
     * http://192.168.37.159:3000/project/8/interface/api/148
     * @param getProMeasureCheckItemsReq
     * @return
     */
    @GetMapping(value = "measure/measure_list/sub_categorys/" ,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<ItemsVo<List<ProMeasureCheckIteamVo>>> getProMeasureCheckItems(@Valid GetProMeasureCheckItemsReq getProMeasureCheckItemsReq,HttpServletRequest request) throws Exception {
        LjBaseResponse<ItemsVo<List<ProMeasureCheckIteamVo>>> ljBaseResponse = proMeasureService.getProMeasureCheckItems(getProMeasureCheckItemsReq,request);
        return ljBaseResponse;
    }

    /**
     * go项目实测任务列表区域
     * http://192.168.37.159:3000/project/8/interface/api/150
     * go项目实测任务列表区域详情楼层
     * http://192.168.37.159:3000/project/8/interface/api/160
     * @param getProMeasureAreaListReq
     * @return
     */
    @GetMapping(value = "area/area/subs/" ,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<AreaInfoVo> getProMeasureAreaList(@Valid GetProMeasureAreaListReq getProMeasureAreaListReq) throws IntrospectionException, InstantiationException, IllegalAccessException, InvocationTargetException {
        LjBaseResponse<AreaInfoVo> ljBaseResponse = proMeasureService.getProMeasureAreaList(getProMeasureAreaListReq);
        return ljBaseResponse;
    }

    /**
     * go项目实测任务列表请求检查人员
     * http://192.168.37.159:3000/project/8/interface/api/154
     * @param getCheckerListReq
     * @return
     */
    @GetMapping(value = "measure/ajax_json/user_list/" ,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<ItemsVo<List<CheckerVo>>> getCheckerList(@Valid GetCheckerListReq getCheckerListReq){
        LjBaseResponse<ItemsVo<List<CheckerVo>>> ljBaseResponse = proMeasureService.getCheckerList(getCheckerListReq);
        return ljBaseResponse;
    }


}
