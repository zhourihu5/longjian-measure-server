package com.longfor.longjian.measure.app.controller.proMeasureManagerController;

import com.longfor.longjian.common.base.LjBaseResponse;
import com.longfor.longjian.measure.app.appService.proMeasureManagerService.IProMeasureService;
import com.longfor.longjian.measure.app.req.proMeasureManagerReq.GetCheckerListReq;
import com.longfor.longjian.measure.app.vo.ItemsVo;
import com.longfor.longjian.measure.app.vo.proMeasureVo.CheckerVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * go服务
 * 项目实测实量
 * @author wangxs
 * @date 2018-11-17 14:07
 */
@RestController
@RequestMapping("oapi/v3/measure/")
@Slf4j
public class ProOapiMeasureController {
    @Autowired
    private IProMeasureService proMeasureService;


    /**
     * go项目实测任务列表请求检查人员
     * http://192.168.37.159:3000/project/8/interface/api/154
     * @param getCheckerListReq
     * @return
     */
    @GetMapping(value = "ajax_json/user_list/" ,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<ItemsVo<List<CheckerVo>>> getCheckerList(@Valid GetCheckerListReq getCheckerListReq){
        LjBaseResponse<ItemsVo<List<CheckerVo>>> ljBaseResponse = proMeasureService.getCheckerList(getCheckerListReq);
        return ljBaseResponse;
    }
}
