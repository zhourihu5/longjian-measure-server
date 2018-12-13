package com.longfor.longjian.measure.app.appService.appMeasureSyncService.impl;

import com.longfor.longjian.common.base.LjBaseResponse;
import com.longfor.longjian.measure.app.appService.appMeasureSyncService.IAPPMeasureSyncService;
import com.longfor.longjian.measure.app.req.apiMeasureRuleReq.ApiMeasureRuleReq;
import com.longfor.longjian.measure.app.req.apiMeasureRuleReq.ApiMyTaskReq;
import com.longfor.longjian.measure.app.vo.appMeasureSyncVo.*;
import com.longfor.longjian.measure.consts.util.ConvertUtil;
import com.longfor.longjian.measure.domain.externalService.ICategoryV3Service;
import com.longfor.longjian.measure.domain.externalService.IMeasureListService;
import com.longfor.longjian.measure.domain.externalService.IMeasureRuleService;
import com.longfor.longjian.measure.po.zhijian2.CategoryV3;
import com.longfor.longjian.measure.po.zhijian2.MeasureList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Slf4j
public class APPMeasureSyncServiceImpl implements IAPPMeasureSyncService {
    @Autowired
    private IMeasureRuleService measureRuleService;
    @Autowired
    private ICategoryV3Service categoryV3Service;
    @Autowired
    private IMeasureListService measureListService;
    @Override
    public LjBaseResponse<MeasureRuleListVo> getMeasureRule(ApiMeasureRuleReq apiMeasureRuleReq) throws Exception {
        LjBaseResponse<MeasureRuleListVo> ljBaseResponse = new LjBaseResponse<>();
        List<MeasureRuleVo> measureRuleListVos = new ArrayList<>();
        MeasureRuleListVo measureRuleListVo=new MeasureRuleListVo();
        //切割categoryKeys
        if(apiMeasureRuleReq.getCategory_keys().length()==0){
            throw new Exception("category keys is empty.");
        }
        String[] categoryKeys = apiMeasureRuleReq.getCategory_keys().split(",");
        //数组转换List
        List<String> categoryKeysList = Arrays.asList(categoryKeys);
        //查询categoryKeys
        List<CategoryV3> categoryList = categoryV3Service.getCategoryByKeys(categoryKeysList);
        //updateAtDate不知道参数什么意思  默认时间0001-01-01 00:00:00  +0000  UTC
        String stringTime="0001-01-01 00:00:00";
        SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dateString = formatter.parse(stringTime);
        for (CategoryV3 category:categoryList
             ) {
            if (category.getUpdateAt()!=null){
                List<Map<String, Object>> measureRuleList = measureRuleService.searchUnscopedByTeamIdUpdateAtGt(category.getTeamId(), dateString);
                for (Map<String, Object> map : measureRuleList
                        ) {
                    //map转换成vo
                    MeasureRuleVo measureRuleVo  = (MeasureRuleVo) ConvertUtil.convertMap(MeasureRuleVo.class, map);
                    measureRuleListVos.add(measureRuleVo);
                }
            }
        }
        measureRuleListVo.setRule_list(measureRuleListVos);
        ljBaseResponse.setData(measureRuleListVo);
        return ljBaseResponse;
    }

    @Override
    public LjBaseResponse<MeasureListListVo> getMyTask(ApiMyTaskReq apiMyTaskReq, HttpServletRequest request) throws Exception {
        LjBaseResponse<MeasureListListVo> ljBaseResponse = new LjBaseResponse<>();
        MeasureListListVo measureListListVo=new MeasureListListVo();
        List<MeasureListVo> measureListVos=new ArrayList<>();
        String[] projectIds = apiMyTaskReq.getProject_id().split(",");
        if(projectIds.length==0){
            throw  new Exception("project id is empty.");
        }
        //HttpSession session = request.getSession();
        //todo session没有uid
        //Integer userId = (Integer) session.getAttribute("uid");
        Integer userId=1;
        for (String projectId:projectIds
             ) {
         List<Map<String, Object>> measureLists=  measureListService.searchListByProjIdUserId(projectId,userId);
            for (Map<String, Object> map : measureLists
                    ) {
                //map转换成vo
                MeasureListVo measureListVo  = (MeasureListVo) ConvertUtil.convertMap(MeasureListVo.class, map);
                measureListVos.add(measureListVo);
            }
        }
        measureListListVo.setMeasurelist_list(measureListVos);
        ljBaseResponse.setData(measureListListVo);
        return ljBaseResponse;
    }
}
