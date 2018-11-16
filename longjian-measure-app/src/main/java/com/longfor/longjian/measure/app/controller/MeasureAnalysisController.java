package com.longfor.longjian.measure.app.controller;

import com.longfor.gaia.gfs.web.mock.MockOperation;
import com.longfor.longjian.common.base.LjBaseResponse;
import com.longfor.longjian.measure.app.vo.CheckItemListVO;
import com.longfor.longjian.measure.app.vo.CheckItemVO;
import com.longfor.longjian.measure.app.vo.CompanyListVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * @author wangxs
 * @date 2018-11-16 14:16
 */
@RestController
@RequestMapping("gapi/v3/")
@Slf4j
public class MeasureAnalysisController {

    /**
     * 集团统计分析检查项
     * @param requestParam
     * @return
     */
    @MockOperation
    @PostMapping(value = "res/category", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<CheckItemListVO> getAnalysisCheckItems(RequestParam requestParam){
        return null;
    }
}
