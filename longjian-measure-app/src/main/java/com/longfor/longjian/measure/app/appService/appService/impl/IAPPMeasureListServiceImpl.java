package com.longfor.longjian.measure.app.appService.appService.impl;

import com.longfor.longjian.measure.app.appService.appService.IAPPMeasureListService;
import com.longfor.longjian.measure.app.req.MeasureList.*;
import com.longfor.longjian.measure.app.vo.measureListVo.SetStatusVo;
import com.longfor.longjian.measure.domain.externalService.*;
import com.longfor.longjian.measure.domain.externalService.impl.MeasureListServiceImpl;
import com.longfor.longjian.measure.po.zhijian2.MeasureList;
import com.longfor.longjian.measure.po.zhijian2.MeasureZone;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wang on 2019/1/7.
 */
@Service
@Slf4j
public class IAPPMeasureListServiceImpl implements IAPPMeasureListService {

    @Resource
    private IMeasureListService iMeasureListService;

    @Resource
    private IMeasureListAreaService IMeasureListAreaService;

    @Resource
    private IMeasureZoneService iMeasureZoneService;

    @Resource
    private IMeasureZoneResultService iMeasureZoneResultService;

    @Resource
    private IMeasureSquadService iMeasureSquadService;

    @Resource
    private IMeasureSquadUserService iMeasureSquadUserService;

    @Autowired IMeasureZoneService measureZoneService;
    @Resource
    private IMeasureListService measureListService;
    @Override
    public SetStatusVo setStatus(SetStatusReq setStatusReq) {

        MeasureList measureList=new MeasureList();

        measureList.setId(setStatusReq.getList_id());
        measureList.setProjectId(setStatusReq.getProject_id());
        measureList.setCloseStatus(setStatusReq.getClose_status());
        measureList.setFinishStatus(setStatusReq.getFinish_status());

        iMeasureListService.updateMeasureList(measureList);

        SetStatusVo setStatusVo=new SetStatusVo();

        setStatusVo.setList_id(setStatusReq.getList_id());
        setStatusVo.setClose_status(setStatusReq.getClose_status());
        setStatusVo.setFinish_status(setStatusReq.getFinish_status());

        return setStatusVo;
    }

    @Override
    public Map<String,Object> updateName(UpdateNameReq updateNameReq) {

        MeasureList measureList=new MeasureList();
        measureList.setId(updateNameReq.getList_id());
        measureList.setProjectId(updateNameReq.getProject_id());
        measureList.setName(updateNameReq.getName());
        iMeasureListService.updateMeasureList(measureList);

        Map<String,Object>map=new HashMap<>();
        map.put("name",updateNameReq.getName());
        return map;
    }

    @Override
    @Transactional
    public void delete(DeleteReq deleteReq) {
        iMeasureListService.delete(deleteReq.getList_id());

        IMeasureListAreaService.delete(deleteReq.getList_id());

        iMeasureZoneService.delete(deleteReq.getList_id());

        iMeasureZoneResultService.delete(deleteReq.getList_id());

        iMeasureSquadService.delete(deleteReq.getList_id());

        iMeasureSquadUserService.delete(deleteReq.getList_id());
    }

    @Override
    public void updateFinishStatus(UpdateFinishStatusReq updateFinishStatusReq) {

        Map<String,Object> map=new HashMap<>();

        map.put("project_id",updateFinishStatusReq.getProject_id());
        map.put("finish_status",updateFinishStatusReq.getFinish_status());
        map.put("list_ids",updateFinishStatusReq.getList_ids().split(","));
        map.put("update_at",new Date());

        iMeasureListService.updateFinishStatus(map);

    }

    @Override
    public void updateCloseStatus(UpdateCloseStatusReq updateCloseStatusReq) {
        Map<String,Object> map=new HashMap<>();

        map.put("project_id",updateCloseStatusReq.getProject_id());
        map.put("close_status",updateCloseStatusReq.getClose_status());
        map.put("list_ids",updateCloseStatusReq.getList_ids().split(","));
        map.put("update_at",new Date());

        iMeasureListService.updateCloseStatus(map);
    }
    @Override
    public MeasureList GetByProjIdAndIdNoFoundErr(Integer projectId , Integer id) {
        return measureListService.GetByProjIdAndIdNoFoundErr(projectId , id);
    }
}
