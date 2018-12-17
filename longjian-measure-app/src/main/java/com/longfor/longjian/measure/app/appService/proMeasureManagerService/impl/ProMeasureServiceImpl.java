package com.longfor.longjian.measure.app.appService.proMeasureManagerService.impl;

import com.alibaba.fastjson.JSONObject;
import com.longfor.longjian.common.base.LjBaseResponse;
import com.longfor.longjian.measure.app.appService.proMeasureManagerService.IProMeasureService;
import com.longfor.longjian.measure.app.req.proMeasureManagerReq.GetCheckerListReq;
import com.longfor.longjian.measure.app.req.proMeasureManagerReq.GetProMeasureAreaListReq;
import com.longfor.longjian.measure.app.req.proMeasureManagerReq.GetProMeasureCheckItemsReq;
import com.longfor.longjian.measure.app.req.proMeasureManagerReq.GetProMeasurePlanListReq;
import com.longfor.longjian.measure.app.req.proMeasureQuickSearchReq.*;
import com.longfor.longjian.measure.app.vo.ItemsVo;
import com.longfor.longjian.measure.app.vo.proMeasureVo.*;
import com.longfor.longjian.measure.consts.constant.CategoryClsTypeConstant;
import com.longfor.longjian.measure.consts.constant.MeasureListIssueType;
import com.longfor.longjian.measure.util.ConvertUtil;
import com.longfor.longjian.measure.util.DateUtil;
import com.longfor.longjian.measure.util.LambdaExceptionUtil;
import com.longfor.longjian.measure.domain.externalService.*;
import com.longfor.longjian.measure.po.zhijian2.*;
import com.longfor.longjian.measure.po.zhijian2_apisvr.Team;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.longfor.longjian.measure.consts.constant.MeasureListConstant;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.*;

@Service
@Slf4j
public class ProMeasureServiceImpl implements IProMeasureService {

    @Autowired
    private ICategoryV3Service categoryV3Service;
    @Autowired
    private IAreaService areaService;
    @Autowired
    private IMeasureListService measureListService;
    @Autowired
    private IMeasureListIssueService measureListIssueService;
    @Autowired
    private IUserInProjectService userInProjectService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IMeasureZoneService measureZoneService;
    @Autowired
    private IMeasureZoneResultService measureZoneResultService;
    @Autowired
    private IMeasureSquadService measureSquadService;

    @Override
    public LjBaseResponse<ProMeasurePlanListVo> getProMeasurePlanList(GetProMeasurePlanListReq getProMeasurePlanListReq) throws IntrospectionException, InstantiationException, IllegalAccessException, InvocationTargetException, ParseException {
        LjBaseResponse<ProMeasurePlanListVo> ljBaseResponse = new LjBaseResponse<>();
        ProMeasurePlanListVo proMeasurePlanListVo = new ProMeasurePlanListVo();
        // todo 权限鉴定（通过http请求公共方法），获取项目（包括用户？）信息等，暂时项目id从前端获取
        String [] userIds = null;
        if (StringUtils.isNotBlank(getProMeasurePlanListReq.getUser_ids())){
            userIds = getProMeasurePlanListReq.getUser_ids().split(",");
        }
        //categoryPathAndKey 参数赋值
        String categoryPathAndKey = getCategoryPathAndKey(getProMeasurePlanListReq);
        //areaPathAndId 参数赋值
        String areaPathAndId = getAreaPathAndId(getProMeasurePlanListReq);
        //获取measureList
        List<ProMeasurePlanVo> list = SearchByProjIdCategoryKeyAreaIdStatusUserIdInPage(getProMeasurePlanListReq,userIds,categoryPathAndKey,areaPathAndId);
        //获取total
        Integer total = measureListService.getTotalMeasure(getProMeasurePlanListReq.getFinish_status(),getProMeasurePlanListReq.getQ(),getProMeasurePlanListReq.getProject_id(),categoryPathAndKey,areaPathAndId,userIds);
        proMeasurePlanListVo.setItems(list);
        proMeasurePlanListVo.setTotal(total);
        ljBaseResponse.setData(proMeasurePlanListVo);
        return ljBaseResponse;
    }

    @Override
    public LjBaseResponse<ItemsVo<List<ProMeasureCheckIteamVo>>> getProMeasureCheckItems(GetProMeasureCheckItemsReq getProMeasureCheckItemsReq) throws InvocationTargetException, IntrospectionException, InstantiationException, IllegalAccessException {
        LjBaseResponse<ItemsVo<List<ProMeasureCheckIteamVo>>> ljBaseResponse = new LjBaseResponse<>();
        ItemsVo<List<ProMeasureCheckIteamVo>> itemsVo = new ItemsVo<>();
        List<ProMeasureCheckIteamVo> proMeasureCheckIteamVoArrayList = new ArrayList<>();
        //todo 从sessionz中取出group信息（Team表）,然后找到最顶层的父级team ,暂时手动赋值
        Team team = new Team();
        team.setTeamId(4);
        List<Map<String,Object>> list = new ArrayList<>();
        if (StringUtils.isNotBlank(getProMeasureCheckItemsReq.getKey())){
            //查子集
            list = categoryV3Service.getCategoryByFatherKey(getProMeasureCheckItemsReq.getKey());
        }else {
            //查root级
            list = categoryV3Service.getRootCategoryByClsTeamId(CategoryClsTypeConstant.MEASURE,team.getTeamId());
        }
        for (Map<String,Object> map: list) {
            //map转换成vo
            ProMeasureCheckIteamVo proMeasureCheckIteamVo = (ProMeasureCheckIteamVo) ConvertUtil.convertMap(ProMeasureCheckIteamVo.class,map);
            //isParent
            proMeasureCheckIteamVo.setIsParent(categoryV3Service.getCategoryByFatherKey(map.get("key").toString()).size() > 0);
            proMeasureCheckIteamVoArrayList.add(proMeasureCheckIteamVo);
        }
        itemsVo.setItems(proMeasureCheckIteamVoArrayList);
        ljBaseResponse.setData(itemsVo);
        return ljBaseResponse;
    }

    @Override
    public LjBaseResponse<AreaInfoVo> getProMeasureAreaList(GetProMeasureAreaListReq getProMeasureAreaListReq) throws InvocationTargetException, IntrospectionException, InstantiationException, IllegalAccessException {
        LjBaseResponse<AreaInfoVo> ljBaseResponse = new LjBaseResponse<>();
        AreaInfoVo areaInfoVo = new AreaInfoVo();
        List<ProMeasureAreaVo> proMeasureAreaVos = new ArrayList<>();
        List<Map<String,Object>> list = areaService.getProMeasureAreaListByFatherId(getProMeasureAreaListReq.getProject_id(),getProMeasureAreaListReq.getArea_id());
        for (Map<String,Object> map:list
             ) {
            //map转换成vo
            ProMeasureAreaVo proMeasureAreaVo = (ProMeasureAreaVo) ConvertUtil.convertMap(ProMeasureAreaVo.class,map);
            proMeasureAreaVo.setIsParent(areaService.getProMeasureAreaListByFatherId(getProMeasureAreaListReq.getProject_id(),map.get("id").toString()).size() > 0);
            //设置pathNames
            List<String> names = new ArrayList<>();
            for (String s:map.get("path").toString().split("/")
                 ) {
                if (StringUtils.isNotBlank(s)) {
                    Area area = areaService.getAreaByProjIdAndAreaId(Integer.parseInt(getProMeasureAreaListReq.getProject_id()), Integer.parseInt(s));
                    if (area != null) {
                        names.add(area.getName());
                    }
                }
            }
            names.add(map.get("name").toString());
            proMeasureAreaVo.setPathNames(names);
            proMeasureAreaVos.add(proMeasureAreaVo);
        }
        areaInfoVo.setItems(proMeasureAreaVos);
        // todo go代码暂时没有赋值
        areaInfoVo.setTotal(0);
        ljBaseResponse.setData(areaInfoVo);
        return ljBaseResponse;
    }

    @Override
    public LjBaseResponse<ItemsVo<List<CheckerVo>>> getCheckerList(GetCheckerListReq getCheckerListReq) {
        LjBaseResponse<ItemsVo<List<CheckerVo>>> ljBaseResponse = new LjBaseResponse<>();
        ItemsVo<List<CheckerVo>> itemsVo = new ItemsVo<>();
        List<CheckerVo> checkerVos = new ArrayList<>();
        List<Integer> userIds = userInProjectService.getUserIdByProjectIds(new int[]{getCheckerListReq.getProject_id()});
        List<Map<String,Object>> users = userService.getUserByUserIds(userIds);
        users.forEach(LambdaExceptionUtil.throwingConsumerWrapper(user -> {
                CheckerVo checkerVo = (CheckerVo) ConvertUtil.convertMap(CheckerVo.class,user);
                checkerVos.add(checkerVo);
        }));
        itemsVo.setItems(checkerVos);
        ljBaseResponse.setData(itemsVo);
        return ljBaseResponse;
    }

    @Override
    public LjBaseResponse<SquadsAndPassVo> getCompareBetweenGroup(GetCompareBetweenGroupReq getCompareBetweenGroupReq) throws Exception {
        LjBaseResponse<SquadsAndPassVo> ljBaseResponse = new LjBaseResponse<>();
        SquadsAndPassVo squadsAndPassVo = new SquadsAndPassVo();
        //验证任务是否属于这个项目
        boolean existPlan = measureListService.searchByProjectIdAndMeasureListId(getCompareBetweenGroupReq.getProject_id(),getCompareBetweenGroupReq.getMeasure_list_id()) != null;
        if (!existPlan){
            //任务不存在,抛出异常
            throw new Exception("任务不存在");
        }
        // 获取测区数量
        Integer total = measureZoneService.searchTotalByProjectIdAndMeasureListId(getCompareBetweenGroupReq.getProject_id(),new int[]{getCompareBetweenGroupReq.getMeasure_list_id()});
        // 计算出所有小组的总检查数，map格式，无需转换
        List<Map<String,Object>> squadCounts = measureZoneResultService.statMeasureListZoneResultCountByListIdGroupBySquad(getCompareBetweenGroupReq.getMeasure_list_id());
        //查询组
        List<MeasureSquad> measureSquadlist = measureSquadService.searchOnlyMeasureSquadByProjIdAndListId(getCompareBetweenGroupReq.getProject_id(),getCompareBetweenGroupReq.getMeasure_list_id());
        // 填写完成进度数据
        List<SquadsVo> squads = getSquads(measureSquadlist,total,squadCounts);
        // 算出各组的平均合格率
        List<Map<String,Object>> measureZoneResults = measureZoneResultService.statMearureZoneResultSquadTotalCountByListIdCategoryKey(getCompareBetweenGroupReq.getMeasure_list_id(),"");
        // 计算各项的数据
        List<Map<String,Object>> results = measureZoneResultService.statMearureZoneResultSquadTotalCountByListIdGroupByCategoryKey(getCompareBetweenGroupReq.getMeasure_list_id());
        //填写合格率数据
        List<SquadsPassVo> squads_pass = getSquadsPassVos(measureSquadlist,total,measureZoneResults,results);
        squadsAndPassVo.setSquads(squads);
        squadsAndPassVo.setSquads_pass(squads_pass);
        ljBaseResponse.setData(squadsAndPassVo);
        return ljBaseResponse;
    }

    @Override
    public LjBaseResponse<PassDiffVo> getLoserCompareBetweenGroup(GetLoserCompareBetweenGroupReq getLoserCompareBetweenGroupReq) throws Exception {
        LjBaseResponse<PassDiffVo> ljBaseResponse = new LjBaseResponse<>();
        PassDiffVo passDiffVo = new PassDiffVo();
        //验证任务是否属于这个项目
        boolean existPlan = measureListService.searchByProjectIdAndMeasureListId(getLoserCompareBetweenGroupReq.getProject_id(),getLoserCompareBetweenGroupReq.getMeasure_list_id()) != null;
        if (!existPlan){
            //任务不存在,抛出异常
            throw new Exception("任务不存在");
        }
        //获取分组信息
        List<MeasureSquad> measureSquadlist = measureSquadService.searchOnlyMeasureSquadByProjIdAndListId(getLoserCompareBetweenGroupReq.getProject_id(),getLoserCompareBetweenGroupReq.getMeasure_list_id());
        List<String> pass_diff_largest_squad_names = new ArrayList<>();
        List<String> pass_percentage_smallest_squad_names = new ArrayList<>();
        measureSquadlist.forEach( measureSquad -> {
            pass_diff_largest_squad_names.add(measureSquad.getName());
            pass_percentage_smallest_squad_names.add(measureSquad.getName());
        });
        //设置合格率差值最大组
        List<MeasureStatisticSquadsPassDiffLargestInfoVo> pass_diff_largest = searchCategoryDiffLargestByMeasureListId(getLoserCompareBetweenGroupReq.getMeasure_list_id());
        //设置合格率最小组
        List<MeasureStatisticSquadsSmallestPassPercentInfoVo> pass_percentage_smallest = searchCategorySquadSmallestByMeasureListId(getLoserCompareBetweenGroupReq.getMeasure_list_id());
        passDiffVo.setPass_diff_largest_squad_names(pass_diff_largest_squad_names);
        passDiffVo.setPass_percentage_smallest_squad_names(pass_percentage_smallest_squad_names);
        passDiffVo.setPass_diff_largest(pass_diff_largest);
        passDiffVo.setPass_percentage_smallest(pass_percentage_smallest);
        ljBaseResponse.setData(passDiffVo);
        return ljBaseResponse;
    }

    @Override
    public LjBaseResponse<CompareItemBetweenSquadsVo> getCompareItemBetweenSquads(GetCompareItemBetweenSquadsReq getCompareItemBetweenSquadsReq) throws Exception {
        LjBaseResponse<CompareItemBetweenSquadsVo> ljBaseResponse = new LjBaseResponse<>();
        CompareItemBetweenSquadsVo compareItemBetweenSquadsVo = new CompareItemBetweenSquadsVo();
        //验证任务是否属于这个项目
        MeasureList measureList = measureListService.searchByProjectIdAndMeasureListId(getCompareItemBetweenSquadsReq.getProject_id(),getCompareItemBetweenSquadsReq.getMeasure_list_id());
        if (measureList == null){
            //任务不存在,抛出异常
            throw new Exception("任务不存在");
        }
        // 获取分组信息，否则不管
        List<MeasureSquad> measureSquadlist = measureSquadService.searchOnlyMeasureSquadByProjIdAndListId(getCompareItemBetweenSquadsReq.getProject_id(),getCompareItemBetweenSquadsReq.getMeasure_list_id());
        List<SquadsPassVo> squads_rate = new ArrayList<>();
        measureSquadlist.forEach(measureSquad -> {
            SquadsPassVo squadsPassVo = new SquadsPassVo();
            squadsPassVo.setSquad_id(measureSquad.getId());
            squadsPassVo.setSquad_name(measureSquad.getName());
            squadsPassVo.setRate(measureSquad.getPlanRate() + "");
            squads_rate.add(squadsPassVo);
        });
        List<CategoryDetailsVo> category_details = getCategoryDetails(getCompareItemBetweenSquadsReq,measureList);
        compareItemBetweenSquadsVo.setCategory_details(category_details);
        compareItemBetweenSquadsVo.setSquads_rate(squads_rate);
        ljBaseResponse.setData(compareItemBetweenSquadsVo);
        return ljBaseResponse;
    }

    @Override
    public LjBaseResponse<BlisterRateInfoVo> getBlisterRateInfo(GetBlisterRateInfoReq getBlisterRateInfoReq) throws InvocationTargetException, IntrospectionException, InstantiationException, IllegalAccessException {
        LjBaseResponse<BlisterRateInfoVo> ljBaseResponse = new LjBaseResponse<>();
        Map<String,Object> issue = measureListIssueService.getMeasureListIssueBrief(getBlisterRateInfoReq.getProject_id(),getBlisterRateInfoReq.getMeasure_list_id()
                ,MeasureListConstant.UNCLOSECODE, MeasureListIssueType.REPAIRABLE,MeasureListIssueType.NOREPAIRABLE,MeasureListIssueType.NOTENOASSIGN,MeasureListIssueType.ASSIGNNOREFORM,MeasureListIssueType.REFORMNOCHECK,MeasureListIssueType.CHECKYES);
        BlisterRateInfoVo blisterRateInfoVo = (BlisterRateInfoVo)ConvertUtil.convertMap(BlisterRateInfoVo.class,issue);
        ljBaseResponse.setData(blisterRateInfoVo);
        return ljBaseResponse;
    }

    @Override
    public LjBaseResponse<ItemsVo<List<BlisterTimeNotesVo>>> getBlisterRateInfoTimeNotes(GetBlisterRateInfoTimeNotesReq getBlisterRateInfoTimeNotesReq) throws ParseException {
        LjBaseResponse<ItemsVo<List<BlisterTimeNotesVo>>> ljBaseResponse = new LjBaseResponse<>();
        ItemsVo<List<BlisterTimeNotesVo>> itemsVo = new ItemsVo<>();
        List<BlisterTimeNotesVo> blisterTimeNotesVos = new ArrayList<>();
        String startTime = "";
        String endTime = "";
        //如果客户端传上来的为0 ，查找最近一周的
        if (getBlisterRateInfoTimeNotesReq.getBegin_on() <= 0 && getBlisterRateInfoTimeNotesReq.getEnd_on() <= 0){
            startTime = DateUtil.getBeforeWeekShortDate(new Date());
            endTime = DateUtil.getStringDate(new Date());
        }else {
            startTime = DateUtil.getShortDateStringByLong(getBlisterRateInfoTimeNotesReq.getBegin_on());
            endTime = DateUtil.getShortDateStringByLong(getBlisterRateInfoTimeNotesReq.getEnd_on());
        }
        List<Map<String,Object>> blisterTimeNotesList = measureListIssueService.searchMeasureListIssueTrend(getBlisterRateInfoTimeNotesReq.getProject_id(),getBlisterRateInfoTimeNotesReq.getMeasure_list_id(),startTime,endTime,MeasureListConstant.UNCLOSECODE);
        blisterTimeNotesList.forEach(LambdaExceptionUtil.throwingConsumerWrapper(blisterTimeNote -> {
            BlisterTimeNotesVo blisterTimeNotesVo = (BlisterTimeNotesVo)ConvertUtil.convertMap(BlisterTimeNotesVo.class,blisterTimeNote);
            blisterTimeNotesVos.add(blisterTimeNotesVo);
        }));
        itemsVo.setItems(blisterTimeNotesVos);
        ljBaseResponse.setData(itemsVo);
        return ljBaseResponse;
    }

    @Override
    public LjBaseResponse<BlisterCheckItemsVo> getBlisterRateCheckItems(GetBlisterRateCheckItemsReq getBlisterRateCheckItemsReq) {
        LjBaseResponse<BlisterCheckItemsVo> ljBaseResponse = new LjBaseResponse<>();
        BlisterCheckItemsVo blisterCheckItemsVo = new BlisterCheckItemsVo();
        List<BlisterCategoryDetailsVo> items = new ArrayList<>();
        if (StringUtils.isBlank(getBlisterRateCheckItemsReq.getCategory_key())){
            //如果没传父集..取root
            Integer count = measureListIssueService.countMeasureListIssueDistributionCategory(getBlisterRateCheckItemsReq.getProject_id(),getBlisterRateCheckItemsReq.getMeasure_list_id(),MeasureListConstant.UNCLOSECODE);
            blisterCheckItemsVo.setTotal(count);
        }
        //TODO 不会做了
        List<Map<String,Object>> blisterCategoryDetailss = searchMeasureListIssueDistributionCategory(getBlisterRateCheckItemsReq);
        blisterCheckItemsVo.setItems(items);
        ljBaseResponse.setData(blisterCheckItemsVo);
        return ljBaseResponse;
    }

    @Override
    public LjBaseResponse<ItemsVo<List<AreaPOPVo>>> getAreaPOP(GetAreaPOPReq getAreaPOPreq) throws Exception {
        LjBaseResponse<ItemsVo<List<AreaPOPVo>>> ljBaseResponse = new LjBaseResponse<>();
        ItemsVo<List<AreaPOPVo>> itemsVo = new ItemsVo<>();
        List<AreaPOPVo> areaPOPVos = new ArrayList<>();
        //鉴权 todo 获取prod.id 暂时从前端获取
        String [] listIds = getAreaPOPreq.getList_ids().split(",");
        String [] areaIds = getAreaPOPreq.getArea_ids().split(",");
        if (listIds.length == 0 || areaIds.length == 0 || getAreaPOPreq.getParent_category_key().length() == 0){
            throw new Exception("参数不完整");
        }
        List<Map<String,Object>> list = searchMeasureCategoryAreaStatByProjectIdAndListIdsAndParentCategoryKeyAndAreaIds(getAreaPOPreq.getProject_id(),listIds,getAreaPOPreq.getParent_category_key(),areaIds);
        list.forEach(LambdaExceptionUtil.throwingConsumerWrapper(map -> {
            AreaPOPVo areaPOPVo = (AreaPOPVo)ConvertUtil.convertMap(AreaPOPVo.class,map);
            List<MeasureStatisticAreaDistributeVo> area_dist = new ArrayList<>();
            ((ArrayList<Map<String,Object>>)map.get("areaDist")).forEach(LambdaExceptionUtil.throwingConsumerWrapper(areaDist -> {
                MeasureStatisticAreaDistributeVo measureStatisticAreaDistributeVo = (MeasureStatisticAreaDistributeVo)ConvertUtil.convertMap(MeasureStatisticAreaDistributeVo.class,areaDist);
                area_dist.add(measureStatisticAreaDistributeVo);
            }));
            areaPOPVo.setArea_dist(area_dist);
            areaPOPVos.add(areaPOPVo);
        }));
        itemsVo.setItems(areaPOPVos);
        ljBaseResponse.setData(itemsVo);
        return ljBaseResponse;
    }

    /**
     * 统计检查项和区域的统计数据
     * @param project_id
     * @param listIds
     * @param parent_category_key
     * @param areaIds
     * @return
     */
    private List<Map<String, Object>> searchMeasureCategoryAreaStatByProjectIdAndListIdsAndParentCategoryKeyAndAreaIds(Integer project_id, String[] listIds, String parent_category_key, String[] areaIds) {
        List<Map<String, Object>> list = new ArrayList<>();
        // 取出所有的category子项
        List<CategoryV3> categoryV3s = categoryV3Service.searchSubCategoryByFatherKey(parent_category_key);
        categoryV3s.forEach(categoryV3 -> {
            Map<String,Object> map = new HashMap<>();
            map.put("category_key",categoryV3.getKey());
            map.put("category_name",categoryV3.getName());
            map.put("is_leaf",false);
            Integer count = categoryV3Service.countCategoryByFatherKey(categoryV3.getKey());
            if (count == 0){
                map.put("is_leaf",true);
            }
            List<Map<String,Object>> areaDistList = new ArrayList<>();
            for (String areaId:areaIds
                 ) {
                // 考虑到有可能很多测区是没有数据的，先count一下后再执行，测区为0就返回空值
                Integer c = measureZoneResultService.countMeasureZoneByListIdsAndCategoryKeyAndAreaId(project_id,listIds,categoryV3.getKey(),areaId);
                if (c <= 0){
                    continue;
                }
                Map<String,Object> areaDist = getMeasureCategoryAreaStatByListIdsAndCategoryKeyAndAreaId(listIds,categoryV3.getKey(),areaId);
                if (areaDist == null){
                    continue;
                }
                areaDistList.add(areaDist);
            }
            map.put("areaDist",areaDistList);
            list.add(map);
        });
        return list;
    }

    /**
     * 通过任务Id列表和检查项和区域Id获取问题数、销项问题数、实测合格率
     * @param listIds
     * @param key
     * @param areaId
     * @return
     */
    private Map<String, Object> getMeasureCategoryAreaStatByListIdsAndCategoryKeyAndAreaId(String[] listIds, String key, String areaId) {
        Map<String, Object> areaDist = new HashMap<>();
        areaDist.put("area_id",Integer.parseInt(areaId));
        areaDist.put("checked_issue_count",0);
        areaDist.put("issue_count",0);
        Map<Integer,Integer> statusMap = getMeasureListIssueStatusMapByListIdsAndCategoryKeyAndAreaId(listIds,key,areaId);
        if (statusMap == null) {
            return null;
        }
        for (Map.Entry<Integer, Integer> entry : statusMap.entrySet()){
            if (entry.getKey() == Integer.parseInt(MeasureListIssueType.CHECKYES)){
                areaDist.put("checked_issue_count",entry.getValue());
            }
            Integer issueCount = Integer.parseInt(areaDist.get("issue_count").toString());
            areaDist.put("issue_count",issueCount + entry.getValue());
        }
        areaDist.put("percentage",getMeasureZoneResultPassPercentageByListIdsAndCategoryKeyAndAreaId(listIds,key,areaId));
        return areaDist;
    }

    /**
     * 通过任务Id列表和检查项和区域Id获取测区的测点数和合格率
     * @param listIds
     * @param key
     * @param areaId
     * @return
     */
    private Object getMeasureZoneResultPassPercentageByListIdsAndCategoryKeyAndAreaId(String[] listIds, String key, String areaId) {
        double percentage = 0;
        Map<String,Object> item = measureZoneResultService.getMeasureZoneResultPassPercentageByListIdsAndCategoryKeyAndAreaId(listIds,key,areaId);
        if (Integer.parseInt(item.get("total").toString()) > 0){
            percentage = Float.parseFloat(item.get("ok_total").toString()) / Float.parseFloat(item.get("total").toString()) * 100.0;
        }
        return String.format("%.2f", percentage);
    }

    /**
     * 通过任务Id列表和检查项和区域Id获取问题的各个状态分布
     * @param listIds
     * @param key
     * @param areaId
     * @return
     */
    private Map<Integer, Integer> getMeasureListIssueStatusMapByListIdsAndCategoryKeyAndAreaId(String[] listIds, String key, String areaId) {
        CategoryV3 categoryV3 = categoryV3Service.getCategoryByKey(key);
        if (categoryV3 == null){
            return null;
        }
        Area area = areaService.getAreaById(areaId);
        if (area == null){
            return null;
        }
        List<Map<String,Object>> items = measureListIssueService.getMeasureListIssueStatusMapByListIdsAndCategoryKeyAndAreaId(listIds,categoryV3,area,MeasureListConstant.CLOSEDCODE);
        if (items == null || items.size() <= 0){
            return null;
        }
        Map<Integer, Integer> smap = new HashMap<>();
        items.forEach(map -> {
            smap.put(Integer.parseInt(map.get("status").toString()),Integer.parseInt(map.get("count").toString()));
        });
        return smap;
    }

    /**
     *
     * @param getBlisterRateCheckItemsReq
     * @return
     */
    private List<Map<String, Object>> searchMeasureListIssueDistributionCategory(GetBlisterRateCheckItemsReq getBlisterRateCheckItemsReq) {
        List<MeasureListIssue> issues = measureListIssueService.searchMeasureListIssueDistributionCategory(getBlisterRateCheckItemsReq.getProject_id(),getBlisterRateCheckItemsReq.getMeasure_list_id(),MeasureListConstant.UNCLOSECODE);
        if (issues == null || issues.size() <= 0){
            return null;
        }
        //map去重，获取categorys
        Map<String,String> mCategory = new HashMap<>();
        issues.forEach(issue -> {
            mCategory.put(issue.getCategoryKey(),issue.getCategoryKey());
        });
        List<String> categoryKeys = new ArrayList<>();
        for (Map.Entry<String,String> entry : mCategory.entrySet()) {
            categoryKeys.add(entry.getValue());
        }
        if (StringUtils.isBlank(getBlisterRateCheckItemsReq.getCategory_key())){
            //获取顶级检查项
            List<CategoryV3> categoryV3s = SearchCategoryTopByCategoryKeyIn(categoryKeys);
        }
        return null;
    }

    /**
     *
     * @param categoryKeys
     * @return
     */
    private List<CategoryV3> SearchCategoryTopByCategoryKeyIn(List<String> categoryKeys) {
        List<CategoryV3> categoryV3s = categoryV3Service.getCategoryByKeys(categoryKeys);
        List<String> rootCategoryIds = new ArrayList<>();
        categoryV3s.forEach(categoryV3 -> {
            rootCategoryIds.add(categoryV3.getRootCategoryId().toString());
        });
        List<CategoryV3> rootCategorys = categoryV3Service.getCategoryByKeys(rootCategoryIds);
        Map<Integer,Map<String,Object>> trees = new HashMap<>();
        rootCategorys.forEach(rootCategory -> {
            trees.put(rootCategory.getId(),getPathTreeByRootCategory(rootCategory));
        });
        return null;
    }

    /**
     *
     * @param rootCategory
     * @return
     */
    private Map<String, Object> getPathTreeByRootCategory(CategoryV3 rootCategory) {
        List<CategoryV3> categoryV3s = categoryV3Service.searchByRootCategoryId(rootCategory.getId());
        categoryV3s.add(rootCategory);
        Map<String,String> categoryPathMap = new HashMap<>();
        categoryV3s.forEach(categoryV3 -> {
            categoryPathMap.put(categoryV3.getKey(),categoryV3.getPath()+categoryV3.getKey() + "/");
        });
        Map<String, Object> tree = categoryV3Service.getPathTreeByRootCategory(rootCategory);
        return null;
    }

    /**
     *
     * @param getCompareItemBetweenSquadsReq
     * @param measureList
     * @return
     */
    private List<CategoryDetailsVo> getCategoryDetails(GetCompareItemBetweenSquadsReq getCompareItemBetweenSquadsReq, MeasureList measureList) {
        List<CategoryDetailsVo> categoryDetailsVos = new ArrayList<>();
        if (StringUtils.isBlank(getCompareItemBetweenSquadsReq.getCategory_key())){
            //没传CategoryKey，取最顶级
            getCompareItemBetweenSquadsReq.setCategory_key(measureList.getRootCategoryKey());
        }
        List<MeasureZoneResult> measureZoneResults = measureZoneResultService.getSubActiveMeasureCategoryZonesByListIdCategoryKey(getCompareItemBetweenSquadsReq.getProject_id(),getCompareItemBetweenSquadsReq.getMeasure_list_id(),getCompareItemBetweenSquadsReq.getCategory_key());
        measureZoneResults.forEach(measureZoneResult -> {
            CategoryDetailsVo categoryDetailsVo = new CategoryDetailsVo();
            //获取子节点
            String[] subKeys = getSubKeyByKeyPathAndKey(measureZoneResult.getCategoryPathAndKey(),getCompareItemBetweenSquadsReq.getCategory_key());
            String subKey = subKeys[0];
            //是否是叶子节点
            boolean isLeaf = subKey.length() == 1;
            CategoryV3 categoryV3 = categoryV3Service.getCategoryByKey(subKey);
            if (categoryV3 == null){
                return;
            }
            //zoneCount
            int zoneCount = measureZoneService.getMeasureZoneCountByListIdCategoryKey(getCompareItemBetweenSquadsReq.getProject_id(),getCompareItemBetweenSquadsReq.getMeasure_list_id(),subKey);
            if (zoneCount <= 0){
                return;
            }
            //squads
            List<Map<String,Object>> squadList = measureZoneResultService.statMearureZoneResultSquadTotalCountByListIdCategoryKey(getCompareItemBetweenSquadsReq.getMeasure_list_id(),categoryV3.getKey());
            categoryDetailsVo.setCategory_key(categoryV3.getKey());
            categoryDetailsVo.setCategory_name(categoryV3.getName());
            categoryDetailsVo.setIs_leaf(isLeaf);
            categoryDetailsVo.setZone_count(zoneCount);
            List<SquadsVo> squads = new ArrayList<>();
            squadList.forEach(squad -> {
                SquadsVo squadsVo = new SquadsVo();
                squadsVo.setSquad_id(Integer.parseInt(squad.get("squadId").toString()));
                squadsVo.setPass_percent(Float.parseFloat(squad.get("pass_percent").toString()) * 100 + "");
                if (isLeaf){
                    squadsVo.setChecked_percent(Float.parseFloat(squad.get("count").toString()) / zoneCount * 100.0 + "");
                }
                squads.add(squadsVo);
            });
            categoryDetailsVo.setSquads(squads);
            categoryDetailsVos.add(categoryDetailsVo);
        });
        return categoryDetailsVos;
    }

    /**
     * 获取子节点
     * @param categoryPathAndKey
     * @param category_key
     * @return
     */
    private String [] getSubKeyByKeyPathAndKey(String categoryPathAndKey, String category_key) {
        String key = "/" + category_key + "/";
        if (categoryPathAndKey.charAt(0) == '/'){
            categoryPathAndKey  = categoryPathAndKey.substring(1);
        }
        if (categoryPathAndKey.charAt(categoryPathAndKey.length() - 1) == '/'){
            categoryPathAndKey = categoryPathAndKey.substring(0,categoryPathAndKey.length() - 1);
        }
        categoryPathAndKey = "/" + categoryPathAndKey + "/";
        int index = categoryPathAndKey.indexOf(key);
        if (index < 0){
            return new String[]{""};
        }
        index += key.length();
        String [] subKeys = categoryPathAndKey.substring(index).split("/");
        return subKeys;
    }

    /**
     * 设置合格率最小组
     * @param measure_list_id
     * @return
     */
    private List<MeasureStatisticSquadsSmallestPassPercentInfoVo> searchCategorySquadSmallestByMeasureListId(Integer measure_list_id) {
        List<MeasureStatisticSquadsSmallestPassPercentInfoVo> measureStatisticSquadsSmallestPassPercentInfoVos = new ArrayList<>();
        List<Map<String,Object>> categoryKeys = measureZoneResultService.getSquadPassPercentSmallestCategoryKeyListByMeasureListId(measure_list_id);
        categoryKeys.forEach(categoryKey -> {
            MeasureStatisticSquadsSmallestPassPercentInfoVo measureStatisticSquadsSmallestPassPercentInfoVo = new MeasureStatisticSquadsSmallestPassPercentInfoVo();
            CategoryV3 categoryV3 = categoryV3Service.getCategoryByKey(categoryKey.get("category_key").toString());
            List<Map<String,Object>> sInfos = measureZoneResultService.getSquadsZoneResultPassPercentByListIdAndCategoryKey(measure_list_id,categoryKey.get("category_key").toString(),MeasureListConstant.CLOSEDCODE);
            if (sInfos != null && sInfos.size() > 0) {
                measureStatisticSquadsSmallestPassPercentInfoVo.setCategory_key(categoryV3.getKey());
                measureStatisticSquadsSmallestPassPercentInfoVo.setCategory_name(categoryV3.getName());
                List<String> squads = new ArrayList<>();
                sInfos.forEach(squadsInfo -> {
                    JSONObject squadsInfoJson = (JSONObject) squadsInfo;
                    squads.add(Float.parseFloat(squadsInfoJson.get("pass_percent").toString()) * 100.0 + "");
                });
                measureStatisticSquadsSmallestPassPercentInfoVo.setSquads(squads);
                measureStatisticSquadsSmallestPassPercentInfoVos.add(measureStatisticSquadsSmallestPassPercentInfoVo);
            }
        });
        return measureStatisticSquadsSmallestPassPercentInfoVos;
    }

    /**
     * 设置合格率差值最大组
     * @param measure_list_id
     * @return
     */
    private List<MeasureStatisticSquadsPassDiffLargestInfoVo> searchCategoryDiffLargestByMeasureListId(Integer measure_list_id) {
        List<MeasureStatisticSquadsPassDiffLargestInfoVo> measureStatisticSquadsPassDiffLargestInfoVos = new ArrayList<>();
        List<Map<String,Object>> categoryKeys = measureZoneResultService.getPassPercentDiffCategoryKeyListByMeasureListId(measure_list_id);
        categoryKeys.forEach(categoryKey -> {
            MeasureStatisticSquadsPassDiffLargestInfoVo measureStatisticSquadsPassDiffLargestInfoVo = new MeasureStatisticSquadsPassDiffLargestInfoVo();
            CategoryV3 categoryV3 = categoryV3Service.getCategoryByKey(categoryKey.get("category_key").toString());
            List<Map<String,Object>> sInfos = measureZoneResultService.getSquadsZoneResultPassPercentByListIdAndCategoryKey(measure_list_id,categoryKey.get("category_key").toString(),MeasureListConstant.CLOSEDCODE);
            if (sInfos != null && sInfos.size() > 0) {
                measureStatisticSquadsPassDiffLargestInfoVo.setCategory_key(categoryV3.getKey());
                measureStatisticSquadsPassDiffLargestInfoVo.setCategory_name(categoryV3.getName());
                List<String> squads = new ArrayList<>();
                sInfos.forEach(squadsInfo -> {
                    JSONObject squadsInfoJson = (JSONObject) squadsInfo;
                    squads.add(Float.parseFloat(squadsInfoJson.get("pass_percent").toString()) * 100.0 + "");
                });
                measureStatisticSquadsPassDiffLargestInfoVo.setSquads(squads);
                measureStatisticSquadsPassDiffLargestInfoVos.add(measureStatisticSquadsPassDiffLargestInfoVo);
            }
        });
        return measureStatisticSquadsPassDiffLargestInfoVos;
    }

    /**
     * 填写合格率数据
     * @param measureSquadlist
     * @param total
     * @param measureZoneResults
     * @return
     */
    private List<SquadsPassVo> getSquadsPassVos(List<MeasureSquad> measureSquadlist, Integer total, List<Map<String, Object>> measureZoneResults,List<Map<String,Object>> results) {
        List<SquadsPassVo> squadsPassVos = new ArrayList<>();
        measureSquadlist.forEach(measureSquad -> {
            SquadsPassVo squadsPassVo = new SquadsPassVo();
            squadsPassVo.setId(measureSquad.getId());
            squadsPassVo.setName(measureSquad.getName());
            squadsPassVo.setAverage_percent("0");
            squadsPassVo.setLt60_count(0);
            squadsPassVo.setGte60_count(0);
            squadsPassVo.setGte80_count(0);
            measureZoneResults.forEach(measureZoneResult -> {
                if (total > 0 && measureSquad.getId().toString().equals(measureZoneResult.get("squadId").toString())){
                    squadsPassVo.setAverage_percent(Float.parseFloat(measureZoneResult.get("ok_total_sum").toString()) / Float.parseFloat(measureZoneResult.get("total_sum").toString()) * 100.0 + "");
                }
            });
            results.forEach(result -> {
                if (result.get("total_sum") != null && Integer.parseInt(result.get("total_sum").toString()) > 0 && measureSquad.getId().toString().equals(result.get("squadId").toString())){
                    float rate = Float.parseFloat(result.get("total_sum").toString()) / Float.parseFloat(result.get("ok_total_sum").toString());
                    if (rate < 0.6){
                        squadsPassVo.setLt60_count(squadsPassVo.getLt60_count() + 1 );
                    }
                    if (rate >= 0.6){
                        squadsPassVo.setGte60_count(squadsPassVo.getGte60_count() + 1 );
                    }
                    if (rate >= 0.8){
                        squadsPassVo.setGte80_count(squadsPassVo.getGte80_count() + 1 );
                    }
                }
            });
            squadsPassVos.add(squadsPassVo);
        });
        return squadsPassVos;
    }

    /**
     * 填写完成进度数据
     * @param measureSquadlist
     * @param total
     * @param squadCounts
     * @return
     */
    private List<SquadsVo> getSquads(List<MeasureSquad> measureSquadlist, Integer total, List<Map<String,Object>> squadCounts) {
        List<SquadsVo> squadsVos = new ArrayList<>();
        measureSquadlist.forEach(measureSquad -> {
            SquadsVo squadsVo = new SquadsVo();
            squadsVo.setId(measureSquad.getId());
            squadsVo.setName(measureSquad.getName());
            squadsVo.setRequire_percent(measureSquad.getPlanRate().toString());
            squadsVo.setComplete_percent("0");
            squadCounts.forEach(squadCount -> {
                if (total > 0 && measureSquad.getId().toString().equals(squadCount.get("squadId").toString())){
                    squadsVo.setComplete_percent(Float.parseFloat(squadCount.get("count").toString()) / Float.parseFloat(total.toString()) * 100.0*100.0 / Float.parseFloat(measureSquad.getPlanRate().toString()) + "");
                }
            });
            squadsVos.add(squadsVo);
        });
        return squadsVos;
    }

    /**
     *areaPathAndId 参数赋值
     * @param getProMeasurePlanListReq
     * @return
     */
    private String getAreaPathAndId(GetProMeasurePlanListReq getProMeasurePlanListReq) {
        //areaPathAndId 参数赋值
        String areaPathAndId = "";
        if (getProMeasurePlanListReq.getArea_id() != null){
            //查找子集
            Area area = areaService.getAreaByProjIdAndAreaId(getProMeasurePlanListReq.getProject_id(),getProMeasurePlanListReq.getArea_id());
            if (area != null){
                areaPathAndId = area.getPath() + area.getId() + "/";
            }
        }
        return areaPathAndId;
    }

    /**
     * categoryPathAndKey 参数赋值
     * @param getProMeasurePlanListReq
     * @return
     */
    private String getCategoryPathAndKey(GetProMeasurePlanListReq getProMeasurePlanListReq) {
        //categoryPathAndKey 参数赋值
        String categoryPathAndKey = "";
        if (StringUtils.isNoneBlank(getProMeasurePlanListReq.getCategory_key())){
            //查找子集
            CategoryV3 categoryV3 = categoryV3Service.getCategoryByKey(getProMeasurePlanListReq.getCategory_key());
            if (categoryV3 != null){
                categoryPathAndKey = categoryV3.getPath() + categoryV3.getKey() + "/";
            }
        }
        return categoryPathAndKey;
    }

    /**
     * 获取任务列表
     * @param getProMeasurePlanListReq
     * @param userIds
     * @param categoryPathAndKey
     * @param areaPathAndId
     * @return
     */
    private List<ProMeasurePlanVo> SearchByProjIdCategoryKeyAreaIdStatusUserIdInPage(GetProMeasurePlanListReq getProMeasurePlanListReq, String[] userIds, String categoryPathAndKey, String areaPathAndId) throws InvocationTargetException, IntrospectionException, InstantiationException, IllegalAccessException, ParseException {
        List<ProMeasurePlanVo> measurePlanVoList = new ArrayList<>();
        //查询 MeasureList
        List<Map<String,Object>> list = measureListService.getMeasureList(getProMeasurePlanListReq.getFinish_status(),getProMeasurePlanListReq.getQ(),getProMeasurePlanListReq.getProject_id(),categoryPathAndKey,areaPathAndId,userIds,getProMeasurePlanListReq.getPage(),getProMeasurePlanListReq.getPage_size());
        for (Map<String,Object> map: list
                ) {
            //map转换成vo
            ProMeasurePlanVo measurePlanVo = (ProMeasurePlanVo) ConvertUtil.convertMap(ProMeasurePlanVo.class,map);
            if ("1".equals(map.get("closeStatusId").toString())){
                measurePlanVo.setClose_status(MeasureListConstant.UNCLOSE);
            }else {
                measurePlanVo.setClose_status(MeasureListConstant.CLOSED);
            }
            if ("1".equals(map.get("finishStatusId").toString())){
                measurePlanVo.setFinish_status(MeasureListConstant.UNFINISH);
            }else {
                measurePlanVo.setFinish_status(MeasureListConstant.FINISHED);
            }
            measurePlanVo.setIssue_count(measureListIssueService.countByMeasureListId(map.get("id").toString()));
            measurePlanVo.setCreate_at(DateUtil.getLongFromString(map.get("createAt").toString()));
            // todo area 数据处理
            measurePlanVoList.add(measurePlanVo);
        }
        return measurePlanVoList;
    }
}
