package com.longfor.longjian.measure.app.controller.measureV1PapiController;

import com.longfor.longjian.common.base.LjBaseResponse;
import com.longfor.longjian.measure.app.appService.appService.IAPPMeasureListService;
import com.longfor.longjian.measure.app.req.MeasureList.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by Wang on 2019/1/7.
 */
@RestController
@RequestMapping("measure/v1/papi/measure_list/")
@Slf4j
public class MeasureListController {


    @Autowired
    private IAPPMeasureListService measureListService;

    /**
     *修改 任务状态
     * @param setStatusReq
     * @return
     */
    @PostMapping(value = "set_status" , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse setStatus(@Valid @RequestBody SetStatusReq setStatusReq) {
        return new LjBaseResponse(measureListService.setStatus(setStatusReq));
    }

    /**
     * 修改任务名称
     * @param updateNameReq
     * @return
     */
    @PostMapping(value = "update_name" , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse updateName(@Valid @RequestBody UpdateNameReq updateNameReq) {
        return new LjBaseResponse(measureListService.updateName(updateNameReq));
    }

    /**
     * 删除任务
     * @param deleteReq
     * @return
     */
    @PostMapping(value = "delete" , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse delete(@Valid @RequestBody DeleteReq deleteReq) {
        measureListService.delete(deleteReq);
        return new LjBaseResponse();
    }

    /**
     * 实测任务完成状态批量修改
     * @param updateFinishStatusReq
     * @return
     */
    @PostMapping(value = "update_finish_status" , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse updateFinishStatus(@Valid @RequestBody UpdateFinishStatusReq updateFinishStatusReq) {
        measureListService.updateFinishStatus(updateFinishStatusReq);
        return new LjBaseResponse();
    }

    /**
     * 实测任务开关状态批量修改
     * @param updateCloseStatusReq
     * @return
     */
    @PostMapping(value = "update_close_status" , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse updateCloseStatus(@Valid @RequestBody UpdateCloseStatusReq updateCloseStatusReq) {
        measureListService.updateCloseStatus(updateCloseStatusReq);
        return new LjBaseResponse();
    }


    @PostMapping(value = "bg_add" , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse bgAdd(@Valid @RequestBody UpdateCloseStatusReq updateCloseStatusReq) {
        measureListService.updateCloseStatus(updateCloseStatusReq);
        return new LjBaseResponse();
    }


}
