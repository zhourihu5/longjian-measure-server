package com.longfor.longjian.measure.app.appService.appService;

import com.alibaba.fastjson.JSONObject;
import com.longfor.longjian.common.exception.CommonException;
import com.longfor.longjian.measure.app.req.staff.*;
import com.longfor.longjian.measure.app.vo.staffVo.AllowUserSearchVo;
import com.longfor.longjian.measure.app.vo.staffVo.RepairerListSearchVo;

import java.util.List;

/**
 * Created by Wang on 2019/1/7.
 */
public interface IStaffService {

    JSONObject squadSearch(SquadSearchReq squadSearchReq) throws CommonException;

    List<AllowUserSearchVo> allowUserSearch(AllowUserSearchReq allowUserSearchReq);

    List<RepairerListSearchVo>repairerListSearch(RepairerListSearchReq repairerListSearchReq);

    void squadAdd(SquadAddReq squadAddReq);

    void squadUpdate(SquadUpdateReq squadUpdateReq);

    void squadDelete(SquadDeleteReq squadDeleteReq);

    void repairerListUpdate(RepairerListUpdateReq repairerListUpdateReq);
}
