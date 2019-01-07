package com.longfor.longjian.measure.app.controller.measureV1PapiController;

import com.longfor.longjian.common.base.LjBaseResponse;
import com.longfor.longjian.measure.app.appService.appService.MeasureListService;
import com.longfor.longjian.measure.app.req.MeasureList.SetStatusReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by Wang on 2019/1/7.
 */
@RestController
@RequestMapping("measure/v1/papi/measure_list/")
@Slf4j
public class MeasureListController {


    @Autowired
    private MeasureListService measureListService;

    /**
     *修改 MeasureList状态
     * @param setStatusReq
     * @return
     * @throws IntrospectionException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    @GetMapping(value = "set_status" ,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse setStatus(@Valid SetStatusReq setStatusReq) {
        return new LjBaseResponse(measureListService.setStatus(setStatusReq));
    }
}
