package com.longfor.longjian.measure.app.controller.checkItemController;

import com.longfor.longjian.common.base.LjBaseResponse;
import com.longfor.longjian.common.exception.CommonException;
import com.longfor.longjian.measure.app.appService.CheckItemMeasureService.IOapiCheckItemMeasureService;
import com.longfor.longjian.measure.app.req.fileReq.FileReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Wang on 2019/1/7.
 */
@RestController
@RequestMapping("v3/check_item/measure/")
@Slf4j
public class CheckItemMeasureController {
    @Autowired
    private IOapiCheckItemMeasureService oapiCheckItemMeasureService;

    /**
     * 集团检查项管理下载
     * http://192.168.37.159:3000/project/8/interface/api/3100
     * http://192.168.37.159:3000/mock/8/devlongjian.longhu.net/v3/check_item/measure/file/?team_id=4&id=18400&_download
     *
     * @return
     */
    @RequestMapping(value = "file/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<Object> file(FileReq fileReq, HttpServletRequest request, HttpServletResponse response) throws Exception {
        LjBaseResponse<Object> ljBaseResponse = oapiCheckItemMeasureService.file(fileReq,request,response);
        return ljBaseResponse;
    }
}
