package com.longfor.longjian.measure.app.controller.measureV1PapiController;

import com.longfor.longjian.common.base.LjBaseResponse;
import com.longfor.longjian.measure.app.appService.appService.IStaffService;
import com.longfor.longjian.measure.app.req.staff.RepairerListUpdateReq;
import com.longfor.longjian.measure.app.req.staff.SquadAddReq;
import com.longfor.longjian.measure.app.req.staff.SquadDeleteReq;
import com.longfor.longjian.measure.app.req.staff.SquadUpdateReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
     * 添加检查小组
     * @param squadAddReq
     * @return
     */
    @PostMapping(value = "squad_add" , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse squadAdd(@Valid @RequestBody SquadAddReq squadAddReq) {
        iStaffService.squadAdd(squadAddReq);
        return new LjBaseResponse();
    }


    /**
     * 实测小组更新
     * @param squadUpdateReq
     * @return
     */
    @PostMapping(value = "squad_update" , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse squadUpdate(@Valid @RequestBody SquadUpdateReq squadUpdateReq) {
        iStaffService.squadUpdate(squadUpdateReq);
        return new LjBaseResponse();
    }

    /**
     * 删除小组以及小组所有人员
     * @param squadDeleteReq
     * @return
     */
    @PostMapping(value = "squad_delete" , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse squadDelete(@Valid @RequestBody SquadDeleteReq squadDeleteReq) {
        iStaffService.squadDelete(squadDeleteReq);
        return new LjBaseResponse();
    }

    /**
     * 更新实测跟进人员
     * @param repairerListUpdateReq
     * @return
     */
    @PostMapping(value = "repairer_list_update" , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse repairerListUpdate(@Valid @RequestBody RepairerListUpdateReq repairerListUpdateReq) {
        iStaffService.repairerListUpdate(repairerListUpdateReq);
        return new LjBaseResponse();
    }
}
