package com.longfor.longjian.measure.app.controller;

import com.longfor.gaia.gfs.web.mock.MockOperation;
import com.longfor.longjian.common.base.LjBaseResponse;
import com.longfor.longjian.measure.app.vo.CheckItemsVo.CheckItemsPlanTypeVo;
import com.longfor.longjian.measure.app.vo.ItemsVo;
import com.longfor.longjian.measure.app.vo.measureAnalysisVo.CheckItemVo;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.cert.ocsp.Req;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *
 * @author wangxs
 * @date 2018-11-17 12:17
 */
@RestController
@RequestMapping("oapi/v3/")
@Slf4j
public class CheckItemsController {

    /**
     * go集团实测检查项管理
     * @return
     */
    @MockOperation
    @GetMapping(value = "check_item/check_item/list/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<ItemsVo<List<CheckItemVo>>> getCheckItemsManagerList(RequestParam requestParam){
        return null;
    }

    /**
     * go集团实测检查项管理任务类型
     * @param requestParam
     * @return
     */
    @MockOperation
    @GetMapping(value = "check_item/check_item/list_tree_json/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<ItemsVo<List<CheckItemsPlanTypeVo>>> getCheckItemsPlanType(RequestParam requestParam){
        return null;
    }

}
