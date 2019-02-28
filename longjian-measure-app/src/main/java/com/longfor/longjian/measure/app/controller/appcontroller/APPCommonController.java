package com.longfor.longjian.measure.app.controller.appcontroller;

import com.longfor.gaia.gfs.web.mock.MockOperation;
import com.longfor.longjian.common.base.LjBaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *   app 类似公共
 * wangxs
 * 2018-11-20
 */
@RestController
@Slf4j
public class APPCommonController {
    /**
     * 项目同步/v2/api/team_and_project/setting
     *http://192.168.37.159:3000/project/8/interface/api/588
     * @return
     */
    @MockOperation
    @GetMapping(value = "v2/api/team_and_project/setting/",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse setting(RequestParam requestParam){
        return null;
    }

    /**
     * 项目同步/v3/api/info/area_v2
     * http://192.168.37.159:3000/project/8/interface/api/590
     * @return
     */
    @MockOperation
    @GetMapping(value = "v3/api/info/area_v2/",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse areaV2(RequestParam requestParam){
        return null;
    }

    /**
     * 项目同步/v3/api/info/check_item_v3
     * http://192.168.37.159:3000/project/8/interface/api/616
     * @return
     */
    @MockOperation
    @GetMapping(value = "v3/api/info/check_item_v3",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse checkItemV3(RequestParam requestParam){
        return null;
    }

    /**
     * aaa项目同步/v3/api/info/check_item_attachment
     * http://192.168.37.159:3000/project/8/interface/api/622
     * @return
     */
    @MockOperation
    @GetMapping(value = "v3/api/info/check_item_attachment",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse checkItemAttachment(RequestParam requestParam){
        return null;
    }



    /**
     * 项目同步/core_srv_check_item/check_item/fixing_preset_app/app_list_fixing_preset
     * http://192.168.37.159:3000/project/8/interface/api/636
     * @return
     */
    @MockOperation
    @GetMapping(value = "core_srv_check_item/check_item/fixing_preset_app/app_list_fixing_preset/",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse appListFixingPreset(RequestParam requestParam){
        return null;
    }


    /**
     * 项目同步/uc/app_api/user_related_org_info/
     * http://192.168.37.159:3000/project/8/interface/api/656
     * @return
     */
    @MockOperation
    @GetMapping(value = "uc/app_api/user_related_org_info/",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse userRelatedOrgInfo(RequestParam requestParam){
        return null;
    }

    /**
     * /v3/api/project/members/
     * http://192.168.37.159:3000/project/8/interface/api/1428
     * @return
     */
    @MockOperation
    @GetMapping(value = "v3/api/project/members/",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse members(RequestParam requestParam){
        return null;
    }
}
