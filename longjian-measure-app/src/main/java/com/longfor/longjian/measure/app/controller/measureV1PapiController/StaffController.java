package com.longfor.longjian.measure.app.controller.measureV1PapiController;

import com.longfor.longjian.common.base.LjBaseResponse;
import com.longfor.longjian.common.exception.CommonException;
import com.longfor.longjian.measure.app.appService.appService.IStaffService;
import com.longfor.longjian.measure.app.req.staff.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by Wang on 2019/1/7.
 */
@RestController
@RequestMapping("measure/v1/papi/staff/")
@Slf4j
public class StaffController {

    @Autowired
    private IStaffService iStaffService;


    /**
     * 搜索检查小组
     * @param squadSearchReq
     * @return
     * @throws CommonException
     */
    @RequestMapping(value = "squad_search" , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse squadSearch(@Valid SquadSearchReq squadSearchReq) throws CommonException {
        return new LjBaseResponse(iStaffService.squadSearch(squadSearchReq));
    }

    /**
     * 项目任务列表详情修改小组人员
     * @param allowUserSearchReq
     * @return
     * @throws CommonException
     */
    @RequestMapping(value = "allow_user_search" , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse allowUserSearch(@Valid AllowUserSearchReq allowUserSearchReq) throws CommonException {
        return new LjBaseResponse(iStaffService.allowUserSearch(allowUserSearchReq));
    }

    @RequestMapping(value = "repairer_list_search" , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse repairerListSearch( @Valid RepairerListSearchReq repairerListSearchReq) throws CommonException {
        return new LjBaseResponse(iStaffService.repairerListSearch(repairerListSearchReq));
    }



    /**
     * 添加检查小组
     * @param squadAddReq
     * @return
     */
    @RequestMapping(value = "squad_add" , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse squadAdd(@Valid SquadAddReq squadAddReq) {
        iStaffService.squadAdd(squadAddReq);
        return new LjBaseResponse();
    }


    /**
     * 实测小组更新
     * @param squadUpdateReq
     * @return
     */
    @RequestMapping(value = "squad_update" , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse squadUpdate(@Valid SquadUpdateReq squadUpdateReq) {
        iStaffService.squadUpdate(squadUpdateReq);
        return new LjBaseResponse();
    }

    /**
     * 删除小组以及小组所有人员
     * @param squadDeleteReq
     * @return
     */
    @RequestMapping(value = "squad_delete" , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse squadDelete(@Valid SquadDeleteReq squadDeleteReq) {
        iStaffService.squadDelete(squadDeleteReq);
        return new LjBaseResponse();
    }

    /**
     * 更新实测跟进人员
     * @param repairerListUpdateReq
     * @return
     */
    @RequestMapping(value = "repairer_list_update" , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse repairerListUpdate(@Valid RepairerListUpdateReq repairerListUpdateReq) {
        iStaffService.repairerListUpdate(repairerListUpdateReq);
        return new LjBaseResponse();
    }
}
