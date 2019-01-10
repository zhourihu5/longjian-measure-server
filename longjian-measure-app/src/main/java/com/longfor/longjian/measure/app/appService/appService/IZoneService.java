package com.longfor.longjian.measure.app.appService.appService;

import com.alibaba.fastjson.JSONObject;
import com.longfor.longjian.common.exception.CommonException;
import com.longfor.longjian.measure.app.req.zone.*;

import java.util.List;

/**
 * Created by Wang on 2019/1/8.
 */
public interface IZoneService {

   JSONObject getResult(GetResultReq getResultReq);


   JSONObject paginationSearch(PaginationSearchReq paginationSearchReq) throws CommonException;


   void updateStatus(UpdateStatusReq  updateStatusReq);


   void delByUuidList(DelByUuidListReq delByUuidListReq);


   void delBySquadIdUuid(DelBySquadIdUuidReq delBySquadIdUuidReq);

   void delByZoneUuid(DelByZoneUuidReq delByZoneUuidReq);


}
