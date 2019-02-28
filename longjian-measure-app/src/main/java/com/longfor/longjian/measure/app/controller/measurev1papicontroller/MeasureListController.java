package com.longfor.longjian.measure.app.controller.measurev1papicontroller;

import com.alibaba.fastjson.JSON;
import com.longfor.longjian.common.base.LjBaseResponse;
import com.longfor.longjian.common.exception.LjBaseRuntimeException;
import com.longfor.longjian.common.util.SessionInfo;
import com.longfor.longjian.measure.app.appservice.appservice.IAPPMeasureListService;
import com.longfor.longjian.measure.app.bgtask.UserTaskNotice;
import com.longfor.longjian.measure.app.req.measurelist.*;
import com.longfor.longjian.measure.app.vo.measurelistvo.BgAddVo;
import com.longfor.longjian.measure.app.vo.measurelistvo.MeasureListInfoVo;
import com.longfor.longjian.measure.consts.enums.BgtaskEnum;
import com.longfor.longjian.measure.util.ConvertUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * Created by Wang on 2019/1/7.
 */
@RestController
@RequestMapping("measure/v1/papi/measure_list/")
@Slf4j
public class MeasureListController {


    @Autowired
    private IAPPMeasureListService measureListService;
    @Autowired
    private UserTaskNotice userTaskNotice;
    @Autowired
    private SessionInfo sessionInfo;

    /**
     *修改 任务状态
     * @param setStatusReq
     * @return
     */
    @RequestMapping(value = "set_status" , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse setStatus(@Valid SetStatusReq setStatusReq) {
        return new LjBaseResponse(measureListService.setStatus(setStatusReq));
    }

    /**
     * 修改任务名称
     * @param updateNameReq
     * @return
     */
    @RequestMapping(value = "update_name" , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse updateName(@Valid UpdateNameReq updateNameReq) {
        return new LjBaseResponse(measureListService.updateName(updateNameReq));
    }

    /**
     * 删除任务
     * @param deleteReq
     * @return
     */
    @RequestMapping(value = "delete" , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse delete(@Valid DeleteReq deleteReq) {
        measureListService.delete(deleteReq);
        return new LjBaseResponse();
    }

    /**
     * 实测任务完成状态批量修改
     * @param updateFinishStatusReq
     * @return
     */
    @RequestMapping(value = "update_finish_status" , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse updateFinishStatus(@Valid UpdateFinishStatusReq updateFinishStatusReq) {
        measureListService.updateFinishStatus(updateFinishStatusReq);
        return new LjBaseResponse();
    }

    /**
     * 实测任务开关状态批量修改
     * @param updateCloseStatusReq
     * @return
     */
    @RequestMapping(value = "update_close_status" , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse updateCloseStatus(@Valid UpdateCloseStatusReq updateCloseStatusReq) {
        measureListService.updateCloseStatus(updateCloseStatusReq);
        return new LjBaseResponse();
    }


    /**
     * 异步添加实测任务
     * @param bgAddReq
     * @return
     */
    @RequestMapping(value = "bg_add" , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<BgAddVo> bgAdd(@Valid BgAddReq bgAddReq) throws LjBaseRuntimeException, IllegalAccessException, IntrospectionException, InvocationTargetException {
        LjBaseResponse<BgAddVo> ljBaseResponse = new LjBaseResponse<>();
        BgAddVo bgAddVo = new BgAddVo();
        Map params = null;
            params = ConvertUtil.convertBean(bgAddReq);
        log.info(JSON.toJSONString(params));
        Integer userId = sessionInfo.getSessionUser().getUserId();
        String r = null;
        try {
            r = userTaskNotice.sendToRedis(userId, BgtaskEnum.MEASURE_LIST_CREATE,"",params, null);
        } catch (Exception e) {
           throw new LjBaseRuntimeException(-9999,e+"");
        }
        bgAddVo.setId(r);
        ljBaseResponse.setData(bgAddVo);
        return ljBaseResponse;
    }

    /**
     * http://192.168.37.159:3000/project/8/interface/api/772
     * http://192.168.37.159:3000/mock/8/measure/v1/papi/measure_list/condition_search/?_ct=json&project_id=927&page_level=project&group_id=4&team_id=25&category_key=&area_id=&finish_status=&name=&user_id_list=&page=1&page_size=20
     * @param req
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "condition_search",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<MeasureListInfoVo> conditionSearch(@Valid ConditionSearchReq req) throws LjBaseRuntimeException {
        return measureListService.conditionSearch(req);
    }

}
