package com.longfor.longjian.measure.app.appService.appService;

import com.longfor.longjian.measure.app.req.zone.*;

import java.util.List;

/**
 * Created by Wang on 2019/1/8.
 */
public interface IZoneService {

   List getResult(GetResultReq getResultReq);


   void updateStatus(UpdateStatusReq  updateStatusReq);


   void delByUuidList(DelByUuidListReq delByUuidListReq);


   void delBySquadIdUuid(DelBySquadIdUuidReq delBySquadIdUuidReq);

   void delByZoneUuid(DelByZoneUuidReq delByZoneUuidReq);


}
