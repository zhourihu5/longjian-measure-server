package com.longfor.longjian.measure.app.appService.appService;

import com.longfor.longjian.measure.app.req.staff.RepairerListUpdateReq;
import com.longfor.longjian.measure.app.req.staff.SquadAddReq;
import com.longfor.longjian.measure.app.req.staff.SquadDeleteReq;
import com.longfor.longjian.measure.app.req.staff.SquadUpdateReq;

/**
 * Created by Wang on 2019/1/7.
 */
public interface IStaffService {

    void squadAdd(SquadAddReq squadAddReq);

    void squadUpdate(SquadUpdateReq squadUpdateReq);

    void squadDelete(SquadDeleteReq squadDeleteReq);

    void repairerListUpdate(RepairerListUpdateReq repairerListUpdateReq);
}
