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
@RequestMapping("oapi/v3/check_item/measure/")
@Slf4j
public class OapiCheckItemMeasureController {
    /**
     *
     * @return
     */
    @GetMapping(value = "get_category_json/" ,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse getCategoryJson() {
        return null;
    }


    /**
     *
     * @return
     */
    @GetMapping(value = "get_check_item_json/" ,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse getCheckItemJson() {
        return null;


    }

    /**
     *
     * @return
     */
    @PostMapping(value = "update_json/" ,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse updateJson() {
        return null;


    }


    /**
     *
     * @return
     */
    @GetMapping(value = "list_tree_json/" ,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse listTreeJson() {
        return null;


    }
}
