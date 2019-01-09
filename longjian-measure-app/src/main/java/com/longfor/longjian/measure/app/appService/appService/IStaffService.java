package com.longfor.longjian.measure.app.appService.appService;

import com.github.pagehelper.Page;
import com.longfor.longjian.common.exception.CommonException;
import com.longfor.longjian.measure.app.req.staff.*;

/**
 * Created by Wang on 2019/1/7.
 */
public interface IStaffService {

    Page squadSearch(SquadSearchReq squadSearchReq) throws CommonException;

    void squadAdd(SquadAddReq squadAddReq);

    void squadUpdate(SquadUpdateReq squadUpdateReq);

    void squadDelete(SquadDeleteReq squadDeleteReq);

    void repairerListUpdate(RepairerListUpdateReq repairerListUpdateReq);
}
