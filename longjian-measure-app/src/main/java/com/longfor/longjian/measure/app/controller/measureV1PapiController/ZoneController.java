package com.longfor.longjian.measure.app.controller.measureV1PapiController;

import com.longfor.longjian.common.base.LjBaseResponse;
import com.longfor.longjian.common.exception.CommonException;
import com.longfor.longjian.measure.app.appService.appService.IZoneService;
import com.longfor.longjian.measure.app.req.zone.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by Wang on 2019/1/8.
 */
@RestController
@RequestMapping("measure/v1/papi/zone/")
@Slf4j
public class ZoneController {

    @Autowired
    private IZoneService zoneService;

    /**
     * 获取测区
     * @param paginationSearchReq
     * @return
     */
    @GetMapping(value = "pagination_search" , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse paginationSearch(PaginationSearchReq paginationSearchReq)throws CommonException {
        return new LjBaseResponse(zoneService.paginationSearch(paginationSearchReq));
    }

    /**
     * 获取单个测区结果
     * @param getResultReq
     * @return
     */
    @GetMapping(value = "get_result" , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse getResult( GetResultReq getResultReq) {
        return new LjBaseResponse(zoneService.getResult(getResultReq));
    }

    /**
     * 更新测区状态
     * @param updateStatusReq
     * @return
     */
    @PostMapping(value = "update_status" , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse updateStatus(@Valid @RequestBody UpdateStatusReq updateStatusReq) {
        zoneService.updateStatus(updateStatusReq);
        return new LjBaseResponse();
    }

    /**
     * 删除测区以及所有测量结果
     * @param delByUuidListReq
     * @return
     */
    @PostMapping(value = "del_by_uuid_list" , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse delByUuidList(@Valid @RequestBody DelByUuidListReq delByUuidListReq) {
        zoneService.delByUuidList(delByUuidListReq);
        return new LjBaseResponse();
    }

    /**
     *删除小队的指定测量结果
     * @param delBySquadIdUuidReq
     * @return
     */
    @PostMapping(value = "del_result_by_squad_id_zone_uuid_list" , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse delBySquadIdUuid(@Valid @RequestBody DelBySquadIdUuidReq delBySquadIdUuidReq) {
        zoneService.delBySquadIdUuid(delBySquadIdUuidReq);
        return new LjBaseResponse();
    }

    /**
     * 删除单个测区下的所有测量结果
     * @param delByZoneUuidReq
     * @return
     */
    @PostMapping(value = "del_result_by_zone_uuid_list" , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse delByZoneUuid(@Valid @RequestBody DelByZoneUuidReq delByZoneUuidReq) {
        zoneService.delByZoneUuid(delByZoneUuidReq);
        return new LjBaseResponse();
    }

}
