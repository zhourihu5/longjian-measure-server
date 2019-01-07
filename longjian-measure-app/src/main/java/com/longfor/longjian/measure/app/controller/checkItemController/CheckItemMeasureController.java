package com.longfor.longjian.measure.app.controller.checkItemController;

import com.longfor.longjian.common.base.LjBaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Wang on 2019/1/7.
 */
@RestController
@RequestMapping("v3/check_item/measure/")
@Slf4j
public class CheckItemMeasureController {

    /**
     *
     * @return
     */
    @PostMapping(value = "file/" ,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse file() {
        return null;
    }
}
