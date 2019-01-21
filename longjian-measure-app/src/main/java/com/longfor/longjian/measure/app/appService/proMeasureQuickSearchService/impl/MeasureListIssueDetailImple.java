package com.longfor.longjian.measure.app.appService.proMeasureQuickSearchService.impl;

import com.longfor.longjian.common.base.LjBaseResponse;
import com.longfor.longjian.common.exception.LjBaseRuntimeException;
import com.longfor.longjian.measure.app.appService.areaService.ICoreAreaService;
import com.longfor.longjian.measure.app.appService.proMeasureQuickSearchService.IMeasureListIssueDetailService;
import com.longfor.longjian.measure.app.commonEntity.AreasMap;
import com.longfor.longjian.measure.app.commonEntity.MeasureListIssueHelper;
import com.longfor.longjian.measure.app.commonEntity.MeasureSquadAndSquadUser;
import com.longfor.longjian.measure.app.req.proMeasureQuickSearchReq.MeasureListDetailUpdateIssueRepairerReq;
import com.longfor.longjian.measure.app.req.proMeasureQuickSearchReq.PostMeasureListDetailUpdateIssuePlanEndOnReq;
import com.longfor.longjian.measure.app.req.proMeasureQuickSearchReq.PostMeasureListDetailUpdateIssueTypeReq;
import com.longfor.longjian.measure.app.vo.proMeasureQuickSearchVo.*;
import com.longfor.longjian.measure.app.commonEntity.MeasureListIssueInfo;
import com.longfor.longjian.measure.app.req.proMeasureQuickSearchReq.GetMeasureListIssueDetailReq;
import com.longfor.longjian.measure.consts.constant.MeasureListIssueType;
import com.longfor.longjian.measure.domain.externalService.*;
import com.longfor.longjian.measure.po.zhijian2.*;
import com.longfor.longjian.measure.po.zhijian2_apisvr.User;
import com.longfor.longjian.measure.util.StringSplitToListUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.*;

/**
 * @description:
 * @author: DDC
 * @create: 2019-01-09 17:49
 **/
@Service
@Slf4j
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

    @Autowired
    private IMeasureRuleService measureRuleService;

    @Autowired
    private IMeasureZoneResultService measureZoneResultService;

    @Autowired
    private IMeasureZoneService measureZoneService;

    @Autowired
    private IMeasureRegionService measureRegionService;

    @Autowired
    private IMeasureSquadService measureSquadService;

    @Autowired
    private IMeasureSquadUserService measureSquadUserService;

    @Autowired
    private IMeasureRepairerUserService measureRepairerUserService;

    @Autowired
    private MeasureListIssueHelper measureListIssueHelper;

    @Autowired
    private ICoreAreaService coreAreaService;

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
        rsp.setCreate_at(info.getIssue().getCreateAt().getTime());
        rsp.setIssue_type(info.getIssue().getTyp());
        rsp.setClose_status(info.getIssue().getCloseStatus());
        rsp.setPlan_end_on(info.getIssue().getPlanEndOn());
        rsp.setRepairer_id(info.getIssue().getRepairerId());
        rsp.setRepairer_name(info.getRepairerName());
        rsp.setStatus(info.getIssue().getStatus());
        return rsp;
    }

    @Override
    public MeasureListIssueDetailZoneInfoVo zoneInfo(GetMeasureListIssueDetailReq req) {
        MeasureListIssueDetailZoneInfoVo vo = new MeasureListIssueDetailZoneInfoVo();
        List<MeasureListIssueDetailSquadVo> squads = new ArrayList<>();
        List<MeasureListIssueDetailSquadResultVo> voResesults = new ArrayList<>();
        MeasureListIssue issue =null;
        try {
            issue = measureListIssueService.GetIssueByProjectIdAndUuid(req.getProject_id(), req.getUuid());
        }catch (Exception e){
            log.warn("");
        }
        if (issue!=null) {
            MeasureRule rule = measureRuleService.getByCategoryKey(issue.getCategoryKey());
            if (rule!=null) {
                MeasureRuleVo rulevo = new MeasureRuleVo();
                rulevo.setId(rule.getId());
                rulevo.setCategory_key(rule.getCategoryKey());
                rulevo.setDesc(rule.getDesc());
                rulevo.setFormula(rule.getFormula());
                rulevo.setFormula_default(rule.getFormulaDefault());
                rulevo.setGroup_count_init(rule.getGroupCountInit());
                rulevo.setGroup_count_max(rule.getGroupCountMax());
                rulevo.setGroup_count_min(rule.getGroupCountMin());
                rulevo.setPoint_need(rule.getPointNeed());
                rulevo.setPoints(rule.getPoints());
                rulevo.setRule_type(rule.getRuleType());
                rulevo.setTeam_id(rule.getTeamId());
                rulevo.setTextures(rule.getTextures());
                rulevo.setCreate_at(rule.getCreateAt());
                rulevo.setUpdate_at(rule.getUpdateAt());
                rulevo.setDelete_at(rule.getDeleteAt());
                vo.setRule(rulevo);
            }
        }

        List<MeasureZoneResult> results = measureZoneResultService.SearchZoneResultByProjIdZoneUuid(req.getProject_id(), req.getUuid());
        for (MeasureZoneResult result : results) {
            MeasureListIssueDetailSquadResultVo squadResult = new MeasureListIssueDetailSquadResultVo();
            squadResult.setSquad_id(result.getSquadId());
            squadResult.setData(result.getData());
            voResesults.add(squadResult);
        }
        vo.setResults(voResesults);

        MeasureZone measureZone = null;
        try {
            //方法需要的proj_id来自于权限部分, 先写死
            measureZone = measureZoneService.GetZoneByUuid(20, issue.getZoneUuid());
        }catch (Exception e){
            log.error("");
        }
        if (measureZone!=null) {
            // proj_id 同上
            MeasureRegion region = measureRegionService.GetByUuid(20, measureZone.getRegionUuid());
            MeasureRegionVo regionvo = new MeasureRegionVo();
            if (region!=null) {
                regionvo.setId(region.getId());
                regionvo.setProject_id(region.getProjectId());
                regionvo.setArea_id(region.getAreaId());
                regionvo.setDrawing_md5(region.getDrawingMd5());
                regionvo.setPolygon(region.getPolygon());
                regionvo.setRegion_index(region.getRegionIndex());
                regionvo.setRel_id(region.getRelId());
                regionvo.setSrc_type(region.getSrcType());
                regionvo.setUuid(region.getUuid());
                regionvo.setTag_id_list(region.getTagIdList());
                regionvo.setCreate_at(region.getCreateAt());
                regionvo.setUpdate_at(region.getUpdateAt());
                regionvo.setDelete_at(region.getDeleteAt());
                vo.setRegion(regionvo);
            }
        }
        List<MeasureSquad> measureSquads = measureSquadService.SearchSquadByProjIdListId(req.getProject_id(), issue.getListId());
        List<MeasureSquadAndSquadUser> squals = new ArrayList<>();
        List<MeasureSquadUser> measureSquadUsers = null;
        List<Integer> uids = new ArrayList<>();
        for (MeasureSquad measureSquad : measureSquads) {
            MeasureSquadAndSquadUser measureSquadAndSquadUser = new MeasureSquadAndSquadUser();
            measureSquadAndSquadUser.setSquad(measureSquad);
            measureSquadUsers = measureSquadUserService.SearchBySquadId(req.getProject_id(), measureSquad.getId());
            measureSquadAndSquadUser.setUsers(measureSquadUsers);
            squals.add(measureSquadAndSquadUser);
        }
        if (!squals.isEmpty()) {
            for (MeasureSquadUser measureSquadUser : measureSquadUsers) {
                uids.add(measureSquadUser.getUserId());
            }
            Map<Integer, User> userMap = GetUsersByIds(uids);
            for (MeasureSquadAndSquadUser squal : squals) {
                MeasureListIssueDetailSquadVo squadVo = new MeasureListIssueDetailSquadVo();
                List<String> users = new ArrayList<>();
                squadVo.setId(squal.getSquad().getId());
                squadVo.setName(squal.getSquad().getName());
                for (MeasureSquadUser user : squal.getUsers()) {
                    if (userMap.containsKey(user.getUserId())) {
                        users.add(userMap.get(user.getUserId()).getRealName());
                    }
                }
                squadVo.setUsers(users);
                squads.add(squadVo);
            }
            vo.setSquads(squads);
        }
        return vo;
    }

    @Override
    public List<MeasureListIssueDetailRepairerVo> repairList(GetMeasureListIssueDetailReq req) {
        List<MeasureListIssueDetailRepairerVo> vo = new ArrayList<>();
        MeasureListIssue issue = measureListIssueService.GetIssueByProjectIdAndUuid(req.getProject_id(), req.getUuid());
        List<MeasureRepairerUser> measureRepairerUsers = null;
        if (issue!=null) {
            measureRepairerUsers = measureRepairerUserService.SearchMeasureReparierUserByListId(req.getProject_id(), issue.getListId());
        }
        List<Integer> uids = new ArrayList<>();
        for (MeasureRepairerUser repairerUser : measureRepairerUsers) {
            uids.add(repairerUser.getId());
        }
        Map<Integer, User> userMap = GetUsersByIds(uids);
        for (MeasureRepairerUser measureRepairerUser : measureRepairerUsers) {
            if (userMap.containsKey(measureRepairerUser.getUserId())) {
                MeasureListIssueDetailRepairerVo repairerVo = new MeasureListIssueDetailRepairerVo();
                repairerVo.setUser_id(measureRepairerUser.getUserId());
                repairerVo.setUser_name(userMap.get(measureRepairerUser.getUserId()).getRealName());
                vo.add(repairerVo);
            }
        }
        return vo;
    }

    @Override
    public void updateRepairer(MeasureListDetailUpdateIssueRepairerReq req) {
        /**
         * c *niuhe.Context
         * uId := getCurUid(c)
         */
        //todo id是鉴权部分返回, 暂时写死
        Integer uid= 1;
        boolean isClosed = UpdateIssueRepairInfoByUuid(req.getUuid(), req.getProject_id(), uid, req.getRepairer_id(), -1L);
        if (isClosed) {
            //todo 异常码
            throw new LjBaseRuntimeException(-1,"问题已被关闭");
        }
    }

    @Override
    public LjBaseResponse updateIssueType(PostMeasureListDetailUpdateIssueTypeReq req) {
        /**
         * c *niuhe.Context
         * uId := getCurUid(c)
         */
        //todo id是鉴权部分返回, 暂时写死
        Integer uid = 1;
        boolean isClosed = UpdateIssueTypeByUuid(req.getUuid(), req.getProject_id(),uid, req.getType());
        if (isClosed) {
            //todo 异常码
            throw new LjBaseRuntimeException(-1,"问题已被关闭");
        }
        return new LjBaseResponse();

    }

    @Override
    public LjBaseResponse UpdatePlanEndOn(PostMeasureListDetailUpdateIssuePlanEndOnReq req) {
        /**
         * c *niuhe.Context
         * uId := getCurUid(c)
         */
        //todo id是鉴权部分返回, 暂时写死
        Integer uid = 1;
        boolean isClosed = UpdateIssueRepairInfoByUuid(req.getUuid(), req.getProject_id(), uid, -1, req.getPlan_end_on());
        if (isClosed) {
            //todo 异常码
            throw new LjBaseRuntimeException(-1,"问题已被关闭");
        }
        return new LjBaseResponse();
    }

    public MeasureListIssueInfo FormatMeasureListIssue(Integer projId,MeasureListIssue issue){
        MeasureListIssueInfo info = new MeasureListIssueInfo();
        info.setIssue(issue);
        MeasureList list;
        if (projId>0) {
            list = measureListService.getMeasureListByProjIdAndId(projId, issue.getListId());
            if (list!=null) {
                info.setTaskName(list.getName());
            }else {
                info.setTaskName("");
            }
        }
        //对categoryPathAndKey字段进行处理
        String categoryPathAndKey = issue.getCategoryPathAndKey();
        List<String> keys = StringSplitToListUtil.removeStartAndEndStrAndSplit(categoryPathAndKey, "/", "/");
        Map<String, List<String>> cateMap = getCategoryPathNamesMap(keys);
        if (cateMap.containsKey(issue.getCategoryKey())) {
            info.setAreaPathNames(cateMap.get(issue.getCategoryKey()));
        }
        List<Integer> ids = new ArrayList<>();
        ids.add(issue.getAreaId());
        Map<Integer, List<String>> areaMap = GetAreaPathNamesMap(ids);
        if (areaMap.containsKey(issue.getAreaId())) {
            info.setAreaPathNames(areaMap.get(issue.getAreaId()));
        }

        if (issue.getRepairerId()>0) {
            User user = userService.getUserByUserId(issue.getRepairerId());
            if (user!=null) {
                info.setRepairerName(user.getRealName());
            }else{
                info.setRepairerName("");
            }
        }
        return info;
    }

    public Map<String,List<String>> getCategoryPathNamesMap(List<String> keys){
        Map<String,List<String>> cateMap = new HashMap<>();
        List<CategoryV3> categorys = categoryV3Service.SearchCategoryByKeyIn(keys);
        //cMap中key是category的key, 值为category实例
        Map<String,CategoryV3> cMap = new HashMap<>();
        for (CategoryV3 category : categorys) {
            cMap.put(category.getKey(),category);
        }

        for (String key : keys) {
            List<String> fullNames = null;
            if (cMap.containsKey(key)) {
                fullNames = getFullNames(cMap, cMap.get(key));
            }
            cateMap.put(key,fullNames);
        }
        return cateMap;
    }

    public Map<Integer,List<String>>GetAreaPathNamesMap(List<Integer> ids){
        AreasMap aMap = coreAreaService.createAreasMapByLeaveIds(ids);
        Map<Integer,List<String>> r = new HashMap<>();
        for (Integer id : ids) {
            r.put(id,GetPathNames(aMap,id));
        }

        return r;
    }

    public List<String> GetPathNames(AreasMap aMap,Integer id){
        List<String> names = new ArrayList<>();
        Area area = aMap.getAreas().get(id);
        if (area == null) {
            return names;
        }
        List<Integer> ids = getPathIds(area);
        for (Integer aid : ids) {
            Map<Integer, Area> areas = aMap.getAreas();
            if (areas.containsKey(aid)) {
                names.add(areas.get(aid).getName());
            }

        }
        return names;

    }

    public List<Integer> getPathIds(Area area){
        List<Integer> ids = new ArrayList<>();
        String[] idsStr = area.getPath().split("/");
        for (String idStr : idsStr) {
            int id = Integer.parseInt(idStr);
            ids.add(id);
        }
        ids.add(area.getId());
        return ids;
    }

    public List<String> getFullNames(Map<String,CategoryV3> cMap,CategoryV3 category){
        List<String> names = null;
        if (category!=null) {
            names = new ArrayList<>();
            String path = category.getPath();
            List<String> paths = StringSplitToListUtil.removeStartAndEndStrAndSplit(path, "/", "/");
            paths.add(category.getKey());
            //遍历paths
            for (String singlePath : paths) {
                if ("".equals(singlePath)) {
                    continue;
                }
                if (cMap.containsKey(singlePath)) {
                    names.add(cMap.get(singlePath).getName());
                }
            }
            return names;
        }
        return names;
    }

    public Map<Integer,User> GetUsersByIds(List<Integer> uids){
        Map<Integer,User> userMap = new HashMap<>();
        List<User> usersByUserIds = userService.getUserEntitiesByUserIds(uids);
        for (User user : usersByUserIds) {
            userMap.put(user.getUserId(),user);
        }
        return userMap;
    }

    public boolean UpdateIssueRepairInfoByUuid(String uuid, Integer projectId, Integer senderId, Integer repairerId ,Long planEndOn) {
        Integer eInt = -1;
        Long eLong = -1L;
        String eStr = "";
        MeasureListIssue issue = measureListIssueService.GetIssueByProjectIdAndUuid(projectId, uuid);

        boolean isClose = false;
        if (issue.getCloseStatus() == MeasureListIssueType.CLOSED) {
            isClose = true;
            return isClose;
        }
        Integer status = eInt;
        if (issue.getStatus() == MeasureListIssueType.NOTENOASSIGN && repairerId >0) {
            status = MeasureListIssueType.ASSIGNNOREFORM;
        }

        measureListIssueHelper.init(projectId);
        try {
            measureListIssueHelper.start().setNormalField(
                    UUID.randomUUID().toString().replace("-",""),
                    issue.getListId(),
                    issue.getUuid(),
                    senderId, eStr, eInt, status, eStr, eStr,
                    System.currentTimeMillis()/1000L
            ).setDatailField(
                    eStr,planEndOn, eLong, repairerId, eInt, eInt, eStr, eInt, eInt, eInt, eInt, eLong, eInt
            ).done();
        } catch (ParseException e) {
            log.warn("日期解析异常");
            e.printStackTrace();

        }
        measureListIssueHelper.execute();
        return isClose;
    }

    public boolean UpdateIssueTypeByUuid(String uuid,Integer projectId,Integer senderId,Integer typ){
        Integer eInt = -1;
        Long eLong = -1L;
        String eStr = "";
        MeasureListIssue issue = measureListIssueService.GetIssueByProjectIdAndUuid(projectId, uuid);
        boolean isClose = false;
        if (issue.getCloseStatus() == MeasureListIssueType.CLOSED) {
            isClose = true;
            return isClose;
        }
        measureListIssueHelper.init(projectId);
        try {
            measureListIssueHelper.start().setNormalField(
                    UUID.randomUUID().toString().replace("-",""),
                    issue.getListId(),
                    issue.getUuid(),
                    senderId,eStr,typ,eInt,eStr,eStr,
                    System.currentTimeMillis()/1000L
            ).setDatailField(
                    eStr, eLong, eLong, eInt, eInt, eInt, eStr, eInt, eInt, eInt, eInt, eLong, eInt
            ).done();
        } catch (ParseException e) {
            log.warn("日期解析异常");
            e.printStackTrace();
        }
        measureListIssueHelper.execute();
        return isClose;
    }
}


