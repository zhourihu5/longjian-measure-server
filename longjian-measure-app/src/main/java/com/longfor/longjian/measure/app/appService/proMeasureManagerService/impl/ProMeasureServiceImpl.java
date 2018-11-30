package com.longfor.longjian.measure.app.appService.proMeasureManagerService.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import com.longfor.longjian.common.base.LjBaseResponse;
import com.longfor.longjian.measure.app.appService.proMeasureManagerService.IProMeasureService;
import com.longfor.longjian.measure.app.req.proMeasureManagerReq.GetCheckerListReq;
import com.longfor.longjian.measure.app.req.proMeasureManagerReq.GetProMeasureAreaListReq;
import com.longfor.longjian.measure.app.req.proMeasureManagerReq.GetProMeasureCheckItemsReq;
import com.longfor.longjian.measure.app.req.proMeasureManagerReq.GetProMeasurePlanListReq;
import com.longfor.longjian.measure.app.req.proMeasureQuickSearchReq.GetCompareBetweenGroupReq;
import com.longfor.longjian.measure.app.req.proMeasureQuickSearchReq.GetLoserCompareBetweenGroupReq;
import com.longfor.longjian.measure.app.vo.ItemsVo;
import com.longfor.longjian.measure.app.vo.proMeasureVo.*;
import com.longfor.longjian.measure.consts.constant.CategoryClsTypeConstant;
import com.longfor.longjian.measure.consts.util.ConvertUtil;
import com.longfor.longjian.measure.consts.util.DateUtil;
import com.longfor.longjian.measure.consts.util.LambdaExceptionUtil;
import com.longfor.longjian.measure.domain.externalService.*;
import com.longfor.longjian.measure.po.zhijian2.Area;
import com.longfor.longjian.measure.po.zhijian2.CategoryV3;
import com.longfor.longjian.measure.po.zhijian2.MeasureSquad;
import com.longfor.longjian.measure.po.zhijian2.MeasureZoneResult;
import com.longfor.longjian.measure.po.zhijian2_apisvr.Team;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.omg.CORBA.ObjectHelper;
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
    public LjBaseResponse<SquadsAndPassVo> getCompareBetweenGroup(GetCompareBetweenGroupReq getCompareBetweenGroupReq) {
        LjBaseResponse<SquadsAndPassVo> ljBaseResponse = new LjBaseResponse<>();
        SquadsAndPassVo squadsAndPassVo = new SquadsAndPassVo();
        //验证任务是否属于这个项目
        boolean existPlan = measureListService.searchByProjectIdAndMeasureListId(getCompareBetweenGroupReq.getProject_id(),getCompareBetweenGroupReq.getMeasure_list_id()) != null;
        if (!existPlan){
            //任务不存在,抛出异常
            new Throwable("任务不存在");
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
    public LjBaseResponse<PassDiffVo> getLoserCompareBetweenGroup(GetLoserCompareBetweenGroupReq getLoserCompareBetweenGroupReq) {
        LjBaseResponse<PassDiffVo> ljBaseResponse = new LjBaseResponse<>();
        PassDiffVo passDiffVo = new PassDiffVo();
        //验证任务是否属于这个项目
        boolean existPlan = measureListService.searchByProjectIdAndMeasureListId(getLoserCompareBetweenGroupReq.getProject_id(),getLoserCompareBetweenGroupReq.getMeasure_list_id()) != null;
        if (!existPlan){
            //任务不存在,抛出异常
            new Throwable("任务不存在");
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
