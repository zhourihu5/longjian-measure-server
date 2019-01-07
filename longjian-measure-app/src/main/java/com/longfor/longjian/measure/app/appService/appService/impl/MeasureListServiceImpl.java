package com.longfor.longjian.measure.app.appService.appService.impl;

import com.longfor.longjian.measure.app.appService.appService.MeasureListService;
import com.longfor.longjian.measure.app.req.MeasureList.SetStatusReq;
import com.longfor.longjian.measure.app.vo.measureListVo.SetStatusVo;
import com.longfor.longjian.measure.domain.externalService.IMeasureListService;
import com.longfor.longjian.measure.po.zhijian2.MeasureList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Wang on 2019/1/7.
 */
@Service
@Slf4j
public class MeasureListServiceImpl implements MeasureListService {

    @Resource
    private IMeasureListService iMeasureListService;

    @Override
    public SetStatusVo setStatus(SetStatusReq setStatusReq) {

        MeasureList measureList=new MeasureList();

        measureList.setId(setStatusReq.getList_id());
        measureList.setProjectId(setStatusReq.getProject_id());
        measureList.setCloseStatus(setStatusReq.getClose_status());
        measureList.setFinishStatus(setStatusReq.getFinish_status());

        iMeasureListService.setStatus(measureList);

        SetStatusVo setStatusVo=new SetStatusVo();

        setStatusVo.setList_id(setStatusReq.getList_id());
        setStatusVo.setClose_status(setStatusReq.getClose_status());
        setStatusVo.setFinish_status(setStatusReq.getFinish_status());

        return setStatusVo;
    }
}
