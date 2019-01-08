package com.longfor.longjian.measure.app.appService.appService.impl;

import com.longfor.longjian.measure.app.appService.appService.IZoneService;
import com.longfor.longjian.measure.app.req.zone.*;
import com.longfor.longjian.measure.domain.externalService.IMeasureZoneResultService;
import com.longfor.longjian.measure.domain.externalService.IMeasureZoneService;
import com.longfor.longjian.measure.po.zhijian2.MeasureZoneResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Wang on 2019/1/8.
 */
@Slf4j
@Service
public class IZoneServiceImpl implements IZoneService {

    @Resource
    private IMeasureZoneResultService measureZoneResultService;

    @Resource
    private IMeasureZoneService measureZoneService;

    @Override
    public List getResult(GetResultReq getResultReq) {
        MeasureZoneResult measureZoneResult=new MeasureZoneResult();

        measureZoneResult.setProjectId(getResultReq.getProject_id());
        measureZoneResult.setZoneUuid(getResultReq.getZone_uuid());

        List<MeasureZoneResult> measureZoneResults=measureZoneResultService.getMeasureZoneResult(measureZoneResult);

        return null;
    }

    @Override
    public void updateStatus(UpdateStatusReq updateStatusReq) {

        String [] zoneId=updateStatusReq.getZone_id_list().split(",");

        Map<String,Object> map=new HashMap<>();

        map.put("project_id",updateStatusReq.getProject_id());
        map.put("close_status",updateStatusReq.getClose_status());
        map.put("zoneId",zoneId);

        measureZoneService.updateStatus(map);

    }

    @Override
    @Transactional
    public void delByUuidList(DelByUuidListReq delByUuidListReq) {

        String [] zoneId=delByUuidListReq.getUuid_list().split(",");

        Map<String,Object> map=new HashMap<>();

        map.put("project_id",delByUuidListReq.getProject_id());
        map.put("zoneId",zoneId);
        map.put("delete_at",new Date());

        measureZoneResultService.delByUuidList(map);

        measureZoneService.delByUuidList(map);

    }

    @Override
    public void delBySquadIdUuid(DelBySquadIdUuidReq delBySquadIdUuidReq) {

        String [] zoneId=delBySquadIdUuidReq.getZone_uuid_list().split(",");

        Map<String,Object> map=new HashMap<>();

        map.put("project_id",delBySquadIdUuidReq.getProject_id());
        map.put("zoneId",zoneId);
        map.put("squad_id",delBySquadIdUuidReq.getSquad_id());
        map.put("delete_at",new Date());

        measureZoneResultService.delBySquadIdUuid(map);

    }

    @Override
    public void delByZoneUuid(DelByZoneUuidReq delByZoneUuidReq) {

        String [] zoneId=delByZoneUuidReq.getUuid_list().split(",");

        Map<String,Object> map=new HashMap<>();

        map.put("project_id",delByZoneUuidReq.getProject_id());
        map.put("zoneId",zoneId);
        map.put("squad_id",null);
        map.put("delete_at",new Date());

        measureZoneResultService.delBySquadIdUuid(map);

    }
}
