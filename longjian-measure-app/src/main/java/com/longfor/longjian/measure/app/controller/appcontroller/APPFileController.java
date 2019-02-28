package com.longfor.longjian.measure.app.controller.appcontroller;

import com.longfor.gaia.gfs.web.mock.MockOperation;
import com.longfor.longjian.common.base.LjBaseResponse;
import com.longfor.longjian.measure.app.vo.appfilevo.FileDownVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *   app 文件操作
 * wangxs
 * 2018-11-20
 */
@RestController
@RequestMapping("v3/api/")
@Slf4j
public class APPFileController {

    /**
     * 文件下载
     * http://192.168.37.159:3000/project/8/interface/api/1444
     * @return
     */
    @MockOperation
    @GetMapping(value = "file/download/",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<FileDownVo> download(RequestParam requestParam){
        return null;
    }

    /**
     * 文件上传
     * http://192.168.37.159:3000/project/8/interface/api/1452
     * @return
     */
    @MockOperation
    @GetMapping(value = "file/upload/",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void upload(RequestParam requestParam){
    }


}
