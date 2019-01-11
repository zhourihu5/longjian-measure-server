package com.longfor.longjian.measure.app.appService.proMeasureQuickSearchService.impl;

import com.longfor.longjian.common.exception.LjBaseRuntimeException;
import com.longfor.longjian.measure.app.appService.proMeasureQuickSearchService.IMeasureListIssueDetailService;
import com.longfor.longjian.measure.app.commonEntity.AreasMap;
import com.longfor.longjian.measure.app.commonEntity.MeasureListIssueInfo;
import com.longfor.longjian.measure.app.req.proMeasureQuickSearchReq.GetMeasureListIssueDetailReq;
import com.longfor.longjian.measure.app.vo.proMeasureQuickSearchVo.MeasureListIssueDetailIssueInfoVo;
import com.longfor.longjian.measure.domain.externalService.*;
import com.longfor.longjian.measure.po.zhijian2.Area;
import com.longfor.longjian.measure.po.zhijian2.CategoryV3;
import com.longfor.longjian.measure.po.zhijian2.MeasureList;
import com.longfor.longjian.measure.po.zhijian2.MeasureListIssue;
import com.longfor.longjian.measure.po.zhijian2_apisvr.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * @description:
 * @author: DDC
 * @create: 2019-01-09 17:49
 **/

public class MeasureListIssueDetailImple implements IMeasureListIssueDetailService {

    @Autowired
    private IMeasureListIssueService measureListIssueService;

    @Autowired
    private IMeasureListService measureListService;

    @Autowired
    private ICategoryV3Service categoryV3Service;

    @Autowired
    private IAreaService areaService;

    @Autowired
    private IUserService userService;

    @Override
    public MeasureListIssueDetailIssueInfoVo IssueInfo(GetMeasureListIssueDetailReq req) {
        MeasureListIssue issue = measureListIssueService.GetIssueByProjectIdAndUuid(req.getProject_id(), req.getUuid());
        if (issue == null) {
            throw new LjBaseRuntimeException(-1,"");
        }
        //todo 鉴权返回proj , 现暂时写死
        MeasureListIssueInfo info = FormatMeasureListIssue(20, issue);
        MeasureListIssueDetailIssueInfoVo rsp = new MeasureListIssueDetailIssueInfoVo();
        rsp.setTask_name(info.getTaskName());
        rsp.setCategory_path_names(info.getAreaPathNames());
        rsp.setArea_path_names(info.getAreaPathNames());
        //rsp.setCreate_at();
        rsp.setIssue_type(info.getIssue().getTyp());
        rsp.setClose_status(info.getIssue().getCloseStatus());
        rsp.setPlan_end_on(info.getIssue().getPlanEndOn());
        rsp.setRepairer_id(info.getIssue().getRepairerId());
        rsp.setRepairer_name(info.getRepairerName());
        rsp.setStatus(info.getIssue().getStatus());
        return rsp;
    }

    public MeasureListIssueInfo FormatMeasureListIssue(Integer projId,MeasureListIssue issue){
        MeasureListIssueInfo info = new MeasureListIssueInfo();
        info.setIssue(issue);
        MeasureList list;
        if (projId>0) {
            list = measureListService.getMeasureListByProjIdAndId(projId, issue.getListId());
            if (list!=null) {
                info.setTaskName(list.getName());
            }
        }

        String categoryPathAndKey = issue.getCategoryPathAndKey();
        List<String> keys = Arrays.asList((categoryPathAndKey.substring(1, categoryPathAndKey.length() - 2)).split("/"));
        List<CategoryV3> categorys = categoryV3Service.SearchCategoryByKeyIn(keys);
        //新建一个map , key是category的key, 值为category实例
        Map<String,CategoryV3> cmap = new HashMap<>();
        for (CategoryV3 category : categorys) {
            cmap.put(category.getKey(),category);
        }


        Map<String,List<String>> cateMap = new HashMap<>();
        for (String key : keys) {
            CategoryV3 category = cmap.get(key);
            if (category!=null) {
                List<String> names = new ArrayList<>();
                String path = category.getPath();
                List<String> paths = Arrays.asList((path.substring(1, path.length() - 2)).split("/"));
                paths.add(category.getKey());
                for (String s : paths) {
                    if (cmap.get(s)!=null) {
                        names.add(cmap.get(s).getName());
                    }
                }
                cateMap.put(key,names);
            }
        }
        List<String> cateNames = cateMap.get(issue.getCategoryKey());
        if (cateNames!=null) {
            info.setCategoryPathNames(cateNames);
        }


        Map<Integer,List<String>> areaMap = new HashMap<>();
        List<Integer> areaIds = new ArrayList<>();
        areaIds.add(issue.getAreaId());
        List<Area> areaByIds = areaService.getAreaByIds(areaIds);
        AreasMap areasMap = new AreasMap();
        for (Area area : areaByIds) {
            areasMap.getAreas().put(area.getId(),area);
        }
        areasMap.setList(areaByIds);

        for (Integer id : areaIds) {
            Area area = areasMap.getAreas().get(id);
            List<Integer> ids = new ArrayList<>();
            List<String> names = new ArrayList<>();
            String[] strings = area.getPath().split("/");
            for (String string : strings) {
                try {
                    int parseInt = Integer.parseInt(string);
                    ids.add(parseInt);
                }catch (NumberFormatException e){
                    throw new LjBaseRuntimeException(-1,"字符转化异常");
                }
            }
            for (Integer integer : ids) {
                Area area1 = areasMap.getAreas().get(integer);
                if (area1!=null) {
                    names.add(area1.getName());
                }
            }
            areaMap.put(id,names);
        }

        List<String> idNames = areaMap.get(issue.getAreaId());
        if (idNames!=null) {
            info.setAreaPathNames(idNames);
        }

        if (issue.getRepairerId()>0) {
            User user = userService.getUserByUserId(issue.getRepairerId());
            if (user!=null) {
                info.setRepairerName(user.getRealName());
            }
        }
        return info;
    }
}


